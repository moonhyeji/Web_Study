<%@page import="com.dept.dto.DeptDto"%>
<%@page import="java.util.List"%>
<%@ page import="com.dept.dao.DeptDao" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   
<%
   // scriptlet : java code 영역 <% % > 로 표시한다.
   DeptDao dao = new DeptDao(); 
   List<DeptDto> list = dao.selectList();  //selectList : 쿼리 결과를 List<E>로 반환한다.
  
   
  /* 새로운 DeptDao 클래스를 생성 =>이 클래스의 이름은 dao로 한다. 
 새로운 DeptDao 클래스(dao)의 쿼리값을 가져와서
 List<DeptDto>로 반환하여, list라는 이름의 객체에 담는다. */
   
%>
   <!--  table 만드는 html 부분 -->
   <h1>DEPT</h1>

   <table border="1">
      <tr>
         <th>deptno</th>
         <th>dname</th>
         <th>loc</th>
      </tr>
      
<%
   for (int i = 0; i < list.size(); i++) {
    //배열의 크기는 size()를 이용.  
	//i가 배열의 크기 전까지, 0번 인덱스부터 1씩 증가하면서 반복하는데,
	//10,20,30,40 총 4개가 반복될 것이다. 
%>
   
   <tr>
   <!-- list 배열 안에서 0번지의 값의 Deptno를 가져와서 tr 의 td 안에 넣겠다. --> 
      <td><%=list.get(i).getDeptno() %></td> 
      <%-- 위의 java부분에서, list에 담은 배열의 중에서 (i)번지의 값의, 
      getDeptno() 을 가져와서 td안에 넣겠다.      --%>  
      <td><%=list.get(i).getDname() %></td>
      <td><%=list.get(i).getLoc() %></td>
   </tr>

<%
   }
%>
   <%-- 이것도 주석이에요 --%>
   <!-- 보여요 -->
   </table>

</body>
</html>