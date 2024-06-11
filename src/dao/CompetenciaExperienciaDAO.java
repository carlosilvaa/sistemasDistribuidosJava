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

	public List<CompetenciaExperiencia> encontrarCompetenciaExperiencaiPorCandidato(Usuario usuario)
			throws SQLException {
		List<CompetenciaExperiencia> lista = new ArrayList<>();

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(
					"SELECT c.competencia, ce.experiencia FROM competenciaexperiencia ce LEFT JOIN competencia c on c.idCompetencia = ce.idCompetencia WHERE idCandidato = ?");
			st.setInt(1, usuario.getId());
			rs = st.executeQuery();

			while (rs.next()) {
				CompetenciaExperiencia ce = new CompetenciaExperiencia();
				ce.setCompetencia(rs.getString("competencia"));
				ce.setExperiencia(rs.getInt("experiencia"));
				lista.add(ce);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw ex;
		} finally {
			BancoDados.finalizarStatement(st);
			BancoDados.finalizarResultSet(rs);
		}

		return lista;
	}

	public CompetenciaExperiencia encontrarCompetenciaExperienciaPorCompetencia(Usuario usuario, String competencia)
			throws SQLException {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(
					"SELECT * FROM competenciaexperiencia WHERE idCandidato = ? AND competencia = ?");
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
	
	public int getIdCompetencia(String competencia) throws SQLException{
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT idCompetencia FROM competencia WHERE competencia = ?");
			st.setString(1, competencia);
			rs = st.executeQuery();
			
			if (rs.next()) {
				return rs.getInt("idCompetencia");
			} else {
				return -1;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return -1;
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
				
				int idCompetencia = this.getIdCompetencia(competencia);
				if(idCompetencia == -1) {
					return "Competência não encontrada";
				}

				st = conn.prepareStatement(
						"INSERT INTO competenciaexperiencia (idCandidato, idCompetencia, experiencia) VALUES (?,?,?)");
				st.setInt(1, usuario.getId());
				st.setInt(2, idCompetencia);
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

	public String atualizarCompetenciaExperiencia(JSONArray competenciasArray, int idCandidato) {
		
        String sql = "UPDATE CompetenciaExperiencia SET experiencia = ? WHERE idCandidato = ? AND idCompetencia = ?";

        try {
            conn.setAutoCommit(false);

            for (int i = 0; i < competenciasArray.length(); i++) {
                JSONObject competenciaJSON = competenciasArray.getJSONObject(i);
                
                int idCompetencia = this.getIdCompetencia(competenciaJSON.getString("competencia"));
				if(idCompetencia == -1) {
					return "Competência não encontrada";
				}

                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, competenciaJSON.getInt("experiencia"));
                    stmt.setInt(2, idCandidato);
                    stmt.setInt(3, idCompetencia);

                    int affectedRows = stmt.executeUpdate();

                    if (affectedRows == 0) {
                    	conn.rollback(); 
                        return "Competencia '" + competenciaJSON.getString("competencia") + "' não encontrada";
                    }
                }
            }
            conn.commit();
            return "OK";
        } catch (SQLException ex) {
            try {
            	conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return "Erro ao atualizar as competências: " + ex.getMessage();
        } finally {
            try {
            	conn.setAutoCommit(true); 
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

	public JSONObject apagarCompetenciaExperiencia(JSONArray competenciasArray, Usuario usuario) throws SQLException {
		PreparedStatement st = null;
		JSONObject resposta = new JSONObject();
		try {
			for (int i = 0; i < competenciasArray.length(); i++) {
				
				JSONObject competenciaJSON = competenciasArray.getJSONObject(i);
				String competencia = competenciaJSON.getString("competencia");
				int idCompetencia = this.getIdCompetencia(competencia);
				if(idCompetencia == -1) {
					resposta.put("operacao", "apagarCompetenciaExperiencia");
					resposta.put("status", 422);
					resposta.put("mensagem", "Competência " + competencia +" não encontrada");
					return resposta;
				}
				st = conn.prepareStatement(
						"DELETE FROM competenciaexperiencia WHERE idCandidato = ? AND idCompetencia = ? AND experiencia = ?");
				st.setInt(1, usuario.getId());
				st.setInt(2, idCompetencia);
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