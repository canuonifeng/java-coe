package com.codeages.thread;

public class ThreadTest {

	public static int index = 0;
	public static String a = "abc";

	public static void main(String[] args) {
		// for (int i = 0; i < 10; i++) {
		// Thread t1 = new Thread(new Thread1(i));
		// t1.start();
		// }

		Thread t1 = new Thread(new Thread1(0));
		t1.start();

		Thread t2 = new Thread2();
		t2.start();
		System.out.println("main running!!!");
		
		
		
		try {
			Thread.sleep(2000);
			synchronized (ThreadTest.a) {
				ThreadTest.a.notify();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread1 implements Runnable {

	private int num;

	public Thread1(int num) {
		this.num = num;
	}

	@Override
	public void run() {
		try {
			synchronized (ThreadTest.a) {
				ThreadTest.a.wait(5000);
				ThreadTest.index = ThreadTest.index + 1;
				System.out.println("thread" + num + " running!!!" + "index: " + ThreadTest.index);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

class Thread2 extends Thread {

	public int abc = 1;

	@Override
	public void run() {
		try {
			Thread.sleep(2000);
			synchronized (ThreadTest.a) {
				System.out.println("thread2 running!!!");
//				notify();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
