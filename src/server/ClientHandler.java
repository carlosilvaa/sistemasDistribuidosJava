
package server;
import java.io.*;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;

import org.json.JSONObject;
import routes.UsuarioRotas;

public class ClientHandler extends Thread {

    private Socket clientSocket;
    private Connection conn;

    public ClientHandler(Socket socket, Connection conn) {
        this.clientSocket = socket;
        this.conn = conn;
    }
    
    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String line;
            UsuarioRotas usuarioRotas = new UsuarioRotas(this.conn);
            while ((line = in.readLine()) != null) {
                System.out.println("Recebido do cliente: " + line);
                if ("exit".equalsIgnoreCase(line)) {
                    out.println("Conexão encerrada.");
                    break;
                }
                JSONObject response = usuarioRotas.handleRequest(new JSONObject(line));
                System.out.println(response);
                out.println(response.toString());
            }
        } catch (IOException | SQLException e ) {
        	System.out.println("Cliente Desconectado!");
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}