package controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;
import dao.LoginDAO;
import dao.UsuarioDAO;
import entities.Login;
import entities.Usuario;
import helper.ValidarFormulario;

public class LoginController {
	private LoginDAO loginDao;
	private UsuarioDAO usuarioDAO;

	public LoginController(Connection conn) {
		new UsuarioDAO(conn);
		this.loginDao = new LoginDAO(conn);
		this.usuarioDAO = new UsuarioDAO(conn);
	}

	public JSONObject loginCandidato(JSONObject request) throws JSONException, SQLException {
	    JSONObject responseJson = new JSONObject();

	    // Valida se informou todas as keys
	    boolean hasKeys = ValidarFormulario.checarChaves(request, "email", "senha");
	    if (!hasKeys) {
	        responseJson.put("operacao", "loginCandidato");
	        responseJson.put("status", 401);
	        responseJson.put("mensagem", "Informe todos os campos");
	        return responseJson;
	    }

	    // Valida email
	    JSONObject responseEmail = ValidarFormulario.checarEmail(request, "loginCandidato");
	    if (responseEmail.getInt("status") != 200) {
	        return responseEmail;
	    }

	    String email = request.getString("email");
	    String senha = request.getString("senha");

	    Usuario camposLogin = this.usuarioDAO.buscarPorEmail(email);

	    if (camposLogin != null) {
	        try {
	            if (camposLogin.getSenha().equals(senha)) {
	                Login loginCandidato = this.loginDao.buscarLoginPorCandidato(camposLogin);

	                if (loginCandidato != null) {
	                    responseJson.put("operacao", "loginCandidato");
	                    responseJson.put("status", 200);
	                    responseJson.put("token", loginCandidato.getToken());
	                    return responseJson;
	                }

	                String uuid = UUID.randomUUID().toString();
	                loginCandidato = new Login();
	                loginCandidato.setUsuario(camposLogin);
	                loginCandidato.setToken(uuid);

	                loginDao.loginCandidato(loginCandidato);

	                responseJson.put("operacao", "loginCandidato");
	                responseJson.put("status", 200);
	                responseJson.put("token", uuid);
	                return responseJson;
	            } else {
	                responseJson.put("operacao", "loginCandidato");
	                responseJson.put("status", 401);
	                responseJson.put("mensagem", "Login ou senha incorretos");
	                return responseJson;
	            }
	        } catch (JSONException ex) {
	            responseJson.put("operacao", "loginCandidato");
	            responseJson.put("status", 401);
	            responseJson.put("mensagem", "Senha deve ser string");
	            return responseJson;
	        } catch (Exception ex) {
	            responseJson.put("operacao", "loginCandidato");
	            responseJson.put("status", 401);
	            responseJson.put("mensagem", "Erro ao tentar cadastrar Login Candidato");
	            return responseJson;
	        }
	    } else {
	        responseJson.put("operacao", "loginCandidato");
	        responseJson.put("status", 401);
	        responseJson.put("mensagem", "Login ou senha incorretos");
	        return responseJson;
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
