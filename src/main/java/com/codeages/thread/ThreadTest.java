package com.codeages.thread;

public class ThreadTest {

	public static ThreadLocal<String> str = new ThreadLocal<String>();

	public static void main(String[] args) throws Exception {
		ThreadTest.str.set("yao ge");

		Thread t1 = new Thread(new RunableThread());
		t1.start();
		
		Thread t2 = new ThreadExample();
		t2.start();
		
		
	}
}

class RunableThread implements Runnable {
	@Override
	public void run() {
		System.out.println("I am a runable thread!");
	}
}

class ThreadExample extends Thread {

	@Override
	public void run() {
		try {
			Thread.sleep(100);
			System.out.println("I am a thread!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
