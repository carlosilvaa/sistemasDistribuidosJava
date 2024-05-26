package controllers;

import entities.Usuario;
import helper.ValidarFormulario;
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
				responseValidacao.put("operacao", "loginCandidato");
				responseValidacao.put("status", 401);
				responseValidacao.put("mensagem", "Informe todos os campos");
				return responseValidacao;
			}

			JSONObject responseEmail = ValidarFormulario.checarEmail(request, "loginCandidato");
			if (responseEmail.getInt("status") != 200) {
				return responseEmail;
			}
			System.out.println("responseEmail" + responseEmail);
			JSONObject responseNome = ValidarFormulario.checarNome(request, "loginCandidato");
			if (responseNome.has("status") && responseNome.getInt("status") != 200) {
				return responseNome;
			}

			JSONObject responseSenha = ValidarFormulario.checarSenha(request, "loginCandidato");
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

	public JSONObject realizarLogin(JSONObject request) throws SQLException {
		JSONObject responseValidacao = new JSONObject();

		try {
			String nome = request.getString("nome");
			String senha = request.getString("senha");

			boolean hasKeys = ValidarFormulario.checarChaves(request, "email", "senha");
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

			loginDAO.loginCandidato(nome, senha);
			return successResponse("loginCandidato", "Login realizado com sucesso!", "UUID");
		} catch (JSONException e) {
			return errorResponse("realizarLogin", "JSON inválido", 404);
		}
	}

	public JSONObject excluirCandidato(JSONObject request) {
	    JSONObject responseValidacao = new JSONObject();

	    try {
	        String email = request.getString("email");

	        System.out.println("Email recebido: " + email);

	        boolean hasKeys = ValidarFormulario.checarChaves(request, "email");
	        if (!hasKeys) {
	            responseValidacao.put("operacao", "apagarCandidato");
	            responseValidacao.put("status", 401);
	            responseValidacao.put("mensagem", "Informe todos os campos");
	            return responseValidacao;
	        }

	        JSONObject responseEmail = ValidarFormulario.checarEmail(request, "apagarCandidato");
	        if (responseEmail.getInt("status") != 200) {
	            return responseEmail;
	        }

	        System.out.println("Validação de email bem-sucedida para: " + email);

	        try {
	            int resultado = usuarioDAO.excluirCandidato(email);
	            if (resultado > 0) {
	                return successResponse("apagarCandidato", "Candidato excluído com sucesso!");
	            } else {
	                return errorResponse("apagarCandidato", "Candidato não encontrado", 404);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace(); // Adicione isso para ver o stack trace do SQLException
	            return errorResponse("apagarCandidato", "Erro ao excluir candidato", 500);
	        }
	    } catch (JSONException e) {
	        e.printStackTrace(); // Adicione isso para ver o stack trace do JSONException
	        return errorResponse("apagarCandidato", "JSON inválido", 404);
	    }
	}


	public JSONObject editarCandidato(JSONObject request) {
		JSONObject responseValidacao = new JSONObject();

		try {
			String nome = request.getString("nome");
			String senha = request.getString("senha");
			String email = request.getString("email");

			boolean hasKeys = ValidarFormulario.checarChaves(request, "email", "senha");
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

			JSONObject responseNome = ValidarFormulario.checarNome(request, "loginCandidato");
			if (responseNome.has("status") && responseNome.getInt("status") != 200) {
				return responseNome;
			}

			JSONObject responseSenha = ValidarFormulario.checarSenha(request, "loginCandidato");
			if (responseSenha.has("status") && responseSenha.getInt("status") != 200) {
				return responseSenha;
			}

			try {
				boolean candidatoExiste = usuarioDAO.verificarUsuario(email);

				if (candidatoExiste) {
					boolean sucesso = usuarioDAO.editarCandidato(email, nome, senha);

					if (sucesso) {
						return successResponse("atualizarCandidato", "Candidato editado com sucesso!");
					} else {
						return errorResponse("atualizarCandidato", "Erro ao editar candidato");
					}
				} else {
					return errorResponse("atualizarCandidato", "Candidato não encontrado");
				}
			} catch (SQLException e) {
				return errorResponse("atualizarCandidato", "Erro ao editar candidato", 500);
			} catch (Exception ex) {
				return errorResponse("atualizarCandidato", "Erro ao editar candidato", 500);
			}
		} catch (JSONException e) {
			return errorResponse("editarCandidato", "JSON inválido", 404);
		}
	}

	/*
	 * public JSONObject buscarTodos() { JSONObject responseValidacao = new
	 * JSONObject();
	 * 
	 * try { boolean hasKeys = ValidarFormulario.checarChaves(request,
	 * "email"); if (!hasKeys) { responseValidacao.put("operacao",
	 * "loginCandidato"); responseValidacao.put("status", 401);
	 * responseValidacao.put("mensagem", "Informe todos os campos"); return
	 * responseValidacao; }
	 * 
	 * JSONObject responseEmail = ValidarFormulario.checarEmail(request,
	 * "loginCandidato"); if (responseEmail.getInt("status") != 200) { return
	 * responseEmail; }
	 * 
	 * try { List<Usuario> usuarios = usuarioDAO.buscarTodos(); JSONArray jsonArray
	 * = new JSONArray(); for (Usuario usuario : usuarios) { JSONObject jsonObject =
	 * new JSONObject(); jsonObject.put("nome", usuario.getNome());
	 * jsonObject.put("email", usuario.getEmail()); jsonObject.put("senha",
	 * usuario.getSenha());
	 * 
	 * } return successResponse("visualizarCandidato", usuario.getNome(),
	 * usuario.getEmail(), usuario.getSenha()); } catch (SQLException e) { return
	 * errorResponse("visualizarCandidato", "Erro ao buscar todos os usuários",
	 * 500); } } catch (JSONException e) { return errorResponse("buscarTodos",
	 * "JSON inválido", 404); } }
	 */

	public JSONObject buscarPorEmail(JSONObject request) {

		JSONObject responseValidacao = new JSONObject();

		try {
			boolean hasKeys = ValidarFormulario.checarChaves(request, "email");
			if (!hasKeys) {
				responseValidacao.put("operacao", "loginCandidato");
				responseValidacao.put("status", 401);
				responseValidacao.put("mensagem", "Informe todos os campos");
				return responseValidacao;
			}

			JSONObject responseEmail = ValidarFormulario.checarEmail(request, "visualizarCandidato");
			if (responseEmail.getInt("status") != 200) {
				return responseEmail;
			}

			try {
				Usuario usuarios = usuarioDAO.buscarPorEmail(request.getString("email"));
				if (usuarios != null) {
					return successResponse("visualizarCandidato", usuarios.getNome(), usuarios.getEmail(),
							usuarios.getSenha());
				} else {
					return errorResponse("visualizarCandidato", "Usuario nao encontrado", 404);
				}
			} catch (SQLException e) {
				System.out.println(e);
				return errorResponse("visualizarCandidato", "Erro ao buscar todos os usuários", 500);
			}
		} catch (JSONException e) {
			return errorResponse("buscarTodos", "JSON inválido", 404);
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