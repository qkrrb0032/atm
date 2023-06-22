package atm;

import java.util.Scanner;

public class Atm {
	private final int MEM_JOIN = 1;
	private final int MEM_DROP = 2;
	private final int MEM_LOGIN = 3;
	private final int MEM_LOGOUT = 4;
	private final int ACC_CREATE = 5;
	private final int ACC_DELETE = 6;
	private final int ACC_VIEW = 7;
	private final int BANK_INPUT = 8;
	private final int BANK_OUT = 9;
	private final int BANK_MOVE = 10;
	private final int FILE_SAVE = 11;
	private final int FILE_LOAD = 12;
	private final int QUIT = 13;

	public static final Scanner scan = new Scanner(System.in);
	
	private String brandName;
	private int log = -1;

	private UserManager userM;
	private AccountManager accM;
	private FileManager fileM;

	public Atm(String brandName) {
		this.brandName = brandName;
		this.userM = UserManager.getInstance();
		this.accM = AccountManager.getInstance();
		this.fileM = FileManager.getInstance();
	}

	public void run() {
		while (true) {
			printAlldata(); // 검토용
			printMenu();
			int select = inputNumber("메뉴");
			if (select == MEM_JOIN) {
				userM.joinUser();
			} 
			else if (select == MEM_DROP) {
				userM.leaveUser();
			} 
			else if (select == MEM_LOGIN) {
				userM.loginUser();
			} 
			else if (select == MEM_LOGOUT) {
				userM.logoutUser();
			}
			else if (select == ACC_CREATE) {
				accM.createAcc();
			}
			else if (select == ACC_DELETE) {
				accM.deleteAcc();
			}
//			else if (select == ACC_VIEW) {
//				accM.viewAcc();
//			}
//			else if (select == BANK_INPUT) {
//				accM.inputMoney();
//			}
//			else if (select == BANK_OUT) {
//				accM.outMoney();
//			}
//			else if (select == BANK_MOVE) {
//				accM.moveMoney();
//			}
//			else if (select == FILE_SAVE) {
//				fileM.saveFile();
//			}
//			else if (select == FILE_LOAD) {
//				fileM.loadFile();
//			}
			else if (select == QUIT) {
				break;
			} 
		}

	}
	private void printAlldata() {
		for(User user : userM.getList()) {
			System.out.println(user);
		}
	}

	private int inputNumber(String msg) {
		System.out.print(msg + " : ");
		String input = this.scan.next();

		int number = -1;
		try {
			number = Integer.parseInt(input);
		} catch (Exception e) {
			System.out.println("숫자를 입력하세요.");
		}
		return number;
	}

	private void printMenu() {
		System.out.println("1. 회원가입");
		System.out.println("2. 회원탈퇴");
		System.out.println("3. 로그인");
		System.out.println("4. 로그아웃");
		System.out.println("5. 계좌계설");
		System.out.println("6. 계좌철회");
		System.out.println("7. 계좌조회");
		System.out.println("8. 입금");
		System.out.println("9. 출금");
		System.out.println("10. 이체");
		System.out.println("11. 저장");
		System.out.println("12. 불러오기");
		System.out.println("13. 종료");
		
	}
}
