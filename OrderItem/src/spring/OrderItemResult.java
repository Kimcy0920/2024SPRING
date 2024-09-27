package spring;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class OrderItemResult {
	private Long id;
	private String name;
	private Long itemId;
	private Long orderId;
	private int orderPrice;
	private int count;
}
