package atm;

import java.util.ArrayList;

public class AccountManager {

	private ArrayList<Account> list = new ArrayList<Account>();
	private Atm atm = new Atm();

	private AccountManager() {
	}

	private static AccountManager instance = new AccountManager();

	public static AccountManager getInstance() {
		return instance;
	}

	public void createAccount(User user) {
		Account acc = null;

		int accNumber = generateRandomCode();
		int accPassword = Atm.inputNumber("계좌 비밀번호");

		if (accPassword != -1) {
			acc = new Account(user.getUserCode(), accNumber, accPassword);
			this.list.add(acc);

			// AccountManager 의 list 에 추가된 객체를 생성과 동시에 반환 받음
			// -> User 객체가 가진 acc 즐겨찾기 목록에도 추가
			ArrayList<Account> accs = user.getAccs();
			accs.add(acc);
			user.setAccs(accs);
		}
	}

	private int generateRandomCode() { // ####-####
		int code = 0;

		while (true) {
			code = (int) (Math.random() * 9000) + 1000;

			boolean dupl = false;
			for (Account acc : this.list) {
				if (acc.getAccNumber() == code)
					dupl = true;
			}
			if (!dupl)
				break;
		}

		return code;
	}

	public void deleteAcc() {
		int idx = -1;
		System.out.print("계좌번호 : ");
		int accNumber = Atm.scanner.nextInt();

		for (int i = 0; i < this.list.size(); i++) {
			if (this.list.get(i).getAccNumber() == accNumber) {
				idx = i;
			}
		}
		if (idx != -1) {
			System.out.println("1. " + this.list.get(idx));
			this.list.remove(idx);
			
		} else {
			System.out.println("계좌번호 재확인");
		}
	}

	private boolean isLogin() {
		return atm.getLog() == -1 ? false : true;
	}

	public void viewBalance() {
		if(isLogin()) {
			int accNum = Atm.inputNumber("계좌번호");
			int idx = -1;
			for(int i = 0; i < this.list.size(); i++) {
				if(accNum == this.list.get(i).getAccNumber()) {
					idx = i;
				}
			}
			if(idx != -1) {
				System.out.println(this.list.get(idx));
			}
		}else {
			System.out.println("로그인 후 이용가능");
		}
	}

	public void inputMoney() {
		if(isLogin()) {
			System.out.print("입금할 계좌 : ");
			int acc = Atm.scanner.nextInt();
			int idx = -1;
			for(int i = 0; i < this.list.size(); i++) {
				if(acc == this.list.get(i).getAccNumber()) {
					idx = i;
				}
			}
			if(idx != -1) {
				System.out.print("입금 금액 : ");
				int money = Atm.scanner.nextInt();
				if(money > 0) {
					this.list.get(idx).setMoney(this.list.get(idx).getMoney() + money);
					System.out.println("입금 완료");
					System.out.printf("입금 후 금액 : %d",this.list.get(idx).getMoney());
				}
			}else {
				System.out.println("존재하지 않는 계좌입니다.");
			}
		}else {
			System.out.println("로그인 후 이용가능");
		}
	}

	public void outMoney() {
		if(isLogin()) {
			System.out.print("출금할 계좌 : ");
			int acc = Atm.scanner.nextInt();
			int idx = -1;
			for(int i = 0; i < this.list.size(); i++) {
				if(acc == this.list.get(i).getAccNumber()) {
					idx = i;
				}
			}
			if(idx != -1) {
				System.out.print("출금 금액 : ");
				int money = Atm.scanner.nextInt();
				if(this.list.get(idx).getMoney() - money >= 0) {
					this.list.get(idx).setMoney(this.list.get(idx).getMoney() - money);
					System.out.println("출금 완료");
					System.out.printf("입금 후 금액 : %d",this.list.get(idx).getMoney());
				}
			}else {
				System.out.println("존재하지 않는 계좌입니다.");
			}
		}else {
			System.out.println("로그인 후 이용가능");
		}
	}

	public void moveMoney() {
		if(isLogin()) {			
			System.out.print("빠질 계좌 : ");
			int out = Atm.scanner.nextInt();
			System.out.print("받을 계좌 : ");
			int in = Atm.scanner.nextInt();
			
			int idx1 = -1;
			int idx2 = -1;
			for(int i = 0; i < this.list.size(); i++) {
				if(out == this.list.get(i).getAccNumber()) {
					idx1 = i;
				}else if(in == this.list.get(i).getAccNumber()) {
					idx2 = i;
				}
			}
			if(idx1 != -1 && idx2 != -1) {
				System.out.print("이체할 금액 : ");
				int money = Atm.scanner.nextInt();
				
				this.list.get(idx1).setMoney(this.list.get(idx1).getMoney() - money);
				this.list.get(idx2).setMoney(this.list.get(idx2).getMoney() + money);
				System.out.printf("빠진 후 금액(%d) : %d",this.list.get(idx1).getAccNumber(), this.list.get(idx1).getMoney());
				System.out.printf("받은 후 금액(%d) : %d",this.list.get(idx2).getAccNumber(), this.list.get(idx2).getMoney());
			}
		}
	}
}