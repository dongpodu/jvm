package will.jvm.test;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class JstackTest {

    private Object lock_a = new Object();
    private Object lock_b = new Object();

    private static Executor pool = Executors.newCachedThreadPool();
    private static Executor pool1 = Executors.newFixedThreadPool(1000);

    public static void loop() {
        int i = 0;
        while (true) {
            i++;
        }
    }


    public void testDeadLock() {
        synchronized (lock_a) {
            System.out.println(Thread.currentThread().getName() + "进入lock_a");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lock_b) {
                System.out.println(Thread.currentThread().getName() + "进入lock_b");
            }

        }
    }


    public void testDeadLock1() {
        synchronized (lock_b) {
            System.out.println(Thread.currentThread().getName() + "进入lock_b");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lock_a) {
                System.out.println(Thread.currentThread().getName() + "进入lock_a");
            }
        }
    }

    public void thread() {
        AtomicInteger count = new AtomicInteger(0);
        for (; ; ) {
            new Thread(() -> {
                count.addAndGet(1);
                System.out.println("New thread #" + count);
                for (; ; ) {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                }

            }).start();
        }
    }


    public static void main(String[] args) {
//        loop();
        JstackTest test = new JstackTest();
        pool.execute(test::testDeadLock);
        pool.execute(test::testDeadLock1);

//        test.thread();
    }
}
