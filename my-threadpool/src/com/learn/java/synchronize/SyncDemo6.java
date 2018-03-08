/**
 * 
 */
package com.learn.java.synchronize;

/**
 * 【Demo6】:
 * 修饰一个类
 * synchronized作用于一个类T时，是给这个类T加锁，T的所有对象用的是同一把锁。
 * 
 * 总结:
 * 
 * 	A. 无论synchronized关键字加在方法上还是对象上，如果它作用的对象是非静态的，则它取得的锁是对象；
 * 	       如果synchronized作用的对象是一个静态方法或一个类，则它取得的锁是对类，该类所有的对象同一把锁。 
 *	B. 每个对象只有一个锁（lock）与之相关联，谁拿到这个锁谁就可以运行它所控制的那段代码。 
 *	C. 实现同步是要很大的系统开销作为代价的，甚至可能造成死锁，所以尽量避免无谓的同步控制。
 * @author wei.sun02
 *
 */
public class SyncDemo6 implements Runnable {

	private static int count;

	   public SyncDemo6() {
	      count = 0;
	   }

	   public static void method() {
		   //修饰类
	      synchronized(SyncThread.class) {
	         for (int i = 0; i < 5; i ++) {
	            try {
	               System.out.println(Thread.currentThread().getName() + ":" + (count++));
	               Thread.sleep(100);
	            } catch (InterruptedException e) {
	               e.printStackTrace();
	            }
	         }
	      }
	   }

	   public synchronized void run() {
	      method();
	   }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SyncDemo6 demo1 = new SyncDemo6();
		SyncDemo6 demo2 = new SyncDemo6();
		Thread thread1 = new Thread(demo1, "SyncThread1");
		Thread thread2 = new Thread(demo2, "SyncThread2");
		thread1.start();
		thread2.start();

	}

}
