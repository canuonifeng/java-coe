package com.codeages.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketService {

	public static void main(String[] args) throws IOException {
		SocketService socketService = new SocketService();
		socketService.oneServer();
	}

	public void oneServer() {
		try {
			ServerSocket server = new ServerSocket(5209);
			System.out.println("服务器启动成功");
			Socket socket = server.accept();

			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String line = br.readLine();
			while (!line.equals("end")) {
				writer.println(line);
				writer.flush();
				System.out.println("Server:" + line);
				System.out.println("Client:" + in.readLine());
				line = br.readLine();
			}

			writer.close();
			in.close();
			socket.close();
			server.close();
		} catch (Exception e) {
			System.out.println("Error." + e);
		}
	}
}