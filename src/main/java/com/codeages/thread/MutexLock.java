package com.codeages.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author zs 排它锁
 */
public class MutexLock implements Lock {

	// 通常使用静态内部类，实现自定义同步器
	private static class Sync extends AbstractQueuedSynchronizer {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 当前线程是否独占这个锁
		 */
		protected boolean isHeldExclusively() {
			return getExclusiveOwnerThread() == Thread.currentThread();
		}

		/**
		 * 获取锁 0:unlocked; 1:locked
		 */
		protected boolean tryAcquire(int arg) {
			if (compareAndSetState(0, 1)) {
				setExclusiveOwnerThread(Thread.currentThread());
				return true;
			}
			return false;
		}

		/**
		 * 释放锁
		 */
		protected boolean tryRelease(int arg) {
			if (getState() == 0) {
				throw new IllegalMonitorStateException("锁未被当前线程占用");
			}
			setExclusiveOwnerThread(null);// 置为null表示锁未被任何线程占用
			setState(0);
			return true;
		}

		/**
		 * 返回一个Condition，类似Lock实现中的Condition：await()&& signal()&&signalAll()
		 * 
		 * @return
		 */
		protected Condition newCondition() {
			return new ConditionObject();
		}
	}

	// Sync 其实就是个AQS（继承关系），这个Sync对象为使用者屏蔽了锁的实现，
	// 使用者只需要通过组合使用这个sync来实现锁的使用；
	private final Sync sync = new Sync();

	@Override
	public void lock() {
		sync.acquire(1);// AQS独占式获取锁的模版方法
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		sync.acquireInterruptibly(1);// AQS独占式可响应中断 获取锁的模版方法
	}

	@Override
	public boolean tryLock() {
		return sync.tryAcquire(1);
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return sync.tryAcquireNanos(1, unit.toNanos(time));
	}

	@Override
	public void unlock() {
		sync.release(0);
	}

	@Override
	public Condition newCondition() {
		return sync.newCondition();
	}

	/**
	 * 当前线程是否独占锁
	 */
	public boolean isLocked() {
		return sync.isHeldExclusively();
	}

	/**
	 * FIFO队列中是否有等待获取锁的 线程
	 */
	public boolean hasQueuedThreads() {
		return sync.hasQueuedThreads();
	}

	public static void main(String[] args) {
		final MutexLock mutexLock = new MutexLock();
		// ---------------------------------Task one:
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (!Thread.interrupted()) {
					mutexLock.lock();
					try {
						System.out.println(Thread.currentThread().getName() + " acquired successfully!");
						TimeUnit.SECONDS.sleep(2);
						System.out.println(Thread.currentThread().getName() + " done!");
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						mutexLock.unlock();
					}
					break;
				}
			}
		}, "Task one").start();
		// --------------------------------- Task two:
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (!Thread.interrupted()) {
					mutexLock.lock();
					try {
						System.out.println(Thread.currentThread().getName() + " acquired successfully!");
						TimeUnit.SECONDS.sleep(30);
						System.out.println(Thread.currentThread().getName() + " done!");

					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						mutexLock.unlock();
					}
					break;
				}
			}
		}, "Task two").start();
		// --------------------------------- Task three:
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (!Thread.interrupted()) {
					mutexLock.lock();
					try {
						System.out.println(Thread.currentThread().getName() + " acquired successfully!");
						TimeUnit.SECONDS.sleep(30);
						System.out.println(Thread.currentThread().getName() + " done!");

					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						mutexLock.unlock();
					}
					break;
				}
			}
		}, "Task three").start();
	}

}