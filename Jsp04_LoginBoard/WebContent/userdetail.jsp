<%@page import="com.login.dto.LoginDto"%>
<%@page import="java.util.List"%>
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


    <h1>내 정보(USER))</h1>
	   <table border="1">
	       <col width="50">
	       <col width="100">
	       <col width="100">
	       <col width="500">
	       <col width="200">
	       <col width="200">
	       <col width="100">
	       <col width="100">
	       <col width="100">
	       
	     <tr>
	        <th>MYNO</th>
	        <th>ID</th>
	        <th>PW</th>
	        <th>NAME</th>
	        <th>ADDR</th>
	        <th>PHONE</th>
	        <th>EMAIL</th>
	        <th>ENABLED</th>
	        <th>ROLE</th>
	     </tr>     


	     <tr>
         <td><%=dto.getMyno() %></td>
	     <td><%=dto.getMyid() %></td>
	     <td><%=dto.getMypw() %></td>
	     <td><%=dto.getMyname() %></td>
	     <td><%=dto.getMyaddr() %></td>
	     <td><%=dto.getMyphone() %></td>
	     <td><%=dto.getMyemail() %></td>
	     <td><%=dto.getMyenabled() %></td>
	     <td><%=dto.getMyrole() %></td>
	     </tr>
	      
	  
	     <tr>
	     <td colspan="9">
	       <input type="button" value="수정" onclick="location.href='logincontroller.jsp?command=userupdateform&myno=${dto.myno}'"> 
	       <input type="button" value="탈퇴" onclick="location.href='logincontroller.jsp?command=delete&myno=${dto.myno}'"> 
	     </td>
	     </tr>

	    </table>
	     

</body>
</html>