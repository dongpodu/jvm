package will.jvm.test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duyisong on 09/07/2018.
 */
public class HeapOOMTest {

    public static void main(String[] args) {
        List<HeapOOMTest> list = new ArrayList();
        while (true){
            list.add(new HeapOOMTest());
        }
    }

}
