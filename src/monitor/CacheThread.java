package monitor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 通过Executor接口的四个工厂方法来实现四种线程池。
 * 每一个线程都是通过调用execute（Runnable command） 方法来执行，该方法接收一个Runnable 实例。
 * @author ZYK
 *
 */
public class CacheThread  {	
	
	public static void main(String args[]){
	
		/*
		 * Executors 提供了一一系列工厂方法来创建线程池，返回的线程池都实现了ExecutorServie接口
		 * 1. Executors.newCachedThreadPool();   //可缓存的线程池
		 * 2. Executors.newScheduledThreadPool(int ); //支持定时和周期性的任务的线程池
		 * 3. Executors.newFixedThreadPool(int nThreads); // 固定数目的线程池
		 * 4. Executors.newSingleThreadExecutor();  //但线程话的线程池
		 */		
		ExecutorService cacheThread =  Executors.newCachedThreadPool();
		//ExecutorService cacheThread =  Executors.newSingleThreadExecutor();
		//ExecutorService cacheThread =  Executors.newScheduledThreadPool(4);
		//ExecutorService cacheThread =  Executors.newFixedThreadPool(4);

		
		
		for(int i=0; i<20; i++){
			final int index = i;
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(i);
			cacheThread.execute(new Runnable() {
				
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getState());
					System.out.println(Thread.currentThread().getName() + "线程被调用了");
					System.out.println(Thread.currentThread().getState());
				}
			});
		}
//		System.out.println(cacheThread.isShutdown());
		//关闭方法，调用后执行之前提交的任务，不再接受新的任务
//		cacheThread.shutdown();
//		System.out.println(cacheThread.isShutdown());
		//是否终止
//		System.out.println(cacheThread.isTerminated());
		//从语义上可以看出是立即停止的意思，将暂停所有等待处理的任务并返回这些任务的列表
//		cacheThread.shutdownNow();
		
	}

}
