package com.codeages.thread;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

	public static void main(String[] args) throws Exception {
		System.out.println("the game cost "+new CountDownLatchTest().timecost(10));
	}

	public long timecost(final int times) throws InterruptedException {
		if (times <= 0)
			throw new IllegalArgumentException();
		final CountDownLatch startLatch = new CountDownLatch(1);
		final CountDownLatch overLatch = new CountDownLatch(times);
		for (int i = 0; i < times; i++) {
			new Runner(startLatch,overLatch,i).start();
		}
		long start = System.nanoTime();
		startLatch.countDown();
		overLatch.await();
		return System.nanoTime() - start;
	}
}

class Runner extends Thread {
	CountDownLatch startLatch;
	CountDownLatch overLatch;
	int i = 0;

	public Runner(CountDownLatch startLatch, CountDownLatch overLatch, int num) {
		this.startLatch = startLatch;
		this.overLatch = overLatch;
		this.i = num;
	}

	public void run() {
		try {
			startLatch.await();
			System.out.println("NO [" + i + "] running!!! ");
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		} finally {
			overLatch.countDown();
		}
	}
}