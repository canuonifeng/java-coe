package com.codeages.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
	public static Integer i = 0;
	public static Lock lock = new ReentrantLock();

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new ReentrantLockThread().start();
		}
	}
}

class ReentrantLockThread extends Thread {
	@Override
	public void run() {

		ReentrantLockTest.lock.lock();

		// synchronized (ReentrantLockTest.i) {
		ReentrantLockTest.i++;
		System.out.println(ReentrantLockTest.i);
		// }

		ReentrantLockTest.lock.unlock();

	}
}
