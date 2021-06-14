<%@page import="com.login.dto.LoginDto"%>
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

<%
   LoginDto dto = (LoginDto)request.getAttribute("dto");
%>
  <h1> User Update</h1>
  <h2>If you need to update your info, please update here</h2> 
  <form action="logincontroller.jsp" method="post" >
  <input type="hidden" name="command" value="userupdate">
  
  <table border="1">
    <tr>
      <th>ID</th>
      <td>
      <input type="text" name="myid" value="<%=dto.getMyid() %>" required="required">
      <td>
    </tr>
    <tr>
      <th>PW</th>
      <td>
      <input type="text" name="mypw" value="<%=dto.getMypw() %>" required="required">
      <td>
    </tr>
    <tr>
      <th>NAME</th>
      <td>
      <input type="text" name="myname" value="<%=dto.getMyname() %>"required="required">
      <td>
    </tr>
    <tr>
      <th>ADDR</th>
      <td>
      <input type="text" name="myaddr" value="<%=dto.getMyaddr() %>" required="required">
      <td>
    </tr>
    <tr>
      <th>PHONE</th>
      <td>
      <input type="text" name="myphone" value="<%=dto.getMyphone() %>" required="required">
      <td>
    </tr>
    <tr>
      <th>EMAIL</th>
      <td>
      <input type="text" name="myemail" value="<%=dto.getMyemail() %>" required="required">
      <td>
    </tr>
    <tr>
     <td colspan="3">
     <input type="submit" value="수정완료">
     </td>
    </tr>
  
  </table>
  </form>
</body>
</html>