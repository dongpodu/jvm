package will.jvm.book.chapter4;

public class MinorGcTest {
	private static final int _1MB=1024*1024;
	
	public static void test(){
		byte[] all1,all2,all3,all4;
		all1 = new byte[2*_1MB];
		all2 = new byte[2*_1MB];
		all3 = new byte[2*_1MB];
		all4 = new byte[4*_1MB];
	}
	
	public static void main(String[] args) {
		test();
	}
	
}
