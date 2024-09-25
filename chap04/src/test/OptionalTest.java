package test;

import java.util.Optional;

public class OptionalTest {
	
	public static void main(String[] args) {
		String nStr = null;
		String oStr = Optional.ofNullable(nStr).orElse("Not Null");
		System.out.println(oStr);
		
		String value = "Hello, Optional!"; // 문자열
		String value2 = null;
		Optional<String> optionalValue = Optional.ofNullable(value2); // null check해서 optionalValue에 보관
		
		if(optionalValue.isPresent()) {
			System.out.println("Value is present: " + optionalValue.get());
		} else {
			System.out.println("Value is not present.");
		}
	}
}
