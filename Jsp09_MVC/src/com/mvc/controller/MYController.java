
package com.mvc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import com.mvc.biz.MYBiz;
import com.mvc.biz.MYBizImpl;
import com.mvc.dto.MYDto;

/**
 * Servlet implementation class MYController
 */
@WebServlet("/MYController")
public class MYController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public MYController() {
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		MYBiz biz = new MYBizImpl();
		
		String command = request.getParameter("command");
		//----------------------------------------------------------------------------
		if(command.equals("list")) {
			//1 받을게 있으면 받자, 받을게 없음 - 인덱스에서 받을게 없음. 
			//2 
			List<MYDto> list = biz.selectList();
			//3 전달할 값이 잇으면 담아주자,  list를 담아주자 .
			request.setAttribute("list",list);
			//4
			dispatch(request, response, "myboardlist.jsp");
		
		
		//-------------------------------------------------
		
		}else if(command.equals("insertform")){
        //1
			//2
			//3
			//4
			response.sendRedirect("myboardinsert.jsp");
		
		
		}else if(command.equals("insertres")) {
			//1
			String myname = request.getParameter("myname");
			String mytitle = request.getParameter("mytitle");
			String mycontent = request.getParameter("mycontent");
			//2
			MYDto dto = new MYDto(0,myname,mytitle,mycontent,null);
			int res = biz.insert(dto);
			
			//3
			//4
			if( res > 0) {
				jsResponse(response,"글 작성 성공~~!! ","mymvc.do?command=list");
			}else {
				jsResponse(response,"글 작성 실패..ㅠㅠ","mymvc.do?command=insertform");
			}
		
		
		}else if(command.equals("select")) {
			//1.
			int myseq =Integer.parseInt(request.getParameter("myseq"));
			//2
			MYDto dto = biz.selectOne(myseq);
			//3.
			request.setAttribute("dto", dto);
			//4
			dispatch(request, response, "myboardselect.jsp");
		
		
		
		
		}else if(command.equals("updateform")) {
			//1.
			int myseq = Integer.parseInt(request.getParameter("myseq"));
			//3
			MYDto dto = biz.selectOne(myseq);
			//3
			request.setAttribute("dto", dto);
			//4
			dispatch(request, response, "myboardupdate.jsp");
	
		
		
		
		}else if(command.equals("updateres")) {
		
		//1
        int myseq = Integer.parseInt(request.getParameter("myseq"));
		String mytitle = request.getParameter("mytitle");
		String mycontent = request.getParameter("mycontent");
		
		//2
		MYDto dto = new MYDto(myseq,null ,mytitle,mycontent,null);
		int res = biz.update(dto);
		
		//3
		//4
		if( res > 0) {
			jsResponse(response,"글 수정 성공~~!! ","mymvc.do?command=select&myseq="+myseq); //수정 성공 하고 나서 select에 가잇음. 
		}else {
			jsResponse(response,"글 수정 실패..ㅠㅠ","mymvc.do?command=updateform&myseq="+myseq);
		}
		
		
	}else if(command.equals("delete")) {
		//1
		int myseq =Integer.parseInt(request.getParameter("myseq"));
		//2
		int res =biz.delete(myseq);
		//3
		//4

		if( res > 0) {
			jsResponse(response,"글 삭제  성공~~!! ","mymvc.do?command=list"); //삭제하고 리스트 가기
		//	jsResponse(response,"글 삭제  성공~~!! ","mymvc.do?command=select&myseq="+myseq); - 삭제하고 나서 selecpage임. 삭제되었으니까 빈 화면 보임. 

		}else {
			jsResponse(response,"글 삭제 실패..ㅠㅠ","mymvc.do?command=list");
		}
		
	}
		
		
	}
private void dispatch(HttpServletRequest request,HttpServletResponse response,String path) throws ServletException, IOException{
	
	request.getRequestDispatcher(path).forward(request, response);
 
}

private void jsResponse(HttpServletResponse response, String msg, String url) throws IOException {
	String responseText ="<script type ='text/javascript'>"
			+"alert('"+msg+ "');"
            +"location.href='"+url+"';"
            +"</script>";
	response.getWriter().print(responseText);
}

}
