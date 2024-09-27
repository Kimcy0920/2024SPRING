package spring;

import java.util.List;

public class MemberListPrinter {

	private OrderDao orderDao;

	public MemberListPrinter(OrderDao orderDao) {
		this.orderDao = orderDao; // 생성자 주입 방식
	}

	public void printAllMembers() {
		List<Member> members = orderDao.selectAllMembers();

		if (members == null) {
			System.out.println("등록된 회원이 없습니다.");
		} else {
			for (Member member : members) {
				print(member);
			}
		}
	}

	public void print(Member member) {
		System.out.print(
				"번호: " + member.getId() + ", " + "이름: " + member.getName() + ", " +
				"주소: " + member.getCity() + ", " + member.getStreet() + ", " + member.getZipcode() + "\n");
	}

}
