package concurrent.futureTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * FutureTask和ExecutorService 结合的案例， 用来实现多线程执行任务，并在任务执行完后返回结果
 *
 * @author 叶静
 */
public class FutureTaskAndExecutor {

    public static void main(String[] args) {
        List<FutureTask<Integer>> list = new ArrayList<FutureTask<Integer>>();// 任务队列
        // 创建线程池
        ExecutorService exec = Executors.newFixedThreadPool(5);
        for (int i = 10; i < 20; i++) {
            // 创建对象
            FutureTask<Integer> ft = new FutureTask<Integer>(new GetSum(i));
            // 添加到list,方便后面取得结果
            list.add(ft);
            // 逐个提交给线程池，当然也可以一次性的提交给线程池，exec.invokeAll(list);
            exec.submit(ft);
        }

        // 统计结果
        Integer total = 0;
        for (FutureTask<Integer> tempFt : list) {
            try {
                total = total + tempFt.get();// 要等到FutureTask完成之后才出结果
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        // 处理完毕，一定要记住关闭线程池，这个不能在统计之前关闭，因为如果线程多的�?执行中的可能被打�?
        exec.shutdown();
        System.out.println("多线程计算后的计算结果" + total);

    }
}

class GetSum implements Callable {

    private Integer total;
    private Integer sum = 0;

    public GetSum(Integer total) {
        this.total = total;
    }

    public Object call() throws Exception {
        for (int i = 1; i < total + 1; i++) {
            sum = sum + i;
        }
        System.out.println(Thread.currentThread().getName() + " sum:" + sum);
        return sum;
    }

}
