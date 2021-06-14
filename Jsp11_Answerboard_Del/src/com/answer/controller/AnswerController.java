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
public class AnswerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	
    public AnswerController() {
    	
    
    }

    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	doPost(request, response);		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");
	
	AnswerBiz biz = new AnswerBizImpl();
	String command = request.getParameter("command");
	System.out.println("command : "+ command );
	
	
	
	//--------------리스트 ----------------------------
	if(command.equals("list")) {
		List<AnswerDto> list = biz.selectList();
		request.setAttribute("list", list);
		dispatch(request,response,"boardlist.jsp");
	
	
	
//-------------------------insert ----------------------------------
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
		     
			//3
			//4
			if( biz.boardInsert(dto) ) {         //biz에 boardinsert 한 결과가 참이라면 
			          jsResponse(response,"글 작성 성공~~!! ","answer.do?command=list");
			}else {
				       jsResponse(response,"글 작성 실패..ㅠㅠ","answer.do?command=insertform");	
		 }      
		
		//----------select ---------------------------------------------
	
	  }else if(command.equals("select")) {
		 int boardno = Integer.parseInt(request.getParameter("boardno"));
		 AnswerDto dto = biz.selectOne(boardno);
			request.setAttribute("dto", dto);
			dispatch(request,response,"boardselect.jsp");

		  
		  
	  }
	
	
	
	}
	
	//-----------------------------------------------------

	
	//jsresponse 메소드 만들자! 
	private void jsResponse(HttpServletResponse response, String msg, String url)throws IOException {
		String responseText ="<script>"
			             	+ "alert('" +msg+"');"
                            + "location.href='"+url+"';"
                            + "</script>";
		
		response.getWriter().append(responseText);
	}
	
	private void dispatch(HttpServletRequest request, HttpServletResponse response, String path)throws ServletException, IOException {
		RequestDispatcher dispatch= request.getRequestDispatcher(path);
	      dispatch.forward(request,response);
	}
	
	
}
















