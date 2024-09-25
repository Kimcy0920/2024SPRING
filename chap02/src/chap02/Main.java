package chap02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
//		[기존 방식] - new를 사용함, 메인 메소드가 실행되면 순서대로 실행됨
//		Greeter greeter = new Greeter();
//		greeter.setFormat("%s, 안녕!");
//		String msg1 = greeter.greet("스프링");
//		System.out.println(msg1);
		
//		POJO방식 - 이 코드가 언제 실행될 지를 정할 수 있음
//		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
//		// ApplicationContext(객체 컨테이너) -> AppContext를 말함
//		Greeter g = ctx.getBean("greeter", Greeter.class); // getBean() 생성한 Bean객체를 검색
//		                      // Bean이름, Greeter에서 찾기
//		String msg = g.greet("스프링");
//		System.out.println(msg);
//		ctx.close();
		
		// p.51 싱글톤
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
		Greeter g1 = ctx.getBean("greeter", Greeter.class);
		Greeter g2 = ctx.getBean("greeter", Greeter.class);
		System.out.println("(g1 == g2) = " + (g1 == g2)); // true, 단일객체여서 같음
		ctx.close();
	}

}
