package chap02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 클래스 내에 Bean이 여러 개 존재, 이 어노태이션이 있으면 클래스가 객체 컨테이너가 됨
public class AppContext {
	
	@Bean // 스프링이 생성하는 객체, Bean의 이름을 지정하지 않으면 메소드명이 default값이 됨
	public Greeter greeter() {
		Greeter g = new Greeter();
		g.setFormat("%s, 안녕하세요!");
		return g;
	}
	
	public Greeter greeter2() {
		Greeter g = new Greeter();
		g.setFormat("%s, 안녕하세요!");
		return g;
	}
}
