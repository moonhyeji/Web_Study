<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.my.dao.MyBoardDao"%>
<%@ page import="java.util.List" %>
<%@ page import="com.my.dto.MyBoardDto" %>
<%@ page import="com.my.biz.MyBoardBiz" %>

  <% request.setCharacterEncoding("UTF-8"); %>
  <% response.setContentType("texthtml; charset=UTF-8"); %>
    <%-- 한글 안깨지도록 방지해주는 코드들!  --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

 <%
  MyBoardBiz biz = new MyBoardBiz();
  List<MyBoardDto> list = biz.selectList();
 %>
 <h1>LIST</h1>
 <table border ="1">

   <col width="50px">
   <col width="100px">
   <col width="500px">
   <col width="100px">
      <tr>
	      <th>번호</th>
	      <th>작성자</th>
	      <th>제목</th>
	      <th>작성일</th>
      </tr>


 <%
   for( MyBoardDto dto: list){
 %>


   <tr>
     <td><%= dto.getMyno() %></td>
     <td><%= dto.getMyname() %></td>
     <td><a href ="myselect.jsp?myno=<%=dto.getMyno()%>"><%= dto.getMytitle() %></a></td>
     <td><%= dto.getMydate()%></td>
   </tr>

 <%
   }
 %>
	  <tr>
	     <td colspan="4" align="right">
	        <input type="button" value="글작성" onclick="location.href='myinsert.jsp'">
	      </td>
	      
	  </tr>
 </table>

</body>
</html>
