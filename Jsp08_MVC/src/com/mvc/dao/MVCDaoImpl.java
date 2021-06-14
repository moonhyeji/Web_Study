
package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static com.mvc.db.JDBCTemplate.*;

import com.mvc.dto.MVCDto;

public class MVCDaoImpl implements MVCDao {

	@Override

	public List<MVCDto> selectList() {
		
		
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<MVCDto> list = new ArrayList<MVCDto>();
		
		try {
			stmt=con.createStatement();
			System.out.println("3.쿼리준비");
			
			rs=stmt.executeQuery(SELECT_LIST_SQL);
			System.out.println("4.쿼리 실행 및 리턴");
			while(rs.next()) {
				MVCDto dto = new MVCDto();
				dto.setSeq(rs.getInt("SEQ"));
				dto.setWriter(rs.getString("WRITER"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setContent(rs.getString("CONTENT"));
				dto.setRegdate(rs.getDate("REGDATE"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
			close(con);
		}
		return list;
	}

	
	
	//-----------------------------------------------------
	
	@Override
	public MVCDto selectOne(int seq) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		MVCDto dto = null;
		
		try {
			pstm=con.prepareStatement(SELECT_ONE_SQL);
			pstm.setInt(1, seq);
			System.out.println("3.쿼리준비"+ SELECT_ONE_SQL);
			
			rs = pstm.executeQuery();
			System.out.println("4.쿼리 실행리턴");
			while(rs.next()) {
				dto = new MVCDto(rs.getInt(1), 
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
			System.out.println("db종료");
		}
		return dto;
	}
	
	//-----------------------------------------------------

	@Override
	public int insert(MVCDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		try {
			pstm = con.prepareStatement(INSERT_SQL);
			pstm.setString(1, dto.getWriter());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			System.out.println("3.쿼리준비" +INSERT_SQL);
			
			res = pstm.executeUpdate();
			System.out.println("4.쿼리실행");
			if(res>0) {
				commit(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
			System.out.println("5.db종료");
		}
		return res;
	}
	
	//-----------------------------------------------------

	@Override
	public int update(MVCDto dto) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(UPDATE_SQL);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getSeq());
			System.out.println("3.쿼리준비 ");
			
			res= pstm.executeUpdate();
			System.out.println("4.쿼리실행");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm);
		    close(con);
		}
		return res;
	}
	
	//-----------------------------------------------------

	@Override
	public int delete(int seq) {
        Connection con = getConnection();
        PreparedStatement pstm = null;
        int res= 0;
        try {
        	pstm = con.prepareStatement(DELETE_SQL);
			pstm.setInt(1, seq);
			
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
			
		}
		return res;
	}

}
