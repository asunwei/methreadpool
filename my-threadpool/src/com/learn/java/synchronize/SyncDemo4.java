package com.learn.java.synchronize;

/**
 * Demo4��
 * synchronized����һ������,�����÷��൱�������������ڵ�ʵ������
 * 
 * ����synchronized���η���ʱҪע�����¼��㣺 
 *	1. synchronized�ؼ��ֲ��ܼ̳С� 
 *	��Ȼ����ʹ��synchronized�����巽������synchronized�������ڷ��������һ���֣���ˣ�
 *	synchronized�ؼ��ֲ��ܱ��̳С�����ڸ����е�ĳ������ʹ����synchronized�ؼ��֣�
 *	���������и���������������������е��������Ĭ������²�����ͬ���ģ�
 *	��������ʽ�����������������м���synchronized�ؼ��ֲſ��ԡ���Ȼ�������������෽���е��ø�������Ӧ�ķ�����
 *	������Ȼ�����еķ�������ͬ���ģ�����������˸����ͬ����������ˣ�����ķ���Ҳ���൱��ͬ��
 *	2.�ڶ���ӿڷ���ʱ����ʹ��synchronized�ؼ��֡�
 *	3.���췽������ʹ��synchronized�ؼ��֣�������ʹ��synchronized�����������ͬ���� 
 * @author wei.sun02
 *
 */

public class SyncDemo4 implements Runnable {

	public static int count;
	
	public SyncDemo4() {
		count = 0;
	}
	
	//Synchronized����������������д���� 
	@Override
	public synchronized void run() {
		for (int i = 0; i < 5; i ++) {
		      try {
		         System.out.println(Thread.currentThread().getName() + ":" + (count++));
		         Thread.sleep(100);
		      } catch (InterruptedException e) {
		         e.printStackTrace();
		      }
		   }
	}

}
