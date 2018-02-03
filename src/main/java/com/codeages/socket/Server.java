package com.codeages.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(8080);
			// 等待请求
			while (true) {
				Socket socket = server.accept();
				new TestThread(socket).start();
			}

			// server.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class TestThread extends Thread {
	Socket socket;

	public TestThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String line = is.readLine();
			System.out.println("received from client:" + line);
			// 创建PrintWriter，用于发送数据
			PrintWriter pw = new PrintWriter(socket.getOutputStream());
			pw.println("this data is from server");
			pw.flush();
			// 关闭资源
			pw.close();
			is.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}