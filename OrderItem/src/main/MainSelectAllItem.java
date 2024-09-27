package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import spring.Item;
import spring.OrderDao;

public class MainSelectAllItem {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx 
		     = new AnnotationConfigApplicationContext(AppCtx.class);

		OrderDao dbQuery = ctx.getBean(OrderDao.class);
		
		for (Item item : dbQuery.selectAllItem()) {
			System.out.println(item);
		}
		ctx.close();
	}
}
