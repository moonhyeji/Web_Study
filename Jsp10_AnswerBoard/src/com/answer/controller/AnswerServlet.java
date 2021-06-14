package com.answer.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.answer.biz.AnswerBiz;
import com.answer.biz.AnswerBizImpl;
import com.answer.dto.AnswerDto;



@WebServlet("/answer.do")  
//이렇게 생성해주면 @WebServlet("/URL")의 URL 주소로 접속하면 톰캣에서 매핑된 서블릿을 찾아 실행해 준다
public class AnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;charset=UTF-8");
	
	
	AnswerBiz biz = new AnswerBizImpl();
	String command = request.getParameter("command");   //getParameter는(name값이 들어간다.): 문자열을 반환한다 따라서 String
	
//------------------리스트 -------------------------------
	if(command.equals("list")) {
		List<AnswerDto> list = biz.selectList();
		request.setAttribute("list",list);
		dispatch(request, response, "boardlist.jsp");
	
		
//--------------디테일 -----------------------------------
	}else if(command.equals("detail")) {
		int boardno = Integer.parseInt(request.getParameter("boardno")); //getParameter는 문자열을 반환하기 때문에 int형으로 바꿔준다.
		AnswerDto dto = biz.selectOne(boardno);
		request.setAttribute("dto", dto);
		dispatch(request,response,"boardselect.jsp");
	
	
//------------업데이트 ----------------------------------------		
	}else if(command.equals("updateform")) {
		//1.
		int boardno = Integer.parseInt(request.getParameter("boardno"));
		//3
		AnswerDto dto = biz.selectOne(boardno);
		//3
		request.setAttribute("dto", dto);
		//4
		dispatch(request, response, "boardupdate.jsp"); 


	
	
	}else if(command.equals("updateres")) {
	//1 받음 (번호 제목 내용)
    int boardno = Integer.parseInt(request.getParameter("boardno"));
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	//2 담음 
	AnswerDto dto = new AnswerDto();
    dto.setBoardno(boardno);
    dto.setTitle(title);
    dto.setContent(content);
    
	int res = biz.boardUpdate(dto);  //boardUpdate!!!!!!!!!!!주의 
	
	//3
	//4
	if( res > 0) {
		jsResponse(response,"글 수정 성공~~!! ","answer.do?command=detail&boardno="+boardno);
	}else {
		jsResponse(response,"글 수정 실패..ㅠㅠ","answer.do?command=updateform&boardno="+boardno);
	}
	//--------------------인서트 --------------------------------------------
	
	
	}else if(command.equals("insertform")){
	    //1
			//2
			//3
			//4
			response.sendRedirect("boardinsert.jsp");
		
		
		}else if(command.equals("insertres")) {
			//1
			String writer = request.getParameter("writer"); /////이부분을 틀림!!!!!!!!!!
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			//2
			AnswerDto dto = new AnswerDto();
			 dto.setWriter(writer);
		     dto.setTitle(title);
		     dto.setContent(content);
		     
			int res = biz.boardInsert(dto);
			//3
			//4
			if( res > 0) {
				jsResponse(response,"글 작성 성공~~!! ","answer.do?command=list");
			}else {
				jsResponse(response,"글 작성 실패..ㅠㅠ","answer.do?command=insertform");
			}
		
			
	//------------------ 삭제 ------------------------------------------
	
   }else if(command.equals("delete")) {
	//1
	int boardno =Integer.parseInt(request.getParameter("boardno"));
	//2
	int res = biz.boardDelete(boardno);
	//이부분 추가해서 써야직!
	if(res > 0) {
		response.sendRedirect("answer.do?command=list");
	}else {
		response.sendRedirect("answer.do?command=detail&boardno"+boardno);
	}
	
	
	
	
	//-------------------------answer ---------------------------------------
   }else if(command.equals("answerform")) {
	   int boardno = Integer.parseInt(request.getParameter("boardno"));
	   AnswerDto dto = biz.selectOne(boardno);
	   request.setAttribute("dto", dto);
	   dispatch(request,response,"boardanswer.jsp");
	   
	   
	   
   }else if(command.equals("answerproc")){
	   int parentboardno =Integer.parseInt(request.getParameter("parentboardno"));
	   String writer = request.getParameter("writer");
	   String title = request.getParameter("title");
	   String content = request.getParameter("content");
	   AnswerDto dto = new AnswerDto(parentboardno, 0,0,0,title, content,writer,null);
      
	    int res = biz.answerProc(dto);
	    if(res>0) {
			jsResponse(response,"답변 성공~~!! ","answer.do?command=list");

	    }else {
			jsResponse(response,"답변 실패..ㅠㅠ","answer.do?command=answerform&boardno="+parentboardno);
	    }

   
   }
	//------------------------------------------------------------------
	
	
	
	response.getWriter().append("<h1 style='color:skyblue;'>잘못왔다!!!!!!!!!!!!</h1>");
	//printWriter out = response.getWriter();
	//out.print("<h1 sytle='color: skyblue;'>잘못왔다!</h1>");
	
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		}
		
	
	
	//jsresponse 메소드 만들자! 

	   // 아래 메소드는 String res를 응답시킨다. (우리가 만든 메소드)
	   // msg를 alert시키고,  location으로 넘어가는 스크립트를 response한다. 

	private void jsResponse(HttpServletResponse response,String msg, String url) throws IOException {
		String res ="<script>"
			    	+"alert('" + msg +"');"
			    	+"location.href='" + url +"';"
			    	+"</script>";
		response.getWriter().append(res); // 
	}


	
	private void dispatch(HttpServletRequest request,HttpServletResponse response,String path) throws ServletException, IOException{
      RequestDispatcher dispatch = request.getRequestDispatcher(path);
      dispatch.forward(request,response);
	}
}



