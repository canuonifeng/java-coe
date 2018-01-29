package com.codeages.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketService {

	public static void main(String[] args) throws IOException {
		try {
			ServerSocket server = new ServerSocket(5209);
			System.out.println("服务器启动成功");
			
			Socket socket = server.accept();
			System.out.println("收到一个连接！！！！");

			InputStream in = socket.getInputStream();
			OutputStream outputStream = System.out;
			
			int inRead = 0;
			while((inRead = in.read()) >= 0) {
				outputStream.write(inRead);
			}
			
			outputStream.flush();
			outputStream.close();
			
			OutputStream out = socket.getOutputStream();
			PrintWriter printWriter = new PrintWriter(out);
			printWriter.write("i get!!!!");
			printWriter.flush();
			printWriter.close();
			out.close();
			
			in.close();
			socket.close();
			server.close();
		} catch (Exception e) {
			System.out.println("Error." + e);
		}
	}
}