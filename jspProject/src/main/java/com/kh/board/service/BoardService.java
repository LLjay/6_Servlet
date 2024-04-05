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
import com.kh.board.model.vo.Reply;
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
	
	public Attachment selectAttachment(int boardNo) {
		Connection conn = getConnection();
		Attachment at = new BoardDao().selectAttachment(conn, boardNo);
		
		close(conn);
		return at;
	}
	
	public Board selectBoard(int boardNo) {
		Connection conn = getConnection();
		Board b = new BoardDao().selectBoard(conn, boardNo);
		
		close(conn);
		return b;
	}
	
	public int updateBoard(Board b, Attachment at) {
		// 새로운 첨부 파일 X							b, null -> board update
		// 새로운 첨부 파일 O, 기존 첨부 파일 O			b, fileNo at -> board update, attachment update
		// 새로운 첨부 파일 O, 기존 첨부 파일 X			b, refBoardNo가 담긴 at -> board update, attachment insert

		Connection conn = getConnection();
		BoardDao bDao = new BoardDao();
		int result1 = bDao.updateBoard(conn, b);
		
		int result2 = 1;
//		at이 null일 수 있기 때문에 null이면 무조건 성공이어야 함
		
		if (at != null) {
			if(at.getFileNo() != 0) { // 기존 첨부 파일이 있었을 경우 -> 첨부파일 넘버가 1부터 시작하는 걸로 설정 해줬으니까 파일넘버가 0에 해당하는 파일은 없음
				result2 = bDao.updateAttachment(conn, at);
			} else { // 기존 첨부 파일이 없으므로 insert
				result2 = bDao.insertNewAttachment(conn, at);
			}
		}
		
		if (result1 > 0 && result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result1 * result2;
	}
	
	public int insertThumbnailBoard(Board b, ArrayList<Attachment> list) {
		Connection conn = getConnection();
		int result1 = new BoardDao().insertThumbnailBoard(conn, b);
//		비슷해보여도 메소드는 다 쪼개두는 게 나중에 편함
		
		int result2 = new BoardDao().insertAttachmentList(conn, list);
		
		if (result1 > 0 && result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result1 * result2;
	}
	
	public ArrayList<Board> selectThumbnailList(){
		Connection conn = getConnection();
		ArrayList<Board> list = new BoardDao().selectThumbnailList(conn);
		
		close(conn);
		return list;
	}
	
	public ArrayList<Attachment> selectAttachmentList(int boardNo){
		Connection conn = getConnection();
		ArrayList<Attachment> list = new BoardDao().selectAttachmentList(conn, boardNo);
		
		close(conn);
		return list;
	}
	
	public int insertReply(Reply r) {
		Connection conn = getConnection();
		int result = new BoardDao().insertReply(conn, r);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public ArrayList<Reply> selectReplyList(int boardNo){
		Connection conn = getConnection();
		ArrayList<Reply> list = new BoardDao().selectReplyList(conn, boardNo);
		
		close(conn);
		return list;
	}
}
