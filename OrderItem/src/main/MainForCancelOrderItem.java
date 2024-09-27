package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import spring.Item;
import spring.OrderDao;

public class MainForCancelOrderItem {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx 
		     = new AnnotationConfigApplicationContext(AppCtx.class);

		OrderDao dbQuery = ctx.getBean(OrderDao.class);
		
		dbQuery.cancelOrderItem(11L);
		System.out.println("상품을 삭제했습니다.");
		ctx.close();
	}
}
