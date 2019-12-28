package will.jvm.test;

import will.jvm.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class HeapTest {
    public static void main(String[] args) throws InterruptedException {
//        testEden();
//        testBigObject();
        testMaxTenuringThreshold();
    }


    private static void testEden() throws InterruptedException {
        int i = 0;
        for (; ; i++) {
            new User("" + i);
            if (i / 1000 == 0) {
                Thread.sleep(5000);
            }
        }
    }

    /**
     * -Xms10m -Xmx10m -XX:NewRatio=1 -XX:SurvivorRatio=8 -XX:+UseSerialGC -XX:+PrintGCDetails -XX:PretenureSizeThreshold=1024
     *
     * @throws InterruptedException
     */
    public static void testBigObject() throws InterruptedException {
        while (true) {
            String[] arrary = new String[1024 * 1024];
            Thread.sleep(5000);
        }
    }

    /**
     * -Xms10m -Xmx10m -XX:NewRatio=3 -XX:SurvivorRatio=8 -XX:+UseSerialGC -XX:+PrintGCDetails -XX:MaxTenuringThreshold=1
     *
     * @throws InterruptedException
     */
    public static void testMaxTenuringThreshold() throws InterruptedException {
        int i = 0;
        List<User> list = new ArrayList<>();
        for (; ; i++) {
            list.add(new User("" + i));
            if (i / 10000 == 0) {
                Thread.sleep(5000);
            }
        }
    }

}
