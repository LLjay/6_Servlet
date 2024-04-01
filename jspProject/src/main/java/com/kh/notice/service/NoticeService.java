package com.kh.notice.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.member.model.vo.Member;
import com.kh.notice.model.dao.NoticeDao;
import com.kh.notice.model.vo.Notice;


public class NoticeService {

	public ArrayList<Notice> selectNoticeList(){
		Connection conn = getConnection();
		ArrayList<Notice> list = new NoticeDao().selectNoticeList(conn);
	
		close(conn);
		return list;
	}
	
	public int insertNotice(String title, String content, Member loginUser) {
		Connection conn = getConnection();
		int result = new NoticeDao().insertNotice(conn, title, content, loginUser);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	public Notice increaseCount(int noticeNo) {
		Notice n = null;
		Connection conn = getConnection();
		int result = new NoticeDao().increaseCount(conn, noticeNo);

		if (result > 0) {
			commit(conn);
			n = new NoticeDao().selectNotice(conn, noticeNo);
		} else {
			rollback(conn);
		}
		close(conn);
		return n;
	}
	
	public Notice selectNotice(int noticeNo) {
		Connection conn = getConnection();
		Notice n = new NoticeDao().selectNotice(conn, noticeNo);
		
		close(conn);
		return n;
	}
	
//	public Notice updateNotice(Notice n) {
//		Connection conn = getConnection();
//		int result = new NoticeDao().updateNotice(conn, n);
//		Notice updateN = null;
//		
//		if (result > 0) {
//			commit(conn);
//			updateN = new NoticeDao().selectNotice(conn, n.getNoticeNo());
//		} else {
//			rollback(conn);
//		}
//		
//		return updateN;
//	}
	
	public int updateNotice(Notice n) {
		Connection conn = getConnection();
		int result = new NoticeDao().updateNotice(conn, n);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	public int deleteNotice(int noticeNo) {
		Connection conn = getConnection();
		int result = new NoticeDao().deleteNotice(conn, noticeNo);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
}
