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

				AnswerDto dto = new AnswerDto(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getDate(8));
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

	// ------------select ------------------------------------------------------------

	@Override
	public AnswerDto selectOne(int boardno) {

		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		AnswerDto dto = new AnswerDto();
		try {
			pstm = con.prepareStatement(BOARD_DETAIL_SQL);
			pstm.setInt(1, boardno);
			System.out.println("3" + BOARD_DETAIL_SQL);
			
			
			rs = pstm.executeQuery();
			System.out.println("4  ");

			while (rs.next()) { //한 row씩 정보를 사용
				dto = new AnswerDto(rs.getInt(1), 
						rs.getInt(2), 
						rs.getInt(3), 
						rs.getInt(4), 
						rs.getString(5),
						rs.getString(6), 
						rs.getString(7), 
						rs.getDate(8));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("종료 ");
		}
		return dto;
	}

	// -------------------------------------------------------------------------
	@Override
	public int boardInsert(AnswerDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;

		try {
			pstm = con.prepareStatement(BOARD_INSERT_SQL);
			pstm.setString(1, dto.getWriter());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());

			res = pstm.executeUpdate();

			if (res > 0) {
				commit(con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
		}
		return res;
	}
//-------------------------------------------------------
	@Override
	public int boardUpdate(AnswerDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;

		try {
			pstm = con.prepareStatement(BOARD_UPDAET_SQL);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getBoardno());

			res = pstm.executeUpdate();
			if (res > 0) {
				commit(con);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
		}
		return res;
	}

	// -------------------------------------------

	@Override
	public int boardDelete(int boardno) {

		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		try {
			pstm = con.prepareStatement(BOARD_DELETE_SQL);
			pstm.setInt(1, boardno);
			res = pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
		}
		return res;
	}

	// -----------답변수정 --------------------------------------

	@Override
	public int answerUpdate(int parentboardno) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		try {
			pstm = con.prepareStatement(ANSWER_UPDATE_SQL);
			pstm.setInt(1, parentboardno);
			pstm.setInt(2, parentboardno);
			System.out.println("3" + ANSWER_UPDATE_SQL);

			res = pstm.executeUpdate();
			if (res > 0) { //
				commit(con); //
			} // 이부분이 트렌젝션 처리하는 부분. -원래 비즈에 잇어야 함. answerupdate.insert 다 성공햇을 때 , 커밋해라 라는 건데 .
			  // 너무 어려워지니까 그냥 다오에서 처리해주는 것 .
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

//-------------------답변추가 -----------------------------------
	@Override
	public int answerInsert(AnswerDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		try {
			pstm = con.prepareStatement(ANSWER_INSERT_SQL);
			pstm.setInt(1, dto.getBoardno());
			pstm.setInt(2, dto.getBoardno());
			pstm.setInt(3, dto.getBoardno());
			pstm.setString(4, dto.getTitle());
			pstm.setString(5, dto.getContent());
			pstm.setString(6, dto.getWriter());
			System.out.println("3 쿼리 준비!  " + ANSWER_INSERT_SQL);

			res = pstm.executeUpdate();
			if (res > 0) {
				commit(con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("5종료 ");
		}
		return res; 
	}
}
