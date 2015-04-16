package test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class testReentrantLock extends Thread {

	//此处只用static变量才可以保持同步，如果没有static关键字，不能同步。
	
	private static Lock lock = new ReentrantLock();
	
	public testReentrantLock() {
		
	}
	
	/**
	 * @param lock  main函数里面定义的ReentrantLock 变量
	 * 
	 */
	public testReentrantLock (Lock lock) {
		this.lock = lock;
	}
	public void run() {
		//使用Lock加锁的方法时，一定要注意的是最后要在finally里面进行unlock；
		//如果不进行unlock，会发生很大的问题，正因为如此，更推荐使用synchronized 进行同步，虽然ReentrantLock更优秀。
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
		//此处定义lock在内存（堆）中是唯一的
		//不管使用testReentrantLock(lock)构造函数会 new 多少个新的实例，
		//但是它们只是在栈中引用都指向同一个堆中的lock。故可以实现同步
//		Lock lock = new ReentrantLock();
		for (int i = 0; i < 10; i++) {
			new testReentrantLock().start();
			//使用构造函数进行传值
//			new testReentrantLock(lock).start();
		}
	}

}
