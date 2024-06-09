package controllers;

import entities.Empresa;
import helper.ValidarFormulario;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;
import dao.EmpresaDAO;

public class EmpresaController {

    private EmpresaDAO empresaDAO;
    public EmpresaController(Connection conn) {
        this.empresaDAO = new EmpresaDAO(conn);
    }

    public EmpresaController() {
    }

    public JSONObject cadastrarEmpresa(JSONObject request) {
        try {
            boolean hasKeys = ValidarFormulario.checarChaves(request, "email", "senha", "razaoSocial", "cnpj", "descricao", "ramo");
            if (!hasKeys) {
                return errorResponse("cadastrarEmpresa", "Informe todos os campos", 401);
            }

            JSONObject responseEmail = ValidarFormulario.checarEmail(request, "loginEmpresa");
			if (responseEmail.getInt("status") != 200) {
				return responseEmail;
			}
			System.out.println("responseEmail" + responseEmail);
			JSONObject responseSenha = ValidarFormulario.checarSenha(request, "loginEmpresa");
			if (responseSenha.has("status") && responseSenha.getInt("status") != 200) {
				return responseSenha;
			}
			System.out.println("responseSenha" + responseSenha);
            
            JSONObject responseCnpj = ValidarFormulario.checarCnpj(request, "loginEmpresa");
			if (responseCnpj.has("status") && responseCnpj.getInt("status") != 200) {
				return responseCnpj;
			}
			
			System.out.println("responseCnpj" + responseCnpj);
            Empresa newEmpresa = new Empresa();
            newEmpresa.setRazaoSocial(request.getString("razaoSocial"));
            newEmpresa.setEmail(request.getString("email"));
            newEmpresa.setSenha(request.getString("senha"));
            newEmpresa.setCNPJ(request.getString("cnpj"));
            newEmpresa.setRamo(request.getString("ramo"));
            newEmpresa.setDescricao(request.getString("descricao"));

            this.empresaDAO.cadastrarEmpresa(newEmpresa);
            System.out.println(newEmpresa);
            String uuid = UUID.randomUUID().toString();
            return successResponse("cadastrarEmpresa", "Empresa cadastrada com sucesso", uuid);
        } catch (SQLException ex) {
            return errorResponse("cadastrarEmpresa", "Erro ao tentar cadastrar Empresa", 404);
        } catch (JSONException e) {
            return errorResponse("cadastrarEmpresa", "JSON inválido", 404);
        }
    }

    public JSONObject excluirEmpresa(JSONObject request) {
        try {
        	String response = this.empresaDAO.verificarToken(request);
            if (!response.equals("sucesso")) {
                return new JSONObject(response);
            }

            boolean hasKeys = ValidarFormulario.checarChaves(request, "email");
            if (!hasKeys) {
                return errorResponse("apagarEmpresa", "Informe todos os campos", 401);
            }

            String email = request.getString("email");
            int result = this.empresaDAO.excluirEmpresa(email);
            if (result > 0) {
                return successResponse("apagarEmpresa", "Empresa excluída com sucesso");
            } else {
                return errorResponse("apagarEmpresa", "E-mail não encontrado", 404);
            }
        } catch (SQLException e) {
            return errorResponse("apagarEmpresa", "Erro ao excluir empresa", 500);
        } catch (JSONException e) {
            return errorResponse("apagarEmpresa", "JSON inválido", 404);
        }
    }

    public JSONObject editarEmpresa(JSONObject request) {
        try {
        	String response = this.empresaDAO.verificarToken(request);
            if (!response.equals("sucesso")) {
                return new JSONObject(response);
            }

            boolean hasKeys = ValidarFormulario.checarChaves(request, "email", "senha", "razaoSocial", "cnpj", "descricao", "ramo");
            if (!hasKeys) {
                return errorResponse("atualizarEmpresa", "Informe todos os campos", 401);
            }

            JSONObject responseEmail = ValidarFormulario.checarEmail(request, "atualizarEmpresa");
            if (responseEmail.getInt("status") != 200) {
                return responseEmail;
            }

            String cnpj = request.getString("cnpj");
            String novaRazaoSocial = request.getString("razaoSocial");
            String senha = request.getString("senha");
            String descricao = request.getString("descricao");
            String ramo = request.getString("ramo");
            String email = request.getString("email");

            boolean sucesso = this.empresaDAO.editarEmpresa(cnpj, email, novaRazaoSocial, senha, descricao, ramo);
            if (sucesso) {
                return successResponse("atualizarEmpresa", "Empresa editada com sucesso");
            } else {
                return errorResponse("atualizarEmpresa", "E-mail não encontrado", 404);
            }
        } catch (SQLException e) {
            return errorResponse("atualizarEmpresa", "Erro ao editar empresa", 500);
        } catch (JSONException e) {
            return errorResponse("atualizarEmpresa", "JSON inválido", 404);
        }
    }

    public JSONObject buscarEmpresaPorEmail(JSONObject request) {
        try {
        	String response = this.empresaDAO.verificarToken(request);
            if (!response.equals("sucesso")) {
                return new JSONObject(response);
            }

            boolean hasKeys = ValidarFormulario.checarChaves(request, "email", "token");
            if (!hasKeys) {
                return errorResponse("visualizarEmpresa", "Informe todos os campos", 401);
            }

            String email = request.getString("email");
            Empresa empresa = this.empresaDAO.buscarPorEmail(email);
            if (empresa != null) {
                return successResponse("visualizarEmpresa", empresa);
            } else {
                return errorResponse("visualizarEmpresa", "E-mail não encontrado", 404);
            }
        } catch (SQLException e) {
            return errorResponse("visualizarEmpresa", "Erro ao buscar empresa", 500);
        } catch (JSONException e) {
            return errorResponse("visualizarEmpresa", "JSON inválido", 404);
        }
    }


    private JSONObject successResponse(String operacao, String message) {
        JSONObject response = new JSONObject();
        response.put("operacao", operacao);
        response.put("status", 201);
        response.put("mensagem", message);
        return response;
    }

    private JSONObject successResponse(String operacao, String message, String token) {
        JSONObject response = new JSONObject();
        response.put("operacao", operacao);
        response.put("status", 201);
        response.put("mensagem", message);
        response.put("token", token);
        return response;
    }

    private JSONObject successResponse(String operacao, Empresa empresa) {
        JSONObject response = new JSONObject();
        response.put("operacao", operacao);
        response.put("status", 201);
        response.put("razaoSocial", empresa.getRazaoSocial());
        response.put("senha", empresa.getSenha());
        response.put("email", empresa.getEmail());
        response.put("cnpj", empresa.getCNPJ());
        response.put("descricao", empresa.getDescricao());
        response.put("ramo", empresa.getRamo());
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

