package test;

public class MyObject {
	
	@Myanno(nnn=4, vvv="가나다")
	public void test1() {
		System.out.println("This is testMethod1");
	}
	
	@Myanno(vvv="My new Annotation")
	public void test2() {
		System.out.println("This is testMethod2");
	}
}
