package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component ("changePwdSvc")
public class ChangePasswordService {
	
	// 생성자, setter 자동 주입 방식 대신 @Autowired를 사용
	@Autowired // 의존 자동 주입 기능. 알아서 의존 객체를 찾아서 주입함
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