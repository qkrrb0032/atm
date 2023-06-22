package atm;

import java.util.ArrayList;

public class AccountManager {	
	private static AccountManager instance = new AccountManager();	
	private ArrayList<Account> list = new ArrayList<Account>();	
	
	private AccountManager() {}
	
	public static AccountManager getInstance() {
		return instance;
	}

	public void createAcc() {
		int log = isLogin();
		if(log != -1) {
			System.out.print("password : ");
			String password = Atm.scan.next();
			
			String userPassword = UserManager.getInstance().getList().get(log).getPassword(); 
			if(userPassword.equals(password)) {
				int userCode = UserManager.getInstance().getList().get(log).getUserCode();
				System.out.print("accNumber : ");
				int accNumber = Atm.scan.nextInt();
				System.out.print("accPassword : ");
				int accPassword = Atm.scan.nextInt();
				if(!duplAccNum(accNumber)) {				
					Account account = new Account(userCode, accNumber, accPassword);
					this.list.add(account);
				}else {
					System.out.println("중복되는 계좌번호입니다.");
				}
			}else {
				System.out.println("비밀번호를 다시 확인하세요");
			}
		}else {
			System.out.println("로그인 후 이용");
		}
	}	
	private boolean duplAccNum(int accNumber) {
		boolean dupl = false;
		for(Account account : this.list) {
			if(account.getAccNumber() == accNumber) {
				dupl = true;
			}
		}		
		return dupl;		
	}

	public void deleteAcc() {
				
	}
	
	private int isLogin() {		
		return UserManager.getInstance().getLog();
	}
}
