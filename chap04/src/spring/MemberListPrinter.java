package spring;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component ("listPrinter")
public class MemberListPrinter {
	
	private MemberDao memberDao;
	private MemberPrinter printer;
	
	public MemberListPrinter() {
	}

	public void printAll() {
		Collection<Member> members = memberDao.selectAll(); // ArrayList라 생각하면 됨
//		List<Member> members = memberDao.selectAll(); List의 맨 위가 Collection, Object라 생각하면 됨
		
		for(Member m : members) { // 1. 향상된 for문
			printer.print(m);
		}		
//		members.forEach(m -> printer.print(m)); // 2. 람다식 코드
	}

	@Autowired
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	@Autowired // Qualifer가 없으면 필드나 파라미터 이름을 한정자로 사용함.
	@Qualifier("printer") // @Qualifier("summaryPrinter")
	public void setMemberPrinter(MemberPrinter printer) {
		this.printer = printer;
	}
	
}
