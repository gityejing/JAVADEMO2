package concurrent.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CachedData {

    Object data;
    volatile boolean cacheValid;
    final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    void processCachedData() {
        rwl.readLock().lock();
        if (!cacheValid) {
            // Must release read lock before acquiring write lock
            rwl.readLock().unlock();
            rwl.writeLock().lock();
            try {
                // Recheck state because another thread might have
                // acquired write lock and changed state before we did.
                if (!cacheValid) {
                    cacheValid = true; //                    data = ...
                }
                rwl.readLock().lock(); // Downgrade by acquiring read lock before releasing write lock
            } finally {
                rwl.writeLock().unlock(); // Unlock write, still hold read
            }
        }

        try {
//            use(data);
        } finally {
            rwl.readLock().unlock();
        }
    }
}
