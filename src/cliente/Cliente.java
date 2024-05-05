package cliente;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import org.json.*;

public class Cliente {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o IP do servidor:");
		String ipServidor = scanner.nextLine();
		
		try (Socket socket = new Socket(ipServidor, 22222)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            System.out.println("Conectado ao servidor");
            
            System.out.println("Digite o numero da operacao:");
            System.out.println("1- Cadastrar Candidato");
            System.out.println("2- Visualizar Candidato ");
            System.out.println("3- Atualizar Candidato");
            System.out.println("4- Apagar Candidato");
            System.out.println("5- Login Candidato");
            System.out.println("6- Logout Candidato");
            
            String token = "";

            while (true) {
                switch (Integer.parseInt(scanner.nextLine())) {
                    case 1 -> {
                        // Testando cadastro
                        JSONObject json = new JSONObject();
                        json.put("operacao", "cadastrarCandidato");
                        json.put("nome", "Pedrooo");
                        json.put("email", "pedro@gmail.com");
                        json.put("senha", 1234);

                        out.println(json.toString());
                        System.out.println("Resposta do servidor: " + in.readLine());
                    }
                    case 2 -> {
                        // Testando visualizarCandidato
                        JSONObject json = new JSONObject();
                        json.put("operacao", "visualizarCandidato");
                        json.put("email", "pedro@gmail.com");

                        out.println(json.toString());
                        System.out.println("Resposta do servidor: " + in.readLine());
                    }
                    case 3 -> {
                        // Testando atualizarCandidato
                        JSONObject json = new JSONObject();
                        json.put("operacao", "atualizarCandidato");
                        json.put("nome", "UsuarioAtualizado");
                        json.put("email", "pedro@gmail.com");
                        json.put("senha", 12345);

                        out.println(json.toString());
                        System.out.println("Resposta do servidor: " + in.readLine());
                    }
                    case 4 -> {
                        // Testando apagarCandidato
                        JSONObject json = new JSONObject();
                        json.put("operacao", "apagarCandidato");
                        json.put("email", "pedro@gmail.com");

                        out.println(json.toString());
                        System.out.println("Resposta do servidor: " + in.readLine());
                    }
                    case 5 -> {
                        // Testando loginCandidato
                        JSONObject json = new JSONObject();
                        json.put("operacao", "loginCandidato");
                        json.put("email", "pedro@gmail.com");
                        json.put("senha", 1234);
                        
                        out.println(json.toString());
                        System.out.println("Resposta do servidor: " + in.readLine());
                        
                        try {
                            token = new JSONObject(json).getString("token");
                        } catch (JSONException ex) {
                            System.out.println("Erro ao tentar resgatar token");
                        }
                    }
                    case 6 -> {
                        // Testando logout
                        if (!token.isEmpty()) {
                            JSONObject json = new JSONObject();
                            json.put("operacao", "logout");
                            json.put("token", token);

                            out.println(json.toString());
                            System.out.println("Resposta do servidor: " + in.readLine());
                        } else {
                            System.out.println("Token não disponível. Faça login primeiro.");
                        }
                    }
                    case 7 -> {
                        out.println("exit");
                        System.out.println("Desconectando...");
                        break;
                    }
                    default -> {
                        out.println("exit");
                        System.out.println("Opção não encontrada");
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
