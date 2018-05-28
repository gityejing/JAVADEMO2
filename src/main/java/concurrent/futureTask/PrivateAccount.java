package concurrent.futureTask;

import java.util.Random;
import java.util.concurrent.Callable;

public class PrivateAccount implements Callable<Integer> {

	Integer totalMoney;

	@Override
	public Integer call() throws Exception {
		Thread.sleep(5000);
		totalMoney = new Integer(new Random().nextInt(10000));
		System.out.println("您当前有" + totalMoney + "在您的私有账户中");
		return totalMoney;
	}

}