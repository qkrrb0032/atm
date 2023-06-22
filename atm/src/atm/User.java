package atm;

import java.util.ArrayList;

public class User {
	private int userCode;		// R
	private String name;		// R
	private String id;			// R
	private String password;	// R U
	private int age;			// U
	private ArrayList<Account> accs;	// U

	public User(int userCode, String name, String id, String password) {
		this.accs = new ArrayList<Account>();
		this.userCode = userCode;
		this.name = name;
		this.id = id;
		this.password = password;
	}
	
	public User(int userCode, String name, String id, String password, int age) {
		this.accs = new ArrayList<Account>();
		this.userCode = userCode;
		this.name = name;
		this.id = id;
		this.password = password;
		this.age = age;
	}
	
	public User(int userCode, String name, String id, String password, int age, ArrayList<Account> accs) {
		this.accs = new ArrayList<Account>();
		this.userCode = userCode;
		this.name = name;
		this.id = id;
		this.password = password;
		this.age = age;
		this.accs = accs;
	}
	
	public int getUserCode() {
		return this.userCode;
	}

	public String getName() {
		return this.name;
	}

	public String getId() {
		return this.id;
	}
	
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public ArrayList<Account> getAccs() {
		return (ArrayList<Account>) this.accs.clone();	// 복제본 제공
	}

	public void setAccs(ArrayList<Account> accs) {
		this.accs = accs;
	}
	
	@Override
	public String toString() {
		String str = String.format("%s(%d) : %s/%s", this.name, this.userCode, this.id, this.password);
		for(int i = 0; i < this.accs.size(); i++) {
			str += "\n" + this.accs.get(i);
		}
		
		return str;
	}
	/*
	 * name (userCode) : id/password
	 * ㄴ accNumber1(accPassword) : balance1
	 * ㄴ accNumber2(accPassword) : balance2
	 * ㄴ accNumber3(accPassword) : balance3
	 * ㄴ accNumber4(accPassword) : balance4
	 */
}
	
















