package com.my.biz;
import java.util.List;
import com.my.dao.MyBoardDao;
import com.my.dto.MyBoardDto;
public class MyBoardBiz {


	private MyBoardDao dao = new MyBoardDao();



	public List<MyBoardDto> selectList(){
		return dao.selectList();
	}
	public MyBoardDto selectOne(int myno) {  //int myno받아서.
		return dao.selectOne(myno);  //dao.selectOne(myno); 로 전달해줄 것이다.
	}
	public int insert(MyBoardDto dto) {
		return dao.insert(dto);
	}
	public int update(MyBoardDto dto) {
		return dao.update(dto);
	}
    public int delete (int myno) {
    	return dao.delete(myno);
    }


}
