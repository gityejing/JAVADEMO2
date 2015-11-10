package threadDown;

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
			System.out.println("等待所有模块都完成");
			latch.await();
			System.out.println("所有模块都完成，任务完成");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
