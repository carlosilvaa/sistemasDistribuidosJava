package controllers;

import entities.Login;
import entities.Usuario;
import helper.ValidarFormulario;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;
import dao.LoginDAO;
import dao.UsuarioDAO;

public class UsuarioController {

	private UsuarioDAO usuarioDAO;
	private LoginDAO loginDAO;

	public UsuarioController(Usuario usuario, Connection conn) {
		this.usuarioDAO = new UsuarioDAO(conn);
	}

	public UsuarioController() {
	}

	public JSONObject cadastrarCandidato(JSONObject request) {
		JSONObject responseValidacao = new JSONObject();

		try {
			boolean hasKeys = ValidarFormulario.checarChaves(request, "nome", "email", "senha");
			if (!hasKeys) {
				responseValidacao.put("operacao", "cadastrarCandidato");
				responseValidacao.put("status", 401);
				responseValidacao.put("mensagem", "Informe todos os campos");
				return responseValidacao;
			}

			JSONObject responseNome = ValidarFormulario.checarNome(request, "cadastrarCandidato");
			if (responseNome.has("status") && responseNome.getInt("status") != 200) {
				return responseNome;
			}

			JSONObject responseSenha = ValidarFormulario.checarSenha(request, "cadastrarCandidato");
			if (responseSenha.has("status") && responseSenha.getInt("status") != 200) {
				return responseSenha;
			}

			Usuario usuario;
			try {
				usuario = this.usuarioDAO.buscarPorEmail(request.getString("email"));
			} catch (SQLException ex) {
				responseValidacao.put("operacao", "cadastrarCandidato");
				responseValidacao.put("status", 500);
				responseValidacao.put("mensagem", "Erro ao buscar usuário por email");
				return responseValidacao;
			}

			if (usuario == null) {
				Usuario newCandidato = new Usuario();
				newCandidato.setEmail(request.getString("email"));
				newCandidato.setNome(request.getString("nome"));
				newCandidato.setSenha(request.getString("senha"));

				try {
					this.usuarioDAO.cadastrarCandidato(newCandidato);
					String uuid = UUID.randomUUID().toString();

					responseValidacao.put("operacao", "cadastrarCandidato");
					responseValidacao.put("status", 201);
					responseValidacao.put("token", uuid);

					return responseValidacao;
				} catch (Exception ex) {
					responseValidacao.put("operacao", "cadastrarCandidato");
					responseValidacao.put("status", 404);
					responseValidacao.put("mensagem", "Erro ao tentar cadastrar Candidato");

					return responseValidacao;
				}

			} else {
				responseValidacao.put("operacao", "cadastrarCandidato");
				responseValidacao.put("status", 422);
				responseValidacao.put("mensagem", "E-mail já cadastrado");

				return responseValidacao;
			}
		} catch (JSONException e) {
			responseValidacao.put("operacao", "cadastrarCandidato");
			responseValidacao.put("status", 404);
			responseValidacao.put("mensagem", "JSON inválido");

			return responseValidacao;
		}
	}

	public JSONObject realizarLogin(JSONObject request) throws SQLException, IOException {
	    JSONObject responseValidacao = new JSONObject();

	    try {
	        String email = request.getString("email");
	        String senha = request.getString("senha");

	        boolean hasKeys = ValidarFormulario.checarChaves(request, "nome", "senha");
	        if (!hasKeys) {
	            responseValidacao.put("operacao", "loginCandidato");
	            responseValidacao.put("status", 401);
	            responseValidacao.put("mensagem", "Informe todos os campos");
	            return responseValidacao;
	        }

	        JSONObject responseEmail = ValidarFormulario.checarEmail(request, "loginCandidato");
	        if (responseEmail.getInt("status") != 200) {
	            return responseEmail;
	        }

	        JSONObject responseSenha = ValidarFormulario.checarSenha(request, "loginCandidato");
	        if (responseSenha.has("status") && responseSenha.getInt("status") != 200) {
	            return responseSenha;
	        }

	        Login loginCandidato = new Login();
	        loginCandidato.setEmail(email);
	        loginCandidato.setSenha(senha);

	        JSONObject loginResponse = loginDAO.loginCandidato(loginCandidato);

	        return loginResponse;
	    } catch (JSONException e) {
	        return errorResponse("realizarLogin", "JSON inválido", 404);
	    }
	}


	public JSONObject excluirCandidato(JSONObject request) throws SQLException {
		JSONObject responseValidacao = new JSONObject();

		try {
			String email = request.getString("email");

			System.out.println("Email recebido: " + email);

			boolean hasKeys = ValidarFormulario.checarChaves(request, "email", "token");
			if (!hasKeys) {
				responseValidacao.put("operacao", "excluirCandidato");
				responseValidacao.put("status", 401);
				responseValidacao.put("mensagem", "Informe todos os campos");
				return responseValidacao;
			}

			JSONObject responseEmail = ValidarFormulario.checarEmail(request, "excluirCandidato");
			if (responseEmail.getInt("status") != 200) {
				return responseEmail;
			}

			String response = this.usuarioDAO.verificarToken(request);
			if (!response.equals("sucesso")) {
				return new JSONObject(response);
			}

			System.out.println("Validação de email bem-sucedida para: " + email);

			try {
				int resultado = usuarioDAO.excluirCandidato(email);
				if (resultado > 0) {
					return successResponse("excluirCandidato", "Candidato excluído com sucesso!");
				} else {
					return errorResponse("excluirCandidato", "Candidato não encontrado", 404);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return errorResponse("excluirCandidato", "Erro ao excluir candidato", 500);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return errorResponse("excluirCandidato", "JSON inválido", 404);
		}
	}

	public JSONObject editarCandidato(JSONObject request) throws SQLException {
		JSONObject responseValidacao = new JSONObject();

		try {

			String nome = request.getString("nome");
			String senha = request.getString("senha");
			String email = request.getString("email");

			boolean hasKeys = ValidarFormulario.checarChaves(request, "email", "senha", "token");
			if (!hasKeys) {
				responseValidacao.put("operacao", "editarCandidato");
				responseValidacao.put("status", 401);
				responseValidacao.put("mensagem", "Informe todos os campos");
				return responseValidacao;
			}

			JSONObject responseEmail = ValidarFormulario.checarEmail(request, "editarCandidato");
			if (responseEmail.getInt("status") != 200) {
				return responseEmail;
			}

			JSONObject responseNome = ValidarFormulario.checarNome(request, "editarCandidato");
			if (responseNome.has("status") && responseNome.getInt("status") != 200) {
				return responseNome;
			}

			JSONObject responseSenha = ValidarFormulario.checarSenha(request, "editarCandidato");
			if (responseSenha.has("status") && responseSenha.getInt("status") != 200) {
				return responseSenha;
			}

			String response = this.usuarioDAO.verificarToken(request);
			if (!response.equals("sucesso")) {
				return new JSONObject(response);
			}

			try {
				boolean candidatoExiste = usuarioDAO.verificarUsuario(email);

				if (candidatoExiste) {
					boolean sucesso = usuarioDAO.editarCandidato(email, nome, senha);

					if (sucesso) {
						return successResponse("editarCandidato", "Candidato editado com sucesso!");
					} else {
						return errorResponse("editarCandidato", "Erro ao editar candidato");
					}
				} else {
					return errorResponse("editarCandidato", "Candidato não encontrado");
				}
			} catch (SQLException e) {
				return errorResponse("editarCandidato", "Erro ao editar candidato", 500);
			} catch (Exception ex) {
				return errorResponse("editarCandidato", "Erro ao editar candidato", 500);
			}
		} catch (JSONException e) {
			return errorResponse("editarCandidato", "JSON inválido", 404);
		}
	}

	public JSONObject buscarPorEmail(JSONObject request) throws SQLException {
		try {
			boolean hasKeys = ValidarFormulario.checarChaves(request, "email", "token");
			if (!hasKeys) {
				JSONObject responseValidacao = new JSONObject();
				responseValidacao.put("operacao", "visualizarCandidato");
				responseValidacao.put("status", 401);
				responseValidacao.put("mensagem", "Informe todos os campos");
				return responseValidacao;
			}

			JSONObject responseEmail = ValidarFormulario.checarEmail(request, "visualizarCandidato");
			if (responseEmail.getInt("status") != 200) {
				return responseEmail;
			}

			String response = this.usuarioDAO.verificarToken(request);
			if (!response.equals("sucesso")) {
				return new JSONObject(response);
			}

			try {
				Usuario usuario = usuarioDAO.buscarPorEmail(request.getString("email"));
				if (usuario != null) {
					return successResponse("visualizarCandidato", usuario.getNome(), usuario.getEmail(), usuario.getSenha());
				} else {
					return errorResponse("visualizarCandidato", "Usuário não encontrado", 404);
				}
			} catch (SQLException e) {
				System.out.println(e);
				return errorResponse("visualizarCandidato", "Erro ao buscar usuário", 500);
			}
		} catch (JSONException e) {
			return errorResponse("visualizarCandidato", "JSON inválido", 404);
		}
	}

	private JSONObject successResponse(String operacao, String message) {
		JSONObject response = new JSONObject();
		response.put("operacao", operacao);
		response.put("status", 201);
		response.put("mensagem", message);
		return response;
	}

	private JSONObject successResponse(String operacao, String nome, String email, String senha) {
		JSONObject response = new JSONObject();
		response.put("operacao", operacao);
		response.put("nome", nome);
		response.put("email", email);
		response.put("senha", senha);
		response.put("status", 201);
		return response;
	}

	private JSONObject successResponse(String operacao, String message, String token) {
		JSONObject response = new JSONObject();
		response.put("operacao", operacao);
		response.put("status", 201);
		response.put("token", token);
		return response;
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
