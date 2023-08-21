package edu.kh.jdbc.member.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.common.Session;
import edu.kh.jdbc.member.model.dto.Member;
import edu.kh.jdbc.member.model.service.MemberService;

public class MemberView {
	
	Scanner sc = new Scanner(System.in);
	
	private MemberService service = new MemberService();
	
	/** 회원 기능 메뉴
	 * 
	 */
	public void memberMenu() {

		int input = 0;

		do {

			try {

				System.out.println("회원 기능");
				System.out.println("1. 내 정보 조회");
				System.out.println("2. 회원 목록 조회");
				System.out.println("3. 내 정보 수정");
				System.out.println("4. 비밀번호 변경");
				System.out.println("5. 회원탈퇴");
				System.out.println("9. 메인 메뉴로 돌아가기");
				System.out.println("0. 프로그램 종료");


				System.out.print("\n 메뉴 선택 : ");
				input = sc.nextInt();
				sc.nextLine();

				switch(input) {
				case 1 : selectMyInfo(); break;
				case 2 : selectMemberList(); break;
				case 3 : updateMember(); break;
//				case 4 : selectPassWord(); break;
				case 5 :  break;
				case 9 : System.out.println("\n 메인메뉴로 돌아갑니다. \n"); break;
				case 0 : System.out.println("\n 프로그램 종료 \n");
				// JVM 강제 종료 구문
				System.exit(0);
				default : System.out.println("\n 메뉴 번호만 입력하시오. \n");		
				}

			} catch(InputMismatchException e) {
				e.printStackTrace();
			}
		} while (input != 9);
			
	}

	
	



	/** 내 정보 조회
	 * 
	 */
	private void selectMyInfo() {
		System.out.println("\n === 내 정보 조회 === \n");
		
		// Session.loginMember 이용
		
		System.out.println("회원 번호 : " + Session.loginMember.getMemberNo());
		System.out.println("아이디 : " + Session.loginMember.getMemberId());
		System.out.println("이름 : " + Session.loginMember.getMemberName());
		
		if(Session.loginMember.getMemberGender().equals("M")){
			System.out.println("성별 : 남");
		} else {
			System.out.println("성별 : 남");
		}
		System.out.println("가입일 : " + Session.loginMember.getEnrollDate());
		
	}
	
	
	/** 회원 목록 조회
	 * 
	 */
	private void selectMemberList() {
		System.out.println("\n === 회원 목록 조회 === \n"); 
		
		try {
			// 회원 목록 조회 서비스 호출 후 결과 반환 받기.
			List<Member> memberList = service.selectMemberList();
			
			if(memberList.isEmpty()) {
				System.out.println("\n === 조회 결과가 없습니다. === \n");
			} else {
				
				for(int i = 0; i < memberList.size(); i++) {
					System.out.printf("%d\t\t%s\t\t%s\t\t%s \n", i+1,
							memberList.get(i).getMemberId(),
							memberList.get(i).getMemberName(),
							memberList.get(i).getMemberGender()
							);
				}
			}
			
			
		} catch(Exception e) {
			System.out.println("\n 회원 목록 조회중 예외발생 \n");
		}
		
	}

	

	/** 내 정보 수정
	 * 
	 */
	private void updateMember() {
		System.out.println("\n ===== 내 정보 수정 ===== \n");
		
		System.out.print("수정할 이름 : ");
		String memberName = sc.next();
		
		String memberGender = null;
		while(true) {
			System.out.println("수정할 성별 (M/F) : ");
			
			memberGender = sc.next().toUpperCase();
			
			if(memberGender.equals("M") || memberGender.equals("F")) {
				break;
			}
			
			System.out.println("[M/F 입력]");
		}
		try {
			int result = service.updateMember(memberName, memberGender, Session.loginMember.getMemberNo());
			
			
			if(result > 0) {
				System.out.println("\n === 수정 되었습니다. === \n");
				
				// DB와 Java 프로그램 데이터 동기화 필요!
				Session.loginMember.setMemberName(memberName);
				Session.loginMember.setMemberGender(memberGender);
				
				
			} else {
				System.out.println("\n === 수정 실패 === \n");
			}
			
			
			
		} catch(Exception e) {
			System.out.println("\n 내 정보 수정 중 예외 발생 \n");
			e.printStackTrace();
		}
		
		
	}


	
	
	
	
	
	
	
	
	
	
	

}






























