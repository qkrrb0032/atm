package atm;

import java.util.Scanner;

/*
 * ATM 예제 만들기 
 * ㄴ 회원관리 (가입/탈퇴/로그인/로그아웃)
 * ㄴ 계좌관리 (계약/철회/조회)
 * ㄴ 뱅킹서비스 (입금/인출/이체)
 * ㄴ 파일처리 (저장/로드)
 */

public class Atm {
	
	private final int JOIN = 1;
	private final int LEAVE = 2;
	private final int LOGIN = 3;
	private final int LOGOUT = 4;
	private final int CREATE_ACC = 5;
	private final int DELETE_ACC = 6;
	private final int VIEW_BALANCE = 7;
	private final int INPUT_MONEY = 8;
	private final int OUT_MONEY = 9;
	private final int MOVE_MONEY = 10;
	private final int SAVE_FILE = 11;
	private final int LOAD_FILE = 12;
	private final int QUIT = 13;
	
	public static final Scanner scanner = new Scanner(System.in);
	
	private String brandName;
	
	private UserManager userManager;
	private AccountManager accManager;
	private FileManager fileManager;
	
	private static int log;
	
	
	public Atm() {}
	
	public Atm(String brandName) {
		this.brandName = brandName;
		
		this.userManager = UserManager.getInstance();
		this.accManager = AccountManager.getInstance();
		this.fileManager = FileManager.getInstace();
		log = -1;
	}
	
	public int getLog() {
		return this.log;
	}

	public void setLog(int log) {
		this.log = log;
	}

	private void printMenu() {
		System.out.printf("--- %s BANK ---\n", this.brandName);
		
		System.out.printf("현재 로그인 중 : %d\n", log);
		
		System.out.println("1. 회원가입");
		System.out.println("2. 회원탈퇴");
		System.out.println("3. 로그인");
		System.out.println("4. 로그아웃");
		System.out.println("5. 계좌개설");
		System.out.println("6. 계좌철회");
		System.out.println("7. 계좌조회");
		System.out.println("8. 입금");
		System.out.println("9. 출금");
		System.out.println("10. 이체");
		System.out.println("11. 저장");
		System.out.println("12. 로드");
		System.out.println("13. 종료");
	}
	
	public static int inputNumber(String msg) {
		System.out.print(msg + " : ");
		String input = scanner.next();
		
		int number = -1;
		try {
			number = Integer.parseInt(input);
		} catch (Exception e) {
			System.err.println("숫자 입력만 가능합니다.");
		}
		return number;
	}
	
	private void printAlldata() {
		for(User user : userManager.getList())
			System.out.println(user);
	}
	
	public void run() {
		while(true) {
			printAlldata(); // 검토용
			printMenu();
			int select = inputNumber("메뉴");
			if(select == JOIN)
				userManager.joinUser();
			else if(select == LEAVE)
				userManager.leaveUser();
			else if(select == LOGIN)
				userManager.loginUser();
			else if(select == LOGOUT)
				userManager.logoutUser();
			else if(select == CREATE_ACC)
				accManager.createAccount(userManager.getUserByUserCode(this.log));
			else if(select == DELETE_ACC)
				accManager.deleteAcc();
			else if(select == VIEW_BALANCE)
				accManager.viewBalance();
			else if(select == INPUT_MONEY)
				accManager.inputMoney();
			else if(select == OUT_MONEY)
				accManager.outMoney();
			else if(select == MOVE_MONEY)
				accManager.moveMoney();
			else if(select == SAVE_FILE)
				fileManager.saveFile();
//			else if(select == LOAD_FILE)
//				fileManager.loadFile();
			else if(select == QUIT)
				break;
		}
	}

}