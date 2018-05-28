package concurrent;

public class MyRunnableTask implements Runnable{

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
	}
}