package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import spring.Member;
import spring.OrderDao;
import spring.OrderItemRequest;
import spring.OrderItemResult;

public class MainSelectByOrderItem {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx 
		     = new AnnotationConfigApplicationContext(AppCtx.class);

		OrderDao dbQuery = ctx.getBean(OrderDao.class);
		
		for (OrderItemResult orderItemResult : dbQuery.selectByOrderItem(2L)) {
			System.out.println(orderItemResult);
		}
		ctx.close();
	}
}
