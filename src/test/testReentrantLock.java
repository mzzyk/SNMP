package test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class testReentrantLock extends Thread {

	//�˴�ֻ��static�����ſ��Ա���ͬ�������û��static�ؼ��֣�����ͬ����
	
	private static Lock lock = new ReentrantLock();
	
	public testReentrantLock() {
		
	}
	
	/**
	 * @param lock  main�������涨���ReentrantLock ����
	 * 
	 */
	public testReentrantLock (Lock lock) {
		this.lock = lock;
	}
	public void run() {
		//ʹ��Lock�����ķ���ʱ��һ��Ҫע��������Ҫ��finally�������unlock��
		//���������unlock���ᷢ���ܴ�����⣬����Ϊ��ˣ����Ƽ�ʹ��synchronized ����ͬ������ȻReentrantLock�����㡣
		lock.lock();
		try {
			for (int i = 0; i < 5; i++) {
				System.out.print(i + " ");
			}
			System.out.println();
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		//�˴�����lock���ڴ棨�ѣ�����Ψһ��
		//����ʹ��testReentrantLock(lock)���캯���� new ���ٸ��µ�ʵ����
		//��������ֻ����ջ�����ö�ָ��ͬһ�����е�lock���ʿ���ʵ��ͬ��
//		Lock lock = new ReentrantLock();
		for (int i = 0; i < 10; i++) {
			new testReentrantLock().start();
			//ʹ�ù��캯�����д�ֵ
//			new testReentrantLock(lock).start();
		}
	}

}
