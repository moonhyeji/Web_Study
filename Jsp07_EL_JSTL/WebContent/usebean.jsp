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


 <h1>jsp:useBean</h1>
 
  <jsp:useBean id="sc" class="com.eljstl.score.Score" scope="session"></jsp:useBean>
   <%-- Score sc = new Score(); 과 같은 뜻  --%>
   <%-- jsp 에서 : 자바객체(useBean) 사용할 것이다
   class="com.eljstl.score.Score 이 클래스타입으로 객체 만들건데 /score 객체 만들건데   
   id="sc" 객체의 변수이름을 이걸로 하겠다.
   
    scope="session 객체가 class="com.eljstl.score.Score"에 setattribute 됨.
   
    --%> 
   
   
  
 <jsp:setProperty property="name" name="sc" value="hong"></jsp:setProperty>
 <%-- sc.setName("hong"); --%>
 
 <jsp:getProperty property="name" name="sc"></jsp:getProperty>
 <%-- sc.setName(); --%>
 
 <a href="result.jsp">scope</a>
 
 
</body>
</html>