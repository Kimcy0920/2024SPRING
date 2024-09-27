package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import lombok.extern.slf4j.Slf4j;
import spring.Item;
import spring.Member;
import spring.Order;
import spring.OrderDao;

//@Slf4j
public class MainForSpring {

	private static ApplicationContext ctx = null;
	private static OrderDao orderDao = null;
	
	public static void main(String[] args) throws IOException {
		ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		orderDao = ctx.getBean(OrderDao.class);
		
		BufferedReader reader = 
				new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println("명령어를 입력하세요:");
			String command = reader.readLine();
			if (command.equalsIgnoreCase("exit")) {
				System.out.println("종료합니다.");
				break;
			}
			if (command.startsWith("newOrder ")) {
				processNewOrderCommand(command.split(" "));
				continue;
			} else if (command.startsWith("change ")) {
//				processChangeCommand(command.split(" "));
				continue;
			} else if (command.startsWith("list")) {
//				processListCommand();
				continue;
			}else if (command.startsWith("info ")) {
//				processInfoCommand(command.split(" "));
				continue;
			} else if (command.equals("showMember")) {
//				log.info("테스트");
				processShowMemberCommand();
				continue;
			} else if (command.equals("showItem")) {
				processShowItemCommand();
				continue;
			}
			printHelp();
		}
	}
	
	private static void processShowMemberCommand() {
		for (Member member : orderDao.selectAllMember()) {
			System.out.println(member);
		}
	}
	
	private static void processShowItemCommand() {
		for (Item item : orderDao.selectAllItem()) {
			System.out.println(item);
		}
	}
//	
//	private static void processInfoCommand(String[] arg) {
//		if (arg.length != 2) {
//			printHelp();
//			return;
//		}
//		MemberInfoPrinter infoPrinter = 
//				ctx.getBean("infoPrinter", MemberInfoPrinter.class);
//		infoPrinter.printMemberInfo(arg[1]);
//	}
//	
//	private static void processListCommand() {
//		MemberListPrinter listPrinter = ctx.getBean(MemberListPrinter.class);
//		listPrinter.printAll();
//	}

	private static void processNewOrderCommand(String[] arg) {
		if (arg.length != 5) {
			printHelp();
			return;
		}

		Order order = new Order(999L, Long.parseLong(arg[1]), arg[2], arg[3], arg[4], null);
		Long num = orderDao.insertOrder(order);
		System.out.println(num + "번 주문이 생성되었습니다.");

	}
//
//	private static void processChangeCommand(String[] arg) {
//		if (arg.length != 4) {
//			printHelp();
//			return;
//		}
//		ChangePasswordService changePwdSvc = 
//				ctx.getBean(ChangePasswordService.class);
//		try {
//			changePwdSvc.changePassword(arg[1], arg[2], arg[3]);
//			System.out.println("암호를 변경했습니다.\n");
//		} catch (MemberNotFoundException e) {
//			System.out.println("존재하지 않는 이메일입니다.\n");
//		} catch (WrongIdPasswordException e) {
//			System.out.println("이메일과 암호가 일치하지 않습니다.\n");
//		}
//	}

	private static void printHelp() {
		System.out.println();
		System.out.println("잘못된 명령입니다. 아래 명령어 사용법을 확인하세요.");
		System.out.println("명령어 사용법:");
		System.out.println("showMember");
		System.out.println("showItem");
		System.out.println("newOrder 회원ID 도시 길 우편번호");
		System.out.println();
	}



}