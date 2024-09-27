package spring;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Member {

	private Long id;
	private String name;
	private String city;
	private String street;
	private String zipcode;
//	private Address address;
//	private List<Order> orders = new ArrayList<>();
}
