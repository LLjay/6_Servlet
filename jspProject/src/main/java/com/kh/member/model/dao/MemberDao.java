package com.kh.member.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import static com.kh.common.JDBCTemplate.close;

import com.kh.member.model.vo.Member;

public class MemberDao {
	
	private Properties prop = new Properties();
	
	public MemberDao() { // 생성자
		String filePath = MemberDao.class.getResource("/db/sql/member-mapper.xml").getPath();
//		모든 파일이 실행되는 폴더는 classes
		
		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public Member loginMember(Connection conn, String userId, String userPwd) {
		// SELECT문 -> Member 객체 (한 행) 조회 => ResultSet 객체
		
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("loginMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			rset = pstmt.executeQuery(); // 조회 결과가 있다면 한 행 반환 | 없다면 반환 X
//			rset은 무조건 null은 아님
			
			if(rset.next()) {
				m = new Member(
							rset.getInt("user_no"),
							rset.getString("user_id"),
							rset.getString("user_pwd"),
							rset.getString("user_name"),
							rset.getString("phone"),
							rset.getString("email"),
							rset.getString("address"),
							rset.getString("interest"),
							rset.getDate("enroll_date"),
							rset.getDate("modify_date"),
							rset.getString("status")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return m;
	}
}
