package spring;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

public class MemberPrinter {

	private DateTimeFormatter dateTimeFormatter;

	public MemberPrinter() {
		System.out.println("생성자 실행");
		dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
	}
	/* 생성자가 setter보다 먼저 실행됨
	 * DateTimeFormatter타입 빈이 존재하지 않을 때 기본생성자에서 초기화한 DateTimeFormatter를 사용해서 출력함.
	 * 1번 setter방식의 경우 @Autowired(required = false)이면 일치하는 빈이 존재하지 않을 때 null을 전달하지 않음.
	 * 3번 setter방식의 경우 @Nullable
	*/
	
	public void print(Member member) {
		if (dateTimeFormatter == null) {
			System.out.printf(
					"회원정보: 아이디=%d, 이메일=%s, 이름=%s, 등록=%tF\n",
					member.getId(), member.getEmail(), member.getName(), member.getRegisterDateTime());			
		} else {
			System.out.printf(
					"회원정보: 아이디=%d, 이메일=%s, 이름=%s, 등록=%s\n",
					member.getId(), member.getEmail(), member.getName(), dateTimeFormatter.format(member.getRegisterDateTime()));						
		}
	}
	
//	**********************@Autowired 애노테이션의 필수 여부 3가지 방식**********************

// 	3. setter가 실행이 됨 @Nullable가 Null을 넣음
//	@Autowired
//	public void setDateFormatter(@Nullable DateTimeFormatter dateTimeFormatter) {
//		System.out.println("setter 실행");
//		this.dateTimeFormatter = dateTimeFormatter;
//	}
	
// 	2. setter가 실행이 됨 Optional - dateTimeFormatter에 Null을 넣음
	@Autowired // Optional을 사용해 NullPointException 예외 처리 방지
	public void setDateFormatter(Optional<DateTimeFormatter> formatterOpt) {
		// Optional: NullPointException 예외 방지
		if (formatterOpt.isPresent()) { // isPresent(): formatterOpt가 존재하는지 여부
			this.dateTimeFormatter = formatterOpt.get(); // true
		} else {
			this.dateTimeFormatter = null; // false
		}
	}
	
// 	1. setter가 실행이 되지 않음. (required = false)
//	@Autowired(required = false)
//	public void setDateFormatter(DateTimeFormatter dateTimeFormatter) {
//		this.dateTimeFormatter = dateTimeFormatter;
//	}
}
