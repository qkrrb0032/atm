package atm;

import java.util.ArrayList;

public class UserManager {
	
	private AccountManager accManager = AccountManager.getInstance();
	private Atm atm = new Atm("mybasdsad");
	
	private ArrayList<User> list = new ArrayList<User>();
	
	// Design Pattern (GOF) 설계 패턴 중, 
	// 싱글 인스턴스를 만드는 
	// Singleton Pattern 을 사용해보자 
	
	// 1) 생성자를 숨긴다 private 
	private UserManager() {}
	
	// 2) 클래스 내부에서 단일 인스턴스를 생성해준다 
	private static UserManager instance = new UserManager();
	
	// 3) 외부에서 단일 인스턴스를 참조할 수 있도록 -> getter 를 제공한다 
	public static UserManager getInstance() {
		return instance;
	}
	
	public void joinUser() {
		int userCode = generateRandomCode();
		System.out.print("id : ");
		String id = Atm.scanner.next();
		System.out.print("password : ");
		String password = Atm.scanner.next();
		System.out.print("name : ");
		String name = Atm.scanner.next();
		
		if(!duplId(id)) {
			User user = new User(userCode, name, id, password);
			this.list.add(user);
			accManager.createAccount(user);
			
			
			System.out.println("회원가입 완료");
		} else {
			System.err.println("중복되는 아이디 입니다.");
		}
	}
	
	public User getUserByUserCode(int log) {	// log : userCode
		for(User user : this.list) {
			if(user.getUserCode() == this.list.get(log).getUserCode())
				return user;
		}
		return null;
	}
	
	public ArrayList<User> getList() {
		return (ArrayList<User>) this.list.clone();
	}
	
	private boolean duplId(String id) {
		boolean dupl = false;
		for(User user : this.list) {
			if(user.getId().equals(id))
				dupl = true;
		}
		return dupl;
	}
	
	private int generateRandomCode() {
		int code = 0;
		
		while(true) {
			code = (int)(Math.random() * 9000) + 1000;
			
			boolean dupl = false;
			for(User user : this.list) {
				if(user.getUserCode() == code) 
					dupl = true;
			}			
			if(!dupl)
				break;
		}		
		return code;
	}

	public void loginUser() {		
		System.out.print("id : ");
		String id = Atm.scanner.next();
		System.out.print("password : ");
		String password = Atm.scanner.next();	
		
		for(int i = 0; i < this.list.size(); i++) {
			
			String chkId = this.list.get(i).getId();
			String chkPw = this.list.get(i).getPassword();
			
			if(id.equals(chkId) && password.equals(chkPw)) {
				atm.setLog(i);
			}
		}
		if(atm.getLog()!= -1) {
			System.out.printf("%s님 환영합니다\n", this.list.get(new Atm().getLog()).getId());
		}else {
			System.out.println("로그인 실패");
		}
	}

	public void logoutUser() {
		if(isLogin()) {
			atm.setLog(-1);
			System.out.println("로그아웃 성공");
		}
	}

	public void leaveUser() {
		if(isLogin()) {
			System.out.print("password : ");
			String password = Atm.scanner.next();
			
			String chkPw = this.list.get(atm.getLog()).getPassword();
			if(chkPw.equals(password)) {
				this.list.remove(atm.getLog());
				//accManager.deleteAcc();
			}
		}else {
			System.out.println("들어감?");
		}
	}

	private boolean isLogin() {
		return atm.getLog() == -1 ? false : true;
	}
}




















