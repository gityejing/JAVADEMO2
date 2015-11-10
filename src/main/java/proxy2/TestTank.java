package proxy2;

public class TestTank {
	public static void main(String[] args) {
		Tank t = new Tank();
		Moveable move = new TanktimeProxy(t);
		move.move();
	}
}