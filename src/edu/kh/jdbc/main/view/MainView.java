package edu.kh.jdbc.main.view;

import java.util.Scanner;

import edu.kh.jdbc.common.Session;
import edu.kh.jdbc.main.model.service.MainService;

public class MainView {
	
	private Scanner sc = new Scanner(System.in);
	
	private MainService service = new MainService();
	
	/** 메인 메뉴 출력
	 * 
	 */
	public void mainMenu() {
		
		int input = 0;
		
		do {
			
			try {
				
				if(Session.loginMember == null) { // 로그인 X
					
					System.out.println("\n ========== 회원제 게시판 프로그램 ==========\n");
					System.out.println("1. 로그인");
					System.out.println("2. 회원가입");
					System.out.println("0. 프로그램 종료");
					
					System.out.print("\n메뉴선택 : ");
					input = sc.nextInt();
					sc.nextLine();
					
					switch(input) {
					case 1 : login(); break;
					case 2 : singUp(); break;
					case 0 : System.out.println("\n========== 프로그램 종료 ==========\n");
					default : System.out.println("\n *** 메뉴 번호만 입력해 주세요 ***\n");
					}
					
				} else { // 로그인 O
					
					System.out.println("\n ========== 로그인 메뉴 ==========\n");
					System.out.println("1. 회원 기능");
					System.out.println("2. 게시판 기능");
					System.out.println("3. 로그아웃");
					System.out.println("0. 프로그램 종료");
					
					System.out.print("\n메뉴선택 : ");
					input = sc.nextInt();
					sc.nextLine();
					
					switch(input) {
					case 1 :  break;
					case 2 :  break;
					case 3 : 
						System.out.println("\n === 로그아웃 되었습니다. ===");
						
						Session.loginMember = null;
						// 참조하고 있던 로그인 회원 객체를 없앰
						
						break;
					case 0 : System.out.println("\n========== 프로그램 종료 ==========\n");
					default : System.out.println("\n *** 메뉴 번호만 입력해 주세요 ***\n");
					}
					
				}
				
				
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		} while(input != 0);
		
	}


	/** 로그인
	 * 
	 */
	private void login() {
		System.out.println("\n[로그인]\n");
		
		System.out.print("아이디 : ");
		String memberId = sc.next();
		
		System.out.print("비밀번호 : ");
		String memberPw = sc.next();
		
		try {
			
			// 로그인 서비스 호출 후 결과 반환 받기
			// -> 반환 받은 결과는 Session.loginMember에 저장
			Session.loginMember = service.login(memberId, memberPw);
			
			if(Session.loginMember == null) { // 로그인 실패
				System.out.println("\n ** 아이디 / 비밀번호가 일치하지 않습니다. **\n ");
			} else {
				System.out.printf("\n === %s 님 환영합니다 === \n\n",
									Session.loginMember.getMemberName()
								);
			}
			
		} catch(Exception e) {
			System.out.println("\n *** 로그인 중 예외발생 ***\n");
			e.printStackTrace();
		}
		
	}
	
	private void singUp() {
		System.out.println("\n[회원가입]\n");
		
		System.out.print("아이디 : ");
		String memberId = sc.next();
		
		System.out.print("비밀번호 : ");
		String memberPw = sc.next();
		
		System.out.print("비밀번호 재확인 : ");
		String memberPwRe = sc.next();
		
		System.out.print("이름 : ");
		String memberName = sc.next();
		
		System.out.print("성별 (M/F) : ");
		String memberGender = sc.next();

		
		try {
			
			Session.loginMember = service.singUp(memberId, memberPw, memberPwRe, memberName, memberGender);
			
		} catch(Exception e) {
			System.out.println("\n *** 회원가입 중 예외발생 ***\n");
			e.printStackTrace();
		}
		
	}

}





























