package monitor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ͨ��Executor�ӿڵ��ĸ�����������ʵ�������̳߳ء�
 * ÿһ���̶߳���ͨ������execute��Runnable command�� ������ִ�У��÷�������һ��Runnable ʵ����
 * @author ZYK
 *
 */
public class CacheThread  {	
	
	public static void main(String args[]){
	
		/*
		 * Executors �ṩ��һһϵ�й��������������̳߳أ����ص��̳߳ض�ʵ����ExecutorServie�ӿ�
		 * 1. Executors.newCachedThreadPool();   //�ɻ�����̳߳�
		 * 2. Executors.newScheduledThreadPool(int ); //֧�ֶ�ʱ�������Ե�������̳߳�
		 * 3. Executors.newFixedThreadPool(int nThreads); // �̶���Ŀ���̳߳�
		 * 4. Executors.newSingleThreadExecutor();  //���̻߳����̳߳�
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
					System.out.println(Thread.currentThread().getName() + "�̱߳�������");
					System.out.println(Thread.currentThread().getState());
				}
			});
		}
//		System.out.println(cacheThread.isShutdown());
		//�رշ��������ú�ִ��֮ǰ�ύ�����񣬲��ٽ����µ�����
//		cacheThread.shutdown();
//		System.out.println(cacheThread.isShutdown());
		//�Ƿ���ֹ
//		System.out.println(cacheThread.isTerminated());
		//�������Ͽ��Կ���������ֹͣ����˼������ͣ���еȴ���������񲢷�����Щ������б�
//		cacheThread.shutdownNow();
		
	}

}
