package test;

public class testLock implements Runnable {

	public String lock;

	public testLock(String lock) {
		this.lock = lock;
	}

	/**
	 * 方法一 Synchronized 同步方法，来实现代码加锁。
	 * 如果采用method级别的同步，则对象锁即为method所在的对象；
	 * 如果是静态方法，对象锁即指method所在的Class对象(唯一)，在JVM中，所有被加载的类都有唯一的类对象.
	 */
	public static synchronized void printInter() {
		for (int i = 0; i < 5; i++) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	/* 若在该方法处采用synchronized 同步方法，对象锁为该方法所在的对象；
	 * 由于在main函数中new 了多个该类的实例，所以每个线程持有自己 线程对象的那个对象锁，不能实现同步（加锁效果）。
	 * 结论：在该方法出加Synchronized是没有用的。
	 * @see java.lang.Runnable#run()
	 */
	public void run() {

		// 方法二 ： synchronized 同步代码块,来实现代码加锁;
		//对于代码块，对象锁即指synchronized(lock)中的lock；

		// synchronized (lock) {
		// for (int i = 0; i < 5; i++) {
		// System.out.print(i + " ");
		// }
		// System.out.println();
		// }

		 printInter();
	}

	public static void main(String args[]) {
		// 根据Java方法的传值特点，我们知道，这些线程的lock变量
		// 实际上指向的是 堆 内存中的 同一个区域，即存放main函数中的lock变量的区域。
		String lock = new String("lock");
		for (int i = 0; i < 10; i++) {
			testLock tl = new testLock(lock);
			new Thread(tl).start();
		}
	}
}


