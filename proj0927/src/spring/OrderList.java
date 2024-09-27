package spring;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderList {
	private Long id;
	private String name;
	private Long order_id;
	private int orderprice;
	private int count;
}
