package com.learn.java.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPoolTest {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		MyCallable task1 = new MyCallable(0);
		MyCallable task2 = new MyCallable(1);
		MyCallable task3 = new MyCallable(2);
		Callable<?> callTask = () -> {System.out.println("newTask");return "true";} ;
		ExecutorService es = Executors.newFixedThreadPool(4);
		try {

			// �ύ��ִ��������������ʱ������һ��Future����
			// �����õ�����ִ�еĽ���������쳣�ɶ����Future������в���
			Future<?> future1 = es.submit(task1);
			
			// ��õ�һ������Ľ�����������get��������ǰ�̻߳�ȴ�����ִ����Ϻ������ִ��
			System.out.println("task1: " + future1.get());
			Future<?> future2 = es.submit(task2);

			// �ȴ�5�����ֹͣ�ڶ���������Ϊ�ڶ���������е�������ѭ��
			Thread.sleep(5000);
			System.out.println("task2 cancel: " + future2.cancel(true));

			// ��ȡ������������������Ϊִ�е���������������쳣
			// �����������佫�����쳣���׳�
			Future<?> future3 = es.submit(task3);
			
			System.out.println("task3: " + future3.get());
			
			//��ȡ���ĸ���������,��Ϊjava8��ʽ��
			Future<?> future4 = es.submit(callTask);
			System.out.println("task4: " + future4.get());

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		// ֹͣ����ִ�з���
		es.shutdownNow();

	}
}
