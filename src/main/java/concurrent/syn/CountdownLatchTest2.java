package concurrent.syn;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountdownLatchTest2 {
	
	public static int COUNT = 3;
	
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final CountDownLatch cdOrder = new CountDownLatch(1);
		final CountDownLatch cdAnswer = new CountDownLatch(COUNT);
		for (int i = 0; i < COUNT; i++) {
			Runnable runnable = new Runnable() {
				public void run() {
					try {
						cdOrder.await();
						Thread.sleep((long) (Math.random() * 10000));
						cdAnswer.countDown();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			service.execute(runnable);
		}
		try {
			Thread.sleep((long) (Math.random() * 10000));
			cdOrder.countDown();
			cdAnswer.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
		service.shutdown();
	}
}
