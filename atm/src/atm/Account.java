package atm;

public class Account {
	private int userCode; 		// R
	private int accNumber;		// R
	private int accPassword;	// R U
	private int money;			// U
	
	public Account(int userCode, int accNumber, int accPassword) {
		this.userCode = userCode;
		this.accNumber = accNumber;
		this.accPassword = accPassword;
	}

	public Account(int userCode, int accNumber, int accPassword, int money) {
		this.userCode = userCode;
		this.accNumber = accNumber;
		this.accPassword = accPassword;
		this.money = money;
	}

	public int getUserCode() {
		return userCode;
	}

	public int getAccNumber() {
		return accNumber;
	}
	
	public int getAccPassword() {
		return accPassword;
	}

	public void setAccPassword(int accPassword) {
		this.accPassword = accPassword;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}	
	
	@Override
	public String toString() {
		
		return String.format("%d(%d) : %d", this.accNumber, this.accPassword, this.money);
	}
}
