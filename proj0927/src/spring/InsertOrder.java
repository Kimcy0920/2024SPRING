//package spring;
//
//public class InsertOrder {
//	private OrderDao orderDao;
//
//	public InsertOrder(OrderDao orderDao) {
//		this.orderDao = orderDao;
//	}
//	
//	public Long regist(String[] arg) {
//		Order order = new Order(
//				order.setId(Long.parseLong(arg[1]),
//				order.setMemberId(Long.parseLong(arg[2]),
//				order.setCity(arg[3]),
//				order.setStreet(arg[4]),
//				order.setZipcode(arg[5]),
//				order.setOrderDate(arg[6]));
//		orderDao.insertOrder();
//	}
//}
//
////private Long id;
////private Long memberId;
////private String city;
////private String street;
////private String zipcode;
////private String orderDate;