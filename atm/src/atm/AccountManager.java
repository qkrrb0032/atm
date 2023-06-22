package atm;

import java.util.ArrayList;

public class AccountManager {
	
	private ArrayList<Account> list = new ArrayList<Account>();
	
	private AccountManager(){}
	private static AccountManager instance = new AccountManager();
	public static AccountManager getInstance() {
		return instance;
	}
	
	public void createAccount(User user) {
		Account acc = null;
		
		int accNumber = generateRandomCode();
		int accPassword = Atm.inputNumber("계좌 비밀번호");
		
		if(accPassword != -1) {
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
		
		while(true) {
			code = (int)(Math.random() * 9000) + 1000;
			
			boolean dupl = false;
			for(Account acc : this.list) {
				if(acc.getAccNumber() == code) 
					dupl = true;
			}			
			if(!dupl)
				break;
		}
		
		return code;
	}

	public void deleteAcc() {
		int idx = -1;
		System.out.print("계좌번호 : ");
		int accNumber = Atm.scanner.nextInt();		
		
		for(int i = 0; i < this.list.size(); i++) {
			if(this.list.get(i).getAccNumber() == accNumber) {
				idx = i;
			}
		}
		if(idx != -1) {			
			System.out.println("계좌철회 성공");
		}else {
			System.out.println("계좌번호 재확인");
		}
	}

	public void viewBalance() {		
		int accNum = Atm.inputNumber("계좌번호");
		
	}
}