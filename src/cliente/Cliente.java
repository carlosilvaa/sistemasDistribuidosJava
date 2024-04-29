	package cliente;
	
	import java.io.*;
	import java.net.Socket;
	import org.json.*;
	
public class Cliente {
	public static void main(String[] args) {
		 try (Socket socket = new Socket("localhost", 22222)) {
	            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	            
	            System.out.println("Conectado ao servidor");
	            
	            JSONObject json = new JSONObject();
	            json.put("operacao", "logout");
	            //json.put("nome", "Pedrooo");
//	            json.put("email", "pedro@gmail.com");
//	            json.put("senha", 1234);
	            //json.put("token", "a31a8b90-612e-4f7d-935b-5ff70b5f4b51");
	            
	            out.println(json.toString());
	            System.out.println("Resposta do servidor: " + in.readLine());	
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();		
			}
    }
}
		