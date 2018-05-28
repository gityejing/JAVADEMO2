package concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author Administrator 
 * 		�ó�������ģ�ⷢ��������ִ��������̴߳���ָ�ӹ٣��½�3���̴߳���սʿ��սʿһֱ�ȴ���ָ�ӹ��´����
 *         ��ָ�ӹ�û���´������սʿ�Ƕ�����ȴ���һ�������´սʿ�Ƕ�ȥִ���Լ�������ָ�ӹٴ��ڵȴ�״̬��սʿ������ִ������򱨸��
 *         ָ�ӹ٣�ָ�ӹ�������ȴ���
 */
public class CountdownLatchTest2 {
	
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool(); // ����һ���̳߳�
		final CountDownLatch cdOrder = new CountDownLatch(1); // ָ�ӹٵ��������Ϊ1��ָ�ӹ�һ�´������cutDown,��Ϊ0��սʿ��ִ������
		final CountDownLatch cdAnswer = new CountDownLatch(3);// ��Ϊ������սʿ�����Գ�ʼֵΪ3��ÿһ��սʿִ�����������cutDownһ�Σ���������ִ����ϣ���Ϊ0����ָ�ӹ�ֹͣ�ȴ���
		for (int i = 0; i < 3; i++) {
			Runnable runnable = new Runnable() {
				public void run() {
					try {
						System.out.println("�߳�"+ Thread.currentThread().getName() + "��׼����������");
						cdOrder.await(); // սʿ�Ƕ����ڵȴ�����״̬
						System.out.println("�߳�"+ Thread.currentThread().getName() + "�ѽ�������");
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("�߳�"+ Thread.currentThread().getName()+ "��Ӧ�������");
						cdAnswer.countDown(); // ����ִ����ϣ����ظ�ָ�ӹ٣�cdAnswer��1��
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			service.execute(runnable);// Ϊ�̳߳��������
		}
		try {
			Thread.sleep((long) (Math.random() * 10000));
			System.out.println("�߳�" + Thread.currentThread().getName()+ "������������");
			cdOrder.countDown(); // �������cdOrder��1�����ڵȴ���սʿ��ֹͣ�ȴ�תȥִ������
			System.out.println("�߳�" + Thread.currentThread().getName()+ "�ѷ���������ڵȴ����");
			cdAnswer.await(); // ����ͺ�ָ�ӹٴ��ڵȴ�״̬��һ��cdAnswerΪ0ʱֹͣ�ȴ���������ִ��
		} catch (Exception e) {
			e.printStackTrace();
		}
		service.shutdown(); // ���������ֹͣ�̳߳ص������߳�
		System.out.println("�߳�" + Thread.currentThread().getName()+ "���յ�������Ӧ���");
	}
}
