package com.codeages.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

	final CyclicBarrier barrier;
	final int MAX_TASK;

	public CyclicBarrierTest(int cnt) {
		barrier = new CyclicBarrier(cnt + 1);
		MAX_TASK = cnt;
	}

	public void doWork(final Runnable work) {
		new Thread() {

			public void run() {
				work.run();
				try {
					int index = barrier.await();
					doWithIndex(index);
				} catch (InterruptedException e) {
					return;
				} catch (BrokenBarrierException e) {
					return;
				}
			}
		}.start();
	}

	private void doWithIndex(int index) {
		if (index == MAX_TASK / 3) {
			System.out.println("Left 30%.");
		} else if (index == MAX_TASK / 2) {
			System.out.println("Left 50%");
		} else if (index == 0) {
			System.out.println("run over");
		}
	}

	public void waitForNext() {
		try {
			doWithIndex(barrier.await());
		} catch (InterruptedException e) {
			return;
		} catch (BrokenBarrierException e) {
			return;
		}
	}

	public static void main(String[] args) {
		final int count = 10;
		CyclicBarrierTest demo = new CyclicBarrierTest(count);
		for (int i = 0; i < 100; i++) {
			demo.doWork(new Runnable() {

				public void run() {
					try {
						Thread.sleep(1000L);
					} catch (Exception e) {
						return;
					}
				}
			});
			if ((i + 1) % count == 0) {
				demo.waitForNext();
			}
		}
	}

}