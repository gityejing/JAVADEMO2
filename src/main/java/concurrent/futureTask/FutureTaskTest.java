package concurrent.futureTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * 多线程完成未来的任务并且获取到任务的返回结果
 */
public class FutureTaskTest {

	public static CountDownLatch downLatch = new CountDownLatch(10);

	public static void main(String[] args) throws InterruptedException,
			ExecutionException {
		new FutureTaskTest().exec();
	}

	void exec() throws InterruptedException, ExecutionException {

		// 固定数量线程池
		ExecutorService executorService = Executors.newFixedThreadPool(10);

		// 任务列表，任务最后的返回结果是Integer
		List<FutureTask<Integer>> futureTasks = new ArrayList<FutureTask<Integer>>();

		long start = System.currentTimeMillis();

		// 具体要做的事情，并且返回了做完之后的结果
		Callable<Integer> callable = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				Integer res = new Random().nextInt(100);// 随机数
				Thread.sleep(10000);
				downLatch.countDown();// 倒数
				System.out.println(Thread.currentThread().getName()+":"+res);
				return res;
			}
		};

		for (int i = 0; i < 10; i++) {
			FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);
			futureTasks.add(futureTask);
			executorService.submit(futureTask);// 提交10个任务给线程池
		}

		int count = 0;
		for (FutureTask<Integer> futureTask : futureTasks) {
			count += futureTask.get(); // 获取任务的返回结果
		}
		long end = System.currentTimeMillis();
		downLatch.await();// 等待
		System.out.println(count);
		System.out.println((end - start) + "ms");
		executorService.shutdown();
	}
}
