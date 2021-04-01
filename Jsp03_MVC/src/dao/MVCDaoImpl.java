package com.mvc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.util.List;
import com.mvc.dao.MVCDao;
import java.sql.PreparedStatement;
import static com.mvc.db.JDBCTemplate.*;

import com.mvc.dto.MVCDto;

public class MVCDaoImpl implements MVCDao{
//------------------------------------------------------
	@Override
	public List<MVCDto> selectList() {
		
		Connection con = getConnection();
       
		PreparedStatement pstm = null;
        ResultSet rs = null;
        
        List<MVCDto> list = new ArrayList<MVCDto>();
		
        
        try {
			pstm = con.prepareStatement(SELECT_LIST_SQL);
		    System.out.println("3");
		    
			rs = pstm.executeQuery();
			System.out.println("4");
			
			while(rs.next()) {
	    	MVCDto dto = new MVCDto(
	    			rs.getInt("SEQ"),
                    rs.getString("WRITER"),
					rs.getString("TITLE"),
					rs.getString("CONTENT"),
					rs.getDate("REGDATE"));
	    		
			        list.add(dto);
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
		}
		
        return list;
	}

	//-------------------------------------------------------

	
	@Override
	public MVCDto selectOne(int seq) {
      Connection con = getConnection();
      PreparedStatement pstm = null;
      ResultSet rs = null;
      
      MVCDto dto = new MVCDto();
      
      
      try {
		pstm= con.prepareStatement(SELECT_SQL);
		pstm.setInt(1,seq);
	    System.out.println("3");

		
		rs = pstm.executeQuery();
		/*주로 Select문​을 이용하는 조회에서 사용된다.
        - 수행 결과로 ResultSet객체의 값을 반환한다.*/
		
	    System.out.println("4");
		while(rs.next()) {
			dto.setSeq(rs.getInt(1));
			dto.setWriter(rs.getString(2));
			dto.setTitle(rs.getString(3));
			dto.setContent(rs.getString(4));
			dto.setRegdate(rs.getDate(5));
		}		
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		close(rs);
		close(pstm);	
		close(con);
	}
      
		return dto;
	}
//-------------------------------------------------------
	@Override
	public int insert(MVCDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(INSERT_SQL);
			
			
			   pstm.setString(1, dto.getWriter());  //첫번째 물음표에dto.getWriter() 넣기  
			   pstm.setString(2, dto.getTitle());
			   pstm.setString(3, dto.getContent());
			   
			   res= pstm.executeUpdate();
			   
			   if(res > 0) {
				   commit(con);
			   }			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
		}
		return res;
	}

	//-------------------------------------------------
	
	
	@Override
	public int update(MVCDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
//		- Statement 객체는 SQL문을 데이터베이스로 전송하는데 사용한다.
        int res = 0;
        
        try {
			pstm=con.prepareStatement(UPDATE_SQL);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getSeq());
			System.out.println("3");
			
			res = pstm.executeUpdate();
			/*-  SQL문을 데이터베이스로 전송할 때, 
			 수행 결과로 int 타입의 값을 반환한다.
			  */
			System.out.println("4");	
			if(res > 0) {
				commit(con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
	     System.out.println("종료");
		}
		
		return res;
	}
//------------------------------------------
	@Override
	public int delete(int seq) {
	 Connection con = getConnection();
	 PreparedStatement pstm = null;
	 int res= 0;
	 
	 try {
		pstm = con.prepareStatement(DELETE_SQL);
/* 매개변수화 된, sql 구문(DELETE_SQL)을 db로 보내기 위해서 prepareStatement 를 사용. 
 * Creates a PreparedStatement object for sending parameterized SQL statements to the database.
*/
		pstm.setInt(1, seq);
/*
 *DELETE_SQL)를 db로 보낼 때 , 드라이버가  (인덱스번호가 1이고 값이 seq인 매개변수를 sql의 integer 값의형태로 전환하여 db로 전송한다. 
 * PreparedStatement.setInt(int parameterIndex, int x) throws SQLException
Sets the designated parameter to the given Java int value. 
The driver converts this to an SQL INTEGER value when it sends it to the database.
*/
       System.out.println("3");
       
       res=pstm.executeUpdate();
       System.out.println("4");
		
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		close(pstm);
		close(con);
	}	
		return res;
	}

}




