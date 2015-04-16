package monitor;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * ͨ���̳߳���ִ�����񣬺�Runnable��ͬ���ǣ����ַ���ʵ����Callable�ӿڡ�
 * ���Ա�ExecutorServiceִ�У�����Runnable����û�з���ֵ����Callable�����з���ֵ
 * @author ZYK *
 */
public class CallableTask {
	
	public static void main(String args[]){
		//����ͨ��newCacheThread����������ʵ���̳߳أ���ȻҲ�������������ַ���
		ExecutorService executorService = Executors.newCachedThreadPool();
		//Future ��ʾ�첽����Ľ�������ṩ�˼������Ƿ���ɵķ������Եȴ��������ɣ�����ȡ����Ľ����
		//������ɺ�ֻ��ʹ�� get ��������ȡ��������б�Ҫ���������ǰ���������˷�����
		ArrayList<Future<String>> resultList = new ArrayList<Future<String>>();
		
		//����10������ִ��
		for(int i=0; i < 10; i++){
			//ʹ��ExecutorServiceִ��Callalbe���͵����񣬲������������future������
			Future<String> future = executorService.submit(new TaskWithResult(i));
			//������ִ�н���洢��List�С�
			resultList.add(future);
		}
		for (Future<String> fs : resultList){
			
			try {
				 //һֱ�ȴ�Future�ķ��ؽ����ֱ��������ɣ���ӡ������ؽ����
				while (!fs.isDone());
				System.out.println(fs.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				//����һ��˳��رգ�ִ����ǰ�ύ�����񣬵�������������
				executorService.shutdown();
			}
			}
			
		}
	}


/**
 * @author ZYK
 *ʵ����Callable �ӿڣ����¶�����call()������
 *�ڴ����У����ǿ����Զ�������Callable�ľ���ʵ�ֺ͹��ܣ����ﵽ�ȶ���Ŀ�ġ�
 *ͨ������call�������������Եõ�Ҫ���صĽ��
 */
class TaskWithResult implements Callable<String>{
	private int id;
	
	/**
	 * ���캯��
	 * @param id �߳�ִ��ʱ�Ĳ���
	 */
	public TaskWithResult(int id){
		this.id = id;
	}
	public String call(){
		System.out.println(Thread.currentThread().getName() + "�̱߳�������");
		//������ʾ��Ϣ���÷��ؽ������Future��get�����еõ���
		String res = "call() ���������ã����񷵻صĽ���ǣ�" + id + "  " + Thread.currentThread().getName() ;
		return res;
	}
}
