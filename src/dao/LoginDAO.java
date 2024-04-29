package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import entities.Login;

public class LoginDAO {
	private Connection conn;
	public LoginDAO(Connection conn) {
		this.conn = conn;
	}

	public Login loginCandidato(String email, String senha) throws SQLException {
	    PreparedStatement st = null;
	    ResultSet rs = null;

	    try {
	        st = conn.prepareStatement("SELECT * FROM candidato WHERE email = ? AND senha = ?");
	        st.setString(1, email);
	        st.setString(2, senha);
	        rs = st.executeQuery();

	        if (rs.next()) {
	            int idCandidato = rs.getInt("idCandidato");
	            String token = UUID.randomUUID().toString();

	            st = conn.prepareStatement("INSERT INTO logincandidato (idCandidato, token) VALUES (?, ?)");
	            st.setInt(1, idCandidato);
	            st.setString(2, token);
	            st.executeUpdate();

	            Login login = new Login();
	            login.setOperacao("loginCandidato");
	            login.setStatus(200);
	            login.setToken(token);
	            return login;
	        } else {
	            Login login = new Login();
	            login.setOperacao("loginCandidato");
	            login.setStatus(401);
	            login.setMensagem("Login ou senha incorretos");
	            return login;
	        }
	    } finally {
	        BancoDados.finalizarStatement(st);
	        BancoDados.finalizarResultSet(rs);
	    }
	}

	
	public Login logoutCandidato(String token) throws SQLException {
	    PreparedStatement st = null;    
	    ResultSet rs = null;    

	    try {
	        st = conn.prepareStatement("SELECT * FROM logincandidato WHERE token = ?");
	        st.setString(1, token);
	        rs = st.executeQuery();

	        if (rs.next()) {
	            st = conn.prepareStatement("DELETE FROM logincandidato WHERE token = ?");
	            st.setString(1, token);
	            st.executeUpdate();

	            Login login = new Login();
	            login.setOperacao("logout");
	            login.setStatus(200);
	            login.setMensagem("Logout realizado com sucesso");
	            login.setToken(token);
	            return login;
	        } else {
	            Login login = new Login();
	            login.setOperacao("logout");
	            login.setStatus(401);
	            login.setMensagem("Candidato não encontrado");
	            return login;
	        }
	    } finally {
	        BancoDados.finalizarStatement(st);
	        BancoDados.finalizarResultSet(rs);
	    }
	}

}
