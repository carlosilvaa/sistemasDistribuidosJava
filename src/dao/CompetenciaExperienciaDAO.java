package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import entities.CompetenciaExperiencia;
import entities.Usuario;

public class CompetenciaExperienciaDAO {

    private Connection conn;

    public CompetenciaExperienciaDAO(Connection conn) {
        this.conn = conn;
    }

    public List<CompetenciaExperiencia> encontrarCompetenciaExperiencaiPorCandidato(Usuario usuario) throws SQLException {
        List<CompetenciaExperiencia> lista = new ArrayList<>();

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM competencia_experiencia WHERE candidato_id = ?");
            st.setInt(1, usuario.getId());
            rs = st.executeQuery();

            while (rs.next()) {
                CompetenciaExperiencia ce = new CompetenciaExperiencia();
                ce.setCompetencia(rs.getString("competencia"));
                ce.setExperiencia(rs.getInt("experiencia"));
                lista.add(ce);
            }
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.finalizarResultSet(rs);
        }

        return lista;
    }
    
    public CompetenciaExperiencia findCompetenciaExperienciaByCompetencia(Usuario usuario, String competencia) throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM competencia_experiencia WHERE candidato_id = ? AND competencia = ?");
            st.setInt(1, usuario.getId());
            st.setString(2, competencia);
            rs = st.executeQuery();

            if (rs.next()) {
                CompetenciaExperiencia ce = new CompetenciaExperiencia();
                ce.setCompetencia(rs.getString("competencia"));
                ce.setExperiencia(rs.getInt("experiencia"));
                return ce;
            } else {
                return null;
            }
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.finalizarResultSet(rs);
        }
    }

    public String criarCompetenciaExperiencia(JSONArray competenciasArray, Usuario usuario) throws SQLException {
        PreparedStatement st = null;

        try {
            List<CompetenciaExperiencia> competenciasExistente = encontrarCompetenciaExperiencaiPorCandidato(usuario);
            for (int i = 0; i < competenciasArray.length(); i++) {
                JSONObject competenciaJSON = competenciasArray.getJSONObject(i);
                String competencia = competenciaJSON.getString("competencia");
                int experiencia = competenciaJSON.getInt("experiencia");

                boolean competenciaJaExiste = false;
                for (CompetenciaExperiencia ce : competenciasExistente) {
                    if (ce.getCompetencia().equals(competencia) && ce.getExperiencia() == experiencia) {
                        competenciaJaExiste = true;
                        break;
                    }
                }

                if (competenciaJaExiste) {
                    return "Competência já cadastrada para o usuário";
                }

                st = conn.prepareStatement("INSERT INTO competencia_experiencia (candidato_id, competencia, experiencia) VALUES (?,?,?)");
                st.setInt(1, usuario.getId());
                st.setString(2, competencia);
                st.setInt(3, experiencia);
                st.executeUpdate();
            }

            return "sucesso";
        } catch (Exception ex) {
            return ex.getMessage();
        } finally {
            BancoDados.finalizarStatement(st);
        }
    }
    
    public JSONObject atualizarCompetenciaExperiencia(JSONArray competenciasArray, Usuario usuario) throws SQLException {
        PreparedStatement st = null;
        JSONObject resposta = new JSONObject();
        try {
            st = conn.prepareStatement("DELETE FROM competencia_experiencia WHERE candidato_id = ?");
            st.setInt(1, usuario.getId());
            st.executeUpdate();
            
            for (int i = 0; i < competenciasArray.length(); i++) {
                JSONObject competenciaJSON = competenciasArray.getJSONObject(i);
                st = conn.prepareStatement("INSERT INTO competencia_experiencia (candidato_id, competencia, experiencia) VALUES (?, ?, ?)");
                st.setInt(1, usuario.getId());
                st.setString(2, competenciaJSON.getString("competencia"));
                st.setInt(3, competenciaJSON.getInt("experiencia"));
                st.executeUpdate();
            }
            resposta.put("operacao", "atualizarCompetenciaExperiencia");
            resposta.put("status", 201);
            resposta.put("mensagem", "Competencia/Experiencia atualizada com sucesso");
        } catch (Exception e) {
            resposta.put("operacao", "atualizarCompetenciaExperiencia");
            resposta.put("status", 422);
            resposta.put("mensagem", e.getMessage());
        } finally {
            BancoDados.finalizarStatement(st);
        }
        return resposta;
    }

    public JSONObject apagarCompetenciaExperiencia(JSONArray competenciasArray, Usuario usuario) throws SQLException {
        PreparedStatement st = null;
        JSONObject resposta = new JSONObject();
        try {
            for (int i = 0; i < competenciasArray.length(); i++) {
                JSONObject competenciaJSON = competenciasArray.getJSONObject(i);
                st = conn.prepareStatement("DELETE FROM competencia_experiencia WHERE candidato_id = ? AND competencia = ? AND experiencia = ?");
                st.setInt(1, usuario.getId());
                st.setString(2, competenciaJSON.getString("competencia"));
                st.setInt(3, competenciaJSON.getInt("experiencia"));
                st.executeUpdate();
            }
            resposta.put("operacao", "apagarCompetenciaExperiencia");
            resposta.put("status", 201);
            resposta.put("mensagem", "Competencia/Experiencia apagada com sucesso");
        } catch (Exception e) {
            resposta.put("operacao", "apagarCompetenciaExperiencia");
            resposta.put("status", 422);
            resposta.put("mensagem", e.getMessage());
        } finally {
            BancoDados.finalizarStatement(st);
        }
        return resposta;
    }
    
    public String verificarToken(JSONObject request) throws SQLException {
        String token = request.getString("token");
        String email = request.getString("email");
        String operacao = request.getString("operacao");

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT 1 FROM logincandidato WHERE token =? AND email =?");
            st.setString(1, token);
            st.setString(2, email);
            rs = st.executeQuery();

            if (rs.next()) {
                return "sucesso";
            } else {
                JSONObject responseJson = new JSONObject();
                responseJson.put("operacao", operacao);
                responseJson.put("status", 404);
                responseJson.put("mensagem", "Token inválido");
                return responseJson.toString();
            }
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.finalizarResultSet(rs);
        }
    }
}