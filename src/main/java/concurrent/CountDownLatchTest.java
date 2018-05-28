package concurrent;

import java.util.concurrent.CountDownLatch;
/**
 * CountDownLatch ����Ҫ�ķ�����countDown()��await()��ǰ����Ҫ�ǵ���һ�Σ������ǵȴ�ֱ��������0�����û�е���0����ֻ�������ȴ��ˡ� 
 * JAVAͬ����֮ CountDownLatch������ѭ��ʹ�ã������Ҫѭ��ʹ�ÿ��Կ���ʹ��CyclicBarrier�� ���ֱȽϳ����÷��� 
 * 1��new CountDownLatch(1);���е��߳��ڿ�ʼ����ǰ��Ҫ��һЩ׼�������������е��̶߳�׼����λ����ͳһִ��ʱ���� 
 * 2��new CountDownLatch(THREAD_COUNT);�����е��̶߳�ִ����Ϻ󣬵ȴ���Щ�̵߳������̲߳ſ�ʼ����ִ��ʱ����
 */
public class CountDownLatchTest {
	private static final int THREAD_COUNT = 10;
	// �ڵ���startSingal.countDown()֮ǰ������startSingal.await()���߳�һ�ɵȴ���ֱ��startSingal.countDown()�ĵ���
	private static final CountDownLatch startSingal = new CountDownLatch(1);
	// ��finishedSingal�ĳ�ʼ��������ͨ������finishedSingal.countDown()����Ϊ0ʱ������finishedSingal.await()���߳�һֱ����
	private static final CountDownLatch finishedSingal = new CountDownLatch(THREAD_COUNT);

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < THREAD_COUNT; i++) {
			new Thread("Task " + i) {
				public void run() {
					System.out.println(Thread.currentThread().getName()+ " prepared!!");
					try {
						System.out.println(Thread.currentThread().getName()
								+ " �̶߳���������!!"+" startSingalͬ������count="+startSingal.getCount());
						startSingal.await();// ���̵߳ȴ���ֱ��������0.
						System.out.println(Thread.currentThread().getName()
								+ " �̷߳���!!"+" startSingalͬ������count="+startSingal.getCount());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+ " finished!!");
					finishedSingal.countDown();// ����һ��
					System.out.println("finishedSingalͬ������count="+finishedSingal.getCount());;
				};
			}.start();
		}
		Thread.sleep(1000);
		startSingal.countDown();// ���е��̱߳����ѣ�ͬʱ��ʼ����.countDown �������̵߳ȵ�����������ʱ�ż���
		if(startSingal.getCount() == 0){
			System.out.println("startSingalͬ������count="+startSingal.getCount() +" �������б�startSingal�������߳�!!");
		}
		finishedSingal.await();// �ȴ����е��߳����,main	�߳�Ҳ��������!!
		System.out.println("All task are finished!!");
	}
}
