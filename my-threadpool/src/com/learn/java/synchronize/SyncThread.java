package com.learn.java.synchronize;

/**
 * Demo1
 * 1.一个线程访问一个对象中的synchronized(this)同步代码块时，
 * 	  其他试图访问该对象的线程将被阻塞。我们看下面一个例子：  ----锁的是对象
 * 
 * @author wei.sun02
 *
 */
public class SyncThread implements Runnable{
	//加上static, 目的是让成为共享对象.
	private static int count;
	
	public  SyncThread() {
		count = 0;
	}
	public int getCount() {
		return count;
	}
	@Override
	public void run() {
		synchronized(this) {
			for(int i=0; i<5; i++) {
				try {
					System.out.println(Thread.currentThread().getName() + ":" + (count++));
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	
	
	public static void main(String[] args) {
		/*
		 * 当两个并发线程(thread1和thread2)访问同一个对象(syncThread)中的synchronized代码块时，
		 * 在同一时刻只能有一个线程得到执行，另一个线程受阻塞，必须等待当前线程执行完这个代码块以后才
		 * 能执行该代码块。Thread1和thread2是互斥的，因为在执行synchronized代码块时会锁定当前的对象，
		 * 只有执行完该代码块才能释放该对象锁，下一个线程才能执行并锁定该对象。
		 */
		//SyncThread demo1 = new SyncThread();
		//Thread thread1 = new Thread(demo1,"Thread1");
		//Thread thread2 = new Thread(demo1,"Thread2");
		
		/*
		 * 这时创建了两个SyncThread的对象syncThread1和syncThread2，线程thread1执行的是syncThread1
		 * 对象中的synchronized代码(run)，而线程thread2执行的是syncThread2对象中的synchronized代码(run)；
		 * 我们知道synchronized锁定的是对象，这时会有两把锁分别锁定syncThread1对象和syncThread2对象，
		 * 而这两把锁是互不干扰的，不形成互斥，所以两个线程可以同时执行。
		 */
		Thread thread1 = new Thread(new SyncThread(),"Thread1");
		Thread thread2 = new Thread(new SyncThread(),"Thread2");
		thread1.start();
		thread2.start();
	}

}
