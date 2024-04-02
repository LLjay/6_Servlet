package com.kh.board.service;
import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.board.model.dao.BoardDao;
import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.Category;
import com.kh.common.vo.PageInfo;

public class BoardService {
//	쿼리 쭉 짜놓고 더미 데이터 짜놓고 폴더 구조 짜놓고 시작 (Service, Dao)
	public int selectListCount() {
		Connection conn = getConnection();
		int listCount = new BoardDao().selectListCount(conn);
		
		close(conn);
		return listCount;
	}
	
	public ArrayList<Board> selectList(PageInfo pi) {
		Connection conn = getConnection();
		ArrayList<Board> list = new BoardDao().selectList(conn, pi);
		
		close(conn);
		return list;
	}
	
	public Board increaseCount(int boardNo) {
		Connection conn = getConnection();
		
		BoardDao bDao = new BoardDao();
		
		int result = bDao.increaseCount(conn, boardNo);
		Board b = null;
		
		if (result > 0) {
			commit(conn);
			b = bDao.selectBoard(conn, boardNo);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return b;
	}
	
	public ArrayList<Category> selectCategoryList() {
		Connection conn = getConnection();
		ArrayList<Category> list = new BoardDao().selectCategoryList(conn);
		
		close(conn);
		return list;
	}
	
	public int insertBoard(Board b, Attachment at) {
		Connection conn = getConnection();
		BoardDao bDao = new BoardDao();
//		Board부터 먼저 올려줘야 첨부파일의 키가 생성 되어 첨부파일을 저장할 수 있게 됨
		int result1 = bDao.insertBoard(conn, b);
		int result2 = 1;
//		at을 넣는 메소드 실행은 at이 null이 아닐 때여야 함
		
		if (at != null) {
			result2 = bDao.insertAttachment(conn, at);
		}
		
		if (result1 > 0 && result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result1 * result2;
	}
}
