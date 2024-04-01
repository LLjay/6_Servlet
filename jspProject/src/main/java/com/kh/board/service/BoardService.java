package com.kh.board.service;
import static com.kh.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import com.kh.board.model.dao.BoardDao;
import static com.kh.common.JDBCTemplate.close;

public class BoardService {
//	쿼리 쭉 짜놓고 더미 데이터 짜놓고 폴더 구조 짜놓고 시작 (Service, Dao)
	public int selectListCount() {
		Connection conn = getConnection();
		int listCount = new BoardDao().selectListCount(conn);
		
		close(conn);
		return listCount;
	}
}
