package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.DbConfig;
import config.DbQueryConfig;
import dbquery.DbQuery;
import domain.entity.Member;
import domain.entity.Orders;

public class Test {
//	member 등록
//	insert into member(name, city, street, zipcode) values
//	('테스트', '서울 광진구', '길길', 999);

	// id = member_id = order_id
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DbConfig.class,
				DbQueryConfig.class);

		DbQuery dbQuery = ctx.getBean(DbQuery.class);
//		List<Member> members = dbQuery.selectAllMember();
//		for (Member member : members) {
//			System.out.println(member);
//		}

//		사용자 등록
//		Member member = new Member(null, "김참직", "서울 강북구", "나로수길", "302");
//		dbQuery.insertMember(member);
//		System.out.println("사용자 등록이 완료되었습니다.");
		
//		사용자 - 주문번호 업데이트
		
		
//		주문 생성
//		Orders orders = new Orders(999L, 1L, 0L, "서울 마포구", "가길", "301", null);
//		Long num = dbQuery.insertOrders(orders);
//		System.out.println(num + "번 주문이 생성되었습니다.");

		ctx.close();
	}
}