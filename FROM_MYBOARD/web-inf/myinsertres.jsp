<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.my.dao.MyBoardDao"%>
<%@ page import="java.util.List" %>
<%@ page import="com.my.dto.MyBoardDto" %>
<%@ page import="com.my.biz.MyBoardBiz" %>


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
	String myname = request.getParameter("myname");
	String mytitle = request.getParameter("mytitle");
	String mycontent = request.getParameter("mycontent");

	MyBoardDto dto = new MyBoardDto();
	dto.setMyname(myname);
	dto.setMytitle(mytitle);
	dto.setMycontent(mycontent);

	MyBoardBiz biz = new MyBoardBiz();
	int res = biz.insert(dto);
	if(res > 0){

%>

	<script type="text/javascript">
	alert("글 작성 성공");
	location.href="mylist.jsp";
	</script>

	<%

}else{
	%>

	<script type="text/javascript">
	   alert("글 작성 실패");
	   //location.href="myinsert.jsp";
      history.back();

	</script>
		<%

}
%>



<!-- myinsertres.jsp 로 myname, mytitle, mycontent 가 request 객체에 담겨서 넘어감.
post 방식은 body 에 담겨서 전달됨.  -->
<!-- myname 이라는 key 에 어떤 값이 담겨서,
mytitle 이라는 key 에 어떤 값이 담겨서,전달됨.
 -->
</body>
</html>
