<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("texthtml; charset=UTF-8"); %>
 <%@ page import="com.my.dao.MyBoardDao"%>
<%@ page import="java.util.List" %>
<%@ page import="com.my.dto.MyBoardDto" %>
<%@ page import="com.my.biz.MyBoardBiz" %>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- request  객 체 안에 key = value  형식으로 넘어갈 때는 문자열로만 넘어가기 때문에 int 로 바꿔줘야 해서 써줌  --> 
  <%
  int myno = Integer.parseInt(request.getParameter("myno"));
  
  MyBoardBiz biz = new MyBoardBiz();
  MyBoardDto dto = biz.selectOne(myno);
  %>
   
 
   <h1>UDATE</h1>
   
   <form action="myupdateres.jsp" method="post">
  <!-- <table></table>에서 <form>의  myupdateres엑션경로로 값을 3개넘겨줘야 성공이다 실패다를 알텐데 myno값을 전달하지 않고 잇음! -->
   <input type="hidden" name="myno" value="<%=dto.getMyno() %>"> 
   
   
		   <table border ="1">
		    <tr>
		      <th>작성자</th>
		      <td><%=dto.getMyname() %></td>
		    </tr>
		    <tr>
		      <th>제목</th>
		      <td><input type="text" name="mytitle" value="<%=dto.getMytitle() %>" ></td>
		    </tr>
		    <tr>
		      <th>내용</th>
		      <td><textarea rows="10" cols="60" name="mycontent"><%=dto.getMycontent()%></textarea></td>
		    </tr>
		    <tr>
		       <td colspan="2" align="right">
		       <input type="submit" value="수정">
		       <input type ="button" value="취소" onclick="location.href='mylist.jsp'">
		    </tr>
		   </table>
   </form>  
</body>
</html>