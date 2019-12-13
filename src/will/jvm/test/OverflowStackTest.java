package will.jvm.test;

/**
 * Vm Args:-Xss128K
 * StackOverflowError
 */
public class OverflowStackTest {

    private int stackLength =1;

    public void test(){
        stackLength++;
        test();
    }

    public static void main(String[] args) {
        OverflowStackTest test = new OverflowStackTest();
        try{
            test.test();
        }catch (Throwable e){
            System.out.println("stack length:"+test.stackLength);
            e.printStackTrace();
        }
    }

}
