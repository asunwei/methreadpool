/**
 * 
 */
package com.learn.java.synchronize;

/**
 * 
 * Demo3.
 * 指定要给某个对象加锁
 * 
 * @author wei.sun02
 *
 */
public class AccountOperator implements Runnable {

	private Account account;

	public AccountOperator(Account account) {
		this.account = account;
	}

	@Override
	public void run() {
		synchronized (account) {
			account.deposit(500);
			account.withdraw(100);
			System.out.println(Thread.currentThread().getName() + ":" + account.getBalance());
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * 在AccountOperator 类中的run方法里，我们用synchronized 给account对象加了锁。
		 * 这时，当一个线程访问account对象时，其他试图访问account对象的线程将会阻塞，
		 * 直到该线程访问account对象结束。也就是说谁拿到那个锁谁就可以运行它所控制的那段代码。 
		 */
		Account account = new Account("zhang san", 10000.0f);
		AccountOperator accountOperator = new AccountOperator(account);

		final int THREAD_NUM = 5;
		Thread threads[] = new Thread[THREAD_NUM];
		for (int i = 0; i < THREAD_NUM; i++) {
			threads[i] = new Thread(accountOperator, "Thread" + i);
			threads[i].start();
		}
	}

}
