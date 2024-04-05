package com.kh.member.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
//import static com.kh.common.JDBCTemplate.*; // 여기서는 * 해도 됨, 다 static으로 만들었으니까
//import com.kh.common.JDBCTemplate;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;

import com.kh.member.model.dao.MemberDao;
// 이렇게 쓰면 저 키워드의 것들만 가져옴, close도 다 가져옴
import com.kh.member.model.vo.Member;

public class MemberService {
	
	public Member loginMember(String userId, String userPwd) {
		Connection conn = getConnection();
		Member mem = new MemberDao().loginMember(conn, userId, userPwd);
		close(conn);
		
		return mem;
	}
	
//	public int insertMember(String userId, String userPwd, String userName, String phone, String email, String address, String[] interest) {
//		Connection conn = getConnection();
//		int result = new MemberDao().insertMember(conn, userId, userPwd, userName, phone, email, address, interest);
//		
//		close(conn);
//		return result;
//	}
	
	public int insertMember(Member m) {
		Connection conn = getConnection();
		int result = new MemberDao().insertMember(conn, m);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	public Member updateMember(Member m) {
		Connection conn = getConnection();
		int result = new MemberDao().updateMember(conn, m);
		Member updateMem = null;
		
		if (result > 0) {
			commit(conn);
			// 갱신된 회원 객체 다시 조회해오기
			updateMem = new MemberDao().selectMember(conn, m.getUserId());
		} else {
			rollback(conn);
		}
		
		close(conn);
		return updateMem;
	}
	
	public Member updatePwd(String userId, String userPwd, String updatePwd) {
		Connection conn = getConnection();
		int result = new MemberDao().updatePwd(conn, userId, userPwd, updatePwd);
		
//		그대로 바뀐 객체를 가져오면 안 됨, 세션에 다시 넣어주려면 반드시 업데이트 된 데이터를 다시 가져오는 것으로 증명해야 함
		
		Member updateMem = null;
		if (result > 0) {
			commit(conn);
			updateMem = new MemberDao().selectMember(conn, userId);
		} else {
			rollback(conn);
		}
		close(conn);
		return updateMem;
	}
	
	public int deleteMember(Member m) {
		Connection conn = getConnection();
		int result = new MemberDao().deleteMember(conn, m);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public int idCheck(String checkId) {
		Connection conn = getConnection();
		int count = new MemberDao().idCheck(conn, checkId);
		
		close(conn);
		return count;
	}
}
