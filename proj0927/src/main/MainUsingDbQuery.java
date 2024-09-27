package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import spring.Item;
import spring.ItemListPrinter;
import spring.Member;
import spring.MemberListPrinter;
import spring.Order;
import spring.OrderDao;
import spring.OrderItemRequest;

public class MainUsingDbQuery {
	
	private static OrderDao orderDao;
	private static ApplicationContext ctx = null;
	
	public static void main(String[] args) throws IOException {
		
		ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		orderDao = ctx.getBean(OrderDao.class);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			System.out.print("[도움말|help] 명령어를 입력하세요: ");
			String command = reader.readLine(); // scanner 같은 역할, 읽어옴
			if (command.equalsIgnoreCase("exit")) {
				System.out.println("종료합니다.");
				break;
			}
			if (command.equals("showMember")) { // 회원정보
				processMemberCommand();
				continue;
			} else if (command.equals("showItem")) { // 상품정보
				processItemCommand();
				continue;
			} else if (command.startsWith("newMember ")) { // 주문 생성
				processNewMemberCommand(command.split(" "));
				continue;
			} else if (command.startsWith("newItem ")) { // 주문 생성
				processNewItemCommand(command.split(" "));
				continue;
			} else if (command.startsWith("newOrder ")) { // 주문 생성
				processNewOrderCommand(command.split(" "));
				continue;
			} else if (command.startsWith("newOrderItem ")) { // 상품 주문
				processNewOrderItemCommand(command.split(" "));
				continue;
			} else if (command.startsWith("cancelOrder ")) {
				processCancelOrderCommand(command.split(" "));
				continue;
			} else if (command.startsWith("cancelOrderItem ")) {
				processCancelOrderItemCommand(command.split(" "));
				continue;
			} else if (command.startsWith("countOrder ")) {
				processCountOrderItemByOrderIdCommand(command.split(" "));
				continue;
			} else if (command.equals("help")) {
				printHelp();
				continue;
			}
			printHelp();
		}
		
	}
	
	private static void processMemberCommand() { // 회원정보 출력
		MemberListPrinter listPrinter = ctx.getBean(MemberListPrinter.class);
		listPrinter.printAllMembers();
	}
	
	private static void processItemCommand() { // 상품정보 출력
		ItemListPrinter listPrinter = ctx.getBean(ItemListPrinter.class);
		listPrinter.printAllItems();
	}
	
	private static void processNewOrderCommand(String[] arg) { // 주문 생성
		if (arg.length != 5) {
			printHelp();
			return;
		}
		Order order = new Order(999L, Long.parseLong(arg[1]), arg[2], arg[3], arg[4], null);
		Long num = orderDao.insertOrder(order);
		System.out.println(num + "번 주문이 생성되었습니다.");
		
	}
	
	private static void processNewMemberCommand(String[] arg) {
		if (arg.length != 6) {
			printHelp();
			return;
		}
		Member member = new Member(Long.parseLong(arg[1]), arg[2], arg[3], arg[4], arg[5]);
		orderDao.insertMember(member);
		System.out.println("사용자 등록이 완료되었습니다.");
	}
	
	private static void processNewItemCommand(String[] arg) {
		if (arg.length != 4) {
			printHelp();
			return;
		}
		Item item = new Item(null, arg[1], Integer.parseInt(arg[2]), Integer.parseInt(arg[3]));
		orderDao.insertItem(item);
		System.out.println("상품 등록이 완료되었습니다.");
	}
	
	private static void processNewOrderItemCommand(String[] arg) { // 상품 주문
		if (arg.length != 5) {
			printHelp();
			return;
		}
		OrderItemRequest orderItem = new OrderItemRequest(Long.parseLong(arg[1]), Long.parseLong(arg[2]), Integer.parseInt(arg[3]), Integer.parseInt(arg[4]));
		orderDao.insertOrderItemRequest(orderItem);
		System.out.println("상품 주문이 완료되었습니다.");
		System.out.println(orderItem.getItemId() + ", "
							+ orderItem.getOrderId() + ", "
							+ orderItem.getOrderPrice() + ", "
							+ orderItem.getCount());
	}
	
	private static void processCancelOrderCommand(String[] arg) {
		if (arg.length != 2) {
			printHelp();
			return;
		}
		orderDao.cancelOrder(Long.parseLong(arg[1]));
		System.out.println("주문이 취소되었습니다.");
	}
	
	private static void processCancelOrderItemCommand(String[] arg) {
		if (arg.length != 2) {
			printHelp();
			return;
		}
		orderDao.cancelOrderItem(Long.parseLong(arg[1]));
		System.out.println("상품 주문이 취소되었습니다.");
	}
	
	private static void processCountOrderItemByOrderIdCommand(String[] arg) {
		if (arg.length != 2) {
			printHelp();
			return;
		}
		orderDao.countOrderItemByOrderId(Long.parseLong(arg[1]));
	}
	
	private static void printHelp() {
		System.out.println("---------------------------------------");
		System.out.println("[명령어]");
		System.out.println("회원정보: showMember");
		System.out.println("상품정보: showItem");
		System.out.println("회원가입: newMember ID | 이름 | 집주소(도시, 도로, 우편번호)");
		System.out.println("상품등록: newItem 상품명 | 가격 | 수량");
		System.out.println("주문생성: newOrder orderID | 배송지(도시, 도로, 우편번호)");
		System.out.println("상품주문: newOrderItem itemID | orderID | 가격 | 수량");
		System.out.println("주문삭제: cancelOrder orderID");
		System.out.println("상품삭제: cancelOrderItem orderItemID");
		System.out.println("수량체크: countOrder orderID");
		System.out.println("종료하기: exit");		
		System.out.println("---------------------------------------");
		System.out.println();
	}
}
