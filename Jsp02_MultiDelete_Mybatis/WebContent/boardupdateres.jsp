<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import="com.muldel.biz.MDBoardBiz" %>
  <%@ page import="com.muldel.biz.MDBoardBizImpl" %>
  <%@ page import="com.muldel.dto.MDBoardDto" %>  
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

  int seq = Integer.parseInt(request.getParameter("seq"));
  String title = request.getParameter("title");
  String content = request.getParameter("content");
  MDBoardDto dto = new MDBoardDto(seq,title,content);
  
  MDBoardBiz biz = new MDBoardBizImpl();
  int res = biz.update(dto);
  if(res > 0){

%>

<script type="text/javascript">
alert("글 수정 성공 ")
<%-- //location.href="boardselect.jsp?seq=<%=seq%>";  이렇게 하면 수정한 곳 selectone으로 감 --%>
 location.href="boardlist.jsp";

</script>


<%
  }else{
%>

<script type="text/javascript">
alert("글 수정 실패")
location.href="boardupdate.jsp?seq=<%=seq%>";

</script>

<%
  }
%>

</body>
</html>