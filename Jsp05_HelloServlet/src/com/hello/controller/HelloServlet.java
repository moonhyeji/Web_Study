package com.hello.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Servlet의 Lifecycle(생명주기)
//: init() -> Service() -> doget()/dopost()를 반복.-> destroy()




@WebServlet("/controller.do") //<----- 매핑명칭 
//@WebServlet: 새로운 서블렛 객체 선언. 
///HelloServlet

public class HelloServlet extends HttpServlet {  //HelloServlet:서블릿명칭 
	
	
	private static final long serialVersionUID = 1L; //클래스를 구분하기 위한 값 
	//serialized : 직렬화: 공부해보기! 
	private String initParam;
       
    public HelloServlet() {
       System.out.println("helloservlet  생성! ");
    }
    
	@Override
	public void init(ServletConfig config) throws ServletException {
         
	      System.out.println("hello servelt init!");
	      
	      initParam = config.getInitParameter("actor");
	      String contextParam = config.getServletContext().getInitParameter("singer");
	      
	      System.out.println("initParam : "+ initParam); //initParam : null
	      System.out.println("contextParam : "+ contextParam);//contextParam : billie

	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   //바로 밑에 두줄은 컨트롤러에 오면 가장 먼저 해야함 
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		System.out.println("get방식으로 들어옴.!!! ");
		 
	    String command = request.getParameter("command"); ////////////command
	    System.out.println("["+command+"]"); //[hello]
	    
	    PrintWriter out = response.getWriter();
	    //response응답객체에 , getwriter,작성해줄것이다.
	    //응답되는 객체에 html 형태의 문자열을 만들어서 응답객체에 넣어줌. 이것이 out.print("html문자열")
	    out.print("<h1 style='color:red;'>Hello Servlet</h1>");
	    out.print("<h2>계층구조/LifeCycle/url-mapping</h2>");
	    out.print("<a href='home.html'>home...</a>");
	    //서블릿은  실행 결과를 클라이언트에게 html문서로 응답해 주기 때문에 ,
	    //response로부터 얻어온 출력스트림인 stream out객체의 출력메소드인 print에 
	    //일일이 하드코딩한 html태그를 기술해야 한다.
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		System.out.println("post 방식으로 들어옴!!");
		
	    String command = request.getParameter("command");////////////command
	    System.out.println("{" +command+"}"); //{servlet}

	    
	    String result ="<h1 style='color:blue;'>Hello Servlet</h1>"
	    		      +"<h2>계층구조/LifeCycle/url-mapping</h2>"
	    		      +"<a href='home.html'>home...</a>";
	    response.getWriter().append(result);
	
	}
	@Override
	public void destroy() {
		System.out.println("helloservlet destroy! ");
	}

	
	
	
	
}
