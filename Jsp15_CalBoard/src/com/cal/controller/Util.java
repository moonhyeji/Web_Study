package com.cal.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import com.cal.dto.CalDto;

public class Util {
	
		private String todates;
		
		
		public String getTodates() {
			return todates;
		}
		
		public void setTodates(String mdate) {
			// yyyy-MM-dd hh:mm:00 형태의 모양으로 바꾸자.
			
			       String d = mdate.substring(0,4) + "-"
							+ mdate.substring(4,6) + "-"
							+ mdate.substring(6,8) + " "
							+ mdate.substring(8,10) + ":"
							+ mdate.substring(10) + ":00";
			
			// 날짜와 시간 패턴 입력 - 원하는 형태의 포멧 선언 
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy년MM월dd일 HH시mm분");
			
			// String 객체를 Timestamp로 변환 문자열 객체를 timestamp값으로 변환 
			Timestamp tm = Timestamp.valueOf(d);
			
			//timestamp값의 형태를 sdf로 바꾼 후 문자열로 저장.
			todates = sdf.format(tm); //tm을 
			
		}
		
		
		//------------------------------------------------------
		public static String fontColor(int date, int dayOfWeek) {
			String color = "";
			
			if ((dayOfWeek-1+date)%7 == 0) {
				color = "blue";
			} else if ((dayOfWeek-1+date)%7 == 1) {
				color = "red";
			} else {
				color = "black";
			}
			
			return color;
    	}
		//----------------------------------------------
		public static String isTwo(String msg) {
			return (msg.length() < 2)? "0" + msg : msg;  //20210303 에서의 0 맞춰주려고 
		}
		
		//----------------------------------------------------------
		public static String getCalView(int i, List<CalDto> list) {
			String d = isTwo( i + "" );  
			String res = ""; //변수선언, 초기화 구문
			
			for(CalDto dto : list) {
				if(dto.getMdate().substring(6, 8).equals(d)) {
					res += "<p>"
					     + ((dto.getTitle().length() > 6)? dto.getTitle().substring(0,6)+"...": dto.getTitle())
				         + "</p>"; //6글자 넘어가면 너무 기니까 ... 이 나와서 ...으로 표시해 주려고 ! 
				}
			}
		return res;
		}
}
//res 에 null 을 입력하고 res += 를 =를 하면 한줄만 나오고 다 null  나옴 .
//res += 는 일정이 여러개일 경우 일정을 하나하나 추가하면서 넣어줘야 하니까 += 으로 함. 
  





