package com.answer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static com.answer.db.JDBCTemplate.*;
import com.answer.dto.AnswerDto;

public class AnswerDaoImpl implements AnswerDao {

	@Override
	public List<AnswerDto> selectList() {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		List<AnswerDto> list = new ArrayList<AnswerDto>();
		try {
			pstm = con.prepareStatement(BOARD_LIST_SQL);
			rs = pstm.executeQuery();

			while (rs.next()) {
				AnswerDto dto = new AnswerDto(
						rs.getInt(1), 
						rs.getInt(2), 
						rs.getInt(3), 
						rs.getInt(4), 
						rs.getString(5),
						rs.getString(6), 
						rs.getString(7), 
						rs.getString(8), 
						rs.getDate(9));
				list.add(dto);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
		}

		return list;
	}

	// ----------select ---------------------------------------
	@Override
	public AnswerDto selectOne(int boardno) {
	    //1.2
		Connection con = getConnection();
	    
		//초기화 
		PreparedStatement pstm = null;
	    ResultSet rs = null;  //select 쿼리 실행 후 결과를 담을 객체


	    AnswerDto dto = new AnswerDto();

	    //쿼리준비 
	    try {
			pstm = con.prepareStatement(BOARD_SELECT_SQL);
			pstm.setInt(1, boardno); // boardno 가 x 인 애 하나만 가져올 거니까 setint  1개, 
			
			rs= pstm.executeQuery();
		    
		    
		    while(rs.next()) {
			    dto.setBoardno(rs.getInt(1));
			    dto.setGroupno(rs.getInt(2));
			    dto.setGroupseq(rs.getInt(3));
			    dto.setTitletab(rs.getInt(4));
			    dto.setDelflag(rs.getString(5));
			    dto.setTitle(rs.getString(6));
			    dto.setContent(rs.getString(7));
			    dto.setWriter(rs.getString(8));
			    dto.setRegdate(rs.getDate(9));
	   		
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

	
	
	//------------------------------------------------
	@Override
public boolean boardInsert(AnswerDto dto) {
		
		 Connection con = getConnection();
         PreparedStatement pstm = null;
          int res = 0;
          
			try {
				pstm = con.prepareStatement(BOARD_INSERT_SQL);
				pstm.setString(2, dto.getTitle());
				pstm.setString(3, dto.getContent());
				pstm.setString(1, dto.getWriter());
            System.out.println("3"+BOARD_INSERT_SQL );
		      res = pstm.executeUpdate();
				
				if( res > 0  ) {
					commit(con);
					//return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstm);
				close(con);
			}
		return (res > 0) ? true : false ; 
		
		/////////return false;
		
		
		
		//이렇게 하면 안되나?????
//	    Connection con = getConnection();
//	    PreparedStatement pstm = null;
//	    boolean res = true;
//	    
//	    try {
//			pstm = con.prepareStatement(BOARD_INSERT_SQL);
//			pstm.setString(2, dto.getTitle());
//			pstm.setString(3, dto.getContent());
//			pstm.setString(1, dto.getWriter());
//			
//			res = pstm.execute();
//			if(res) {
//				commit(con);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close(pstm);
//			close(con);
//		}
//	    return res;
	}
//---------------------------------------------------------
	@Override
	public boolean boardUpdate(AnswerDto dto) {
		// TODO Auto-generated method stub
		return false;
	}
	
	//-------------------------------------------------------

	@Override
	public boolean boardDelete(int boardno) {
		// DELFLAG 를 Y로 바꾸자. -DELETE가 아닌 UPDATE

		return false;
	}
//-----------------------------------------------------------
	@Override
	public boolean answerUpdate(int parentboardno) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;

		return false;
	}
//--------------------------------------
	@Override
	public boolean answerInsert(AnswerDto dto) {
		return false;
	}

}
