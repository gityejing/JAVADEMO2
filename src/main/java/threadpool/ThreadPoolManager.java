package threadpool;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
/**
 * 线程池管理器至少有下列功能：创建线程池，销毁线程池，添加新任务。
 * @author Administrator
 *
 */
public class ThreadPoolManager {
	private static ThreadPoolManager instance = null;
    private List<Upload> taskQueue = Collections.synchronizedList(new LinkedList<Upload>());//任务队列
    private WorkThread[] workQueue;//工作线程（真正执行任务的线程）
    private static int worker_num = 5;//工作线程数量（默认工作线程数量是5）
    private static int worker_count = 0;
    
    private ThreadPoolManager(){
        this(5);
    }
    
    private ThreadPoolManager(int num){
        worker_num = num;
        workQueue = new WorkThread[worker_num];
        for(int i=0;i<worker_num;i++){
            workQueue[i] = new WorkThread(i);
        }
    }
    
    public static synchronized ThreadPoolManager getInstance(){
        if(instance==null)
            instance = new ThreadPoolManager();
        return instance;
    }
    
    public void addTask(Upload task){
        //对任务队列的操作要上锁
        synchronized (taskQueue) {
            if(task!=null){
                taskQueue.add(task);
                taskQueue.notifyAll();
                System.out.println("task id "+task.getInfo() + " submit!");
            }
                
        }
    }
    
    public void BatchAddTask(Upload[] tasks){
        //对任务队列的修改操作要上锁
        synchronized (taskQueue) {
            for(Upload e:tasks){
                if(e!=null){
                    taskQueue.add(e);
                    taskQueue.notifyAll();
                    System.out.println("task id "+e.getInfo() + " submit!");
                }
            }        
        }
    }
    
    public void destory(){
        System.out.println("pool begins to destory ...");
        for(int i = 0;i<worker_num;i++){
            workQueue[i].stopThread();
            workQueue[i] = null;
        }
        //对任务队列的操作要上锁
        synchronized (taskQueue) {
            taskQueue.clear();
        }
        
        System.out.println("pool ends to destory ...");
    }
    
    private class WorkThread extends Thread{
        private int taksId ;
        private boolean isRuning = true;
        private boolean isWaiting = false;
        
        public WorkThread(int taskId){
            this.taksId= taskId;
            this.start();
        }
        
        public boolean isWaiting(){
            return isWaiting;
        }
        
        // 如果任务进行中时，不能立刻终止线程，需要等待任务完成之后检测到isRuning为false的时候，退出run()方法
        public void stopThread(){
            isRuning = false;
        }
        
        @Override
        public void run() {
            while(isRuning){
                Upload temp = null;
                //对任务队列的操作要上锁
                synchronized (taskQueue) {
                    //任务队列为空，等待新的任务加入
                    while(isRuning&&taskQueue.isEmpty()){
                        try {
                            taskQueue.wait(20);
                        } catch (InterruptedException e) {
                            System.out.println("InterruptedException occre...");
                            e.printStackTrace();
                        }
                    }
                    if(isRuning) temp = taskQueue.remove(0);
                }
                //当等待新任务加入时候，终止线程(调用stopThread函数)造成 temp ＝ null
                if(temp!=null){
                    System.out.println("task info: "+temp.getInfo()+ " is begining");
                    isWaiting = false;
                    temp.uploadPic();
                    isWaiting = true;
                    System.out.println("task info: "+temp.getInfo()+ " is finished");
                }    
            }
        }
    }
}
