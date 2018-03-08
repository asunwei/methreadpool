/**
 * 
 */
package com.learn.java.synchronize;

/**
 * Demo2
 * ��һ���̷߳��ʶ����һ��synchronized(this)ͬ�������ʱ��
 * ��һ���߳���Ȼ���Է��ʸö����еķ�synchronized(this)ͬ������顣
 * 
 * @author wei.sun02
 *
 */
class Counter implements Runnable {

	private int count;

	public Counter() {
		count = 0;
	}

	// synchronized�����
	public void countAdd() {
		synchronized (this) {
			for (int i = 0; i < 5; i++) {
				try {
					System.out.println(Thread.currentThread().getName() + ":" + (count++));
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// ��synchronized����飬δ��count���ж�д���������Կ��Բ���synchronized
	public void printCount() {
		for (int i = 0; i < 5; i++) {
			try {
				System.out.println(Thread.currentThread().getName() + " count:" + count);
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();
		if (threadName.equals("A")) {
			countAdd();
		} else if (threadName.equals("B")) {
			// countAdd();
			printCount();
		}
	}

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) {
		/*
		 * ���������countAdd��һ��synchronized�ģ�printCount�Ƿ�synchronized�ġ�
		 * ������Ľ���п��Կ���һ���̷߳���һ�������synchronized�����ʱ�� ����߳̿��Է��ʸö���ķ�synchronized����������������
		 */
		Counter counter = new Counter();
		Thread thread2 = new Thread(counter, "B");
		Thread thread1 = new Thread(counter, "A");
		thread1.start();
		thread2.start();

	}

}
