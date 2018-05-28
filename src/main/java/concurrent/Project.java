package concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Project {
	static final int SIZE = 20;
	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(SIZE);
		Random r = new Random();
		ExecutorService exec = Executors.newCachedThreadPool();
		Controller controller = new Controller(latch);
		exec.execute(controller);
		for(int i = 0; i < SIZE; i++){
			exec.execute(new Module(latch, "ģ��" + (i + 1), r.nextInt(2000)));
		}
		exec.shutdown();
	}
}
