package dbquery;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Item {
	private int id;
	private String name;
	private int price;
	private	int stockquantity;
}
