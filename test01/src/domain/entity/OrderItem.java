package domain.entity;

import lombok.Getter;

@Getter
public class OrderItem {
	public OrderItem(Long orderItemId) {
		this.id = orderItemId;
	}
	private Long id;
	private Item item;
	private Order order; // OrderItem이 속하는 Order를 참조
	private int orderPrice;
	private int count;
}
