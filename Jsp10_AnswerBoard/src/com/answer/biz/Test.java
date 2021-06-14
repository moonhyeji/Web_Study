package com.answer.biz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test {

	
	

	public static Connection getConnection() {
		

		//1
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	//2
	String url = "jdbc:oracle:thin:@192.168.10.21:1521:xe"; 
	String user ="WEB";
	String password="WEB";

	Connection con = null;

	try {
		con = DriverManager.getConnection(url, user, password);
		con.setAutoCommit(false);
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return con;

	}
}
