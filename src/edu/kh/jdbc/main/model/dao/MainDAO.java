package edu.kh.jdbc.main.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import static edu.kh.jdbc.common.JDBCTemplate.*;


import edu.kh.jdbc.member.model.dto.Member;

public class MainDAO {
	
	// 필드
	// JDBC 겍체 참조 변수
	private Statement stmt; // SQL 수행, 결과 반환
	private PreparedStatement pstmt; // 플레이스홀더를 포함한 SQL 세팅/수행
	private ResultSet rs; // SELECT 수행 결과 저장
	
	private Properties prop;
	// - Map<String, String> 형태
	// - XML 파일 입/출력 메서드를 제공
	
	// 기본 생성자
	public MainDAO() {
		
		// DAO 객체가 생성될때 XML 파일을 읽어와 Properties 객체에 저장
		try {
			
			prop = new Properties();
			prop.loadFromXML( new FileInputStream("main-sql.xml") );
			
			// -> prop.getProperty("key") 호출
			// --> value (SQL) 반환
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	

	/** 로그인 DAO (아이디, 비밀번호 일치 회원 조회)
	 * @param conn
	 * @param memberId
	 * @param memberPw
	 * @return
	 */
	public Member login(Connection conn, String memberId, String memberPw) throws Exception{
		
		// 1. 결과 저장용 변수 선언
		Member member = null;
		
		try {
			
			// 2. SQL 작성 후 수행
			String sql = prop.getProperty("login");
			
			pstmt = conn.prepareStatement(sql);
			
			// 플레이스 홀더에 알맞은 값 대입
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);
			
			rs = pstmt.executeQuery(); // SELECT 수행 후 결과 반환 받기
			
			// 3. 조회 결과를 1행씩 접근해서 얻어오기
			if(rs.next()) {
				int memberNo = rs.getInt("MEMBER_NO");
				
				// String memberId = rs.getStirng("MEMBER_ID");
				// 입력 받은 아이디 == 조회한 아이디
				// -> DB에서 얻어오 필요가 없음
				
				String memberName = rs.getString("MEMBER_NM");
				String memberGender = rs.getString("MEMBER_GENDER");
				String enrollDate = rs.getString("ENROLL_DT");
				
				
				// Member 객체 생성 후 값 세팅
				member = new Member();
				
				member.setMemberNo(memberNo);
				member.setMemberId(memberId);
				member.setMemberName(memberName);
				member.setMemberGender(memberGender);
				member.setEnrollDate(enrollDate);
			}
			
		} finally {
			// 4. 사용한 JDBC 객체 자원 반환
			close(rs);
			close(pstmt);
		}
		// 5. 결과반환
		return member;
	}


	public Member singUp(Connection conn, String memberId, String memberPw,
			String memberPwRe, String memberName, String memberGender) throws Exception {
		
		Member member = null;
		
		try {
			
			String sql = prop.getProperty("singUp");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);
			pstmt.setString(3, memberName);
			pstmt.setString(4, memberGender);

			
		} finally {
			
		}
		
		return member;
	}

}






























