package monitor;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 通过线程池来执行任务，和Runnable不同的是，这种方法实现了Callable接口。
 * 可以被ExecutorService执行，但是Runnable任务没有返回值，而Callable任务有返回值
 * @author ZYK *
 */
public class CallableTask {
	
	public static void main(String args[]){
		//这里通过newCacheThread（）方法来实现线程池，当然也可以用其他三种方法
		ExecutorService executorService = Executors.newCachedThreadPool();
		//Future 表示异步计算的结果。它提供了检查计算是否完成的方法，以等待计算的完成，并获取计算的结果。
		//计算完成后只能使用 get 方法来获取结果，如有必要，计算完成前可以阻塞此方法。
		ArrayList<Future<String>> resultList = new ArrayList<Future<String>>();
		
		//创建10个任务并执行
		for(int i=0; i < 10; i++){
			//使用ExecutorService执行Callalbe类型的任务，并将结果保存在future变量中
			Future<String> future = executorService.submit(new TaskWithResult(i));
			//将任务执行结果存储在List中。
			resultList.add(future);
		}
		for (Future<String> fs : resultList){
			
			try {
				 //一直等待Future的返回结果，直到返回完成，打印输出返回结果。
				while (!fs.isDone());
				System.out.println(fs.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				//启动一次顺序关闭，执行以前提交的任务，但不接受新任务
				executorService.shutdown();
			}
			}
			
		}
	}


/**
 * @author ZYK
 *实现了Callable 接口，重新定义了call()方法。
 *在此类中，我们可以自定义任务Callable的具体实现和功能，来达到既定的目的。
 *通过调用call（）函数，可以得到要返回的结果
 */
class TaskWithResult implements Callable<String>{
	private int id;
	
	/**
	 * 构造函数
	 * @param id 线程执行时的参数
	 */
	public TaskWithResult(int id){
		this.id = id;
	}
	public String call(){
		System.out.println(Thread.currentThread().getName() + "线程被调用了");
		//返回提示信息，该返回结果将在Future的get方法中得到。
		String res = "call() 方法被调用，任务返回的结果是：" + id + "  " + Thread.currentThread().getName() ;
		return res;
	}
}
