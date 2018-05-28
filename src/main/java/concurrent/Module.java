package concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * ģ����Ŀ�Ŀ�����ֻ�е�ÿ��ģ�鶼��ɺ���Ŀ�����
 * ÿ��ģ�����ʱ��ͬ
 */
class Module implements Runnable{
	private CountDownLatch latch;
	private String moduleName;
	private int time;//��ʱ

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
		System.out.println(moduleName + " ��ɣ���ʱ:" + time);
	}
}



