package test;

import java.lang.reflect.Method;

public class MyMain {
	public static void main(String[] args) {
		Method[] methodList = MyObject.class.getMethods(); // MyObject클래스에 있는 메소드를 가져옴
		// Method[]: 클래스 내에 있는 Method배열
		
		for (Method method : methodList) {
			if(method.isAnnotationPresent(Myanno.class)) { // Myanno.class 있는지 여부 확인
				Myanno annotation=method.getDeclaredAnnotation(Myanno.class); // 어노테이션 선언 - Myanno 가져오기
				String value = annotation.vvv(); // 문자열 value에 메소드 어노테이션에서 가져온 vvv를 저장함
				System.out.println(method.getName() + ": " + value);
				System.out.println(annotation.nnn());
			}
		}
	}
}
