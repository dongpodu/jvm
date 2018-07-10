package will.jvm.test;

import java.util.ArrayList;
import java.util.List;

/**
 * Vm Args:-Xms10m -Xmx10m
 * java heap space
 */
public class HeapOOMTest {
    public static void main(String[] args) {
        List<HeapOOMTest> list = new ArrayList();
        while (true){
            list.add(new HeapOOMTest());
        }
    }

}
