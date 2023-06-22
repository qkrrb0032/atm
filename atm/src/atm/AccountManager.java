package atm;

import java.util.ArrayList;

public class AccountManager {	
	private static AccountManager instance = new AccountManager();	
	private ArrayList<Account> list = new ArrayList<Account>();	
	
	private AccountManager() {}

	public static AccountManager getInstance() {
		return instance;
	}
}
