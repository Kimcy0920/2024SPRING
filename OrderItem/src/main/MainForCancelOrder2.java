package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import spring.Item;
import spring.OrderDao;

public class MainForCancelOrder2 {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx 
		     = new AnnotationConfigApplicationContext(AppCtx.class);

		OrderDao dbQuery = ctx.getBean(OrderDao.class);
		
		dbQuery.cancelOrder(1L);
		System.out.println("주문을 삭제했습니다.");
		ctx.close();
	}
}
