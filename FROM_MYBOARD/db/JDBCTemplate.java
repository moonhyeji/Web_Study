package com.my.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {

	//static 이용하는 이유:
	//객체로 만들지 않고, class.method로 가져가려고 다 static으로 함.
	//필요할 때마다 class만들어야 하니까 이렇게 하지 말고, class.method  로 static 영역에 올려서 필요할 때마다 꺼내서 쓸라고
	//static으로 만듦.


  import

	 /*	public static Connection getConnection() {
     return null;
     }
     에서 시작 . */

	   public static Connection getConnection() {



		//1.driver연결
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //oracle.jdbc.driver:패키지는 다 소문자. 자바는 낙타등 표기법
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		//2.
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String user = "kh";
		String password = "kh";

		Connection con = null;  //커넥션 컨 객체를 리턴하려고 함. ----[1]


       try {
			con =DriverManager.getConnection(url, user, password);

			con.setAutoCommit(false); //오토커밋 방지. 내가 원하는 상황에서 커밋하고 롤백하고 싶어서 지정
		    System.out.println("2.계정연결");


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return con; //-----------[1]

	  }




		//3.close. commit. rollback
		public static void close(ResultSet rs) {  //ResultSet rs를 클로즈
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		public static void close(Statement stmt) {  //Statement stmt를 클로즈
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		public static void close(Connection con) { //Connection con을 클로즈.
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		//transaction. commit.
		public static void commit(Connection con) {
			try {
				con.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		//transaction. rollback
		public static void rollback(Connection con) {
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}

	}
}
