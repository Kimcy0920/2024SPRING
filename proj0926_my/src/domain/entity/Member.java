package domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
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
