<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <% request.setCharacterEncoding("UTF-8"); %>
  <% response.setContentType("texthtml; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

  <h1>RESULT</h1>
  
  <!-- result누르면  -->
  page: <%=pageContext.getAttribute("pageScope") %> <!-- 안나옴 --> 
  <br>
  request: <%=request.getAttribute("requestScope") %> <!-- 안나옴  --> 
  <br>
  session: <%=session.getAttribute("sessionScope") %> <!-- 나옴  --> 
  <br>
  application: <%= application.getAttribute("applicationScope") %>  <!-- 나옴  --> 
<br>
<%
  String requestVal = request.getParameter("requestVal");
%>
  requestVal : <%=requestVal %>



</body>

</html>