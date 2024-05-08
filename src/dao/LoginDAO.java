package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import org.json.JSONObject;

public class LoginDAO {
	private Connection conn;
	public LoginDAO(Connection conn) {
		this.conn = conn;
	}

	public JSONObject loginCandidato(String email, String senha) throws SQLException {
	    PreparedStatement st = null;
	    ResultSet rs = null;

	    try {
	        st = conn.prepareStatement("SELECT * FROM candidato WHERE email = ? AND senha = ?");
	        st.setString(1, email);
	        st.setString(2, senha);
	        rs = st.executeQuery();
	        JSONObject login = new JSONObject();

	        if (rs.next()) {
	            int idCandidato = rs.getInt("idCandidato");
	            String token = UUID.randomUUID().toString();

	            st = conn.prepareStatement("INSERT INTO logincandidato (idCandidato, token) VALUES (?, ?)");
	            st.setInt(1, idCandidato);
	            st.setString(2, token);
	            st.executeUpdate();

	            login.put("operacao","loginCandidato");
	            login.put("status",200);
	            login.put("token", token);
	            return login;
	        } else {
	            login.put("operacao","loginCandidato");
	            login.put("status",401);
	            login.put("mensagem", "Login ou senha incorretos");
	            return login;
	        }
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

	            login.put("operacao","logout");
	            login.put("status",200);
	            login.put("mensagem", "Logout realizado com sucesso");
	            return login;
	        } else {
	            login.put("operacao","logout");
	            login.put("status",401);
	            login.put("mensagem", "Candidato não encontrado");
	            return login;
	        }
	    } finally {
	        BancoDados.finalizarStatement(st);
	        BancoDados.finalizarResultSet(rs);
	    }
	}

}
