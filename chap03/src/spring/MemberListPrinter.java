package spring;

import java.util.Collection;

public class MemberListPrinter {
	
	private MemberDao memberDao;
	private MemberPrinter printer;
	
	public MemberListPrinter(MemberDao memberDao, MemberPrinter printer) {
		this.memberDao = memberDao;
		this.printer = printer;
	}
	
	public void printAll() {
		Collection<Member> members = memberDao.selectAll(); // ArrayList라 생각하면 됨
//		List<Member> members = memberDao.selectAll(); List의 맨 위가 Collection, Object라 생각하면 됨
		
		for(Member m : members) { // 1. 향상된 for문
			printer.print(m);
		}		
//		members.forEach(m -> printer.print(m)); // 2. 람다식 코드
	}
}
