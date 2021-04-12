package com.login.dao;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import static com.login.db.JDBCTemplate.*;
import com.login.dto.LoginDto;

public class LoginDao {

	/*
	 * 관리자 기능.
	 * 1.회원 전체 정보(모든회원)
	 * 2.가입된 회원의 전체 정보 (MYENAVLED ='Y'인 회원)
	 * 3.회원 등급 조정 
	 * */
	
	 // 1.회원 전체 정보(모든회원)
	public List<LoginDto> selectAllList(){
	Connection con =getConnection();
    String sql= " SELECT MYNO, MYID, MYPW, MYNAME, MYADDR, MYPHONE, MYEMAIL, MYENABLED, MYROLE "
    		   +" FROM MYMEMBER "
    		   +" ORDER BY MYNO ";
    PreparedStatement pstm = null;
    ResultSet rs = null;
    List<LoginDto> list = new ArrayList<LoginDto>();    
    try {
		pstm = con.prepareStatement(sql);
		System.out.println("3");
		
		rs=pstm.executeQuery();
		System.out.println("4");
		while(rs.next()) {
			LoginDto dto = new LoginDto();
			dto.setMyno(rs.getInt("MYNO"));
			dto.setMyid(rs.getString("MYID"));
			dto.setMypw(rs.getString("MYPW"));
			dto.setMyname(rs.getString("MYNAME"));
			dto.setMyaddr(rs.getString("MYADDR"));
			dto.setMyphone(rs.getString("MYPHONE"));
			dto.setMyemail(rs.getString("MYEMAIL"));
			dto.setMyenabled(rs.getString("MYENABLED"));
			dto.setMyrole(rs.getString("MYROLE"));
			
			list.add(dto);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		close(rs);
		close(pstm);
		close(con);
	}
		return list;
	}
	
	
	
	//2.가입된 회원의 전체 정보 (MYENAVLED ='Y'인 회원)
	public List<LoginDto> selectEnabledList(){
	   Connection con =getConnection();
	   String sql= " SELECT MYNO, MYID, MYPW, MYNAME, MYADDR, MYPHONE, MYEMAIL, MYENABLED, MYROLE "
	    		   +" FROM MYMEMBER "
	    		   +" ORDER BY MYNO ";
	    PreparedStatement pstm = null;
		ResultSet rs = null;
		List<LoginDto> list = new ArrayList<LoginDto>();
		try {
			pstm= con.prepareStatement(sql);
		System.out.println("3");
		
		rs=pstm.executeQuery();
		System.out.println("4");
		while(rs.next()) {
			LoginDto dto = new LoginDto(rs.getInt(1),
					                    rs.getString(2),
					                    rs.getString(3),
					                    rs.getString(4),
					                    rs.getString(5),
					                    rs.getString(6),
					                    rs.getString(7),
					                    rs.getString(8),
					                    rs.getString(9));
					      list.add(dto);   
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm);
			close(rs);
			close(con);
		System.out.println("5"); 
		}	
		return list;
	}
	
	//3.회원 등급 조정 
	public int updateRole(int myno, String role) {
	Connection con = getConnection();
	String sql= " UPDATE MYMEMBER "
			   +" SET MYROLE =? "
			   +" WHERE MYNO = ? ";
	
	PreparedStatement pstm= null;
	int res =0;
	
	try {
		pstm = con.prepareStatement(sql);
		pstm.setString(1, role);
		pstm.setInt(2, myno);
		System.out.println("3");
		
		res= pstm.executeUpdate();
		System.out.println("4");
		if(res>0) {
			commit(con);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		close(pstm);
		close(con);
	}
		return res;
	}
	//-----------------------------------------------------------------------
	/*
	 * 사용자기능.
	 * 1.로그인
	 * 2.회원가입
	 * 3.아이디 중복체크
	 * 4.내정보 조회
	 * 5.내정보 수정
	 * 6.탈퇴
	 * */
	
    //1.사용자기능 - 로그인
	public LoginDto login(String myid, String mypw) {
		Connection con = getConnection();
		String sql= " SELECT MYNO, MYID, MYPW, MYNAME, MYADDR, MYPHONE, MYEMAIL, MYENABLED, MYROLE "
				   +" FROM MYMEMBER "
				   +" WHERE MYID =? "
				   +" AND MYPW = ? "
		           +" AND MYENABLED = 'Y' "; //탈퇴시 (myenabled=n) 로그인 되지 않도록,
		 PreparedStatement pstm = null;
	     ResultSet rs =null;
	     LoginDto dto = null;
	 
	     try {
			  pstm= con.prepareStatement(sql);
			  pstm.setString(1, myid);
			  pstm.setString(2, mypw);
			  System.out.println("3");
			  
			  rs = pstm.executeQuery();
			  System.out.println("4");
			   while(rs.next()) {
				dto = new LoginDto(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(rs);
			close(pstm);
			close(con);
		}
			return dto;
		
	}
	//------------------------------------------------------------
	//2.회원가입
	public int insert(LoginDto dto) {
		String sql=" INSERT INTO MYMEMBER "
				  +" VALUES(MYNOSEQ.NEXTVAL,?,?,?,?,?,?,'Y',USER) ";
		
		
		
		
		
		
		
		
		
		return 0;
	}
	
	//------------------------------------------------------------

	//3.아이디 중복체크
	public LoginDto idCheck(String myid) {
		Connection con = getConnection();
		String sql=" SELECT MYID "
				   +" FROM MYMEMBER "
				   +" WHERE MYID =? ";
		PreparedStatement pstm = null;
		ResultSet rs= null;
		LoginDto dto = null;
		
		try {
			pstm= con.prepareStatement(sql);
			pstm.setString(1, myid);
			
			rs=pstm.executeQuery();
			while(rs.next()) {
				
				dto = new LoginDto();
				dto.setMyid(rs.getString(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
		}
		
		
		return dto;
	}
	
	
	//------------------------------------------------------------
	//4.내정보 조회
	public LoginDto selectOne(int myno) {
		Connection con = getConnection();
		String sql= " SELECT  MYNO, MYID, MYPW, MYNAME, MYADDR, MYPHONE, MYEMAIL, MYENABLED, MYROLE "
				   +" FROM MYMEMBER "
				   +" WHERE MYNO =? ";
		PreparedStatement pstm = null;
		ResultSet rs = null;
		LoginDto dto = null; // 밖에 null 해놓고 안에서 객체 만드는 방법 . 있고,
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, myno);  
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				dto = new LoginDto();
			    dto.setMyno(rs.getInt(1));
					dto.setMyid(rs.getString(2));
					dto.setMypw(rs.getString(3));
					dto.setMyname(rs.getString(4));
					dto.setMyaddr(rs.getString(5));
					dto.setMyphone(rs.getString(6));
					dto.setMyemail(rs.getString(7));
					dto.setMyenabled(rs.getString(8));
					dto.setMyrole(rs.getString(9));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm);
			close(rs);
			close(con);
		}
		return dto;
	}
	//------------------------------------------------------------
	
	//5.내정보 수정
	public int update(LoginDto dto) {
		
		
		String sql =" UPDATE MYMEMBER "
				  +" SET MYPW = ? , MYADDR=?, MYPHONE=?, MYEMAIL = ? "
		          +" WHERE MYNO = ? ";
		
		return 0;
	}
	//6.탈퇴
	public int delete(int myno) {
		String sql =" UPDATE MYMEMBER "
				   +" SET MYENABLED = 'N' "
				   +" WEHRE MYNO =? ";
	
		return 0;
	}
	
}
