package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberInfoPrinter;
import spring.MemberListPrinter;
import spring.MemberPrinter;
import spring.MemberRegisterService;
import spring.VersionPrinter;

@Configuration
public class AppCtx {
	
	@Bean
	public MemberDao memberDao() { // Dao가 일종의 Repository 역할
		return new MemberDao();	
	}
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService(memberDao()); // 생성자 주입
	}
	
	@Bean
	public ChangePasswordService changePwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDao(memberDao()); // setter 주입
		return pwdSvc;
	}
	
	@Bean
	public MemberPrinter memberPrinter() { // 필드, 생성자 아무것도 없음
		return new MemberPrinter(); // 빈 생성자(기본)를 만들어서 리턴함.
	}
	
	@Bean
	public MemberListPrinter listPrinter() { // 필드, 생성자 존재함
		return new MemberListPrinter(memberDao(), memberPrinter());
		// public MemberListPrinter(MemberDao memberDao, MemberPrinter printer) {...}
	}
	
	@Bean
	public MemberInfoPrinter infoPrinter() {
		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
		infoPrinter.setMemberDao(memberDao());
		infoPrinter.setPrinter(memberPrinter());
		return infoPrinter;
	}
	
	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(5);
		versionPrinter.setMinorVersion(0);
		return versionPrinter;
	}
}
