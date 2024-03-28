package com.kh.member.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import com.kh.member.model.vo.Member;

public class MemberDao {
	
	private Properties prop = new Properties();
	
	public MemberDao() { // 생성자
		String filePath = MemberDao.class.getResource("/db/sql/member-mapper.xml").getPath();
//		왜 db부터 시작인가?
//		classes 안에 들어가면 모든 패키지 시작 (com/db부터 시작)
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
				System.out.println(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return m;
	}

//	public int insertMember(Connection conn, String userId, String userPwd, String userName, String phone, String email, String address, String[] interest) {
//		int result = 0;
//		PreparedStatement pstmt = null;
//		String sql = prop.getProperty("insertMember");
//		
//		String interestStr = String.join(",", interest);
//		
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, userId);
//			pstmt.setString(2, userPwd);
//			pstmt.setString(3, userName);
//			pstmt.setString(4, phone);
//			pstmt.setString(5, email);
//			pstmt.setString(6, address);
//			pstmt.setString(7, interestStr);
//			
//			result = pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(pstmt);
//		}
//		
//		return result;
//	}
	
	public int insertMember(Connection conn, Member m) {
		// insert 문 => 처리된 행 수 => 트랜잭션 처리
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
//			데이터베이스와 연결하는 Connection 객체를 이용해 sql을 날려준 결과값을 반환
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getPhone());
			pstmt.setString(5, m.getEmail());
			pstmt.setString(6, m.getAddress());
			pstmt.setString(7, m.getInterest());
			
			result = pstmt.executeUpdate();
//			결과 정보가 들어있는 pstmt에서 update와 query 중 얻고 싶은 결과를 선택해 메소드 실행 후 결과를 담는 것

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateMember(Connection conn, Member m) {
		// update -> 처리된 행 수 반환 => 트랜잭션 처리
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMember");

		try {
			pstmt = conn.prepareStatement(sql); // 미완성된 sql이다.
			
			pstmt.setString(1, m.getUserName());
			pstmt.setString(2, m.getPhone());
			pstmt.setString(3, m.getEmail());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getInterest());
			pstmt.setString(6, m.getUserId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public Member selectMember(Connection conn, String userId) {
		// select -> resultset(한 행) -> Member 객체
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
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

	public int updatePwd(Connection conn, String userId, String userPwd, String updatePwd) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updatePwd");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, updatePwd);
			pstmt.setString(2, userId);
			pstmt.setString(3, userPwd);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
}
