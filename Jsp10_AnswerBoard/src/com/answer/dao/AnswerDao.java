


package com.answer.dao;

import java.util.List;

import com.answer.dto.AnswerDto;

public interface AnswerDao {


		String BOARD_LIST_SQL = " SELECT BOARDNO, GROUPNO, GROUPSEQ, TITLETAB, TITLE, CONTENT, WRITER, REGDATE "
				               +" FROM ANSWERBOARD "
		                       +" ORDER BY GROUPNO DESC, GROUPSEQ ";
		
		String BOARD_DETAIL_SQL=" SELECT BOARDNO, GROUPNO, GROUPSEQ, TITLETAB, TITLE, CONTENT, WRITER, REGDATE "
				               +" FROM ANSWERBOARD "
				               +" WHERE BOARDNO =? ";
		
		
		String BOARD_INSERT_SQL=" INSERT INTO ANSWERBOARD "
				               +" VALUES(BOARDNOSEQ.NEXTVAL, GROUPNOSEQ.NEXTVAL, 1, 0, ?, ?, ?, SYSDATE) ";
		
		
		
		String BOARD_UPDAET_SQL=" UPDATE ANSWERBOARD "
                               +" SET TITLE=?, CONTENT =? "
                               +" WHERE BOARDNO=? ";
		
		
		String BOARD_DELETE_SQL=" DELETE FROM ANSWERBOARD "
		                       +" WHERE BOARDNO =? ";
		//----------------------------------------------------------------------------------
		
		//insert 할 때 sql ! 
		// 글번호 그룹번호 그룹순서 제목공백 제목 내용 작성자 작성일 
		 //시퀀스 시퀀스   1    0      title content writer sysdate
		String ANSWER_UPDATE_SQL=" UPDATE ANSWERBOARD "
				                +" SET GROUPSEQ = GROUPSEQ + 1 "
				                +" WHERE "
   				                +" GROUPNO = (SELECT GROUPNO FROM ANSWERBOARD WHERE BOARDNO = ?) " //부모의 그룹번호와 같으면서 ;
				                +" AND "
				                +" GROUPSEQ > (SELECT GROUPSEQ FROM ANSWERBOARD WHERE BOARDNO = ? ) ";

//				            여기서 where boardno =? <- 이건 부모의 글번호 
		
		
		
		String ANSWER_INSERT_SQL=" INSERT INTO ANSWERBOARD "
				                  +" VALUES( "
				                  +" BOARDNOSEQ.NEXTVAL, "
				                  +" (SELECT GROUPNO FROM ANSWERBOARD WHERE BOARDNO =?), "
				                  +" (SELECT GROUPSEQ + 1 FROM ANSWERBOARD WHERE BOARDNO =?), "
				                  +" (SELECT TITLETAB FROM ANSWERBOARD WHERE BOARDNO =?) + 1, "
				                  +"  ?,?,?,SYSDATE) " ;
		
		
		//+1하는 위치가 달라도 상관없ㅇ므!!
		
		
		
		
		public List<AnswerDto> selectList();
		public AnswerDto selectOne(int boardno);
		public int boardInsert(AnswerDto dto);
		public int boardUpdate(AnswerDto dto);
		public int boardDelete(int boardno);
		
		public int answerUpdate(int parentboardno);
		public int answerInsert(AnswerDto dto);
		
		
	}


