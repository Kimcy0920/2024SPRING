package spring;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component ("memberRegSvc")
public class MemberRegisterService {
	
	@Autowired
	private OrderDao orderDao; // 생성자 주입방식
	
	public MemberRegisterService() { // 기본 생성자 추가
	}
	
	public MemberRegisterService(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	public Long regist(RegisterRequest reg) {
		Member member = orderDao.selectById(reg.getId());
		if (member != null) {
			System.out.println("이미 존재합니다.");
		}
		Member newMember = new Member(reg.getId(), reg.getName(), reg.getCity(), reg.getStreet(), reg.getZipcode());
		orderDao.insertMember(newMember);
		return newMember.getId();
	}
}
