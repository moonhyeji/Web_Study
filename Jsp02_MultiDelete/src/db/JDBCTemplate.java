package com.muldel.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class JDBCTemplate {

	


	public static Connection getConnection() {
		// 1. ojdbc6.jar 에 있는 oracle.jdbc.driver 패키지 안에 OracleDriver 클래스를 사용하겠다.
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// 2.
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "kh";
		String password = "kh";

		Connection con = null; // 데이터베이스 연결

		try {
			con = DriverManager.getConnection(url, user, password);
			con.setAutoCommit(false);  //<- 커넥션 객체가 자동으로 commit하는 것을 금지 .


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return con;
	}
	
	//5.db종료 
	public static void close(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt) {
		try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void close(Connection con) {  //
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//얘네 한번에 닫아도 됨. 밑에 두개처럼. 
	///////셀렉트일 때 
	/*
	public static void close(ResultSet rs.Statement stmt, Connection con) {
		
	}
	*/
	
	//인서트. 업데ㅣ트 딜리트 일  때 쓸라고, -> statement stmt, connection con  따로 
	
	
	
	
	
	//transaction -commit 과 rollback 작성 
	public static void commit(Connection con) {
		try {
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void rollback(Connection con) {
		try {
			con.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}























