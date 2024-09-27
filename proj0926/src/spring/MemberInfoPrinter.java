//package spring;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Component;
//
//@Component ("infoPrinter")
//public class MemberInfoPrinter {
//
//	private OrderDao orderDao;
//	private MemberPrinter printer;
//	
//	public void printMemberInfo(String email) {
//		Member member = orderDao.selectByEmail(email);
//		if (member == null) {
//			System.out.println("데이터 없음\n");
//			return;
//		}
//		printer.print(member);
//		System.out.println();
//	}
//	
//	@Autowired
//	public void setMember(OrderDao orderDao) {
//		this.orderDao = orderDao;
//	}
//	
//	@Autowired
//	@Qualifier("printer") // @Qualifier("summaryPrinter")
//	public void setPrinter(MemberPrinter printer) {
//		this.printer = printer;
//	}
//}
