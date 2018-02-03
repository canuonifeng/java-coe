package com.codeages.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {
	public static int num = 0;
	public static AtomicInteger atomicNum = new AtomicInteger(0);

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new IntThread().start();
		}
		
		for (int i = 0; i < 10; i++) {
			new AtomicThread().start();
		}
	}
}

class AtomicThread extends Thread {
	@Override
	public void run() {
		try {
			sleep(10);
			AtomicTest.atomicNum.incrementAndGet();
			System.out.println("AtomicTest.atomicNum: " + AtomicTest.atomicNum.intValue());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class IntThread extends Thread {
	@Override
	public void run() {
		try {
			sleep(10);
			AtomicTest.num++;
			System.out.println("AtomicTest.num: " + AtomicTest.num);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
