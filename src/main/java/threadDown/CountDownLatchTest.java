package threadDown;

import java.util.concurrent.CountDownLatch;
/**
 * CountDownLatch 最重要的方法是countDown()和await()，前者主要是倒数一次；后者是等待直到倒数到0，如果没有到达0，就只有阻塞等待了。 
 * JAVA同步器之 CountDownLatch（不能循环使用，如果需要循环使用可以考虑使用CyclicBarrier） 两种比较常规用法： 
 * 1：new CountDownLatch(1);所有的线程在开始工作前需要做一些准备工作，当所有的线程都准备到位后再统一执行时有用 
 * 2：new CountDownLatch(THREAD_COUNT);当所有的线程都执行完毕后，等待这些线程的其他线程才开始继续执行时有用
 */
public class CountDownLatchTest {
	private static final int THREAD_COUNT = 10;
	// 在调用startSingal.countDown()之前调用了startSingal.await()的线程一律等待，直到startSingal.countDown()的调用
	private static final CountDownLatch startSingal = new CountDownLatch(1);
	// 在finishedSingal的初始化记数量通过调用finishedSingal.countDown()减少为0时调用了finishedSingal.await()的线程一直阻塞
	private static final CountDownLatch finishedSingal = new CountDownLatch(THREAD_COUNT);

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < THREAD_COUNT; i++) {
			new Thread("Task " + i) {
				public void run() {
					System.out.println(Thread.currentThread().getName()+ " prepared!!");
					try {
						System.out.println(Thread.currentThread().getName()
								+ " 线程堵塞在这里!!"+" startSingal同步器的count="+startSingal.getCount());
						startSingal.await();// 让线程等待，直到倒数到0.
						System.out.println(Thread.currentThread().getName()
								+ " 线程放行!!"+" startSingal同步器的count="+startSingal.getCount());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+ " finished!!");
					finishedSingal.countDown();// 倒数一次
					System.out.println("finishedSingal同步器的count="+finishedSingal.getCount());;
				};
			}.start();
		}
		Thread.sleep(1000);
		startSingal.countDown();// 所有的线程被唤醒，同时开始工作.countDown 方法的线程等到计数到达零时才继续
		if(startSingal.getCount() == 0){
			System.out.println("startSingal同步器的count="+startSingal.getCount() +" 放行所有被startSingal堵塞的线程!!");
		}
		finishedSingal.await();// 等待所有的线程完成,main	线程也被阻塞了!!
		System.out.println("All task are finished!!");
	}
}
