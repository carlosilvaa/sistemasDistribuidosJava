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
            if (senha == null || senha.length() < 3 || senha.length() > 8) {
                response.put("operacao", operacao);
                response.put("status", 404);
                response.put("mensagem", "Senha deve ser de 3 a 8 caracteres");
                return response;
            }
        } catch (JSONException ex) {
            try {
                response.put("operacao", operacao);
                response.put("status", 404);
                response.put("mensagem", "Senha deve ser uma string");
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
    
    public static JSONObject checarCnpj(JSONObject request, String operacao) {
        JSONObject responseJson = new JSONObject();

        try {
        	if (!validarCNPJ(request.getString("cnpj"))) {
        		responseJson.put("operacao", operacao);
        		responseJson.put("status", 404);
        		responseJson.put("mensagem", "CNPJ inválido");

	            return responseJson;
	        }
        } catch (JSONException ex) {
            responseJson.put("operacao", operacao);
            responseJson.put("status", 404);
            responseJson.put("mensagem", "Cnpj deve ser String");

            return responseJson;
        }

        return responseJson;
    }

    public static boolean validarEmail(String email) {
        if (email == null) {
            return false;
        }
        String validacaoEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (email.length() < 7 || email.length() >= 50) {
            return false;
        }
        return email.matches(validacaoEmail);
    }
    
    public static boolean validarCNPJ(String cnpj) {
    	if(cnpj == null) {
    		return false;
    	}
    	String regex = "^[0-9]{14}$";

        if (cnpj == null || !cnpj.matches(regex)) {
            return false;
        }
        return cnpj.matches(regex);
    }
	
	
}
