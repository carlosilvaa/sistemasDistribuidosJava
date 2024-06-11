package routes;

import entities.Usuario;
import java.sql.Connection;
import java.sql.SQLException;

import controllers.*;

import org.json.JSONException;
import org.json.JSONObject;

public class UsuarioRotas {
    private UsuarioController usuarioController;
    private EmpresaController empresaController;
    private LoginController loginController;
    private CompetenciaExperienciaController competenciaExperienciaController;
    private VagaController vagaController;
    private String usuario;

    public UsuarioRotas(Connection conn) {
        this.usuarioController = new UsuarioController(new Usuario(), conn);
        this.empresaController = new EmpresaController(conn);
        this.loginController = new LoginController(conn);
        this.competenciaExperienciaController = new CompetenciaExperienciaController(conn);
        this.vagaController = new VagaController(conn);
    }

    public JSONObject handleRequest(JSONObject request) throws SQLException {
        try {
            String token = request.optString("token");

            return switch (request.getString("operacao")) {
                // Rotas Candidato
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
                case "loginEmpresa" -> { this.usuario = "Empresa"; yield this.loginController.loginEmpresa(request); }
                case "logout" -> this.usuario.equals("Candidato") ? this.loginController.logoutCandidato(request) : this.loginController.logoutEmpresa(request);
                
                // --> Rotas para COMPETENCIA
                case "cadastrarCompetenciaExperiencia" -> this.competenciaExperienciaController.cadastrarCompetencia(request);
                case "visualizarCompetenciaExperiencia" -> this.competenciaExperienciaController.visualizarCompetencia(request);
                case "atualizarCompetenciaExperiencia" -> this.competenciaExperienciaController.atualizarCompetencia(request);
                case "apagarCompetenciaExperiencia" -> this.competenciaExperienciaController.excluirCompetencia(request);

                // --> Rotas para VAGA'
                case "cadastrarVaga" -> this.vagaController.cadastrarVaga(request);
                case "visualizarVaga" -> this.vagaController.visualizarVaga(request);
                case "atualizarVaga" -> this.vagaController.atualizarVaga(request);
                case "apagarVaga" -> this.vagaController.apagarVaga(request);
                case "listarVagas" -> this.vagaController.listarVagas(request);
                case "filtrarVagas" -> this.vagaController.filtrarVagas(request);

                default -> new JSONObject().put("status", 404).put("message", "ROTA NÃO ENCONTRADA");
            };
        } catch (JSONException ex) {
            return new JSONObject().put("status", 404).put("message", "operacao nao encontrada");
        }
    }
}
