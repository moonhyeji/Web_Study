package com.mvc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.biz.MVCBiz;
import com.mvc.biz.MVCBizImpl;
import com.mvc.dto.MVCDto;

@WebServlet("/mvc.do")
public class MVCController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public MVCController() {
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		
		String command = request.getParameter("command");
		System.out.println("{"+command+"}");

		MVCBiz biz = new MVCBizImpl();
		try {
			if (command.equals("list")) {  //command 가 list 로 가고싶다고 요청 
				// 1. 전달된 값 있으면 받자. ->리스트는 전달해주는 값이 없음.(인서트.업뎃이랑 다르게)
			    // 2. db연결 필요시 연결하자 ->db에서 list 내용물 받아와야하니까 연결필요 .
				List<MVCDto> list = biz.selectList();
				//전체출력을 하고싶다면,  mylist.jsp 화면에서 biz 호출해서,
				//biz에선 dao호출해서, 
				//dao에서 db랑 연결해서 값을 가져와서 dto에 담는 것을 반복해서 list로 담고,
				//biz로 리턴하면, biz는 화면으로 list를 리턴해주는 것을 의미.
				
				
				// 3. 보내줄 값 있으면 request에 담아주자- requset  list 라는 attributer 를 list라는 이름으로 set 하겠다. 
				request.setAttribute("list", list);
				
				// 4. 보내기 .mvclist.jsp로 보내자 
				dispatch(request, response, "mvclist.jsp");
			//-----------insert ------------------------------------------------
			
			} else if (command.equals("insertform")) {
				// 1.
				// 2.
				// 3.
				// 4. command가 insertform으로 가고싶다고 요청하면,
				//    요청을 받아서 mvcinsert.jsp로 sendredirect 하겠다.
				//(insertform 은 단순 글작성의 html 페이지!)
				response.sendRedirect("mvcinsert.jsp");
				
				

			} else if (command.equals("insertres")) { //응답 
				// 1. 전달할 값 가져와서 
				String writer = request.getParameter("writer");
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				// 2. 디비랑 연결 
				MVCDto dto = new MVCDto(0,
						                writer, 
						                title, 
						                content, 
						                null);
				int res = biz.insert(dto);
				// 3.
				// 4.보내기 
				if (res > 0) {
					     dispatch(request, response, "mvc.do?command=list");
//					--------------------
//					request.setAttribute("list", biz.selectList());
//					dispatch(request, response, "mvclist.jsp");
				} else {
					     dispatch(request, response, "mvc.do?command=insertform");
				}
				
				
				//----------선택 -----------------------------------------------------
			}else if(command.equals("select")) {
				//1.전달된 값 잇으면 받자 
				int seq = Integer.parseInt(request.getParameter("seq"));
				//2. 디비와 연결 
				MVCDto dto = biz.selectOne(seq);
				
				//3.보내줄값 담기. 보내줄 값 잇으면 dto 에담기 . 
				request.setAttribute("dto", dto);
				//4.보내기 
				dispatch(request, response, "mvcselect.jsp");
			
				//------------수정 ---------------------------------------------------

			}else if(command.equals("updateform")) {
				
				 int seq = Integer.parseInt(request.getParameter("seq"));

				 MVCDto dto = biz.selectOne(seq);
                 
				 request.setAttribute("dto",dto);

			     dispatch(request, response, "mvcupdate.jsp");
			     
			     
			     
			}else if(command.equals("updateres")){
				//1
				int seq = Integer.parseInt(request.getParameter("seq"));
			    String title = request.getParameter("title");
			    String content = request.getParameter("content");
					//2.
					MVCDto dto = new MVCDto();
					dto.setSeq(seq);
					dto.setTitle(title);
					dto.setContent(content);
					int res = biz.update(dto);
					
					
					//res = 수정에 성공했거나, 수정에 실패했따.
							//성공했으면 list로 가야함! 
							//성공 -> 했으면 컨트롤러로 감. 컨트롤러로 안가고 페이지로 가도 되는데,
							//바로 패이지로 가면 안보임! 
					if (res > 0){
						//3.
						List<MVCDto> list = biz.selectList();
						request.setAttribute("list",list);
						//4.
					     dispatch(request, response, "mvclist.jsp");

					}else{
						//3.
						MVCDto updateDto = biz.selectOne(seq);
						request.setAttribute("dto", updateDto);
						//4.
					     dispatch(request, response, "mvcselect.jsp");
						////////////이거 3,4, 3,4,  두가지 방법에 대해서 공부하기 . 
					}		
//---------------------------삭제 ---------------------------------
					
			}else if(command.equals("delete")){
				//1.
				int seq = Integer.parseInt(request.getParameter("seq"));
				//2.
				int res = biz.delete(seq);
				//3.
				//4
				if(res > 0){
				     dispatch(request, response, "mvc.do?command=list");
				} else {
				     dispatch(request, response, "mvc.do?command=insertform");
			}
//--------------------------------------------
				   	
					}

		    } catch (Exception e) {
			request.setAttribute("error", e);
			dispatch(request, response, "error.jsp");
		    }

	}

	
	
	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void dispatch(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		// forward or include 때문이다.
		RequestDispatcher dispatch = request.getRequestDispatcher(path);
		// RequestDispatcher 요청받은거 전달해주는 객체.
		dispatch.forward(request, response);
	}

}
