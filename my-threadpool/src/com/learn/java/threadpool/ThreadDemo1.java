package com.learn.java.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 
 * �����̵߳����ַ�ʽ
 * @author wei.sun02
 *
 */
public class ThreadDemo1 {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//��ʽ1��ͨ���̳�thread�ഴ���߳�
		Thread thread1 = new Thread("thread") {
			@Override
			public void run() {
				for(int i=0; i<100; i++) {
					System.out.println(Thread.currentThread().getName() + "�߳�iֵ�� " + i );
				}
			}
		};
		
		//��ʽ2��ͨ��ʵ��Runnable�ӿڴ����̡߳�
		Thread thread2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0; i<100; i++) {
					System.out.println(Thread.currentThread().getName() + "�߳�iֵ�� " + i );
				}
			}
		});
		
		//��ʽ3:ͨ��Callable ��Future�����̡߳�
		/*
		 * step1: ʵ��Callable�ӿڣ�
		 * step2: ����FutureTask������Call�� 
		 * ע�ͣ� FutureTask��һ����װ������ͨ������Callable����������ͬʱʵ���� Future��Runnable�ӿڡ� 
		 * step3��  ��FutureTask����һ��Thread����
		 * step4��  ͨ��start���������̡߳� 
		 */
		MyCallable myCallable = new MyCallable(0);
		@SuppressWarnings("unchecked")
		FutureTask<String> oneTask = new FutureTask<String>(myCallable);
		Thread thread3 = new Thread(oneTask);
		
		thread3.start();
		System.out.println(oneTask.get());
		thread1.start();
		thread2.start();
		for(int i=0; i<100; i++) {
			System.out.println(Thread.currentThread().getName() + "�߳�iֵ�� " + i );
		}
		
	}
}
