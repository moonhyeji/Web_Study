<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- < @ % = directive tag, 지시자. 이 페이지는 이런 설정으로 될 것이다 하는 것  -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--  코어 라이브러리를 사용하고 있기 때문에 prefix를 c로 사용하고 있고 코드를 c: 으로 사용중, -->

<%
request.setCharacterEncoding("UTF-8");
%>
<%
response.setContentType("text/html; charset=UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>JSTL Test</h1>
	
	
	
<!-- 
JSTL
Java Server Page
Standard
Tag
Library -->
	

	<table border="1">
		<tr>
			<th>이름</th>
			<th>국어</th>
			<th>영어</th>
			<th>수학</th>
			<th>총점</th>
			<th>평균</th>
			<th>등급</th>
		</tr>
		<!--Jsp standard Tag Library  -->
		
		<c:forEach items="${list }" var="score">
	  <!-- foreach 는 반복문 : The basic iteration tag,  
      * var : for each 안에서 사용할 변수의 이름.
      * for( a: b ) {    }  
      a = items="${list }" ,
      b=var="score"            <---- 리스트 안에 잇는 갯수만큼 반복한다. // 이렇듯 향상된for문과 같은 역할을 한다. 
      ${list } -> getAttribute라고 생각. -->



			<!-- choose  태그 안에 주석쓰지 말기! -->
			<tr>
				<td>
				  <c:if test="${score.name eq '이름10' }">
						<!-- <c :if  c:if = test 속성내의 EL 의 결과가 참이면 실행. else  구문은 없음. 
						안에 <when> and <otherwise> 를 넣어서 사용가능,
						: Simple conditional tag 안에 조건이 맞다, 틀리다만출력되는 간단 if 구문  -->
						
						<!-- eq :==  (java) /    ne: != (java) / empty: null (java) -->
						<!--  c:if:  test의 결과가 true 이면 c:if 사이에 있는 소스가 실행 
                 
                 false면 아무것도보이지 않는다.
                 score.name 이 이름10과 같으면 홍길동 출력 out -->
						<c:out value="홍길동"></c:out>
						<!-- :< % system.out.println % > ] = [c : out ]-->
					</c:if> 
					
					
					
					<c:choose>  
						<c:when test="${score.name eq '이름20' }"> 
							<c:out value="${score.name } 님!!"></c:out>
						</c:when>

						<c:when test="${score.name eq '이름30' }">
							<c:out value="${score.name }"></c:out>
						</c:when>
						
						<c:otherwise>   
							<c:out value="누구지?"></c:out>
						</c:otherwise>
					</c:choose>
				</td>
	                <!-- =switch-case문 :  when 과 otherwise를 감싸는 필수요소  -->
					<!--  when = case  case문에 맞는 선택되어 적용된 상태를 처리 -->
					<!--  otherwise = default  : choose 안에서 default 상태를 처리할 때 작업 -->
					
					
					<!--  홍길동 누구지? 가 출력되는 이유: <td></td> 안에서 ,c:if 에 의해서 홍길동이 출력됐고, 그 아래로는 홍길동 = 이름10 이니까,
					이름20,이름30 은 홍길동과 다르기 때문에 출력되지 않고 그 아래 default로 가서, 누구지? 가 출력됐기 때문에, -->
					
					 <!-- ====================================== -->
					
					
					
					
				<td>${score.kor }</td>
				<td><c:if test="${score.eng > 70 }">
						<!--  -->
						<c:if test="${score.eng == 90 }">
							<!--  스코어가 90과 같을 때만 멋져 출력  jsp 문 안에서 jsp 문 또 쓸 수 잇따는 걸 보여주기 위해서  
                     if(eng >70){
                     if(eng ==90) {}}-->
							<c:out value="멋져"></c:out>
						</c:if>
					</c:if></td>


				<td>${score.math }</td>
				<td>${score.sum }</td>
				<td>${score.avg }</td>


				<td><c:choose>
						<c:when test="${score.grade eq 'A' || score.grade eq 'B' }">
							<c:out value="PASS"></c:out>
						</c:when>

						<c:otherwise>
							<c:out value="FAIL"></c:out>
						</c:otherwise>
					</c:choose></td>

			</tr>
		</c:forEach>
	</table>


   <!-- ==================================== -->
	<hr>

	<c:forEach begin="1" end="10" step="1" var="i">
     ${i }<br>
	</c:forEach>

	<hr>
	<br>
  <!-- ==================================== -->
	<h3>구구단</h3>


	<c:forEach begin="1" end="9" step="1" var="i">
		<c:forEach begin="1" end="9" step="1" var="j">
             ${i  }  * ${j  } = ${ i *j } <br>
		</c:forEach>
	</c:forEach>
	
	
	<hr>
	<br>
  <!--  =================================   -->
	<h5>이쁜 구구단 !!</h5>
	<table border="1">
	   <c:forEach begin="2" end="9" step="1" var="i">
	   </c:forEach>
	</table>

</body>
</html>