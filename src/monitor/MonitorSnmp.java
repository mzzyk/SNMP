package monitor;

import java.util.Scanner;

public class MonitorSnmp implements Runnable {

	public boolean flag = true;
	public int i = 0;

	/**
	 * 设置flag的值，来改变线程的运行状态
	 */
	public void stopFlag() {
		flag = false;
		System.out.println("Thread stop..");
	}

	@Override
	public void run() {

		while (flag) {
			 System.out.println(i);
			i++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public static void main(String[] args) {
		MonitorSnmp a = new MonitorSnmp();
		Thread t = new Thread(a);
		t.start();
		Scanner in = new Scanner(System.in);
		while (true) {
			int temp = in.nextInt();
			if (temp == 5) {
				a.stopFlag();
				break;
			}
		}
	}

}
