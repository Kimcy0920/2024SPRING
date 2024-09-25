package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import domain.entity.Delivery;
import domain.entity.Member;
import domain.value.Address;


public class MainForCommand {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = 
				new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println("명령어를 입력하세요:");
			String command = reader.readLine();
			if (command.equalsIgnoreCase("exit")) {
				System.out.println("종료합니다.");
				break;
			}
			if (command.startsWith("newMember ")) {
				processNewMember(command.split(" "));
				continue;
			} else if (command.startsWith("newOrder ")) {
				processNewOrder(command.split(" "));
				continue;
			} else if (command.startsWith("newDelivery ")) {
				processNewDelivery(command.split(" "));
				continue;
			}
			printHelp();
		}
	}

	private static void processNewMember(String[] arg) {
	    // 명령어가 6개의 인자를 포함하지 않으면 도움말을 출력하고 종료
	    if (arg.length != 6) {
	        printHelp();
	        return;
	    }
	    // 입력된 인자에서 세 번째부터 다섯 번째 인자를 사용해 Address 객체 생성
	    Address address = new Address(arg[3], arg[4], arg[5]);   
	    // 첫 번째 인자는 memberId, 두 번째 인자는 memberName, 마지막으로 빈 주문 리스트를 가진 Member 객체 생성
	    Member member = new Member(Long.parseLong(arg[1]), arg[2], address, new ArrayList<>());  
	    // 생성된 Member 객체를 콘솔에 출력
	    System.out.println("Member 등록: " + member);
	}

	private static void processNewOrder(String[] arg) {
		if (arg.length != 4) {
			printHelp();
			return;
		}
	}

	private static void processNewDelivery(String[] arg) {
		if (arg.length != 6) {
			printHelp();
			return;
		}
	}
	

	private static void printHelp() {
		System.out.println();
		System.out.println("잘못된 명령입니다. 아래 명령어 사용법을 확인하세요.");
		System.out.println("명령어 사용법:");
		System.out.println("newMember Id Name Street City Zip");
		System.out.println("newOrder # # # #");
		System.out.println("newDelivery Id Order Street City Zip");
		System.out.println();
	}
}