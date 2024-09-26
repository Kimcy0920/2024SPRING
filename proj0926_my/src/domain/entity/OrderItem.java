package domain.entity;

import lombok.Getter;

@Getter
public class OrderItem {
	
	private Long id;
	private Long item_id;
	private Long order_id;
	private int orderPrice;
	private int count;
}
