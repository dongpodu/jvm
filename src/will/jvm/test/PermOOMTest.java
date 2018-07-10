package will.jvm.test;

import java.util.ArrayList;
import java.util.List;

/**
 * Vm Args:-XX:PermSize=10M -XX:MaxPermSize=10M
 * OutOfMemoryError:PermGen space
 */
public class PermOOMTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList();
        int i=0;
        while (true){
            list.add(String.valueOf(i++).intern());
        }
    }

}
