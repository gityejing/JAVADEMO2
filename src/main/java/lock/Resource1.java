package lock;

import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 */
public class Resource1 {

    public void f() {
        System.out.println(Thread.currentThread().getName() + ":stop f()");
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + ":run f()");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":finish f()");
        }
    }

    public void g() {
        // other operations should not be locked...
        System.out.println(Thread.currentThread().getName() + ":not synchronized in g()");
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ":synchronized in g()");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void h() {
        // other operations should not be locked...
        System.out.println(Thread.currentThread().getName() + ":not synchronized in h()");
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ":synchronized in h()");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        final Resource1 rs = new Resource1();
        for (int i = 0; i < 3; i++) {
            new Thread() {
                public void run() {
                    rs.f();
                }
            }.start();
        }
        rs.h();
    }
}
