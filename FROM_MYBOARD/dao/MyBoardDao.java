package com.my.dao;
import static com.my.db.JDBCTemplate.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//class.method를 mehod,만 이용해서 사용하려고 임포트 해줌. 
import java.util.List;

import com.my.dto.MyBoardDto;
public class MyBoardDao {
	
	
	
	
	
	
	
	//전체글 출력----------------------------------------------------------
	    public List<MyBoardDto> selectList(){
	    	
	    	//1
	    	//2
	    	Connection con = getConnection();	
	    	//3 쿼리준비.
	    	String sql= " SELECT MYNO, MYNAME, MYTITLE, MYCONTENT, MYDATE "
	    			   + " FROM MYBOARD "
	    			   + " ORDER BY MYNO DESC ";
	    	PreparedStatement pstm = null;
	    	ResultSet rs = null;
	    	List<MyBoardDto> list = new ArrayList<MyBoardDto>();
			try {
	    	pstm= con.prepareStatement(sql);
	        System.out.println("3.쿼리준비 : " + sql);
	    	//4.쿼리실행 및 리턴 
	    	rs = pstm.executeQuery();
	    	System.out.println("4.쿼리실행 및 리턴 ");
	    			while(rs.next()) {
	    			MyBoardDto dto = new MyBoardDto(
	    						rs.getInt("MYNO"),
	    						rs.getString("MYNAME"),
	    						rs.getString("MYTITLE"),
	    						rs.getString("MYCONTENT"),
	    						rs.getDate("MYDATE"));
	    				
	    				list.add(dto);
	    			}
	    			}catch(SQLException e){
	    				e.printStackTrace();
	    			}finally {
	    				close(rs);
	    				close(pstm);
	    				close(con);
	    				System.out.println("5. db 종료");
	    			}
	              return list;
	    		    }
	    
	    
	    
	    
	    
	    
	    ////선택 ------------------------------------------------------------------
	    
	    
	    public MyBoardDto selectOne(int myno) {
	    	Connection con = getConnection();
            String  sql = " SELECT MYNO, MYNAME, MYTITLE, MYCONTENT, MYDATE "
            		   +" FROM MYBOARD "
            		   +" WHERE MYNO = ? ";
            PreparedStatement pstm = null;
            ResultSet rs = null;
            MyBoardDto dto = new MyBoardDto();
	    	try {
				pstm = con.prepareStatement(sql);
				pstm.setInt(1, myno);
				System.out.println("3.쿼리준비 : " + sql);
                
				rs = pstm.executeQuery();
				System.out.println("4.쿼리실행 및 리턴 ");
				while(rs.next()) {
					dto.setMyno(rs.getInt(1));
					dto.setMyname(rs.getString(2));
					dto.setMytitle(rs.getString(3));
					dto.setMycontent(rs.getString(4));
					dto.setMydate(rs.getDate(5));
				}
	    	    
	    	} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstm);
				close(con);
				System.out.println("db종료 ");
			}
	    	
	    	return dto;
	    }
	    
	    ////게시글 추가 ------------------------------------------------------------------
	    public int insert(MyBoardDto dto) {
	    	Connection con = getConnection();
	    	
	    	String sql=" INSERT INTO MYBOARD "
	    			+" VALUES(MYBOARDSEQ.NEXTVAL,?,?,?,SYSDATE) ";
	    	/*- sequence이름.nextval : 시퀀스의 최종값(현재값)을 리턴하고, 그 다음 값을 생성*/
	    	PreparedStatement pstm = null;
	    	int res= 0;
	    	
	    	try {
				pstm = con.prepareStatement(sql);
				pstm.setString(1, dto.getMyname());
				pstm.setString(2, dto.getMytitle());
				pstm.setString(3, dto.getMycontent());
				
				
				System.out.println("3쿼리준비 : "+ sql);
				
				
				res = pstm.executeUpdate();
				System.out.println("4.쿼리 실행 및 리턴 ");
				if(res > 0) {
					commit(con);
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstm);
				close(con);
				System.out.println("5. db 종료");
			}
	    	return res;
	    }
	    
	    ////게시글 수정 ------------------------------------------------------------------

	    public int update(MyBoardDto dto) {
	    	Connection con = getConnection();
	    	String sql = " UPDATE MYBOARD " 
	    	              + " SET MYTITLE = ? ,  MYCONTENT = ? " 
	    	              + " WHERE MYNO =? "; //제목과 내용만 수정. 
	    	PreparedStatement pstm = null;
	    	int res=0;
	    	try {
				pstm = con.prepareStatement(sql);
				pstm.setString(1, dto.getMytitle());
				pstm.setString(2, dto.getMycontent());
				pstm.setInt(3, dto.getMyno());
                System.out.println("3. 쿼리준비 : " + sql);
			    //4
	    	
            res = pstm.executeUpdate();
            System.out.println("쿼리 실행 및 리턴");
            if(res > 0) {
            	commit(con);
             }
	    	} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstm);
				close(con);
				System.out.println("5.db종료 ");
			}
	    	
	    	return res;
	    }
	    
	    ////게시글 삭제 ------------------------------------------------------------------

	    public int delete(int myno) {
	    	
	    	Connection con = getConnection();
            String sql = " DELETE FROM MYBOARD WHERE MYNO = ? ";
    		PreparedStatement pstm = null;
    		int res = 0;
			try {
				pstm = con.prepareStatement(sql);
				pstm.setInt(1, myno);
				System.out.println("3. 쿼리 준비 : " + sql);
				
				res = pstm.executeUpdate();
				System.out.println("4.쿼리 실행 및 리턴 ");
				if (res > 0) {
					commit(con);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstm);
				close(con);
			System.out.println("5. db종료 ");
			}
			return res;

      }
	    
}
