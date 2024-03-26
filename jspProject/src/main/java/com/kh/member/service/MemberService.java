package com.kh.member.service;

//import com.kh.common.JDBCTemplate;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.close;
//import static com.kh.common.JDBCTemplate.*; // 여기서는 * 해도 됨, 다 static으로 만들었으니까

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

}
