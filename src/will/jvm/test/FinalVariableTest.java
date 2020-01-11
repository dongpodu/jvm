package will.jvm.test;


/**
 * java8 常量存放在堆内存中
 */
public class FinalVariableTest {

    final int[] array = new int[1024 * 1024 * 50];

    public static void main(String[] args) {
        FinalVariableTest test = new FinalVariableTest();
        int[] array = test.array;
        while (true) {

        }
    }
}
