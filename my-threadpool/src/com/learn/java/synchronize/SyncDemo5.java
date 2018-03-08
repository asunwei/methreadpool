/**
 * 
 */
package com.learn.java.synchronize;

/**
 * @author wei.sun02
 *
 */
public class SyncDemo5 implements Runnable {

	private static int count;

	public SyncDemo5() {
		count = 0;
	}

	public synchronized static void method() {
		for (int i = 0; i < 5; i++) {
			try {
				System.out.println(Thread.currentThread().getName() + ":" + (count++));
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public synchronized void run() {
		method();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * syncThread1和syncThread2是SyncThread的两个对象，
		 * 但在thread1和thread2并发执行时却保持了线程同步。这是因为run中调用了静态方法method，
		 * 而静态方法是属于类的，所以syncThread1和syncThread2相当于用了同一把锁。这与Demo1是不同的。
		 */
		SyncDemo5 demo1 = new SyncDemo5();
		SyncDemo5 demo2 = new SyncDemo5();
		Thread thread1 = new Thread(demo1, "SyncThread1");
		Thread thread2 = new Thread(demo2, "SyncThread2");
		thread1.start();
		thread2.start();
	}

}
