package routes;

import entities.Usuario;
import java.sql.Connection;

import controllers.EmpresaController;
import controllers.LoginController;
import controllers.UsuarioController;

import org.json.JSONException;
import org.json.JSONObject;

public class UsuarioRotas {
	private UsuarioController usuarioController;
	private EmpresaController empresaController;
	private LoginController loginController;
	private String usuario;
	
	public UsuarioRotas(Connection conn) {
		this.usuarioController = new UsuarioController(new Usuario(), conn);
		this.empresaController = new EmpresaController(conn);
		this.loginController = new LoginController(conn);
	}

	public JSONObject handleRequest(JSONObject request) {
		try {
			return switch (request.getString("operacao")) {
			// Rodas Candidato
			case "cadastrarCandidato" -> this.usuarioController.cadastrarCandidato(request);
			case "atualizarCandidato" -> this.usuarioController.editarCandidato(request);
			case "apagarCandidato" -> this.usuarioController.excluirCandidato(request);
			case "visualizarCandidato" -> this.usuarioController.buscarPorEmail(request);
			
			// Rotas Empresa

			case "cadastrarEmpresa" -> this.empresaController.cadastrarEmpresa(request);
			case "atualizarEmpresa" -> this.empresaController.editarEmpresa(request);
			case "apagarEmpresa" -> this.empresaController.excluirEmpresa(request);
			case "visualizarEmpresa" -> this.empresaController.buscarEmpresaPorEmail(request);

			// Rotas login
			
			case "loginCandidato" -> { this.usuario = "Candidato"; yield this.loginController.loginCandidato(request); }
			case "loginEmpresa" -> {this.usuario = "Empresa"; yield this.loginController.loginEmpresa(request);}
			case "logout" -> this.usuario.equals("Candidato")? this.loginController.logoutCandidato(request): this.loginController.logoutEmpresa(request);
			
			default -> new JSONObject().put("status", 404).put("message", "ROTA NÃO ENCONTRADA");
			};
		} catch (JSONException ex) {
			return new JSONObject().put("status", 404).put("message", "operacao nao encontrada");
		}

	}
}
