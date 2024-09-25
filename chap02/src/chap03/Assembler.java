package chap03;

public class Assembler { // 객체조립기 - 객체 생성, 의존 객체 주입
	
	private MemberDao memberDao;
	private MemberRegisterService regSvc;
	private ChangePasswordService pwdSvc;
	
	public Assembler() {
		memberDao = new MemberDao(); // 객체생성
		regSvc = new MemberRegisterService(memberDao); // memberDao 주입
		pwdSvc = new ChangePasswordService(); // 없으면 setter 주입방식을 사용한 것.
		pwdSvc.setMemberDao(memberDao); // memberDao 주입
	}
	public MemberDao getMemberDao() {
		return memberDao;
	}
	public MemberRegisterService getMemberRegisterService() {
		return regSvc;
	}
	public ChangePasswordService getChangePasswordService() {
		return pwdSvc;
	}
	
}
