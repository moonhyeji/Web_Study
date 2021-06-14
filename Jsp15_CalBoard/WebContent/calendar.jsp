<%@page import="com.cal.dto.CalDto"%>
<%@page import="java.util.List"%>
<%@page import="com.cal.dao.CalDao"%>
<%@page import="com.cal.controller.Util"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">

	#calendar{
		border-collapse: collapse;
		border: 1px solid gray;
	}
	#calendar th{
		width: 80px;
		border: 1px solid gray;
	}
	#calendar td{
		width: 80px;
		height: 80px;
		border: 1px solid gray;
		text-align: left;
		vertical-align: top;
		position: relative;
	}
	a {
		text-decoration: none;
	}
	
	.list > p {
	font-size: 5px;
	margin: 1px;
	background-color: skyblue;
	}
	
	.preview{
	position: absolute;
	top: -30px;
	left: 10px;
	background-color: skyblue;
	width: 40px;
	height: 40px;
	text-align: center;
	line-height: 40px;
	border-radius: 40px 40px 40px 1px;
	}
</style>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript">

$(function(){
	$(".countview").hover(function(){  //class.가져와서 hover<- 커서 올라가면, 
		//1. 질문 
		//var countView = $(this)  -> var countView = $(".countview") 인가요? 간편하게 사용하려고 countView  라고 해준건가?
		var countView = $(this);                                         //  $(this) 가  $(".countview") //this는 자신의 클래스(이름이 같을 떄 사용) // trim은 좌우 공백을 제거
	    var year = $(".y").text().trim();   
	    var month = $(".m").text().trim();
	    var countDate = countView.text().trim();	    
	    
	    var yyyyMMdd = year + isTwo(month) + isTwo(countDate);
	
	    $.ajax({ //비동기선언 
	    	url: "count.do",
	    	data: "id=kh&yyyyMMdd=" + yyyyMMdd,
	    	type: "post",
	    	dataType: "json",
	    	async: false,  //동기화로 동작 
	    	success: function(msg){ //msg: 성공했을 때 받아오는 데이터값 
	    	    var count = msg.count; 
	    		countView.after("<div class='preview'>" +count+ "</div>");
	    		//.after():요소 다음에 붙여준다. class가 countview인 a태그 <a></a> 의 다음에 붙여준다. 
	    	//count는 datatype 이 json이니까, calcountajax의 jsonobject 객체에서 , 
	    	},
	    	error: function(){
	    		alert("통신 실패");
	    	}
	    });
	
	}, function(){ 
		$(".preview").remove();
	});
});
	function isTwo(n){
		return (n.length < 2)? "0" + n:n;
	}

</script>


</head>
<%
	// 캘린더 객체 생성 : 현재 날짜와, 시간에 대한 정보를 가진다.                             
	// Calender가 가지고 있는 get메소드에 상수를 어떤 것을 넣어주느냐에 따라 다른 값이 나오게 된다.
	
	
	Calendar cal = Calendar.getInstance();
	
    
	int year = cal.get(Calendar.YEAR);
	int month = cal.get(Calendar.MONTH) + 1;	// 월은 0부터 시작한다. 
	
	String paramYear = request.getParameter("year");
	String paramMonth = request.getParameter("month");
	
	// Integer.parseInt : () 값을 int로 변환 
	if (paramYear != null) {
		year = Integer.parseInt(paramYear);
	}
	if (paramMonth != null) {
		month = Integer.parseInt(paramMonth);
	}
	if (month > 12) {
		year++;
		month = 1;
	} 	// 주소창 month=13일 경우 년도+ 1월 출력
			
	if (month < 1) {
		year --;
		month = 12;
	} // 주소창 month=0일 경우 년도- 12월 출력 
	
	cal.set(year, month-1, 1); //년 월 일
	
	int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1~7까지의 값 리턴, 일~토
	int lastday = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // 해당 월의 말일
	
	CalDao dao = new CalDao();
	String yyyyMM = year + Util.isTwo(String.valueOf(month));
	List<CalDto> list = dao.calendarViewList("kh", yyyyMM);

%>
<body>

	<table id = "calendar">
		<caption>
				<a href="calendar.jsp?year=<%=year-1%>&month=<%=month%>">◁</a>				
				<a href="calendar.jsp?year=<%=year%>&month=<%=month-1%>">◀</a>			
				
				<span class="y"><%=year %></span>년
				<span class="m"><%=month %></span>월
				
	
				<a href="calendar.jsp?year=<%=year%>&month=<%=month+1%>">▶</a>
				<a href="calendar.jsp?year=<%=year+1%>&month=<%=month %>">▷</a>
			</caption>
				<tr>
					<th>일</th><th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th>토</th>
				</tr>
				<tr> 
<%
				for (int i = 0; i < dayOfWeek-1; i++) {
					out.print("<td></td>");
				}
	          	for (int i = 1; i <= lastday; i++) {
  
%>				
				<td>	
					<a class="countview" href="cal.do?command=calendarlist&year=<%=year %>&month=<%=month %>&date=<%=i %>"
					 style="color: <%=Util.fontColor(i, dayOfWeek) %>"><%=i %></a>
							
					<a href="insertcalendar.jsp?year=<%=year %>&month=<%=month %>&date=<%=i %>&lastday=<%=lastday %>">
						<img alt="일정 추가" src="resources/image/pen.png" style="width: 10px; height:10px;" />
					</a>
					
					<div class="list">
					   <%=Util.getCalView(i,list) %> <!-- jsp는 < % = 이것만 쓰면 가져와 지니까   -->
					</div>
				</td>
				
<%
			if ((dayOfWeek-1+i)%7 == 0) {
				out.print("</tr><tr>");	  //0이면 다음으로 넘어가게 해주려고,
			}
		}
		for (int i = 0; i < (7-(dayOfWeek-1 + lastday) %7)%7 ; i++ ) {
			 	out.print("<td></td>");	// 마지막에 날짜 남는 애들 빈칸으로 만들어 주기 위한 코드 
		}
%> 
			</tr>
	</table>
</body>
</html>
