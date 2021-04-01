 package com.muldel.dao;
import static com.muldel.db.JDBCTemplate.*;
////static 이용하는 이유:
//객체로 만들지 않고, class.method로 가져가려고 다 static으로 함.
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import com.muldel.dto.MDBoardDto;
import java.sql.PreparedStatement;

public class MDBoardDaoImpl implements MDBoardDao {

	@Override
	public List<MDBoardDto> selectList() {
		
	
     Connection con = getConnection();
     
     String sql =" SELECT SEQ, WRITER, TITLE, CONTENT, REGDATE "
    		    +" FROM MDBOARD "
    		    +" ORDER BY SEQ DESC ";
     
     Statement stmt = null;
     ResultSet rs = null;
     
     List<MDBoardDto> list = new ArrayList<MDBoardDto>();
   
		
     try {
		stmt = con.createStatement();
     System.out.println("3.쿼리준비 : " +  SELECT_LIST_SQL);
     //4
     rs = stmt.executeQuery(SELECT_LIST_SQL);
     //statement는 executeQuery() 안에 저렇게 쿼리문 넣어줘야 함 ! 
   
     System.out.println("4. 실행및 리턴");
     while(rs.next()) {
    	 MDBoardDto dto = new MDBoardDto(
    			 rs.getInt("SEQ"),
    			 rs.getString("WRITER"),
    			 rs.getString("TITLE"),
    			 rs.getString("CONTENT"),
    			 rs.getDate("REGDATE"));
    	        list.add(dto);
     }
     } catch (SQLException e) {
 		e.printStackTrace();
 	}finally{
 		
 		
 		close(rs);
 		close(stmt);
 		close(con); 
 		//close(rs.stmt.con); 하면 왜 ㅔ러나지????????
 		System.out.println("종료 ");
 	}
     
		return list;
	}
	
	//-----------------------------------------------------------------------
     
	public MDBoardDto selectOne(int seq) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs=null;
		MDBoardDto dto = new MDBoardDto();
		
		try {
		  pstm = con.prepareStatement(SELECT_ONE_SQL);
		  pstm.setInt(1, seq);
		  System.out.println("준비 : " + SELECT_ONE_SQL);
		  
		  rs = pstm.executeQuery();
		  System.out.println("4 ");
		 
		  while(rs.next()) {
              dto = new MDBoardDto( 
					 rs.getInt(1),
					 rs.getString(2),
					 rs.getString(3),
					 rs.getString(4),
					 rs.getDate(5));
					  
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
	
	
	//-------------------------------------

	public int insert(MDBoardDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(INSERT_SQL);
			pstm.setString(1, dto.getWriter());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			System.out.println("3: " + INSERT_SQL);
			
			res = pstm.executeUpdate();
			System.out.println("4 ");
			if(res > 0) {
				commit(con);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
			System.out.println(" 5 끗 ");
		}
		
		return res;
	}
	//-------------------------------------

	@Override
	public int update(MDBoardDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm= con.prepareStatement(UPDATE_SQL);
			pstm.setString(1, dto.getTitle()); // 1.2.3 얘네 해주는 게 값 mapping 해주는 것. 
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getSeq());
			System.out.println("쿼리준비 3. " +  UPDATE_SQL );
			
			res = pstm.executeUpdate();
			System.out.println("4 ");
			if(res > 0) {
				commit(con);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
			System.out.println("종료 ");
		}
		
		return res;
	}
	
	//------------------------------------------------------


	@Override
	public int delete(int seq) {
     Connection con = getConnection();
     PreparedStatement pstm =null;
     int res =0;
     
     try {
		pstm = con.prepareStatement(DELETE_SQL);
		pstm.setInt(1, seq);
		System.out.println("3  ");
		
		res = pstm.executeUpdate();
		System.out.println("4 ");
		
		if(res > 0) {
			commit(con);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		close(pstm);
		close(con);
		System.out.println("5 ");
	}
     return res;
	}
	//------------------------------------------------------

	@Override
	public int multiDelete(String[] seqs) {
		//멀티딜리트는 스트링배열 ! 
     Connection con = getConnection();
     
     PreparedStatement pstm = null;
     int res=0;
     int[] cnt = null;
     
     try {
		pstm = con.prepareStatement(DELETE_SQL);
	   System.out.println("3.쿼리준비 ");
     for (int i =0; i<seqs.length; i++) {
    	 pstm.setString(1,seqs[i]);
    	 pstm.addBatch(); //////////////////////////
    	 //메모리에 sql문을 적재 후, executeBatch()메소드가 호출되면 한번에 실행 
    	 System.out.println("삭제할 번호 : " + seqs[i]);
     }
     //메모리에 저장된 sql문을 한번에 실행!
     //리턴 : int[]
     // 성공: -2, 실패 -3 리턴. 
     cnt = pstm.executeBatch();////////////////////////////
     System.out.println("실행 및 리턴 ");
     for (int i =0; i< cnt.length; i++) {
    	 if(cnt[i] == -2 ) { /////////////////////////
    		//( 리턴한 애가 -2 인가?) = (리턴에 성공했는가 )
    		 //cnt[i]  : 성공인 갯수 카운트. 
    		 res++;
    	 }
     }
     if(seqs.length == res) {
    	 commit(con);
     }else {
    	 rollback(con);
     }
     
     
     
     } catch (SQLException e) {
 		e.printStackTrace();
 	}finally {
 		close(pstm);
 		close(con); // 얘네 묶으면 안되는거 jdbc템플릿에서 묶은거 안써서 그럼! 
 		System.out.println("종료 ");
 	}
		
		return res;
	}

}
