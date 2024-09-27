package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import spring.Item;
import spring.OrderDao;

public class MainForCountOrderItemByOrderId {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx 
		     = new AnnotationConfigApplicationContext(AppCtx.class);

		OrderDao dbQuery = ctx.getBean(OrderDao.class);
		
		dbQuery.countOrderItemByOrderId(1L);
		System.out.println(dbQuery.countOrderItemByOrderId(2L) + "건의 상품이 있습니다.");
		ctx.close();
	}
}
