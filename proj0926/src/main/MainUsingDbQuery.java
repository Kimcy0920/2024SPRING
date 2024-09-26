package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;

public class MainUsingDbQuery {
	
	private static ApplicationContext ctx = null;
	
	public static void main(String[] args) throws IOException {
		
		ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			System.out.print("명령어를 입력하세요: ");
			String command = reader.readLine(); // scanner 같은 역할, 읽어옴
			if (command.equalsIgnoreCase("exit")) {
				System.out.println("종료합니다.");
				break;
			}
			if (command.startsWith("new ")) { // 해당 문자로 시작하면 명령어 실행
				processNewCommand(command.split(" "));
				continue;
			} else if (command.equals("user")) {
				processMemberCommand();
				continue;
			} else if (command.equals("item")) {
				processItemCommand();
				continue;
			} else if (command.startsWith("info ")) {
				processInfoCommand(command.split(" "));
				continue;
			}
			printHelp();
		}
		
	}
	
	private static void processNewCommand(String[] arg) { // 배열
		if (arg.length != 5) { // 5글자가 아니면 오류
			printHelp();
			return;
		}
		MemberRegisterService regSvc = ctx.getBean("memberRegSvc", MemberRegisterService.class); // 수정3
		RegisterRequest reg = new RegisterRequest();
		reg.setEmail(arg[1]);
		reg.setName(arg[2]);
		reg.setPassword(arg[3]);
		reg.setConfirmPassword(arg[4]);
		
		if (!reg.isPasswordEqualToConfirmPassword()) {
			System.out.println("암호와 확인이 일치하지 않습니다.\n");
			return;
		}
		try {
			regSvc.regist(reg);
			System.out.println("등록했습니다.\n");
		} catch (IOException e) {
			System.out.println("이미 존재하는 이메일입니다.\n");
		}
	}
	
	private static void processMemberCommand() {
//		MemberListPrinter listPrinter = ctx.getBean("listPrinter", MemberListPrinter.class);
//		listPrinter.printAll();
	}
	
	private static void processItemCommand() {
//		MemberListPrinter listPrinter = ctx.getBean("listPrinter", MemberListPrinter.class);
//		listPrinter.printAll();
	}
	
	private static void processInfoCommand(String[] arg) {
		if (arg.length != 2) {
			printHelp();
			return;
		}
//		MemberInfoPrinter infoPrinter = ctx.getBean("infoPrinter", MemberInfoPrinter.class);
//		infoPrinter.printMemberInfo(arg[1]);
	}
	
	private static void printHelp() {
		System.out.println("---------------------------------------");
		System.out.println("[명령어]");
		System.out.println("new");
		System.out.println("user");
		System.out.println("item");
		System.out.println("info");
		System.out.println("exit");		
		System.out.println("---------------------------------------");
		System.out.println();
	}
}
