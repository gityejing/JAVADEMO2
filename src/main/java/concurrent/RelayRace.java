package concurrent;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RelayRace {

	public static void main(String[] args) throws InterruptedException {
		final Player[] players = new Player[4];
		ExecutorService exec = Executors.newCachedThreadPool();
		CyclicBarrier barrier = new CyclicBarrier(4, new Runnable() {
			@Override
			public void run() {
				System.out.println("����������ʱ��" + players[3].getTime());
			}
		});
		for (int i = 0; i < 4; i++) {
			players[i] = new Player("��Ա" + (i + 1), barrier, i == 0);
		}
		for (int i = 0; i < 4; i++) {
			if (i < 3) {
				players[i].setNext(players[i + 1]);
				exec.execute(players[i]);
			} else {
				exec.execute(players[3]);
				break;
			}
		}
	}
}
