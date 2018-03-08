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

			// 提交并执行任务，任务启动时返回了一个Future对象，
			// 如果想得到任务执行的结果或者是异常可对这个Future对象进行操作
			Future<?> future1 = es.submit(task1);
			
			// 获得第一个任务的结果，如果调用get方法，当前线程会等待任务执行完毕后才往下执行
			System.out.println("task1: " + future1.get());
			Future<?> future2 = es.submit(task2);

			// 等待5秒后，再停止第二个任务。因为第二个任务进行的是无限循环
			Thread.sleep(5000);
			System.out.println("task2 cancel: " + future2.cancel(true));

			// 获取第三个任务的输出，因为执行第三个任务会引起异常
			// 所以下面的语句将引起异常的抛出
			Future<?> future3 = es.submit(task3);
			
			System.out.println("task3: " + future3.get());
			
			//获取第四个任务的输出,此为java8格式。
			Future<?> future4 = es.submit(callTask);
			System.out.println("task4: " + future4.get());

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		// 停止任务执行服务
		es.shutdownNow();

	}
}
