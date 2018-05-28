package concurrent;

public class ExcultTest {

    public static void main(String[] args) {
//		ExecutorService e = Executors.newFixedThreadPool(10);
//		for (int i = 0; i < 20; i++) {
//			MyRunnableTask task = new MyRunnableTask();
//			e.execute(task);
//		}
//		e = Executors.newScheduledThreadPool(10);  
//		ScheduledExecutorService scheduler = (ScheduledExecutorService) e;  
//		scheduler.scheduleAtFixedRate(task, 0, 3, TimeUnit.SECONDS);  

//		int[] numbers = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 10, 11 };  
//		ConcurrentCalculator calc = new ConcurrentCalculator();  
//		Long sum = calc.sum(numbers);  
//		System.out.println(sum); 
//		calc.close(); 
        MyCallable ca = new MyCallable();
        MyFutureTask ft = new MyFutureTask(ca);
        Thread tr = new Thread(ft);
        tr.start();
        System.out.println("线程任务执行，此时时间为" + System.nanoTime());
        if (!ft.isDone()) {
            try {
                Thread.sleep(1000);
                System.out.println("线程任务没有完成，主线程等待");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("线程任务执行完毕，此时时间为" + System.nanoTime());
        Long sum = 0L;
        try {
            sum = ft.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("线程任务执行结果" + sum);
    }
}
