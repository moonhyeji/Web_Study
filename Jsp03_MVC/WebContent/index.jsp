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


    <jsp:forward page="mvccontroller.jsp?command=list"></jsp:forward>
    <!--  인덱스에서 mvccontroller 받아서 값 전달  -->

</body>
</html>