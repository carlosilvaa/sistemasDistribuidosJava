package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import org.json.JSONObject;

import entities.Login;
import entities.Usuario;

public class LoginDAO {
    private Connection conn;

    public LoginDAO(Connection conn) {
        this.conn = conn;
    }

    public JSONObject loginCandidato(Login loginCandidato) throws SQLException, IOException {
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection conn = null;
        
        try {
            conn = BancoDados.conectar();
            
            st = conn.prepareStatement("SELECT idCandidato FROM candidato WHERE email = ? AND senha = ?");
            st.setString(1, loginCandidato.getEmail());
            st.setString(2, loginCandidato.getSenha());
            rs = st.executeQuery();
            
            if (rs.next()) {
                int idCandidato = rs.getInt("idCandidato");
                String token = UUID.randomUUID().toString();

                st = conn.prepareStatement("INSERT INTO logincandidato (idCandidato, token) VALUES (?, ?)");
                st.setInt(1, idCandidato);
                st.setString(2, token);
                st.executeUpdate();

                JSONObject login = new JSONObject();
                login.put("operacao", "loginCandidato");
                login.put("status", 200);
                login.put("token", token);
                return login;
            } else {
                JSONObject login = new JSONObject();
                login.put("operacao", "loginCandidato");
                login.put("status", 401);
                login.put("mensagem", "Login ou senha incorretos");
                return login;
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.finalizarResultSet(rs);
        }
    }


    public JSONObject logoutCandidato(String token) throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM logincandidato WHERE token = ?");
            st.setString(1, token);
            rs = st.executeQuery();
            JSONObject login = new JSONObject();

            if (rs.next()) {
                st = conn.prepareStatement("DELETE FROM logincandidato WHERE token = ?");
                st.setString(1, token);
                st.executeUpdate();

                login.put("operacao", "logout");
                login.put("status", 204);
                login.put("mensagem", "Logout realizado com sucesso");
                return login;
            } else {
                login.put("operacao", "logout");
                login.put("status", 401);
                login.put("mensagem", "Candidato não encontrado");
                return login;
            }
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.finalizarResultSet(rs);
        }
    }

    public JSONObject loginEmpresa(String email, String senha) throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM empresa WHERE email = ? AND senha = ?");
            st.setString(1, email);
            st.setString(2, senha);
            rs = st.executeQuery();
            JSONObject login = new JSONObject();

            if (rs.next()) {
                int idEmpresa = rs.getInt("idEmpresa");
                String token = UUID.randomUUID().toString();

                st = conn.prepareStatement("INSERT INTO loginempresa (idEmpresa, token) VALUES (?, ?)");
                st.setInt(1, idEmpresa);
                st.setString(2, token);
                st.executeUpdate();

                login.put("operacao", "loginEmpresa");
                login.put("status", 200);
                login.put("token", token);
                return login;
            } else {
                login.put("operacao", "loginEmpresa");
                login.put("status", 401);
                login.put("mensagem", "Login ou senha incorretos");
                return login;
            }
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.finalizarResultSet(rs);
        }
    }

    public JSONObject logoutEmpresa(String token) throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM loginempresa WHERE token = ?");
            st.setString(1, token);
            rs = st.executeQuery();
            JSONObject login = new JSONObject();

            if (rs.next()) {
                st = conn.prepareStatement("DELETE FROM loginempresa WHERE token = ?");
                st.setString(1, token);
                st.executeUpdate();

                login.put("operacao", "logout");
                login.put("status", 204);
                login.put("mensagem", "Logout realizado com sucesso");
                return login;
            } else {
                login.put("operacao", "logout");
                login.put("status", 401);
                login.put("mensagem", "Empresa não encontrada");
                return login;
            }
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.finalizarResultSet(rs);
        }
    }

    public boolean verificarToken(String token) throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM loginempresa WHERE token = ?");
            st.setString(1, token);
            rs = st.executeQuery();
            return rs.next(); // Retorna true se encontrar o token, caso contrário false
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.finalizarResultSet(rs);
        }
    }
    
    public Login buscarLoginPorCandidato(Usuario usuario) throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM logincandidato WHERE idCandidato = ?");
            st.setInt(1, usuario.getId()); // Supondo que o objeto Candidato possui um método getId()
            rs = st.executeQuery();

            if (rs.next()) {
            	Login loginCandidato = new Login();
                loginCandidato.setEmail(null);
                loginCandidato.setToken(rs.getString("token"));
                return loginCandidato;
            } else {
                return null;
            }
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.finalizarResultSet(rs);
        }
    }


    public Login buscarCandidatoPorToken(String token) throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM logincandidato WHERE token = ?");
            st.setString(1, token);
            rs = st.executeQuery();

            if (rs.next()) {
            	Login loginCandidato = new Login();
                loginCandidato.setIdLoginCandidato(rs.getInt("idCandidato"));
                loginCandidato.setToken(token);
                return loginCandidato;
            } else {
                return null;
            }
        } finally {
            BancoDados.finalizarStatement(st);
            BancoDados.finalizarResultSet(rs);
        }
    }
}
