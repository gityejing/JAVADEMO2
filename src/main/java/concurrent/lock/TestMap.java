package concurrent.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestMap {

    private static final Map<String, String> map;
    private static final ReentrantReadWriteLock readWriteLock;

    static {
        map = new ConcurrentHashMap<String, String>();
        readWriteLock = new ReentrantReadWriteLock();
    }

    public static String get(String key) {
        try {
            // 读取时使用读锁
            readWriteLock.readLock().lock();
            System.out.println(Thread.currentThread().getId() + " get read lock.");
            if (map.containsKey(key)) {
                return map.get(key);
            }
            return "";
        } finally {
            readWriteLock.readLock().unlock();
            System.out.println(Thread.currentThread().getId() + " release read lock.");
        }
    }

    public static void set(String key, String value) {
        try {
            // 写入时使用写锁
            readWriteLock.writeLock().lock();
            System.out.println(Thread.currentThread().getId()+ " get write lock.");
            map.put(key, value);
        } finally {
            readWriteLock.writeLock().unlock();
            System.out.println(Thread.currentThread().getId()+ " release write lock.");
        }
    }

    public static void main(String[] args){
        Thread thread1 = new Thread(new MapRunable());
        Thread thread2 = new Thread(new MapRunable());
        Thread thread3 = new Thread(new MapRunable());
        Thread thread4 = new Thread(new MapRunable());
        Thread thread5 = new Thread(new MapRunable());
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
    }
}

class MapRunable implements Runnable {
    @Override
    public void run() {
        TestMap.set(String.valueOf(Thread.currentThread().getId()), Thread .currentThread().getName());
        TestMap.get(String.valueOf(Thread.currentThread().getId()));
    }
}
