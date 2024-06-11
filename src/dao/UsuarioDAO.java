package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import entities.Usuario;

public class UsuarioDAO {

    private Connection conn;

    public UsuarioDAO(Connection conn) {
        this.conn = conn;
    }

    public void cadastrarCandidato(Usuario usuario) throws SQLException {
    	if (verificarUsuario(usuario.getEmail())) {
            throw new SQLException("E-mail já cadastrado");
        }
    	
        PreparedStatement st = null;

        try {
            st = this.conn.prepareStatement("INSERT INTO candidato (nome, email, senha) VALUES(?,?,?)");
            st.setString(1, usuario.getNome());
            st.setString(2, usuario.getEmail());
            st.setString(3, usuario.getSenha());
            st.executeUpdate();    
        } finally {
            BancoDados.finalizarStatement(st);
        }
    }

    public int excluirCandidato(String email) throws SQLException {
        PreparedStatement selectStatement = null;
        PreparedStatement deleteLoginStatement = null;
        PreparedStatement deleteCandidatoStatement = null;
        ResultSet rs = null;

        try {
            selectStatement = conn.prepareStatement("SELECT idCandidato FROM candidato WHERE email = ?");
            selectStatement.setString(1, email);
            rs = selectStatement.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("idCandidato");
                System.out.println("ID do candidato a ser excluído: " + id);

                deleteLoginStatement = conn.prepareStatement("DELETE FROM logincandidato WHERE idCandidato = ?");
                deleteLoginStatement.setInt(1, id);
                deleteLoginStatement.executeUpdate();

                deleteCandidatoStatement = conn.prepareStatement("DELETE FROM candidato WHERE idCandidato = ?");
                deleteCandidatoStatement.setInt(1, id);
                int returnDelete = deleteCandidatoStatement.executeUpdate();
                return returnDelete;
            } else {
                System.out.println("Candidato com email " + email + " não encontrado.");
                return 0;
            }
        } finally {
            BancoDados.finalizarStatement(selectStatement);
            BancoDados.finalizarStatement(deleteLoginStatement);
            BancoDados.finalizarStatement(deleteCandidatoStatement);
            BancoDados.finalizarResultSet(rs);
        }
    }

    public boolean verificarUsuario(String email) throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;
        boolean success = false;

        try {
            String sql = "SELECT * FROM candidato WHERE email =?";
            st = conn.prepareStatement(sql);
            st.setString(1, email);
            rs = st.executeQuery();

            if (rs.next()) {
                success = true;
            }
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.finalizarResultSet(rs);
        }
        return success;
    }
    
    public String verificarToken(JSONObject request) throws SQLException {
        String token = request.getString("token");
        String email = request.getString("email");
        String operacao = request.getString("operacao");

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT 1 FROM logincandidato lc LEFT JOIN candidato c on c.idCandidato = lc.idCandidato WHERE lc.token =? AND c.email =?");
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
        } 
        catch(Exception ex){
        	System.out.println(ex.getMessage());
        	throw ex;
        }
        finally {
            BancoDados.finalizarStatement(st);
            BancoDados.finalizarResultSet(rs);
        }
    }

    public boolean editarCandidato(String email, String novoNome, String senha) throws SQLException {
        boolean candidatoExiste = verificarUsuario(email);

        if (candidatoExiste) {
            PreparedStatement st = null;
            try {
                String sql = "UPDATE candidato SET nome =?, senha =? WHERE email =?";
                st = conn.prepareStatement(sql);
                st.setString(1, novoNome);
                st.setString(2, senha);
                st.setString(3, email);
                int rowsAffected = st.executeUpdate();

                return rowsAffected > 0;
            } finally {
                BancoDados.finalizarStatement(st);
            }
        } else {
            System.out.println(
                "{\"operacao\": \"editarCandidato\", \"status\": 404, \"mensagem\": \"Candidato não encontrado\"}");
            return false;
        }
    }

    public List<Usuario> buscarTodos() throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM candidato");
            rs = st.executeQuery();

            List<Usuario> listaUsuarios = new ArrayList<>();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("idCandidato"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                listaUsuarios.add(usuario);
            }
            return listaUsuarios;
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.finalizarResultSet(rs);
        }
    }

    public Usuario buscarPorEmail(String email) throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM candidato WHERE email = ?");
            st.setString(1, email);
            rs = st.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("idCandidato"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setEmail(rs.getString("email"));
                return usuario;
            }
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.finalizarResultSet(rs);
        }
        return null;
    }

    public void close() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }
    
    
}
