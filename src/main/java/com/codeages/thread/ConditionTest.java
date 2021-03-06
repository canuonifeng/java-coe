package com.codeages.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest<T> {
	private final T[] items;
	private final Lock lock = new ReentrantLock();
	private Condition notFull = lock.newCondition();
	private Condition notEmpty = lock.newCondition();
	private int head, tail, count;

	public ConditionTest(int maxSize) {
		items = (T[]) new Object[maxSize];
	}

	public ConditionTest() {
		this(10);
	}

	public void put(T t) throws InterruptedException {
		lock.lock();
		try {
			while (count == getCapacity()) {
				notFull.await();
			}
			items[tail] = t;
			if (++tail == getCapacity()) {
				tail = 0;
			}
			++count;
			notEmpty.signalAll();
		} finally {
			lock.unlock();
		}
	}

	public T pop() throws InterruptedException {
		lock.lock();
		try {
			while (count == 0) {
				notEmpty.await();
			}
			T ret = items[head];
			items[head] = null;
			if (++head == getCapacity()) {
				head = 0;
			}
			--count;
			notFull.signalAll();
			return ret;
		} finally {
			lock.unlock();
		}
	}

	public int getCapacity() {
		return items.length;
	}

	public int size() {
		lock.lock();
		try {
			return count;
		} finally {
			lock.unlock();
		}
	}

}