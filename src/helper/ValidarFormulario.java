package helper;

import org.json.JSONException;
import org.json.JSONObject;

public class ValidarFormulario {
	
	 public static JSONObject checarEmail(JSONObject request, String operacao) {
		    JSONObject response = new JSONObject();

		    try {
		        if (!validarEmail(request.getString("email"))) {
		            response.put("operacao", operacao);
		            response.put("status", 404);
		            response.put("mensagem", "Email inválido");

		            return response;
		        }
		    } catch (JSONException ex) {
		        response.put("operacao", operacao);
		        response.put("status", 404);
		        response.put("mensagem", "Email deve ser uma string");

		        return response;
		    }

		    response.put("operacao", operacao);
		    response.put("status", 200);
		    response.put("mensagem", "Sucesso");

		    return response;
		}


	 public static JSONObject checarNome(JSONObject request, String operacao) {
	        JSONObject response = new JSONObject();

	        try {
	            if (request.getString("nome") == null || request.getString("nome").length() < 6 || request.getString("nome").length() >= 30) {
	                response.put("operacao", operacao);
	                response.put("status", 404);
	                response.put("mensagem", "Nome do usuário inválido");
	                return response;
	            }
	        } catch (JSONException ex) {
	            try {
	                response.put("operacao", operacao);
	                response.put("status", 404);
	                response.put("mensagem", "Nome deve ser uma string");
	            } catch (JSONException e) {
	                e.printStackTrace();
	            }
	        }
	        return response;
	    }

    public static JSONObject checarSenha(JSONObject request, String operacao) {
        JSONObject response = new JSONObject();

        try {
            String senha = String.valueOf(request.getString("senha"));
            if (senha == null || senha.length() < 3 || senha.length() >= 8) {
                response.put("operacao", operacao);
                response.put("status", 404);
                response.put("mensagem", "Senha deve ser de 3 a 8 caracteres");
                return response;
            }
        } catch (JSONException ex) {
            try {
                response.put("operacao", operacao);
                response.put("status", 404);
                response.put("mensagem", "Senha deve ser numérica");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    public static boolean checarChaves(JSONObject request, String... keys) {
        for (String key : keys) {
            if (!request.has(key)) {
                return false;
            }
        }

        return true;
    }

    public static boolean validarEmail(String email) {
        if (email == null || email.length() < 7 || email.length() >= 50) {
            return false;
        }
        String validacaoEmail = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(validacaoEmail);
    }
	
	
}
