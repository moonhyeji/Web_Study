<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <% request.setCharacterEncoding("UTF-8"); %>
  <% response.setContentType("texthtml; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
/*  pageScope 이란 이름에 page scope value 이란 요소를 넣어서 객체생성  */
  pageContext.setAttribute("pageScope","page scope value");
  request.setAttribute("requestScope","request scope value");
  session.setAttribute("sessionScope","session Scope value");
  application.setAttribute("applicationScope","application scope value");
%>

</head>
<body>

  <h1>INDEX</h1>
  page: <%=pageContext.getAttribute("pageScope") %> <!-- 현재페이지 --> 
  <!--  pageScope 이란 이름에 page scope value 이란 요소를 넣어서 객체생성 했으니까,
  pageScope 에는 page scope value가 들어가서  page: page scope value
  됨?????????????????? -->
  <br>
  request: <%=request.getAttribute("requestScope") %> <!-- 요청받은 페이지 --> 
  <br>
  session: <%=session.getAttribute("sessionScope") %> <!-- 세션이 만료되기 전까지 --> 
  <br>
  application: <%= application.getAttribute("applicationScope") %> <!-- 모든 --> 
   <br>
   
   <a href="result.jsp">result</a> <!--  링크생성 -->
   <br>
   <a href="scope.do">servlet</a> <!--  링크생성 -->
   <br>
   <form action="scope.do" method="get">
  <!-- action="scope.do"  는 scope 이란 이름의 서블릿이고,
  scope서블릿의 경로는 : com.scope.controller.ScopeController 이기 때문에 
  이 경로로 get방식으로 이동, -->
     <input type="hidden" name="requestVal" value="my request value">
     <input type="submit" value="request">
   </form>
<!--  버튼 누르니  requestVal : my request value   이 전달됨. -->


</body>
</html>