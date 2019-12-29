package will.jvm.test;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
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
//        testMaxTenuringThreshold();
//        testPermVariable();
//        testStaticVariable();
        testMetaSpace();
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

    /**
     * java7之后，常量移到堆内存中
     *
     * @throws InterruptedException
     */
    public static void testPermVariable() throws InterruptedException {
        String s = "abc";
        int i = 0;
        for (; ; i++) {
            s = s + s;
            System.out.println(s.intern());
            if (i / 10000 == 0) {
                Thread.sleep(5000);
            }
        }
    }

    static String va = "abc";

    public static void testStaticVariable() throws InterruptedException {
        int i = 0;
        for (; ; i++) {
            va = va + va;
            System.out.println(va);
            Thread.sleep(3000);
        }
    }

    /**
     * -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=128m -XX:+PrintGCDetails
     */
    public static void testMetaSpace() {
        int i = 0;
        try {
            for (; ; ) {
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(User.class);
                enhancer.setUseCache(false);
                enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> proxy.invokeSuper(obj, args));
                enhancer.create();
            }
        } catch (Exception e) {
            System.out.println("第" + i + "次时发生异常");
            e.printStackTrace();
        }
    }

}
