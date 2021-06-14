package com.answer.biz;

import java.util.List;
import com.answer.dto.AnswerDto;
import com.answer.dao.AnswerDao;
import com.answer.dao.AnswerDaoImpl;

public class AnswerBizImpl implements AnswerBiz {

	
	private AnswerDao dao = new AnswerDaoImpl();  
	//이렇게 다오 불러 와야지 dao 에 빨간줄 안뜨지!! 
	
	@Override
	public List<AnswerDto> selectList() {
		return dao.selectList();
	}

	@Override
	public AnswerDto selectOne(int boardno) {
		return dao.selectOne(boardno);
	}

	@Override
	public int boardInsert(AnswerDto dto) {
		return dao.boardInsert(dto);
	}

	@Override
	public int boardUpdate(AnswerDto dto) {
		return dao.boardUpdate(dto);
	}

	@Override
	public int boardDelete(int boardno) {
        return dao.boardDelete(boardno);
	}

	
	
	@Override
	public int answerProc(AnswerDto dto) {
		//biz에서 transaction 걸어줌. 
		int update = dao.answerUpdate(dto.getBoardno());  
	
		//getboardno = 부모의 글번호 
		int insert = dao.answerInsert(dto);  
		
		return (update + insert); 
	}

	

	
	
}

























