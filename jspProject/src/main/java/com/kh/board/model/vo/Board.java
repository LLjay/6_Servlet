package com.kh.board.model.vo;

import java.sql.Date;

public class Board {
	private int boardNo;
	private int boardType;
	private String categoryNo;
//	작성 기능 같은 것을 사용할 때 카테고리 번호 | 조회 카테고리 명 등으로 쓸 수 있도록 String으로 함
//	여기에 번호 말고 다른 내용들을 join 해서 넣어올 수 있도록 String으로 바꿔놓은 거구나
	private String boardTitle;
	private String boardContent;
	private String boardWriter; 
//	작성 기능 시 회원 번호 | 조회 기능 회원 아이디
	private int count;
	private String createDate;
	private String status;
	private String titleImg;
	
	public Board() {
		super();
	}

	public Board(int boardNo, String categoryNo, String boardTitle, String boardContent, String boardWriter,
			String createDate) {
		super();
		this.boardNo = boardNo;
		this.categoryNo = categoryNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
		this.createDate = createDate;
	}

	public Board(int boardNo, String categoryNo, String boardTitle, String boardWriter, int count, String createDate) {
		super();
		this.boardNo = boardNo;
		this.categoryNo = categoryNo;
		this.boardTitle = boardTitle;
		this.boardWriter = boardWriter;
		this.count = count;
		this.createDate = createDate;
	}

	public Board(int boardNo, int boardType, String categoryNo, String boardTitle, String boardContent,
			String boardWriter, int count, String createDate, String status, String titleImg) {
		super();
		this.boardNo = boardNo;
		this.boardType = boardType;
		this.categoryNo = categoryNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
		this.count = count;
		this.createDate = createDate;
		this.status = status;
		this.titleImg = titleImg;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getBoardType() {
		return boardType;
	}

	public void setBoardType(int boardType) {
		this.boardType = boardType;
	}

	public String getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public String getTitleImg() {
		return titleImg;
	}

	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}

	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardType=" + boardType + ", categoryNo=" + categoryNo + ", boardTitle="
				+ boardTitle + ", boardContent=" + boardContent + ", boardWriter=" + boardWriter + ", count=" + count
				+ ", createDate=" + createDate + ", status=" + status + ", titleImg=" + titleImg + "]";
	}

}
