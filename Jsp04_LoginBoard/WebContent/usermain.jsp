<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@page import=" com.login.dao.LoginDao" %>
   <%@page import=" com.login.dto.LoginDto" %>
   <%@page import="java.util.List" %>
  <% request.setCharacterEncoding("UTF-8"); %>
  <% response.setContentType("texthtml; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<% 
		//세션에 저장된 값 가지고 오기 
		  LoginDto dto =(LoginDto) session.getAttribute("dto");     
		 %>
		     
        <h1><%=dto.getMyid() %> 님 환영합니다.</h1>
        <p>
           <a href="logincontroller.jsp?command=logout">로그아웃</a>
        </p>
        
        <div>
          <p> 
          <a href="logincontroller.jsp?command=userdetail&myno=${dto.myno }">내 정보 보기</a>
          </p>
        </div>
</body>
</html>