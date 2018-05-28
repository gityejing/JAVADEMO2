package concurrent;

import java.util.concurrent.CountDownLatch;

public class Controller implements Runnable{
	private CountDownLatch latch;
	public Controller(CountDownLatch latch) {
		super();
		this.latch = latch;
	}

	@Override
	public void run() {
		try {
			System.out.println("�ȴ�����ģ�鶼���");
			latch.await();
			System.out.println("����ģ�鶼��ɣ��������");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
