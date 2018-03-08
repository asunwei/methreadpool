package com.learn.java.threadpool;

import java.util.concurrent.Callable;

public class MyCallable implements Callable {
	private int flag;

	public MyCallable(int flag) {
		this.flag = flag;
	}

	@Override
	public String call() throws Exception {
		if(flag == 0){
			return "flag = 0";
		}
		if(this.flag ==1){
		try{
			while(true){
					System.out.println("loop");
					Thread.sleep(1000);
				}
			}catch(Exception e){
				System.out.println("Interrupt");
			}
		return "false";
		} else{
			throw new Exception("Bad flag value!");
		}
	}
}
