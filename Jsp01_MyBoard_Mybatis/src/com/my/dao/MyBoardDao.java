 package com.my.dao;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.my.dto.MyBoardDto;

public class MyBoardDao extends SqlMapConfig {
	
	private String namespace = "com.my.mapper."; //  밑에서 그냥 namespace로 해서 간단히 써줄라고,
	
	    public List<MyBoardDto> selectList(){
	    
	    	SqlSession session = getSqlSessionFactory().openSession();
	    	                 //extends한  SqlMapConfig의 getSqlSessionFactory를 가지고 와서  opensession 하겠다.  
	    	List<MyBoardDto> list = session.selectList("com.my.mapper."+  "selectList");
	    	//com.my.mapper 이게 mapper의 이름. 순서: dao -> mapper. 
	    	session.close();
	    	
	    	return list;
	    }
	    //---------------------------------------------------------------
	    
	    public MyBoardDto selectOne(int myno) {
	    	SqlSession session = null;
	    	MyBoardDto dto = null;
	    	try {
				session = getSqlSessionFactory().openSession();
				dto = session.selectOne(namespace +"selectOne", myno);  //com.my.mapper.selectOne  의 myno 
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				session.close();
			}
	    	return dto;
	    }	
	    //-----------------------------------------------------------
	    public int insert(MyBoardDto dto) {
	    	int res = 0;
	    	//try with resources문 .
	    	try (SqlSession session = getSqlSessionFactory().openSession(true)){
	    		res = session.insert(namespace + "insert", dto);
	    	}
	    	return res;
	    }
	    //----------------------------------------------------------
	    public int update(MyBoardDto dto) {
	    	int res = 0;
	    	//try with resources문 . 
	    	try (SqlSession session = getSqlSessionFactory().openSession(true)){
	    		res = session.insert(namespace + "update", dto);
	    	}
	    	return res;
	    }
	    
	    //--------------------------------------------------------
	    public int delete(int myno) {
	    	int res = 0;
	    	//try with resources문 .
	    	try (SqlSession session = getSqlSessionFactory().openSession(true)){
	    		res = session.insert(namespace + "delete", myno);
	    	}
	    	return res;
      }
}
