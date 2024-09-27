package spring;

import java.util.List;

public class ItemListPrinter {
	private OrderDao orderDao;
	
	public ItemListPrinter(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public void printAllItems() {
		List<Item> items = orderDao.selectAllItem();
		
		if(items == null) {
			System.out.println("등록된 상품이 없습니다.");
		} else {
			for (Item item : items) {
				print(item);
			}
		}
	}
	
	public void print(Item item) {
		System.out.print(
				"번호: " + item.getId() + ", " +
				"상품명: " + item.getName() + ", " +
				"가격: " + item.getPrice() + ", " +
				"수량: " + item.getStockQuantity() + "\n");
	}
}
