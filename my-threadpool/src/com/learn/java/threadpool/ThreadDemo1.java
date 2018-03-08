package com.learn.java.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 
 * 创建线程的三种方式
 * @author wei.sun02
 *
 */
public class ThreadDemo1 {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//方式1，通过继承thread类创建线程
		Thread thread1 = new Thread("thread") {
			@Override
			public void run() {
				for(int i=0; i<100; i++) {
					System.out.println(Thread.currentThread().getName() + "线程i值： " + i );
				}
			}
		};
		
		//方式2，通过实现Runnable接口创建线程。
		Thread thread2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0; i<100; i++) {
					System.out.println(Thread.currentThread().getName() + "线程i值： " + i );
				}
			}
		});
		
		//方式3:通过Callable 和Future创建线程。
		/*
		 * step1: 实现Callable接口，
		 * step2: 创建FutureTask，传入Call， 
		 * 注释： FutureTask是一个包装器，它通过接受Callable来创建，它同时实现了 Future和Runnable接口。 
		 * step3：  由FutureTask创建一个Thread对象
		 * step4：  通过start方法启动线程。 
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
			System.out.println(Thread.currentThread().getName() + "线程i值： " + i );
		}
		
	}
}
