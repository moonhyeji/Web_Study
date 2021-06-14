<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
request.setCharacterEncoding("UTF-8");
%>
<%
response.setContentType("texthtml; charset=UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%-- 
<jsp:getProperty property="" name="myname" name="dto"/>
<td><input type="text" readonly="readonly" value="${dto.myname }"></td>
여기에서 value="" 의""안에 넣는다. 

<jsp:getProperty property="" name="mytitle" name="dto"/>
<jsp:getProperty property="" name="mycontent" name="dto"/>
 --%>
<body>

<jsp:useBean id="dto" class="com.mvc.dto.MYDto" scope="request"></jsp:useBean>



	<h1>UPDATE! hi usebean!</h1>

	<form action="mymvc.do" method="post">
		<input type="hidden" name="command" value="updateres">
	    <input type="hidden" name="myseq" value="${dto.myseq }">
		
		<table border="1">
			<tr>
				<th>작성자</th>
				<td><input type="text" readonly="readonly" value="<jsp:getProperty property="myname"  name="dto"/>"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="mytitle"
					value="<jsp:getProperty property="mytitle"  name="dto"/>"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="60" name="mycontent"><jsp:getProperty property="mycontent"  name="dto"/></textarea></td>
			</tr>

			<tr>
			   <td colspan="2" align="right">
			   <input type="submit" value="수정">
			   <input type="button" value="취소" onclick="location.href='./mymvc.do?command=list'">
			   </td>
			   
			</tr>
		</table>

	</form>


</body>
</html>