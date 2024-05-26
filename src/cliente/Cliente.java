package cliente;

import java.io.*;
import java.net.Socket;

import org.json.*;

public class Cliente {
	
	private String ipServidor;
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;

	public Cliente(String ipServidor) {
        this.ipServidor = ipServidor;
    }

	public boolean connect() {
		try {
			this.socket = new Socket(this.ipServidor, 22222);
			this.out = new PrintWriter(this.socket.getOutputStream(), true);
			this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			System.out.println("Conectado com Sucesso!" + "Ip do Servidor: "+ this.ipServidor);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public String callServer(JSONObject request) {
		try {
			out.println(request.toString());
			return this.in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro ao se comunicar com o servidor";
		}
	}
}