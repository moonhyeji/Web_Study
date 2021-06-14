package com.answer.biz;

import java.util.List;

import com.answer.dao.AnswerDao;
import com.answer.dao.AnswerDaoImpl;
import com.answer.dto.AnswerDto;

public class AnswerBizImpl implements AnswerBiz {
	
	
	
private AnswerDao dao = new AnswerDaoImpl();


	@Override
	public List<AnswerDto> selectList() {
		return dao.selectList();
	}

	@Override
	public AnswerDto selectOne(int boardno) {
		return dao.selectOne(boardno);
	}

	@Override
	public boolean boardInsert(AnswerDto dto) {
		return dao.boardInsert(dto) ;
	}
	

	@Override
	public boolean boardUpdate(AnswerDto dto) {
         return false;
		
		
	}

	@Override
	public boolean boardDelete(int boardno) {
		return false;
	}

	
	
	
	//-------------------------------------
	
	@Override
	public boolean answerProc(AnswerDto dto) {

		boolean update= dao.answerUpdate(dto.getBoardno());
		System.out.println("update" + update);
		
		boolean insert = dao.answerInsert(dto);
		
		return insert;
		
		//업데이트 됏는데 인서트 안되는 경우도 
		//return( update + insert);
		/*
		 * 원글 
		 *  re: content
		 *   re:re: content
		 *  re: content
		 *    re:re: content
		 *     re:re:re: content  <- 이 글이 마지막글인지 판다는 dao로 해서 , 마지막글이 아니라면 업뎃하고 , 마지막글이면 업뎃안되게 
		 * */
	}

}
