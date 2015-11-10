package proxy2;
/**
 * 代理类，和原有的类实现同样的接口，接口中的方法就是原类中需要进行加强的方法。
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
		System.out.println("运行时间为:" + (time2 - time1));
	}
}
