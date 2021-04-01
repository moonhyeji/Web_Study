package com.mvc.biz;

import java.util.List;

import com.mvc.dto.MVCDto;

public interface MVCBiz {

	public List<MVCDto> selectList();
	public MVCDto selectOne(int seq);
	public int insert(MVCDto dto);
	public int update(MVCDto dto);
	public int delete(int seq);
	//dao에서 추상메소드 다가져와 
	
	
}
