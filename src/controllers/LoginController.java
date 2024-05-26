package controllers;

import java.sql.Connection;
import java.sql.SQLException;
import org.json.JSONObject;
import dao.LoginDAO;
import dao.UsuarioDAO;
import helper.ValidarFormulario;

public class LoginController {
	private LoginDAO loginDao;

	public LoginController(Connection conn) {
		new UsuarioDAO(conn);
		this.loginDao = new LoginDAO(conn);
	}

	public JSONObject loginCandidato(JSONObject request) {
		String email = request.getString("email");
		String senha = String.valueOf(request.getString("senha"));
		JSONObject responseValidacao = new JSONObject();

		boolean hasKeys = ValidarFormulario.checarChaves(request, "email", "senha");

		if (!hasKeys) {
			responseValidacao.put("operacao", "loginCandidato");
			responseValidacao.put("status", 401);
			responseValidacao.put("mensagem", "Informe todos os campos");

			return responseValidacao;
		}

		JSONObject responseEmail;
		// Validar endereco de email
		responseEmail = ValidarFormulario.checarEmail(request, "loginCandidato");
		if (responseEmail.getInt("status") != 200) {
			return responseEmail;
		}

		try {
			return loginDao.loginCandidato(email, senha);
			/*
			 * if (login != null) { JSONObject responseLogin = new JSONObject();
			 * responseLogin.put("operacao", "loginCandidato"); responseLogin.put("status",
			 * 200); responseLogin.put("token", login.getToken()); return responseLogin; }
			 * else { return errorResponse("loginCandidato", "Email ou senha inválidos"); }
			 */
		} catch (SQLException e) {
			e.printStackTrace();
			return errorResponse("loginCandidato", "Erro ao buscar usuário", 500);
		}
	}

	public JSONObject logoutCandidato(JSONObject request) {
		String token = request.getString("token");
		JSONObject responseValidacao = new JSONObject();

		boolean hasKeys = ValidarFormulario.checarChaves(request, "token");

		if (!hasKeys) {
			responseValidacao.put("operacao", "logout");
			responseValidacao.put("status", 401);
			responseValidacao.put("mensagem", "Informe todos os campos");

			return responseValidacao;
		}

		try {
			return loginDao.logoutCandidato(token);

		} catch (SQLException e) {

			JSONObject responseJson = new JSONObject();
			responseJson.put("operacao", "logout");
			responseJson.put("status", 500);
			responseJson.put("mensagem", "Erro interno ao tentar fazer logout");

			return responseJson;
		}
	}

	public JSONObject loginEmpresa(JSONObject request) {
		String email = request.getString("email");
		String senha = String.valueOf(request.getString("senha"));
		JSONObject responseValidacao = new JSONObject();

		boolean hasKeys = ValidarFormulario.checarChaves(request, "email", "senha");

		if (!hasKeys) {
			responseValidacao.put("operacao", "loginEmpresa");
			responseValidacao.put("status", 401);
			responseValidacao.put("mensagem", "Informe todos os campos");

			return responseValidacao;
		}

		JSONObject responseEmail;
		// Validar endereço de email
		responseEmail = ValidarFormulario.checarEmail(request, "loginEmpresa");
		if (responseEmail.getInt("status") != 200) {
			return responseEmail;
		}

		try {
			return loginDao.loginEmpresa(email, senha);
		} catch (SQLException e) {
			e.printStackTrace();
			return errorResponse("loginEmpresa", "Erro ao buscar empresa", 500);
		}
	}

	public JSONObject logoutEmpresa(JSONObject request) {
		String token = request.getString("token");
		JSONObject responseValidacao = new JSONObject();

		boolean hasKeys = ValidarFormulario.checarChaves(request, "token");

		if (!hasKeys) {
			responseValidacao.put("operacao", "logout");
			responseValidacao.put("status", 401);
			responseValidacao.put("mensagem", "Informe todos os campos");

			return responseValidacao;
		}

		try {
			return loginDao.logoutEmpresa(token);
		} catch (SQLException e) {
			JSONObject responseJson = new JSONObject();
			responseJson.put("operacao", "logout");
			responseJson.put("status", 500);
			responseJson.put("mensagem", "Erro interno ao tentar fazer logout");

			return responseJson;
		}
	}

	private JSONObject errorResponse(String operacao, String message, int status) {
		JSONObject response = new JSONObject();
		response.put("operacao", operacao);
		response.put("status", 404);

		return response;
	}

}
