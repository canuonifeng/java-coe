package com.codeages.thread;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {
	public static void main(String[] args) {
		final Queue q3 = new Queue();
		for (int i = 0; i < 3; i++) {
			new Thread() {
				public void run() {
					while (true) {
						q3.get();
					}
				}

			}.start();
		}
		for (int i = 0; i < 3; i++) {
			new Thread() {

				public void run() {
					while (true) {
						q3.put(new Random().nextInt(10000));
					}
				}

			}.start();
		}
	}
}

class Queue {
	private Object data = null;
	private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

	public void get() {
		rwl.readLock().lock();
		System.out.println(Thread.currentThread().getName() + " be ready to read data!");
		try {
			Thread.sleep((long) (Math.random() * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "have read data :" + data);
		rwl.readLock().unlock();
	}

	public void put(Object data) {

		rwl.writeLock().lock();
		System.out.println(Thread.currentThread().getName() + " be ready to write data!");
		try {
			Thread.sleep((long) (Math.random() * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.data = data;
		System.out.println(Thread.currentThread().getName() + " have write data: " + data);

		rwl.writeLock().unlock();
	}
}