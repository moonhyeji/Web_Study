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
//<!-- 보내준 것 받기  -->
LoginDto dto = (LoginDto)request.getAttribute("dto");
%>

<h1>update role</h1>

<form action="logincontroller.jsp" method="post">
  <input type="hidden" name="command" value="updaterole">
  <input type="hidden" name="myno" value="<%=dto.getMyno()%>">
  <table border="1">
    <tr>
      <th>번호</th>
      <td><%=dto.getMyno() %></td>
   </tr>
   <tr>
      <th>이름</th>
      <td><%=dto.getMyrole() %></td>
   </tr>
   <tr>
      <th>등급</th>
      <td>
        <select name="myrole">
     <!--    화면에 수정하기 전에 어떤 애가 보이고 싶은거냐 하는 것. 
        긜고 submit 으로 내가 수정할 값이 들어가게 하는 것  -->
           <option value="user" <%=dto.getMyrole().equals("USER")?"selected":"" %> >일반회원</option>
           <option value="user" <%=dto.getMyrole().equals("ADMIN")?"selected":"" %> >관리자</option>
        </select>
      </td>
   </tr>
   <tr>
     <td colspan="2">
     <input type="submit" value="변경완료">
   </tr>
   
  
  
  </table>
</form>
</body>
</html>