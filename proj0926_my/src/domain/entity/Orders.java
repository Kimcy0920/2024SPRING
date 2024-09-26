package domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@Setter
public class Orders {
	private Long id;
	private Long memberId;
	private Long orderItemId;
	private String city;
	private String street;
	private String zipcode;
	private String orderDate;
	
}
