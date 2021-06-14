package com.score.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;



@WebServlet("/score.do")
public class ScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ScoreServlet() {
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  request.setCharacterEncoding("UTF-8");
	  response.setContentType("text/html; charset=UTF-8");
	  
	  String name = request.getParameter("name");
	  int kor = Integer.parseInt(request.getParameter("kor"));
	  int eng = Integer.parseInt(request.getParameter("eng"));
	  int math = Integer.parseInt(request.getParameter("math")); //name,kor,eng,math가 get방식으로 넘어왔으니까 받아줌. 
	  
	  int sum = kor + eng + math;
      double avg = sum / 3.0;   //국영수로 총점,평균 구하고, 
      
      JSONObject obj = new JSONObject();  //json 객체 만들어줌.
      obj.put("name", name);
      obj.put("sum", sum);
      obj.put("avg", String.format("%.2f", avg)); //까지 json객체 
      
      PrintWriter out = response.getWriter();
      out.println(obj.toJSONString()); //toJSONString: 객체의 값을 문자열형태로 바꿔줌 {"avg":"73.33","name":"bilie","sum":220}
      //왜 json 객체로 보내지 않고 toJSONString로 해서 문자열로 보낼까? 
      //->서버에서 클라이언트로 응답할 때, 자바 객체를 그냥 보내면 해당 객체의 주소값이 문자열 형태로 반환되기 때문에.객체 값을 문자열로 바꿔서 반환시켜준다. 
      
      
      System.out.println("servlet에서 보내는 데이터" +obj.toJSONString()	);
      
	  
	}

}
