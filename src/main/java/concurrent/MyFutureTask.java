package concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MyFutureTask extends FutureTask<Long> {
	Callable<Long> myCallable = null;
	
	public MyFutureTask(Callable<Long> callable) {
		super(callable);
		this.myCallable = callable;
	}
	
	

	@Override
	protected void done() {
		super.done();
		System.out.println("任务完成之后，调用这个方法。");
	}

	@Override
	public Long get() throws InterruptedException, ExecutionException {
		return super.get();
	}

	@Override
	public Long get(long timeout, TimeUnit unit) throws InterruptedException,
			ExecutionException, TimeoutException {
		return super.get(timeout, unit);
	}



	@Override
	public void run() {
		super.run();
	}


	

}