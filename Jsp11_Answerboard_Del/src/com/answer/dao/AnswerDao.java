package com.answer.dao;

import java.util.List;

import com.answer.dto.AnswerDto;

public interface AnswerDao {
	
	String BOARD_LIST_SQL=" SELECT BOARDNO, GROUPNO, GROUPSEQ, TITLETAB, DELFLAG, TITLE, CONTENT, WRITER, REGDATE "
			             +" FROM ANSWERBOARD "
			             +" ORDER BY GROUPNO DESC,GROUPSEQ ";
	
	
	String BOARD_SELECT_SQL=" SELECT BOARDNO, GROUPNO, GROUPSEQ, TITLETAB, DELFLAG, TITLE, CONTENT, WRITER, REGDATE "
		                    +" FROM ANSWERBOARD "
		                   	+" WHERE BOARDNO =? ";
	
	String BOARD_INSERT_SQL=" INSERT INTO ANSWERBOARD "
			               +" VALUES( BOARDNOSEQ.NEXTVAL, GROUPNOSEQ.NEXTVAL, 1, 0, 'N',?,?,?,SYSDATE ) ";
	//? : 안에 값이 뭐가 들어갈지모르는 계속 바뀌는 값이라고 생각하는것이기 때문에! N 처럼 그냥 계속 들어가는 값은 계속 써놓는 것이 좋다.!
	
	
	String BOARD_UPDATE_SQL=" UPDATE ANSWERBOARD "
			                +" SET DELFLAG='Y' "
			                + " WHERE BOARDNO =? ";
	
	
	String BOARD_DELETE_SQL="";
	
	String ANSWER_UPDATE_SQL="";
	String ANSWER_INSERT_SQL="";
	
	public List<AnswerDto> selectList();
	public AnswerDto selectOne(int boardno);
	public boolean boardInsert(AnswerDto dto);
	public boolean boardUpdate(AnswerDto dto);
	public boolean boardDelete(int boardno);
	
	public boolean answerUpdate(int parentboardno);
	public boolean answerInsert(AnswerDto dto);
}
