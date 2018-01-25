package com.codeages.thread;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipedStreamTest {
	public static void main(String[] args) {
		Sender t1 = new Sender();
		Receiver t2 = new Receiver();
		PipedOutputStream out = t1.getOutputStream();
		PipedInputStream in = t2.getInputStream();
		try {
			out.connect(in); // 连接
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		t1.start();
		t2.start();
	}
}

class Receiver extends Thread {
	private PipedInputStream in = new PipedInputStream();

	public PipedInputStream getInputStream() {
		return in;
	}

	public void run() {
		byte[] buf = new byte[1024];
		try {
			int len = in.read(buf);
			System.out.println("The following message comes from Sender:");
			System.out.println(new String(buf, 0, len));
			in.close();
			
			System.out.println(new String(buf, 0, len));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Sender extends Thread {
	private PipedOutputStream out = new PipedOutputStream();

	public PipedOutputStream getOutputStream() {
		return out;
	}

	public void run() {
		String strInfo = new String("Hello,Reciever!");
		try {
			out.write(strInfo.getBytes());
			
			sleep(10000);
			
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}