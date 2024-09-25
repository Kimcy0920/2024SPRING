package chap03;

public class ChangePasswordService {
	
	private MemberDao memberDao; // 아래 코드를 사용하려면 객체 생성이 필요함.
	
	public void changePassword(String email, String oldPwd, String newPwd) {
		Member member = memberDao.selectByEmail(email);
		if (member == null)
			throw new MemberNotFoundException();
		
		member.changePassword(oldPwd, newPwd);
		
		memberDao.update(member);
	}
	
	public void setMemberDao(MemberDao memberDao) { // setter 주입 방식, new가 없음
		this.memberDao = memberDao;
	}
}
