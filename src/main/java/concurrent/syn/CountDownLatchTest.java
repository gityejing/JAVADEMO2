package concurrent.syn;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
	
	private static final int THREAD_COUNT = 10;
	private static final CountDownLatch startSingal = new CountDownLatch(1);
	private static final CountDownLatch finishedSingal = new CountDownLatch(THREAD_COUNT);

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < THREAD_COUNT; i++) {
			new Thread("Task " + i) {
				public void run() {
					System.out.println(Thread.currentThread().getName()+ " prepared!!");
					try {
						startSingal.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+ " finished!!");
					finishedSingal.countDown();
				};
			}.start();
		}
		Thread.sleep(1000);
		startSingal.countDown();
		if(startSingal.getCount() == 0){
			
		}
		finishedSingal.await();
		System.out.println("All task are finished!!");
	}
}
