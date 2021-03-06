package com.answer.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {

	
	public static Connection getConnection() {
		

		//1
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	//2
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe"; 
	String user ="kh";
	String password="kh";

	Connection con = null;

	try {
		con = DriverManager.getConnection(url, user, password);
		con.setAutoCommit(false);
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return con;

	}


	//---------------------------------------------close 
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

	public static void close(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//----------------------------------------commit 
	public static void commit(Connection con) {
		try {
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//-------------------------------------------rollback 
	public static void rollback(Connection con) {
		try {
			con.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	
		
	
}
