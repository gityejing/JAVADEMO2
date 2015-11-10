package excult.threadBase;

/**
 * @author 叶静
 */
public class TextThread {
    public static void main(String[] args) {
        TxtThread tt = new TxtThread();
        while(true){
            new Thread(tt).start();
            new Thread(tt).start();
            new Thread(tt).start();
            new Thread(tt).start();
        }
    }
}

class TxtThread implements Runnable {
    int num = 100;
    public void run() {
        synchronized (this) { 
            // 锁为调用这个方法的，此类的一个实例对象。
            if (num > 0) {
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                    e.getMessage();
                }
                System.out.println(Thread.currentThread().getName() + "this is" + num--);
            }
        }
    }
}
