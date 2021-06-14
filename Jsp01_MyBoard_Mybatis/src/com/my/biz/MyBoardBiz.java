package com.my.biz;
import java.util.List;
import com.my.dao.MyBoardDao;
import com.my.dto.MyBoardDto;
public class MyBoardBiz {


	private MyBoardDao dao = new MyBoardDao();
   //private: 클래스 내부에서만 사용 가능 
	//새로운 MyBoardDao 클래스 객체를 생성해서 , dao라는 이름의 객체에 담는다.
	//이 dao라는 객체는 클래스 내부에서만 사용 가능하다.


	public List<MyBoardDto> selectList(){   //List<MyBoardDto> 타입의 selectList메소드 받아서, 배열 안에 넣어서 list형태로 받을 거라서,  
		return dao.selectList();          //return list;
	}
	
	
	public MyBoardDto selectOne(int myno) {   //int myno받아서.
		return dao.selectOne(myno);           //dao.selectOne(myno); 로 전달해줄 것이다.
	} 		//return dto	                 // int 숫자 하나 받아와서 그 번호에 해당하는 데이터 출력해 줘야 하니까 
	
	
	
	public int insert(MyBoardDto dto) {     //타입: int 
		return dao.insert(dto);            //Dao에서 row값 int 로 받아서 res변수에 넣은 다음에 return res해줄거라서,
	}
	public int update(MyBoardDto dto) {
		return dao.update(dto);
	}
    public int delete (int myno) {
    	return dao.delete(myno);
  }


}
