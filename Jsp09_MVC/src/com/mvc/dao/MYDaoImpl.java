package com.mvc.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.util.List;
import java.sql.PreparedStatement;
import static com.mvc.db.JDBCTemplate.*;
import com.mvc.dto.MYDto;

public class MYDaoImpl implements MYDao {

	@Override
	public List<MYDto> selectList() {
		
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		List<MYDto> list = new ArrayList<MYDto>();
		
		
		try {
			pstm = con.prepareStatement(SELECT_LIST_SQL);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				MYDto dto = new MYDto(
						rs.getInt("MYSEQ"),
						rs.getString("MYNAME"),
						rs.getString("MYTITLE"),
						rs.getString("MYCONTENT"),
						rs.getDate("MYDATE"));
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
	
	

	@Override
	public MYDto selectOne(int seq) {
		/// 이거 왜 rs.getInt("MYSEQ") 이렇게 하면 안되지..
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		MYDto dto = new MYDto();

		
		try {
			pstm = con.prepareStatement(SELECT_SQL);
			pstm.setInt(1,seq);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				dto.setMyseq(rs.getInt("MYSEQ"));
				dto.setMyname(rs.getString("MYNAME"));
				dto.setMytitle(rs.getString("MYTITLE"));
				dto.setMycontent(rs.getString("MYCONTENT"));
				dto.setMydate(rs.getDate("MYDATE"));
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

	
	
	
	
	@Override
	public int insert(MYDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(INSERT_SQL);
			
			pstm.setString(1, dto.getMyname());
			pstm.setString(2, dto.getMytitle());
			pstm.setString(3, dto.getMycontent());
			
			res = pstm.executeUpdate();
			
			if(res>0) {
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

	@Override
	public int update(MYDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(UPDATE_SQL);
			pstm.setString(1, dto.getMytitle());
			pstm.setString(2, dto.getMycontent());
			pstm.setInt(3, dto.getMyseq());
			
			res = pstm.executeUpdate();
			if(res>0) {
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

	@Override
	
	
	public int delete(int seq) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
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









