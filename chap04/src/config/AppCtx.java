package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberInfoPrinter;
import spring.MemberListPrinter;
import spring.MemberPrinter;
import spring.MemberRegisterService;
import spring.MemberSummaryPrinter;
import spring.VersionPrinter;

@Configuration
@ComponentScan(basePackages = {"spring"})
public class AppCtx {
	
//	@Bean
//	public MemberDao memberDao() { // Dao가 일종의 Repository 역할
//		return new MemberDao();	
//	}
//	
//	@Bean
//	public MemberRegisterService memberRegSvc() {
//		return new MemberRegisterService(); // 생성자 주입
//	}
//	
//	@Bean
//	public ChangePasswordService changePwdSvc() {
//		// 의존 주입하지 않아도 스프링이 @Autowired가 붙인 필드에 해당 타입의 빈 객체를 찾아서 주입함.
//		return new ChangePasswordService(); 
//	}
	
//	@Bean
//	public MemberPrinter memberPrinter() { // 필드, 생성자 아무것도 없음
//		return new MemberPrinter(); // 빈 생성자(기본)를 만들어서 리턴함.
//	}
	
	// 자동 주입 대상 빈을 한정할 수 있음. 자동 주입 가능한 빈이 두 개 이상인 경우 빈을 지정해야하는데 이때 사용됨.
	@Bean // Qualifier가 없으면 빈의 이름을 한정자로 지정함.
	@Qualifier("printer")
	public MemberPrinter memberPrinter1() {
		return new MemberPrinter();
	}
//	@Bean
//	@Qualifier("summaryPrinter")
//	public MemberSummaryPrinter memberPrinter2() {
//		return new MemberSummaryPrinter();
//	}
	// 빈이 두 개 이상일 때 @Qualifer로 빈의 이름을 지정해주거나 주입받는 빈의 타입을 직접 수정.
	
//	@Bean
//	public MemberListPrinter listPrinter() { // 필드, 생성자 존재함
//		return new MemberListPrinter();
//		// public MemberListPrinter(MemberDao memberDao, MemberPrinter printer) {...}
//	}
//	
//	@Bean
//	public MemberInfoPrinter infoPrinter() {
//		return new MemberInfoPrinter();
//	}
	
	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(5);
		versionPrinter.setMinorVersion(0);
		return versionPrinter;
	}
}
