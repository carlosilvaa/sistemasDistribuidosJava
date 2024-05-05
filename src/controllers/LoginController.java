package controllers;

import java.sql.Connection;
import java.sql.SQLException;
import org.json.JSONObject;
import dao.LoginDAO;
import dao.UsuarioDAO;
import entities.Login;
import helper.ValidarFormulario;


public class LoginController {
	private LoginDAO loginDao;
	private JSONObject request;

	public LoginController(Connection conn, JSONObject request ) {
		new UsuarioDAO(conn);
		this.loginDao = new LoginDAO(conn);
		this.request = request;
	}
	
	public JSONObject loginCandidato(JSONObject json) {
		String email = json.getString("email");
		String senha = String.valueOf(json.getInt("senha"));
		JSONObject responseValidacao = new JSONObject();
		
		boolean hasKeys = ValidarFormulario.checarChaves(this.request, "email", "senha");
				 
	     if (!hasKeys) {
	    	 responseValidacao.put("operacao", "loginCandidato");
	    	 responseValidacao.put("status", 401);
	    	 responseValidacao.put("mensagem", "Informe todos os campos");

	         return responseValidacao;
	     }
	
	     JSONObject responseEmail;
		  // Validar endereco de email
		  responseEmail = ValidarFormulario.checarEmail(this.request, "loginCandidato");
		  	if (responseEmail.getInt("status") != 200) {
		      return responseEmail;
		  }


		try {
			Login login = loginDao.loginCandidato(email, senha);
			if (login != null) {
				JSONObject responseLogin = new JSONObject();
				responseLogin.put("operacao", "loginCandidato");
				responseLogin.put("status", 200);
				responseLogin.put("token", login.getToken());
				return responseLogin;
			} else {
				return errorResponse("loginCandidato", "Email ou senha inválidos");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return errorResponse("loginCandidato", "Erro ao buscar usuário", 500);
		}
	}

	public JSONObject logoutCandidato(JSONObject json) {
		String token = json.getString("token");
		JSONObject responseValidacao = new JSONObject();
		
		boolean hasKeys = ValidarFormulario.checarChaves(this.request, "email", "senha");
		 
	     if (!hasKeys) {
	    	 responseValidacao.put("operacao", "loginCandidato");
	    	 responseValidacao.put("status", 401);
	    	 responseValidacao.put("mensagem", "Informe todos os campos");

	         return responseValidacao;
	     }
		
		try {
			Login login = loginDao.logoutCandidato(token);
			if(login != null) {
				JSONObject responseJson = new JSONObject();
				responseJson.put("operacao", "logout");
				responseJson.put("status", 200);
				responseJson.put("token", login.getToken());	
				return responseJson;
			}else {
				return errorResponse("logout", "Usuário nao possui token");
			}
			
		} catch (SQLException e) {
			
			JSONObject responseJson = new JSONObject();
			responseJson.put("operacao", "logout");
			responseJson.put("status", 500);
			responseJson.put("mensagem", "Erro interno ao tentar fazer logout");

			return responseJson;
		}
	}

	private JSONObject errorResponse(String operacao, String message) {
		JSONObject response = new JSONObject();
		response.put("operacao", operacao);
		response.put("status", 400);
		response.put("mensagem", message);
		return response;
	}

	private JSONObject errorResponse(String operacao, String message, int status) {
		JSONObject response = new JSONObject();
		response.put("operacao", operacao);
		response.put("status", status);
		response.put("mensagem", message);
		return response;
	}

}
