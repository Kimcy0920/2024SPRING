package test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Myanno라는 Annotation 만들기
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Myanno {
	String vvv() default "나의 어노테이션";
	int nnn() default 15;
}
