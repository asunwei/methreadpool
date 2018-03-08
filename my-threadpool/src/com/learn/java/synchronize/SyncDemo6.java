/**
 * 
 */
package com.learn.java.synchronize;

/**
 * ��Demo6��:
 * ����һ����
 * synchronized������һ����Tʱ���Ǹ������T������T�����ж����õ���ͬһ������
 * 
 * �ܽ�:
 * 
 * 	A. ����synchronized�ؼ��ּ��ڷ����ϻ��Ƕ����ϣ���������õĶ����ǷǾ�̬�ģ�����ȡ�õ����Ƕ���
 * 	       ���synchronized���õĶ�����һ����̬������һ���࣬����ȡ�õ����Ƕ��࣬�������еĶ���ͬһ������ 
 *	B. ÿ������ֻ��һ������lock����֮�������˭�õ������˭�Ϳ��������������Ƶ��Ƕδ��롣 
 *	C. ʵ��ͬ����Ҫ�ܴ��ϵͳ������Ϊ���۵ģ���������������������Ծ���������ν��ͬ�����ơ�
 * @author wei.sun02
 *
 */
public class SyncDemo6 implements Runnable {

	private static int count;

	   public SyncDemo6() {
	      count = 0;
	   }

	   public static void method() {
		   //������
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
