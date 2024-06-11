package controllers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

import entities.Usuario;
import entities.CompetenciaExperiencia;
import dao.CompetenciaExperienciaDAO;
import helper.ValidarFormulario;
import dao.UsuarioDAO;

public class CompetenciaExperienciaController {

	private Connection conn;
	private UsuarioDAO usuarioDAO;
	private CompetenciaExperienciaDAO competenciaExperienciaDAO;

	public CompetenciaExperienciaController(Connection conn) {
		this.conn = conn;
		this.competenciaExperienciaDAO = new CompetenciaExperienciaDAO(conn);
		this.usuarioDAO = new UsuarioDAO(conn);
	}

	public JSONObject cadastrarCompetencia(JSONObject request) throws SQLException {
		String response = this.usuarioDAO.verificarToken(request);
		if (!response.equals("sucesso")) {
			return new JSONObject(response);
		}

		JSONObject responseJson = new JSONObject();
		boolean hasKeys = ValidarFormulario.checarChaves(request, "email", "competenciaExperiencia", "token");
		if (!hasKeys) {
			responseJson.put("operacao", "cadastrarCompetenciaExperiencia");
			responseJson.put("status", 422);
			responseJson.put("mensagem", "Informe todos os campos");

			return responseJson;
		}

		JSONObject responseEmail;
		if (!(responseEmail = ValidarFormulario.checarEmail(request, "visualizarCompetenciaExperiencia"))
				.getString("mensagem").equals("Sucesso")) {
			return responseEmail;
		}

		Usuario usuario = this.usuarioDAO.buscarPorEmail(request.getString("email"));

		if (usuario == null) {
			responseJson.put("operacao", "cadastrarCompetenciaExperiencia");
			responseJson.put("status", 422);
			responseJson.put("mensagem", "Usuário não encontrado");

			return responseJson;
		}

		try {
			JSONArray competenciasArray = request.getJSONArray("competenciaExperiencia");
			String resp = this.competenciaExperienciaDAO.criarCompetenciaExperiencia(competenciasArray, usuario);

			if (!resp.equals("sucesso")) {
				responseJson.put("operacao", "cadastrarCompetenciaExperiencia");
				responseJson.put("status", 422);
				responseJson.put("mensagem", resp);

				return responseJson;
			}

			responseJson.put("operacao", "cadastrarCompetenciaExperiencia");
			responseJson.put("status", 201);
			responseJson.put("mensagem", "Competencia/Experiencia cadastrada com sucesso");
			return responseJson;

		} catch (Exception e) {
			responseJson.put("operacao", "cadastrarCompetenciaExperiencia");
			responseJson.put("status", 422);
			responseJson.put("mensagem", "Erro ao resgatar Competencias");

			return responseJson;
		}
	}

	public JSONObject visualizarCompetencia(JSONObject request) throws SQLException {

		String response = this.usuarioDAO.verificarToken(request);
		if (!response.equals("sucesso")) {
			return new JSONObject(response);
		}

		JSONObject responseJson = new JSONObject();

		boolean hasKeys = ValidarFormulario.checarChaves(request, "email", "token");
		if (!hasKeys) {
			responseJson.put("operacao", "visualizarCompetenciaExperiencia");
			responseJson.put("status", 422);
			responseJson.put("mensagem", "Informe todos os campos");

			return responseJson;
		}

		JSONObject responseEmail;
		if (!(responseEmail = ValidarFormulario.checarEmail(request, "visualizarCompetenciaExperiencia"))
				.getString("mensagem").equals("Sucesso")) {
			return responseEmail;
		}

		Usuario usuario = this.usuarioDAO.buscarPorEmail(request.getString("email"));

		if (usuario == null) {
			responseJson.put("operacao", "visualizarCompetenciaExperiencia");
			responseJson.put("status", 422);
			responseJson.put("mensagem", "Usuário não encontrado");

			return responseJson;
		}

		List<CompetenciaExperiencia> competencias = this.competenciaExperienciaDAO.encontrarCompetenciaExperiencaiPorCandidato(usuario);
		if (competencias == null) {
			responseJson.put("operacao", "visualizarCompetenciaExperiencia");
			responseJson.put("status", 201);
			responseJson.put("competenciaExperiencia", new JSONArray());
			return responseJson;
		}
		responseJson.put("operacao", "visualizarCompetenciaExperiencia");
		responseJson.put("status", 201);
		responseJson.put("competenciaExperiencia", competencias);
		return responseJson;
	}

	public JSONObject excluirCompetencia(JSONObject request) throws SQLException {

		String response = this.usuarioDAO.verificarToken(request);
		if (!response.equals("sucesso")) {
			return new JSONObject(response);
		}

		JSONObject responseJson = new JSONObject();
		boolean hasKeys = ValidarFormulario.checarChaves(request, "email", "competenciaExperiencia");
		if (!hasKeys) {
			responseJson.put("operacao", "excluirCompetenciaExperiencia");
			responseJson.put("status", 422);
			responseJson.put("mensagem", "Informe todos os campos");

			return responseJson;
		}

		JSONObject responseEmail;
		if (!(responseEmail = ValidarFormulario.checarEmail(request, "visualizarCompetenciaExperiencia"))
				.getString("mensagem").equals("Sucesso")) {
			return responseEmail;
		}

		Usuario usuario = this.usuarioDAO.buscarPorEmail(request.getString("email"));

		if (usuario == null) {
			responseJson.put("operacao", "apagarCompetenciaExperiencia");
			responseJson.put("status", 422);
			responseJson.put("mensagem", "Usuário não encontrado");

			return responseJson;
		}

		try {
			JSONArray competenciasArray = request.getJSONArray("competenciaExperiencia");
			JSONObject resp = this.competenciaExperienciaDAO.apagarCompetenciaExperiencia(competenciasArray, usuario);

			responseJson.put("operacao", "apagarCompetenciaExperiencia");
			responseJson.put("status", resp.getInt("status"));
			responseJson.put("mensagem", resp.getString("mensagem"));
			return responseJson;

		} catch (Exception e) {
			responseJson.put("operacao", "apagarCompetenciaExperiencia");
			responseJson.put("status", 422);
			responseJson.put("mensagem", "Erro ao apagar Competencias");

			return responseJson;
		}
	}

	public JSONObject atualizarCompetencia(JSONObject request) throws SQLException {

		String response = this.usuarioDAO.verificarToken(request);
		if (!response.equals("sucesso")) {
			return new JSONObject(response);
		}

		JSONObject responseJson = new JSONObject();
		boolean hasKeys = ValidarFormulario.checarChaves(request, "email", "competenciaExperiencia");
		if (!hasKeys) {
			responseJson.put("operacao", "atualizarCompetenciaExperiencia");
			responseJson.put("status", 422);
			responseJson.put("mensagem", "Informe todos os campos");

			return responseJson;
		}

		JSONObject responseEmail;
		if (!(responseEmail = ValidarFormulario.checarEmail(request, "visualizarCompetenciaExperiencia"))
				.getString("mensagem").equals("Sucesso")) {
			return responseEmail;
		}

		Usuario usuario = this.usuarioDAO.buscarPorEmail(request.getString("email"));

		if (usuario == null) {
			responseJson.put("operacao", "atualizarCompetenciaExperiencia");
			responseJson.put("status", 422);
			responseJson.put("mensagem", "Usuário não encontrado");

			return responseJson;
		}

		try {
			JSONArray competenciasArray = request.getJSONArray("competenciaExperiencia");
			String resp = this.competenciaExperienciaDAO.atualizarCompetenciaExperiencia(competenciasArray,
					usuario.getId());
			
			if(resp.equals("OK")) {
				responseJson.put("operacao", "atualizarCompetenciaExperiencia");
				responseJson.put("status", 201);
				responseJson.put("mensagem", "Competência/Experiênca Atuializado com Sucesso");
				return responseJson;
			}
			
			responseJson.put("operacao", "atualizarCompetenciaExperiencia");
			responseJson.put("status", 422);
			responseJson.put("mensagem", resp);
			return responseJson;

		} catch (Exception e) {
			responseJson.put("operacao", "atualizarCompetenciaExperiencia");
			responseJson.put("status", 422);
			responseJson.put("mensagem", "Erro ao atualizar Competencias");

			return responseJson;
		}
	}

}