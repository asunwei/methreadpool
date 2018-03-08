/**
 * 
 */
package com.learn.java.synchronize;

/**
 * 
 * Demo3.
 * ָ��Ҫ��ĳ���������
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
		 * ��AccountOperator ���е�run�����������synchronized ��account�����������
		 * ��ʱ����һ���̷߳���account����ʱ��������ͼ����account������߳̽���������
		 * ֱ�����̷߳���account���������Ҳ����˵˭�õ��Ǹ���˭�Ϳ��������������Ƶ��Ƕδ��롣 
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
