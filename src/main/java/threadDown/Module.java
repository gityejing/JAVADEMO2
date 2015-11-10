package threadDown;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 模拟项目的开发，只有当每个模块都完成后，项目才完成
 * 每个模块的用时不同
 */
class Module implements Runnable{
	private CountDownLatch latch;
	private String moduleName;
	private int time;//用时

	public Module(CountDownLatch latch, String moduleName,int time) {
		super();
		this.latch = latch;
		this.moduleName = moduleName;
		this.time = time;
	}

	@Override
	public void run() {
		try {
			work();
			latch.countDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void work() throws InterruptedException{
		TimeUnit.MILLISECONDS.sleep(time);
		System.out.println(moduleName + " 完成，耗时:" + time);
	}
}



