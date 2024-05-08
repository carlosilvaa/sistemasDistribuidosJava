package routes;

import entities.Usuario;
import java.sql.Connection;
import controllers.LoginController;
import controllers.UsuarioController;

import org.json.JSONException;
import org.json.JSONObject;

public class UsuarioRotas {
    private UsuarioController usuarioController;
    private LoginController loginController;
    private JSONObject request;

    public UsuarioRotas(Connection conn, JSONObject request) {
    	this.request = request;
        this.usuarioController = new UsuarioController(new Usuario(), conn, this.request);
        this.loginController = new LoginController(conn, this.request);
    }

    public JSONObject handleRequest(){
    	try {
    		return switch (this.request.getString("operacao")) {
            case "cadastrarCandidato" -> this.usuarioController.cadastrarCandidato(this.request);
            case "atualizarCandidato" -> this.usuarioController.editarCandidato(this.request);
            case "apagarCandidato" -> this.usuarioController.excluirCandidato(this.request);
            case "visualizarCandidato" -> this.usuarioController.buscarPorEmail(this.request);
            case "loginCandidato" -> this.loginController.loginCandidato(this.request);
            case "logout" -> this.loginController.logoutCandidato(this.request);
            default ->new JSONObject().put("status", 404).put("message", "ROTA NÃO ENCONTRADA");
    		};
    	}catch(JSONException ex) {
    		return new JSONObject().put("status", 404).put("message", "operacao nao encontrada");
    	}
        
    }
}
