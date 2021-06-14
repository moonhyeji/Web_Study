<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
request.setCharacterEncoding("UTF-8");
response.setContentType("texthtml; charset=UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
     <jsp:useBean id="util" class="com.cal.controller.Util"></jsp:useBean>   <%-- <jsp:useBean> 액션 태그 - <jsp:useBean id="빈이름" class="자바빈클래스이름" scope="범위"/> - id : JSP 페이지에서 자바빈 객체에 접글할 때 사용할 이름을 명시한다. - class : 패키지이름을 포함한 자바빈 클래스의 완전한 이름을 입력한다. - scope : 자바빈 객체가 저장될 영역을 지정한다 --%>
	<!-- //< @ @ >를하면 초록색도 보이고 , 자바코드도 보이고 하니까.foreach for문 돌리는 거 다 비슷한거, -->
	<h1>일정목록</h1>

	<form action="cal.do" method="post">
		<input type="hidden" name="command" value="muldel">

		<table border="1">
			<col width="50">
			<col width="50">
			<col width="500">
			<col width="200">
			<col width="100">
			<tr>
				<th><input type="checkbox" name="all" onclick="allCheck(this.checked);"></th>
				<th>번호</th>
				<th>제목</th>
				<th>일정</th>
				<th>작성일</th>
			</tr>
			<c:choose>
				<c:when test="${empty list }">
					<tr>
						<th colspan="5">---------------no event------------</th>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${list }" var="dto">
						<tr>
							<th><input type="checkbox" name="chk" value="${dto.seq }"></th>
								<td>${dto.seq }</td>
								<td><a href="#">${dto.title}</a></td>
								<td>   <!--getter.setter   -->
								<!-- 넣음 -->     <jsp:setProperty property="todates" name="util" value="${dto.mdate }"/> <!-- util안에 있는 todates를 사용하려고 가져옴.  -->
								<!--  가져옴 -->   <jsp:getProperty property="todates" name="util" />
								</td>
								<td><fmt:formatDate value="${dto.regdate }" pattern="yyyy.MM.dd"/></td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			<tr>
				<td colspan="5" align="right">
				    <input type="submit" value="선택삭제">
					<input type="button" value="목록" onclick="" >
					</td>
			</tr>
		</table> 
	</form>
</body>
</html>









