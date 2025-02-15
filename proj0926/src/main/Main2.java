package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import spring.Item;
import spring.Member;
import spring.OrderDao;

public class Main2 {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx 
		     = new AnnotationConfigApplicationContext(AppCtx.class);

		OrderDao dbQuery = ctx.getBean(OrderDao.class);
		
//		Member member = new Member(null, "", "", "", "");
//		System.out.println("사용자 등록이 완료되었습니다.");
//		System.out.println(member.getName() + member.getCity() + member.getStreet() + member.getZipcode());
	
		// 상품 목록
		for (Item item : dbQuery.selectAllItem()) {
			System.out.println("상품명: " + item.getName() + " 가격: " + item.getPrice() + " 수량: " + item.getStockQuantity());
		}
		
//		// 주문 생성
//		Order order = new Order(999L, 1L, "서울 마포구", "가길", "301", null);
//		Long num = dbQuery.insertOrder(order);
//		System.out.println(num + "번 주문이 생성되었습니다.");
//		
//		// 상품 주문
//		OrderItemRequest orderItemRequest = new OrderItemRequest(2L, num, 1400, 2);
//		Long oii = dbQuery.insertOrderItemRequest(orderItemRequest);
		
		// 주문 결과
//		Scanner scan = new Scanner(System.in);
//		System.out.print("사용자명: ");
//		String input = scan.nextLine();
//		for (OrderResult orderResult : dbQuery.selectByOrder(input)) {
//			System.out.println(orderResult);
//		}
		ctx.close();
	}
}
