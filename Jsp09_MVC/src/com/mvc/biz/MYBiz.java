package com.mvc.biz;
import com.mvc.dao.MYDao;
import com.mvc.dto.MYDto;

import java.util.List;

public interface MYBiz {
	 public List<MYDto> selectList();
	    public MYDto selectOne(int seq);
	    public int insert(MYDto dto);
	    public int update(MYDto dto);
	    public int delete(int seq);
}
