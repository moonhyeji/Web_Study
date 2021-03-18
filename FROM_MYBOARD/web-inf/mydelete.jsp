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
 <%--  <%%> : header,body,header와 body 사이.어느 부분이여도 상관이 없음.
따라서, script 도 헤더나 바디 아무 부분이나 들어와도 상관이 없기 때문에
코드 안의 부분 어디나 상관이 없음.
하지만, <%%> 다음에 오는 코드들이 body 부분에만 들어와야 하는 코드들이 있다면, <%%>안의 내용들이 그 코드에 영향을 미친다면,body부분 안에 들어와야 함.   --%>
<%
  int myno = Integer.parseInt(request.getParameter("myno"));

  MyBoardBiz biz = new MyBoardBiz();
  int res = biz.delete(myno);
  if(res > 0){
%>

<script type="text/javascript">
  alert("글 삭제 성공");
  location.href="mylist.jsp";
</script>



<%
}else{
%>

<script type="text/javascript">
  alert("글 삭제 실패 ");
  location.href= "myselect.jsp?myno=<%=myno%>";
</script>

<%
}
%>

</body>
</html>
