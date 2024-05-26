package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Servidor {
	public static void main(String[] args) throws SQLException {
		try (ServerSocket serverSocket = new ServerSocket(22222)) {
			System.out.println("Servidor iniciado na porta 22222.");
			String url = "jdbc:mysql://localhost:3306/sistemasdistribuidos";
			String user = "root";
			String password = "umasenhaponto";
			Connection conn = DriverManager.getConnection(url, user, password);
			while (true) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("Cliente conectado.");
				new ClientHandler(clientSocket, conn).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
