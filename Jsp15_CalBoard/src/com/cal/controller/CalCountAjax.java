package com.cal.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cal.dao.CalDao;

import net.sf.json.JSONObject;

@WebServlet("/count.do")
public class CalCountAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CalCountAjax() {
         }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text.html; charset=UTF-8");
	
	
	String id = request.getParameter("id");
	String yyyyMMdd = request.getParameter("yyyyMMdd");
     System.out.println("param: " +id+ "," +  yyyyMMdd);
     
     CalDao dao = new CalDao();
     int count = dao.calendarViewCount(id, yyyyMMdd);
     System.out.println(" 일정 갯수 : " + count);
     
    Map<String, Integer> map = new HashMap<String, Integer>();
    map.put("count", count);  //map = {[key:"count" : value:count]} 
    //map 이 key value 로 되어있어서 json(key value)으로 바꿀 수 있어서 map을 사용. 
    //json이 내부적으로 map이기 때문에 map사용. 
    
    JSONObject obj = JSONObject.fromObject(map);
    
    PrintWriter out = response.getWriter();
    obj.write(out);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 doGet(request, response);
	}

}














