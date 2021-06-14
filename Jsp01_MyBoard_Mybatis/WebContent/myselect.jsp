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

<!-- //db에서 해당 번호에 맞는 값 하나 가져오기  -->
<!-- mylist.jsp 에서 myselect.jsp 로 넘어올 때 무조건 문자열로넘어오기 때문에
int 인 숫자열로 바꿔줘야 함.  -->
<%
 int myno = Integer.parseInt(request.getParameter("myno"));

MyBoardBiz biz = new MyBoardBiz();
MyBoardDto dto = biz.selectOne(myno);
%>
<body>
  <h1>DETAIL</h1>
  
  <table border ="1">
    <tr>
      <th>작성자</th>
      <td><%=dto.getMyname() %></td>
    </tr>
    <tr>
      <th>제목</th>
      <td><%= dto.getMytitle() %></td>
    </tr>
    <tr> 
       <th>내용</th>
       <td><textarea rows="10" cols="60" readonly="readonly"><%= dto.getMycontent() %></textarea></td>
    </tr>
        <tr> 
    <td colspan ="2" align="right">
	    <input type="button" value="수정" onclick="location.href='myupdate.jsp?myno=<%=dto.getMyno()%>'">
	    <%-- ?myno=<%=dto.getMyno()%>  -> ? key = value 형식으로 넘어감 --%> 
	    <input type="button" value="삭제" onclick="location.href='mydelete.jsp?myno=<%=dto.getMyno()%>'">
	    <input type="button" value="목록" onclick="location.href='mylist.jsp'">
    </td>
    </tr>
  </table>
</body>
</html>