package com.mvc.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {

	
	public static Connection getConnection() {
		//static 으로 만드는 이유: 클라이언트가 서버에 연결 할 때마다 객체를 계속 만들면,
		//jdbc템플릭객체가 계속 서버에 쌓이기 때문에 , 객체 하나만 만들어서 계속 사용하려고 
		//static으로 쓴다. -> 얘를 dao에서 import static 해줘서 static 영역부분을 사용할 수 있도록 import한다.  
		
		
		//1
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String user="kh";
		String password ="kh";
		Connection con = null;
		
		
		
		//2
		try {
			con= DriverManager.getConnection(url,user,password);
			con.setAutoCommit(false);
		     System.out.println("2 계정연결 ");
		     
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	
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
		  //pstm 많이 쓰는데 stmt만 있는 이유 = 다형성 
	}
	
	
	
	
	public static void close(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
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
