package com.codeages.thread;

public class HappenBeforeTest {

	static int x = 0;
	static int y = 0, m, n;

	public static void main(String[] args) throws InterruptedException {
		int count = 10;
		for (int i = 0; i < count; ++i) {
			x = y = m = n = 0;

			Thread one = new Thread() {
				public void run() {
					m = 1;
					x = n;
				};
			};

			Thread two = new Thread() {
				public void run() {
					n = 1;
					y = m;
				};
			};

			one.start();
			two.start();
			one.join();
			two.join();

			System.out.println("index:" + i + " {x:" + x + ",y:" + y + "}");
		}
	}
}