package routes;

import entities.Usuario;
import java.sql.Connection;

import controllers.LoginController;
import controllers.UsuarioController;

import org.json.JSONObject;

public class UsuarioRotas {
    private UsuarioController usuarioController;
    private LoginController loginController;

    public UsuarioRotas(Connection conn) {
        this.usuarioController = new UsuarioController(new Usuario(), conn);
        this.loginController = new LoginController(conn);
    }

    public JSONObject handleRequest(JSONObject requisicao) {
        return switch (requisicao.getString("operacao")) {
            case "cadastrarCandidato" -> this.usuarioController.cadastrarCandidato(requisicao);
            case "atualizarCandidato" -> this.usuarioController.editarCandidato(requisicao);
            case "apagarCandidato" -> this.usuarioController.excluirCandidato(requisicao);
            case "visualizarCandidato" -> this.usuarioController.buscarPorEmail(requisicao);
            case "loginCandidato" -> this.loginController.loginCandidato(requisicao);
            case "logout" -> this.loginController.logoutCandidato(requisicao);
            default -> new JSONObject().put("status", "error").put("message", "ROTA NÃO ENCONTRADA");
        };
    }
}
