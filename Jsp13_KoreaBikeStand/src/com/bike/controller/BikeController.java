package com.bike.controller;
import java.util.List;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bike.dto.BikeDto;
import com.bike.dao.BikeDao;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;

@WebServlet("/bike.do")
public class BikeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public BikeController() {
          }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        request.setCharacterEncoding("UTF-8");
	        response.setContentType("text/html; charset=UTF-8");
	
	        
	        BikeDao dao = new BikeDao();
	        
	        String command = request.getParameter("command");
	        System.out.println("[" +command+"]");
	        
	        if(command.equals("view")) {   //command 가 view 라면,명령을 실행해라.
	        	response.sendRedirect("view.html"); //view.html로 가기. 
	        
	        	
	        	//-------------------------------------------------------------
	        }else if(command.equals("getdata")) {
	        	if(dao.delete()) {
	        		System.out.println("삭제 성공");
	        		
	        	}else {
	        		System.out.println("삭제 실패");
	        	}
	        	
	        	//resources/json/bike.json을 문자열로 바꿔서 mydata 이름으로 보내줬음. (bike.js에서!!!)
	        	//이 보낸걸 받아줌.!!!
	        	String data = request.getParameter("mydata");  //bike.js에서 stringify에 따라서 문자열로 된 mydata를 string문자열의 data에다시 담고,
	        	
	        	
	        	//중요!!!static JsonElement	parseString​(java.lang.String json)	 : Parses the specified JSON string into a parse tree : json문자열에서 json element를 만들어올거야. 
	        	       //!!!  ㄴ jsonparser 에 의해 만들어진 JsonElement: object,primitive, null 일 수도있다.
	        	//parseStirng 하면 return 값이 jsonobject(최상위객체)라서 , Jsonelement 에 넣어주기 위해서 jsonparser를 해준다.
	        	/*
	        	 *JsonElement: JsonObject,JsonArray, Jsonprimitive, jsonNull 들이다. -> 어째든 json요소이다.
	        	 *JsonObject: name-vlaue 한 쌍으로 이루어진 객체이다.=json객체인데, {String: JsonElement} 형태로 되어있다.name은 string 타입이고 값은 jsonelemet 다.
	        	 * */
	        	JsonElement element = JsonParser.parseString(data); //!!Jsonelement:  어쨋든 이건 json 이야! 라고 말해주고 있음. //JsonElement: 최상위클래스 //JsonString을 Jsonobject로 받으려면, jsonparser 나 parseString 해줘야함//parseString의 return은 jsonelement이므로 변환해 jsonobject로 받
	                // ㄴ 보내준 게 {field: v } { record: v} 이 형태로 되어있다.이게 element 인데, 
	        	JsonObject jsonData = element.getAsJsonObject();  //위의 element (타입: JsonElement)를 JsonObject로 타입변환해줌! 
	        	
	        	JsonElement records = jsonData.get("records");   //json 파일의 record부분을 
	        	JsonArray recordsArray = records.getAsJsonArray();   //arrya형태로 담아줘서 
	        	
	        	List<BikeDto> list = new ArrayList<BikeDto>();   // 새로운 비어있는 Arraylist객체 만들어서 , 
	        	JsonArray resultArray = new JsonArray();       //새로운 비어있는 Array객체를 만들어서 , 
	        	
	        	for(int i = 0; i<recordsArray.size(); i++) {   //값들을 하나하나 넣어준다.
	        		String name = recordsArray.get(i).getAsJsonObject().get("자전거대여소명").getAsString();
	        		
	        		String addr = null;
	        		if(recordsArray.get(i).getAsJsonObject().get("소재지도로명주소") != null) {
	        			
	        		addr= recordsArray.get(i).getAsJsonObject().get("소재지도로명주소").getAsString();
	        			        	}else {
	        			        		addr= recordsArray.get(i).getAsJsonObject().get("소재지지번주소").getAsString();
	        			        	}
	        	    Double latitude = recordsArray.get(i).getAsJsonObject().get("위도").getAsDouble();
	        	    
	        	    Double longitude = recordsArray.get(i).getAsJsonObject().get("경도").getAsDouble();
	        	  
	        	    int bike_count = recordsArray.get(i).getAsJsonObject().get("자전거보유대수").getAsInt();
	        	    
	        	    BikeDto dto = new BikeDto(name, addr, latitude,longitude, bike_count);
	        	    list.add(dto);
	        	    
	        	    Gson gson = new Gson();
	        	    String jsonString = gson.toJson(dto);
	        	    resultArray.add(JsonParser.parseString(jsonString));
	        	
	        	}
	        	//---------------------------------------------------------
	        	if(dao.insert(list)) {
	        		System.out.println("저장성공");
	        	}else {
	        		System.out.println("저장실패");
	        	}
	        	JsonObject result = new JsonObject();
	        	result.add("result", resultArray);
	        	
	        	response.getWriter().append(result+"");
	        }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doGet(request, response);
	}

}
