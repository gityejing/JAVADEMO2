package proxy2;
/**
 * �����࣬��ԭ�е���ʵ��ͬ���Ľӿڣ��ӿ��еķ�������ԭ������Ҫ���м�ǿ�ķ�����
 * @author Administrator
 *
 */
public class TanktimeProxy implements Moveable {
	private Moveable t;

	public TanktimeProxy(Moveable t) {
		super();
		this.t = t;
	}

	@Override
	public void move() {
		long time1 = System.currentTimeMillis();
		System.out.println("time1=" + time1);
		t.move();
		long time2 = System.currentTimeMillis();
		System.out.println("time2=" + time2);
		System.out.println("����ʱ��Ϊ:" + (time2 - time1));
	}
}
