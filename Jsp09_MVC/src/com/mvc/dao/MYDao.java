package com.mvc.dao;
import com.mvc.dto.MYDto;
import java.util.List;
public interface MYDao {

	String SELECT_LIST_SQL =" SELECT MYSEQ, MYNAME, MYTITLE, MYCONTENT, MYDATE "
	   						+" FROM MYMVCBOARD "
	   						+" ORDER BY MYSEQ DESC ";
	
    String SELECT_SQL=" SELECT MYSEQ, MYNAME, MYTITLE, MYCONTENT, MYDATE "
    		          +" FROM MYMVCBOARD "
    		          +" WHERE MYSEQ =? ";
    
    String INSERT_SQL=" INSERT INTO MYMVCBOARD "
    		         +" VALUES(MYMVCBOARDSEQ.NEXTVAL, ?,?,?,SYSDATE ) ";
    
    String UPDATE_SQL=" UPDATE MYMVCBOARD "
    		            +" SET MYTITLE =?, MYCONTENT = ? "
                        +" WHERE MYSEQ =? ";    
    
    String DELETE_SQL=" DELETE FROM MYMVCBOARD "
    		         + " WHERE MYSEQ =? ";
    
    
    
    
    public List<MYDto> selectList();
    public MYDto selectOne(int seq);
    public int insert(MYDto dto);
    public int update(MYDto dto);
    public int delete(int seq);
	
	
}
