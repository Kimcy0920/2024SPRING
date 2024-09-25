package spring;

public class MemberPrinter {
	
	// 필드, 생성자 아무것도 없음
	
	public void print(Member member) {
		System.out.printf(
				"회원정보: 아이디=%d, 이메일=%s, 이름=%s, 등록=%tF\n",
				member.getId(), member.getEmail(), member.getName(), member.getRegisterDateTime());
	}
}
