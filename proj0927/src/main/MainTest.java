package main;

import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import spring.OrderDao;
import spring.OrderList;
import spring.OrderResult;

public class MainTest {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx 
	     = new AnnotationConfigApplicationContext(AppCtx.class);

	OrderDao dbQuery = ctx.getBean(OrderDao.class);
	
//	Member member = new Member(null, "", "", "", "");
//	System.out.println("사용자 등록이 완료되었습니다.");
//	System.out.println(member.getName() + member.getCity() + member.getStreet() + member.getZipcode());
	
	
	// 상품 목록
//	for (Item item : dbQuery.selectAllItem()) {
//		System.out.println("상품명: " + item.getName() + " 가격: " + item.getPrice() + " 수량: " + item.getStockQuantity());
//	}
	
//	long num = 2l;
//	for (OrderList orderList : dbQuery.selectByOrderList(num)) {
//		System.out.println(orderList.getId() + orderList.getName() + orderList.getOrderId() + orderList.getOrderprice() + orderList.getCount());
//	}
	
//	// 주문 생성
//	Order order = new Order(999L, 1L, "서울 마포구", "가길", "301", null);
//	Long num = dbQuery.insertOrder(order);
//	System.out.println(num + "번 주문이 생성되었습니다.");

//	// 상품 주문
//	OrderItemRequest orderItemRequest = new OrderItemRequest(2L, num, 1400, 2);
//	Long oii = dbQuery.insertOrderItemRequest(orderItemRequest);
	
	// 주문 결과
	Scanner scan = new Scanner(System.in);
	System.out.print("아이디: ");
	String input = scan.nextLine();
    Long number = Long.parseLong(input);
	for (OrderList orderList : dbQuery.selectByOrderList(number)) {
		System.out.println("ID: " + orderList.getId() + "상품명: " +  orderList.getName() + "주문ID: "
							+ orderList.getOrder_id() + "가격: " + orderList.getOrderprice() + "개수: "
							+ orderList.getCount());
	}
	ctx.close();
	}
}
