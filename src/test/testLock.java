package test;

public class testLock implements Runnable {

	public String lock;

	public testLock(String lock) {
		this.lock = lock;
	}

	/**
	 * ����һ Synchronized ͬ����������ʵ�ִ��������
	 * �������method�����ͬ�������������Ϊmethod���ڵĶ���
	 * ����Ǿ�̬��������������ָmethod���ڵ�Class����(Ψһ)����JVM�У����б����ص��඼��Ψһ�������.
	 */
	public static synchronized void printInter() {
		for (int i = 0; i < 5; i++) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	/* ���ڸ÷���������synchronized ͬ��������������Ϊ�÷������ڵĶ���
	 * ������main������new �˶�������ʵ��������ÿ���̳߳����Լ� �̶߳�����Ǹ�������������ʵ��ͬ��������Ч������
	 * ���ۣ��ڸ÷�������Synchronized��û���õġ�
	 * @see java.lang.Runnable#run()
	 */
	public void run() {

		// ������ �� synchronized ͬ�������,��ʵ�ִ������;
		//���ڴ���飬��������ָsynchronized(lock)�е�lock��

		// synchronized (lock) {
		// for (int i = 0; i < 5; i++) {
		// System.out.print(i + " ");
		// }
		// System.out.println();
		// }

		 printInter();
	}

	public static void main(String args[]) {
		// ����Java�����Ĵ�ֵ�ص㣬����֪������Щ�̵߳�lock����
		// ʵ����ָ����� �� �ڴ��е� ͬһ�����򣬼����main�����е�lock����������
		String lock = new String("lock");
		for (int i = 0; i < 10; i++) {
			testLock tl = new testLock(lock);
			new Thread(tl).start();
		}
	}
}


