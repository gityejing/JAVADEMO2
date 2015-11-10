package threadStatic;

public class StaticThread implements Runnable {

	@Override
	public void run() {
		StaticAction.print();
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			new Thread(new StaticThread()).start();
		}
	}

}
