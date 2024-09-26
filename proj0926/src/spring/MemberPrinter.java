package spring;

public class MemberPrinter {

	public void print(Member member) {
		System.out.printf(
				"회원정보: 아이디=%d, 이름:%s, 도시:%s, 거리:%s, 우편번호:%d",
				member.getId(), member.getName(), member.getCity(), member.getStreet(), member.getZipcode());
	}

}
