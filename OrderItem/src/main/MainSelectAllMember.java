package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import spring.Member;
import spring.OrderDao;

public class MainSelectAllMember {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx 
		     = new AnnotationConfigApplicationContext(AppCtx.class);

		OrderDao dbQuery = ctx.getBean(OrderDao.class);
		
		for (Member member : dbQuery.selectAllMember()) {
			System.out.println(member);
		}
		ctx.close();
	}
}
