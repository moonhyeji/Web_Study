<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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


<%--
EL(표현식 언어)
Expression
Language


-EL로 인해 코드가 굉장히 쉬워졌다.
-처음 mvclist.jsp를 작성할 때

<.% List<MVCDto> list = (List<MVCDto)requst.getAttribute("list"); %.>를 입력했었는데
 $.{로 간단하게 표현할 수 있다.}
 
<.%를 사용해도 상관없다. 
 --%>

	<h1>EL</h1>

	<table border="1">
		<tr>
			<th colspan="2">${score.name  }점수</th>
			
		</tr>
		<tr>
			<th>국어</th>
			<td>${score.kor  }</td>
		</tr>

		<tr>
			<th>영어</th>
			<td>${score.eng  }</td>
		</tr>

		<tr>
			<th>수학</th>
			<td>${score.math  }</td>
		</tr>

		<tr>
			<th>총점</th>
			<td>${score.sum  }</td>
		</tr>

		<tr>
			<th>평균</th>
			<td>${score.avg  }</td>
		</tr>

		<tr>
			<th>등급</th>
			<td>${score.grade  }</td>
		</tr>
	</table>
</body>
</html>