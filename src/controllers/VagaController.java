package controllers;

import dao.VagaDAO;
import dao.EmpresaDAO;
import entities.Empresa;
import entities.Vaga;
import dao.UsuarioDAO;
import helper.ListarVaga;
import helper.ValidarFormulario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VagaController {

	private Connection conn;
	private VagaDAO vagaDAO;
	private EmpresaDAO empresaDAO;
	private UsuarioDAO usuarioDAO;

	public VagaController(Connection conn) {
		this.conn = conn;
		this.vagaDAO = new VagaDAO(conn);
		this.empresaDAO = new EmpresaDAO(conn);
	}

	public JSONObject cadastrarVaga(JSONObject request) {
		JSONObject responseValidacao = new JSONObject();

		boolean hasKeys = ValidarFormulario.checarChaves(request, "email", "token", "nome", "faixaSalarial",
				"descricao", "estado", "competencias");
		if (!hasKeys) {
			responseValidacao.put("operacao", "cadastrarVaga");
			responseValidacao.put("status", 401);
			responseValidacao.put("mensagem", "Informe todos os campos");
			return responseValidacao;
		}

		JSONObject responseEmail = ValidarFormulario.checarEmail(request, "cadastrarVaga");
		if (responseEmail.getInt("status") != 200) {
			return responseEmail;
		}
		try {
			String email = request.getString("email");
			String nome = request.getString("nome");
			double faixaSalarial = request.getDouble("faixaSalarial");
			String descricao = request.getString("descricao");
			String estado = request.getString("estado");
			JSONArray competenciasArray = request.getJSONArray("competencias");

			List<String> competencias = jsonArrayToList(competenciasArray);

			Vaga vaga = new Vaga(request.getString("email").toString());
			vaga.setNome(nome);
			vaga.setFaixaSalarial(faixaSalarial);
			vaga.setDescricao(descricao);
			vaga.setEstado(estado);
			vaga.setCompetencias(competencias);

			vagaDAO.cadastrarVaga(vaga);

			return successResponse("cadastrarVaga", "Vaga cadastrada com sucesso", 201);
		} catch (SQLException ex) {
			return errorResponse("cadastrarVaga", "Erro ao cadastrar a vaga", 500);
		} catch (JSONException e) {
			return errorResponse("cadastrarVaga", "JSON inválido", 400);
		}
	}

	public JSONObject visualizarVaga(JSONObject request) throws SQLException {
		String responseToken = this.empresaDAO.verificarToken(request);
        if (!responseToken.equals("sucesso")) {
            return new JSONObject(responseToken);
        }

		try {
			int idVaga = request.getInt("idVaga");

			Vaga vaga = vagaDAO.visualizarVaga(idVaga);

			if (vaga == null) {
				return errorResponse("visualizarVaga", "Vaga não encontrada", 404);
			}

			JSONObject response = new JSONObject();
			response.put("operacao", "visualizarVaga");
			response.put("faixaSalarial", vaga.getFaixaSalarial());
			response.put("descricao", vaga.getDescricao());
			response.put("estado", vaga.getEstado());
			response.put("competencias", new JSONArray(vaga.getCompetencias()));

			return response;
		} catch (SQLException ex) {
			return errorResponse("visualizarVaga", "Erro ao buscar a vaga", 500);
		} catch (JSONException e) {
			return errorResponse("visualizarVaga", "JSON inválido", 400);
		}
	}

	public JSONObject atualizarVaga(JSONObject request) throws SQLException {
		String responseToken = this.empresaDAO.verificarToken(request);
        if (!responseToken.equals("sucesso")) {
            return new JSONObject(responseToken);
        }
        
        JSONObject responseEmail = ValidarFormulario.checarEmail(request, "cadastrarVaga");
		if (responseEmail.getInt("status") != 200) {
			return responseEmail;
		}
		
		Empresa empresa = this.empresaDAO.buscarPorEmail(request.getString("email"));

		try {
			int idVaga = request.getInt("idVaga");
			String email = request.getString("email");
			String nome = request.getString("nome");
			double faixaSalarial = request.getDouble("faixaSalarial");
			String descricao = request.getString("descricao");
			String estado = request.getString("estado");
			JSONArray competenciasArray = request.getJSONArray("competencias");

			List<String> competencias = jsonArrayToList(competenciasArray);

			Vaga vaga = new Vaga(request.getString("email").toString());
			vaga.setCodigo(idVaga);
			vaga.setNome(nome);
			vaga.setFaixaSalarial(faixaSalarial);
			vaga.setDescricao(descricao);
			vaga.setEstado(estado);
			vaga.setCompetencias(competencias);

			vagaDAO.atualizarVaga(vaga, empresa);

			return successResponse("atualizarVaga", "Vaga atualizada com sucesso", 201);
		} catch (SQLException ex) {
			return errorResponse("atualizarVaga", "Erro ao atualizar a vaga", 500);
		} catch (JSONException e) {
			return errorResponse("atualizarVaga", "JSON inválido", 400);
		}
	}

	public JSONObject apagarVaga(JSONObject request) throws SQLException {
		String responseToken = this.empresaDAO.verificarToken(request);
        if (!responseToken.equals("sucesso")) {
            return new JSONObject(responseToken);
        }


		try {
			int idVaga = request.getInt("idVaga");
			String email = request.getString("email");

			vagaDAO.apagarVaga(idVaga);

			return successResponse("apagarVaga", "Vaga apagada com sucesso", 201);
		} catch (SQLException ex) {
			return errorResponse("apagarVaga", "Erro ao apagar a vaga", 500);
		} catch (JSONException e) {
			return errorResponse("apagarVaga", "JSON inválido", 400);
		}
	}

	private List<String> jsonArrayToList(JSONArray jsonArray) throws JSONException {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < jsonArray.length(); i++) {
			list.add(jsonArray.getString(i));
		}
		return list;
	}

	public JSONObject listarVagas(JSONObject request) throws SQLException {
		JSONObject responseValidacao = new JSONObject();

		boolean hasKeys = ValidarFormulario.checarChaves(request, "email", "token");
		if (!hasKeys) {
			responseValidacao.put("operacao", "listarVagas");
			responseValidacao.put("status", 401);
			responseValidacao.put("mensagem", "Informe todos os campos");
			return responseValidacao;
		}

		JSONObject responseEmail = ValidarFormulario.checarEmail(request, "listarVagas");
		if (responseEmail.getInt("status") != 200) {
			return responseEmail;
		}

		String responseToken = this.empresaDAO.verificarToken(request);
        if (!responseToken.equals("sucesso")) {
            return new JSONObject(responseToken);
        }


		Empresa empresa;
		try {
			empresa = this.empresaDAO.buscarPorEmail(request.getString("email"));
			if (empresa == null) {
				responseValidacao.put("operacao", "listarVagas");
				responseValidacao.put("status", 422);
				responseValidacao.put("mensagem", "Empresa não encontrada");
				return responseValidacao;
			}
		} catch (SQLException e) {
			responseValidacao.put("operacao", "listarVagas");
			responseValidacao.put("status", 500);
			responseValidacao.put("mensagem", "Erro ao buscar a empresa: " + e.getMessage());
			return responseValidacao;
		}

		try {
			List<ListarVaga> resp = this.vagaDAO.buscarVagaPorEmpresa(empresa);
			if (resp == null) {
				responseValidacao.put("operacao", "listarVagas");
				responseValidacao.put("status", 201);
				responseValidacao.put("vagas", new JSONArray());
				return responseValidacao;
			}

			responseValidacao.put("operacao", "listarVagas");
			responseValidacao.put("status", 201);
			responseValidacao.put("vagas", new JSONArray(resp)); // Converte a lista para JSONArray

			return responseValidacao;
		} catch (Exception ex) {
			responseValidacao.put("operacao", "listarVagas");
			responseValidacao.put("status", 422);
			responseValidacao.put("mensagem", "Erro ao tentar listar Vagas: " + ex.getMessage());
			return responseValidacao;
		}
	}

    public JSONObject filtrarVagas(JSONObject request) throws SQLException {
        JSONObject responseJson = new JSONObject();
        // Valida se informou todas as keys
        boolean hasKeys = ValidarFormulario.checarChaves(request, "email", "token", "filtros");
        if (!hasKeys) {
            responseJson.put("operacao", "filtrarVagas");
            responseJson.put("status", 422);
            responseJson.put("mensagem", "Informe todos os campos");
            return responseJson;
        }

        // Valida se informou todas as keys do filtros
        JSONObject filtros = request.getJSONObject("filtros");
        hasKeys = ValidarFormulario.checarChaves(filtros, "competencias", "tipo");
        if (!hasKeys) {
            responseJson.put("operacao", "filtrarVagas");
            responseJson.put("status", 422);
            responseJson.put("mensagem", "Informe todos os campos do objeto filtros");
            return responseJson;
        }

        JSONObject responseEmail = ValidarFormulario.checarEmail(request, "loginEmpresa");
        if (responseEmail.getInt("status") != 200) {
            return responseEmail;
        }

        String responseToken = this.empresaDAO.verificarToken(request);
        if (!responseToken.equals("sucesso")) {
            return new JSONObject(responseToken);
        }


        String tipo = filtros.getString("tipo");
        if (!tipo.equals("OR") && !tipo.equals("AND")) {
            responseJson.put("operacao", "filtrarVagas");
            responseJson.put("status", 422);
            responseJson.put("mensagem", "Tipo tem que ser OR ou AND");
            return responseJson;
        }

        try {
            String where = "WHERE 1=1 ";

            JSONArray competencias = filtros.getJSONArray("competencias");
            for (int i = 0; i < competencias.length(); i++) {
                String comp = competencias.getString(i);
                where = where + tipo + " v.competencias LIKE \"%" + comp + "%\" ";
            }

            List<Vaga> resp = this.vagaDAO.filtrarVagasCandidato(where);
            if (resp == null) {
                responseJson.put("operacao", "filtrarVagas");
                responseJson.put("status", 201);
                responseJson.put("vagas", new JSONArray());
                return responseJson;
            }

            JSONArray newArray = new JSONArray();
            for (Vaga vaga : resp) {
                JSONObject novoObjeto = new JSONObject();
                novoObjeto.put("estado", vaga.getEstado());
                novoObjeto.put("idVaga", vaga.getId());
                novoObjeto.put("nome", vaga.getNome());
                novoObjeto.put("email", vaga.getEmpresa().getEmail());
                novoObjeto.put("competencias", vaga.getCompetencias());
                novoObjeto.put("faixaSalarial", vaga.getFaixaSalarial());
                novoObjeto.put("descricao", vaga.getDescricao());

                newArray.put(novoObjeto);
            }

            responseJson.put("operacao", "filtrarVagas");
            responseJson.put("status", 201);
            responseJson.put("vagas", newArray);

            return responseJson;
        } catch (Exception ex) {
            responseJson.put("operacao", "filtrarVagas");
            responseJson.put("status", 422);
            responseJson.put("mensagem", "Erro ao tentar Filtrar Vagas: " + ex.getMessage());

            return responseJson;
        }
    }

	private JSONObject errorResponse(String operation, String message, int status) {
		JSONObject response = new JSONObject();
		response.put("operacao", operation);
		response.put("status", status);
		response.put("mensagem", message);
		return response;
	}

	private JSONObject successResponse(String operation, String message, int status) {
		JSONObject response = new JSONObject();
		response.put("operacao", operation);
		response.put("status", status);
		response.put("mensagem", message);
		return response;
	}
}