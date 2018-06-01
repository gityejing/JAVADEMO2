package concurrent.syn;

import java.util.concurrent.CountDownLatch;

public class Match {

	public static int PLAYER_COUNT = 10;
	// 模拟了100米赛跑，10名选手已经准备就绪，只等裁判一声令下。当所有人都到达终点时，比赛结束。
	public static void main(String[] args) throws InterruptedException {

		// 开始的倒数锁
		final CountDownLatch begin = new CountDownLatch(1);
		// 结束的倒数锁
		final CountDownLatch end = new CountDownLatch(PLAYER_COUNT);
		// 十名选手

		for (int index = 0; index < PLAYER_COUNT; index++) {
			new Thread(new Player(begin, end), "player" + index).start();
		}
		System.out.println("Game Start");
		// begin减1，计算变为0，开始游戏
		begin.countDown();
		// 等待end变为0，即所有选手到达终点
		end.await();
		System.out.println("Game Over");

	}
	
	
	
	
}

/**
 * 运动员线程
 * @author 30000133
 *
 */
class Player implements Runnable {
	// 开始的倒数锁
	private final CountDownLatch begin;
	// 结束的倒数锁
	private final CountDownLatch end;

	Player(CountDownLatch begin, CountDownLatch end) {
		this.begin = begin;
		this.end = end;
	}

	@Override
	public void run() {
		try {
			// 如果当前计数为零，则此方法立即返回。
			// 等待
			begin.await();
			Thread.sleep((long) (Math.random() * 10000));
			System.out.println(Thread.currentThread().getName() + " arrived");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// 每个选手到达终点时，end就减一
			end.countDown();
		}
	}
}