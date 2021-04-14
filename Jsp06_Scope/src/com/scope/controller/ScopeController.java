package com.scope.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/ScopeController") 
public class ScopeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public ScopeController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
    	doPost(request, response);
    	//얘에 의해서 doget 으로 하던, dopost 로 하던 dopost 에서 실행됨. 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
        String requestScope = (String) request.getAttribute("requestScope");
        //string 으로 형변환 해줌
        HttpSession session = request.getSession();
        String sessionScope = (String) session.getAttribute("sessionScope");
        
        ServletContext application = getServletContext();
        String applicationScope = (String) application.getAttribute("applicationScope");
	   
        System.out.println("request : " + requestScope); //콘솔창에 표시되는 애들 
        System.out.println("session : "+ sessionScope);  //콘솔창에 표시되는 애들
        System.out.println("application : " + applicationScope);  //콘솔창에 표시되는 애들
	  
       // PrintWriter out = response.getWriter(); /////////////////
        String responseText = "<h1>RESULT</h1>"
        		 +"<table border=1>"
        		 +"<tr>"
        		 +"    <th>request<th>"
        		 +"    <td>"+requestScope+"</td>"  //null
        		 +"</tr>"
        		 +"<tr>"
        		 +"    <th>session<th>"
        		 +"    <td>"+sessionScope+"</td>" //session Scope value
        		 +"</tr>"
        		 +"<tr>"
        		 +"    <th>application<th>"
        		 +"    <td>"+applicationScope+"</td>" // application scope value
        		 +"</tr>"
        		 +"</table>";
        
          //out.print(responseText);   //////////
        
        String requestVal = request.getParameter("requestVal");
        System.out.println("requestVal : " + requestVal);
        
        
        
   request.setAttribute("requestScope", "request forward value");
     //requestScope 이란 이름으로 request forward value속성을 담아서 보내겠다, 
    
    
   RequestDispatcher dispatch = request.getRequestDispatcher("result.jsp" );
    //getRequestDispatcher: result.jsp로페이지 보내기.
    //리퀘스트는 result.jsp라는 페이지를 지정해서 보내는데,requestScope를 보낸다. 보낼 객체 -dispatch에 담기 

    
  dispatch.forward(request, response);
    //forward(request, response); - forward 방식으로 페이지 연결하면서 이동?
////      RESULT 페이지로 넘어가고, 
//        page: null
//        request: request forward value 라고 값이 저장되어 잇음. 
//        session: session Scope value
//        application : application scope value
	
        
        
        //1.result 눌렀을 때 request 가 왜 null 인가?
    //페이지 하나에 리퀘스트와 리스펀스 가 있는데,
    //-result의 request에서 ,index의 request에 담아둔 값을 찾았기 때문에 null 이 뜬다. 
    //index 에 잇는 setattribute 의 request 와 result.jsp 에 있는 getattribute의 request 가 다르기 때문에 
    
    
        //2.servlet을 눌렀을 때 console창에 출력되는 request가 왜 null인가.?
        //3.servlet을 눌렀을 때 result.jsp의 request는 왜 null 이 아닌 값이 나오는가?	
	}

}
