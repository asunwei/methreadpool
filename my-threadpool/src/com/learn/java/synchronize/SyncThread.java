package com.learn.java.synchronize;

/**
 * Demo1
 * 1.һ���̷߳���һ�������е�synchronized(this)ͬ�������ʱ��
 * 	  ������ͼ���ʸö�����߳̽������������ǿ�����һ�����ӣ�  ----�����Ƕ���
 * 
 * @author wei.sun02
 *
 */
public class SyncThread implements Runnable{
	//����static, Ŀ�����ó�Ϊ�������.
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
		 * �����������߳�(thread1��thread2)����ͬһ������(syncThread)�е�synchronized�����ʱ��
		 * ��ͬһʱ��ֻ����һ���̵߳õ�ִ�У���һ���߳�������������ȴ���ǰ�߳�ִ�������������Ժ��
		 * ��ִ�иô���顣Thread1��thread2�ǻ���ģ���Ϊ��ִ��synchronized�����ʱ��������ǰ�Ķ���
		 * ֻ��ִ����ô��������ͷŸö���������һ���̲߳���ִ�в������ö���
		 */
		//SyncThread demo1 = new SyncThread();
		//Thread thread1 = new Thread(demo1,"Thread1");
		//Thread thread2 = new Thread(demo1,"Thread2");
		
		/*
		 * ��ʱ����������SyncThread�Ķ���syncThread1��syncThread2���߳�thread1ִ�е���syncThread1
		 * �����е�synchronized����(run)�����߳�thread2ִ�е���syncThread2�����е�synchronized����(run)��
		 * ����֪��synchronized�������Ƕ�����ʱ�����������ֱ�����syncThread1�����syncThread2����
		 * �����������ǻ������ŵģ����γɻ��⣬���������߳̿���ͬʱִ�С�
		 */
		Thread thread1 = new Thread(new SyncThread(),"Thread1");
		Thread thread2 = new Thread(new SyncThread(),"Thread2");
		thread1.start();
		thread2.start();
	}

}
