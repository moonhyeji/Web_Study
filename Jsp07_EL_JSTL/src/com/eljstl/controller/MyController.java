package com.eljstl.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eljstl.score.Score;


@WebServlet("/MyController")
public class MyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public MyController() {
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
//--------------------------------------------------------------------
		String command = request.getParameter("command"); //command 라는 이름의 변수 가져와서 요청해라,
		System.out.printf("[%s]\n", command);

		//---------------
		if(command.equals("basic")) { //command 가 basic 이라면 
			RequestDispatcher dispatch = request.getRequestDispatcher("basic-arithmetic.jsp");
			dispatch.forward(request, response); //basic-arithmetic.jsp로 포워드 
		
			
			
			
			//---------------
		}else if(command.equals("el")) { //command 가 el 이라면 
			Score sc = new Score("홍길동", 100, 100, 100);  // score 배열 만들고 sc에 담기 
			
		    request.setAttribute("score", sc);//score 라는 이름에  sc요소를 담아라. 
		   // request.setAttribute("이름", 값 );
		    
		    RequestDispatcher dispatch = request.getRequestDispatcher("eltest.jsp"); 
		    dispatch.forward(request, response);  //값을 eltest.jsp로 포워드해서 전달하는 걸 요청 
			
		    //------------------------------------------------------------------------
		}else if(command.equals("jstl")) {
			List<Score> list = new ArrayList<Score>();
			
			for (int i = 10; i<50; i+=10) {
				Score sc = new Score("이름"+i , 50+i, 50+i, 50+i);
				list.add(sc);
				
			}
			 request.setAttribute("list", list);
			    RequestDispatcher dispatch = request.getRequestDispatcher("jstltest.jsp");
//			    : 서블릿에서 값을 넘겨주고 해당 페이지에서 처리할 수 있도록 하는 방법
			    //RequestDispatcher는 이동할 경로를 설정하고 생성하고, 
//생성된 객체를 가지고 forward 메소드를 통해 해당 경로 페이지로 이동 -> 얘로 인해서 mycontroller 에서 바로 response가 아니라 jstltest로 가는 것. 
			
			    dispatch.forward(request, response);	
			    //forward = mycontroller 에서 바로 response가 아니라 jstltest로 가는 것.
		
		}else if(command.equals("bean")) {  //command 가 bean 이라면 
			response.sendRedirect("usebean.jsp"); //usebean.jsp으로 sendredirect해라. 
		
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doGet(request,response);	
	}

}


//윈도우: cdrive- tomcat 폴더 - webapps - examples -> jsp,servlets.websockets 가 있는데 , 
//jst-jsp2-el 폴더 -'basic-arithmetic'을 복사해서 웹컨텐트 아래에넣기.