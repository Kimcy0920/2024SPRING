package spring;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class Order {
	private Long id;
	private Long memberId;
	private String city;
	private String street;
	private String zipcode;
	private String orderDate;

}
