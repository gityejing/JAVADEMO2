package concurrent;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<Long> {

	@Override
	public Long call() throws Exception {
		long num = 0;
		for (int i = 0; i < 10; i++) {
			num+=i;
		}
		return num;
	}

}