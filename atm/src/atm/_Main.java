package atm;

/*
 * ATM 예제 만들기
 * ㄴ 회원관리 (가입/탈퇴/로그인/로그아웃)
 * ㄴ 계좌관리 (계약/철회/조회)
 * ㄴ 뱅킹서비스 (입금/인출/이체)
 * ㄴ 파일관리 (저장/로드)
 */

public class _Main {
	public static void main(String[] args) {
		Atm system = new Atm("그린뱅크");
		system.run();
	}
}
