package edu.kh.jdbc.board.model.dao;

public class BoardDAO {
	
	private int boardNo; // 게시글 번호
	private String boardTitle; // 게시글 제목
	private String boardContent; // 게시글 내용
//	private String boardNo; // 작성일
	private int readCount; // 조회수
	private char deleteFl; // 삭제여부(Y/N)
	private int memberNo; // 회원번호(FK)

}
