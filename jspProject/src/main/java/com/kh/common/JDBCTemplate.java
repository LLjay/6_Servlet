package com.kh.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	
	// 1. Connection 객체 생성한 후 Connection 객체 반환 시켜주는 getConnection 메소드
	
	public static Connection getConnection() {
		Connection conn = null;
		Properties prop = new Properties();
		
		// 읽어들이고자 하는 classes 폴더 내의 driver.properties 파일의 물리적인 경로 가져오기
		String filePath = JDBCTemplate.class.getResource("/db/driver/driver.properties").getPath();
//		경로를 String 변수에 넣어서 가져오는 것
//		절대경로 루트가 classes 파일이(폴더가?) 된다?
		// System.out.println(filePath);
		// /C:/workspace/6_servlet_git/jspProject/src/main/webapp/WEB-INF/classes/db/driver/driver.properties
		
		try {
			prop.load(new FileInputStream(filePath));
			
			// jdbc driver 등록
			Class.forName(prop.getProperty("driver"));
			
			// 접속하고자 하는 db의 url, 계정명, 비밀번호 제시해서 Connection 객체 생성
			conn = DriverManager.getConnection(prop.getProperty("url"), 
											prop.getProperty("username"),
											prop.getProperty("password"));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}
	// 이제 코드는 자바가 아닌 서버에서 돌아갈 것
	// 모든 경로는 실제 실행될 때 시작하는 시점에서부터 써줘야 함
	
	// 이제부터는 무조건 프론트로부터 출발
	
	// 2. Connection 객체 전달 받아서 commit 처리 해주는 메소드
	
	public static void commit(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 3. Connection 객체 전달 받아서 rollback 처리 해주는 메소드
	
	public static void rollback(Connection conn) {
		try {
			
			if (conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 4. Connection 객체 전달 받아서 close(반납) 처리 해주는 메소드
	public static void close(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 5. Statement 관련 객체 전달 받아서 반납 시켜주는 메소드
	public static void close(Statement stmt) {
		try {
			if (stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 6. ResultSet 객체 전달 받아서 반납 시켜주는 메소드
	public static void close(ResultSet rset) {
		try {
			if (rset != null && !rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
