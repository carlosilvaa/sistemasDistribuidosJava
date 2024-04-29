package controllers;

import entities.Usuario;
import exceptions.InvalidLogin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import dao.UsuarioDAO;

public class UsuarioController {
    
    private Usuario usuario;
    private UsuarioDAO usuarioDAO;

    public UsuarioController(Usuario usuario, Connection conn) {
        this.usuario = usuario;
        this.usuarioDAO = new UsuarioDAO(conn);
    }
    public UsuarioController() {}
    
    public JSONObject cadastrarCandidato(JSONObject json) {
    	
        String nome = json.getString("nome");
        int senha = json.getInt("senha");
        String email = json.getString("email");

        if (nome == null || nome.trim().isEmpty()) {
            return errorResponse("cadastrarCandidato", "Nome é obrigatório");
        }
        if (email == null || email.trim().isEmpty()) {
            return errorResponse("cadastrarCandidato", "Email é obrigatório");
        }
        if (senha <= 0) {
            return errorResponse("cadastrarCandidato", "Senha é obrigatória");		
        }

        usuario.setNome(nome);
        usuario.setSenha(senha);
        usuario.setEmail(email);
        
        try {
            usuarioDAO.cadastrarCandidato(usuario);
            return successResponse("cadastrarCandidato", "Candidato cadastrado com sucesso!");
        } catch (SQLException e) {
            return errorResponse("cadastrarCandidato", "Erro ao cadastrar candidato", 500);
        }
    }
     
    public JSONObject realizarLogin(JSONObject json) {
        String nome = json.getString("nome");
        int senha = json.getInt("senha");

        if (nome == null || nome.trim().isEmpty()) {
            return errorResponse("realizarLogin", "Nome é obrigatório");
        }
        if (senha <= 0) {
            return errorResponse("realizarLogin", "Senha é obrigatória");
        }

        try {
            usuario.realizarLogin(nome, senha);
            return successResponse("realizarLogin", "Login realizado com sucesso!", "UUID");
        } catch (InvalidLogin e) {
            return errorResponse("realizarLogin", "Login ou senha incorretos", 401);
        }
    }
    
    public JSONObject excluirCandidato(JSONObject json) {
        String email = json.getString("email");

        if (email == null || email.trim().isEmpty()) {
            return errorResponse("excluirCandidato", "Email do candidato é obrigatório");
        }

        try {
            usuarioDAO.excluirCandidato(email);
            return successResponse("excluirCandidato", "Candidato excluído com sucesso!");
        } catch (SQLException e) {
            return errorResponse("excluirCandidato", "Erro ao excluir candidato", 500);
        }
    }
    
    public JSONObject editarCandidato(JSONObject json) {
        String nome = json.getString("nome");
        int senha = json.getInt("senha");
        String email = json.getString("email");

        if (nome == null || nome.trim().isEmpty()) {
            return errorResponse("editarCandidato", "Nome é obrigatório");
        }
        if (email == null || email.trim().isEmpty()) {
            return errorResponse("editarCandidato", "Email é obrigatório");
        }
        if (senha <= 0) {
            return errorResponse("editarCandidato", "Senha é obrigatória");
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
            e.printStackTrace();
            return errorResponse("editarCandidato", "Erro ao editar candidato", 500);
        } catch (Exception ex) {
            ex.printStackTrace();	
            return errorResponse("editarCandidato", "Erro ao editar candidato", 500);
        }	
    }

    public JSONObject buscarTodos() {
        try {
            List<Usuario> usuarios = usuarioDAO.buscarTodos();
            JSONArray jsonArray = new JSONArray();
            for (Usuario usuario : usuarios) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", usuario.getId());
                jsonObject.put("nome", usuario.getNome());
                jsonObject.put("senha", usuario.getSenha());
                jsonArray.put(jsonObject);
            }
            return successResponse("buscarTodosUsuarios", jsonArray.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            return errorResponse("buscarTodosUsuarios", "Erro ao buscar todos os usuários", 500);
        }
    }

    public JSONObject buscarPorEmail(JSONObject json) {
        String email = json.getString("email");

        if (email == null || email.isEmpty()) {
            return errorResponse("buscarUsuarioPorEmail", "Email do usuário é obrigatório");
        }

        try {
        	Usuario usuario = usuarioDAO.buscarPorEmail(email);

            if (usuario != null) {
                JSONObject jsonUsuario = new JSONObject();
                jsonUsuario.put("nome", usuario.getNome());
                jsonUsuario.put("email", usuario.getEmail());
                return successResponse("buscarPorEmail", jsonUsuario.toString());
            } else {
                return errorResponse("buscarPorEmail", "Nenhum usuário encontrado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return errorResponse("buscarPorEmail", "Erro ao buscar usuário", 500);
        }
    }
    
    private JSONObject successResponse(String operacao, String message) {
        JSONObject response = new JSONObject();
        response.put("operacao", operacao);
        response.put("status", 200);
        response.put("mensagem", message);
        return response;
    }

    private JSONObject successResponse(String operacao, String message, String token) {
        JSONObject response = new JSONObject();
        response.put("operacao", operacao);
        response.put("status", 200);
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