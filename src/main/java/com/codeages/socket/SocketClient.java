package com.codeages.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {
    // 搭建客户端
    public static void main(String[] args) throws IOException {
        try {
            Socket socket = new Socket("127.0.0.1", 5209);
            System.out.println("客户端启动成功");
			
			OutputStream out = socket.getOutputStream();
			PrintWriter printWriter = new PrintWriter(out);
			printWriter.write("sender message!!!");
			printWriter.flush();
			printWriter.close();
			
			InputStream in = socket.getInputStream();
			OutputStream outputStream = System.out;
			int inRead = 0;
			while((inRead = in.read()) >= 0) {
				outputStream.write(inRead);
			}
			outputStream.flush();
			outputStream.close();
			
			out.close();
			in.close();
			socket.close();
        } catch (Exception e) {
            System.out.println("can not listen to:" + e);// 出错，打印出错信息
        }
    }

}