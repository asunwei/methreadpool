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
		 * syncThread1��syncThread2��SyncThread����������
		 * ����thread1��thread2����ִ��ʱȴ�������߳�ͬ����������Ϊrun�е����˾�̬����method��
		 * ����̬������������ģ�����syncThread1��syncThread2�൱������ͬһ����������Demo1�ǲ�ͬ�ġ�
		 */
		SyncDemo5 demo1 = new SyncDemo5();
		SyncDemo5 demo2 = new SyncDemo5();
		Thread thread1 = new Thread(demo1, "SyncThread1");
		Thread thread2 = new Thread(demo2, "SyncThread2");
		thread1.start();
		thread2.start();
	}

}
