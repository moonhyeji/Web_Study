package com.my.dao;
import static com.my.db.JDBCTemplate.*;
//class.method를 tmehod,만 이용해서 사용하려고 static 임포트 해줌. 
//static 이용하는 이유: 
//객체로 만들지 않고, class.method로 가져가려고 다 static으로 함. 
//필요할 때마다 class만들어야 하니까 이렇게 하지 말고, 
//class.method  로 static 영역에 올려서 필요할 때마다 꺼내서 쓸라고
//static으로 만듦. 


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	    	PreparedStatement pstm = null; //pstm 선언 
	    	ResultSet rs = null;         //rs 선언 
	    	List<MyBoardDto> list = new ArrayList<MyBoardDto>(); //dto를 list로 틀만, 가져옴.

					try {
			    	pstm = con.prepareStatement(sql);  //sql문을 db에 연결해주겠다.
			        System.out.println("3.쿼리준비 : " + sql);
			        
			    	//4.쿼리실행 및 리턴 
			    	rs = pstm.executeQuery();  //연결해준 sql문으로 쿼리실행 
			    	System.out.println("4.쿼리실행 및 리턴 ");
			    	
			    			while(rs.next()) {  //rs객체의커서를 다음 한줄로 옮기기  
			    			MyBoardDto dto = new MyBoardDto(  
			    						rs.getInt("MYNO"),
			    						rs.getString("MYNAME"),
			    						rs.getString("MYTITLE"),
			    						rs.getString("MYCONTENT"),
			    						rs.getDate("MYDATE"));
	    				list.add(dto); //dto를 list에 덧붙여준다.
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
	    	//1
	    	//2
	    	Connection con = getConnection();
            
	    	String  sql = " SELECT MYNO, MYNAME, MYTITLE, MYCONTENT, MYDATE "
            		     +" FROM MYBOARD "
            		     +" WHERE MYNO = ? ";
            PreparedStatement pstm = null;
            ResultSet rs = null;
            
            MyBoardDto dto = new MyBoardDto(); //dto선언 
	    	try {
				pstm = con.prepareStatement(sql); // 연결된 오라클 서버에 SQL문 전달할 prepareStatement 객체 생성
				pstm.setInt(1, myno);   //pstmt.setInt(1, no); 첫번째 물음표값을 no로 설정
				System.out.println("3.쿼리준비 : " + sql);
                
				rs = pstm.executeQuery(); //prepareStatement객체를 사용해서 쿼리실행
				System.out.println("4.쿼리실행 및 리턴 ");
				
				while(rs.next()) {
					dto.setMyno(rs.getInt(1));  //*쿼리실행했을 때 ,1번째에 위치한 int형 값을 가져와서, dto의 setMyno에넣기.
					dto.setMyname(rs.getString(2)); //MYNAME가져와서 넣고,
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
				pstm = con.prepareStatement(sql);// 연결된 오라클 서버에 SQL문 전달할 prepareStatement 객체 생성. pstm은 sql문 가지고 잇음.
				pstm.setString(1, dto.getMyname()); //pstmt.setString(1, no); 첫번째 물음표에 들어갈 Strign형태의 값을 dto.getMyname()로 가져와서 pstm에 넣는다.
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
				
				pstm.setString(1, dto.getMytitle()); //첫번째 물음표에 들어갈 Strign형태의 값을 가져와서 dto의 getMytitle로 가져와서 pstm에 넣는다. 
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
