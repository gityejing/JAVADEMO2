package concurrent.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁实现读写锁的缓存机制
 */
public class CacheMap {

    // 缓存的map
    private Map<String, Object> map = new HashMap<String, Object>();

    // 读写锁对象
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private String data="1";

    /**
     * 进行读操作
     * 可以多个读线程同时进入，写线程不能执行
     */
    public Object getReadWriteLock(String tt) {
        //获取读锁，并加锁
        Lock readLock = readWriteLock.readLock();
        readLock.lock();
        try {
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + "------->>>>have read data :"+data );
            return map.get(tt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //!!!!!!注意：锁的释放一定要在trycatch的finally中，因为如果前面程序出现异常，锁就不能释放了
            //释放读锁
            readLock.unlock();
        }

        return null;
    }


    /**
     * 进行写操作
     * 只能一个写线程进入，读线程不能执行
     */
    public void putReadWriteLock(String data){
        //获取写锁，并加锁
        Lock writeLock = readWriteLock.writeLock();
        writeLock.lock();
        try {
            Thread.sleep(3000);
            this.data = data;
        } catch (InterruptedException e) {
            System.out.println("error!!!");
        }finally {
            writeLock.unlock(); //释放写锁
        }

    }


    /**
     * 设计一个缓存系统
     * 读写锁的应用。
     * JDK1.5自带的读写锁特性，读与读不互斥，读与写互斥，写与写互斥。
     * 为什么要使用读写锁？一句话概括那就是提高系统性能，如何提高呢？
     * 试想，对于所有对读的操作是不需要线程互斥的，而如果方法内
     * 使用了synchronized关键字同步以达到线程安全，对于所有的线程不管是读还是写的操作都要同步。
     * 这时如果有大量的读操作时就会又性能瓶颈。
     *
     * 所以，当一个方法内有多个线程访问，并且方法内有读和写读操作时，
     * 提升性能最好的线程安全办法时采用读写锁的机制对读写互斥、写写互斥。这样对于读读就没有性能问题了
     * @author zhurudong
     *
     */
    public void readWriteMethod(String key){
        readWriteLock.readLock().lock();// 读锁，只对写的线程互斥
        Object value = null;
        try {
            // 尝试从缓存中获取数据
            value = map.get(key);
            if (value == null) {
                readWriteLock.readLock().unlock();//发现目标值为null,释放掉读锁
                readWriteLock.writeLock().lock();//发现目标值为null,需要取值操作,上写锁
                try {
                    value = map.get(key);// 很严谨这一步。再次取目标值
                    if (value == null) {//很严谨这一步。再次判断目标值,防止写锁释放后，后面获得写锁的线程再次进行取值操作
                        // 模拟DB操作
                        value = new Random().nextInt(10000) + "test";
                        map.put(key, value);
                        System.out.println("db completed!");
                    }
                    readWriteLock.readLock().lock();//再次对读进行锁住，以防止写的操作，造成数据错乱
                } finally {
                    /*
                     * 先加读锁再释放写锁读作用：
                     * 防止在100行出多个线程获得写锁进行写的操作，所以在写锁还没有释放前要上读锁
                     */
                    readWriteLock.writeLock().unlock();
                }
            }
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}
