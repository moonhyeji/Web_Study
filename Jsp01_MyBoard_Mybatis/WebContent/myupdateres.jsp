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
<%
int myno = Integer.parseInt(request.getParameter("myno"));
String mytitle = request.getParameter("mytitle");
String mycontent = request.getParameter("mycontent");
/* 3개의값을 받아준 부분  */

MyBoardDto dto = new MyBoardDto();
dto.setMyno(myno);
dto.setMytitle(mytitle);
dto.setMycontent(mycontent);

MyBoardBiz biz = new MyBoardBiz();
int res = biz.update(dto);
if(res > 0){
%>
	
<script type="text/javascript">
  alert("글 수정 성공");
  location.href="myselect.jsp?myno=<%=myno%>";
</script>


<%
}else{
%>

  <script type="text/javascript">
  alert("글 수정 실패");
  location.href="myupdate.jsp?myno=<%=myno%>";
  </script>
  
<%
}
%>

</body>
</html>






