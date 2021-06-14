<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="UTF-8"%>
    
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
  <% request.setCharacterEncoding("UTF-8"); %>
  <% response.setContentType("texthtml; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<!-- 
JSTL
Java Server Page
Standard
Tag
Library

EL(표현식 언어)
Expression
Language

EL로 인해 코드가 굉장히 쉬워졌다.
처음 mvclist.jsp를 작성할 때
<.% List<MVCDto> list = (List<MVCDto)requst.getAttribute("list"); %.>
를 입력했었는데 $.{로 간단하게 표현할 수 있다.
<.%를 사용해도 상관없다. 
 -->


  <h1>LIST</h1>
  
  <table border="1">
     <col width="50">
     <col width="100">
     <col width="500">
     <col width="100">
     <tr>
         <th>번호</th>
         <th>작성자</th>
         <th>제목</th>
         <th>작성일</th>
     </tr>
     <c:choose>
       <c:when test="${empty list }">
       <tr>
          <th colspan="4">-------------작성된 글이 없습니다.------------</th>
       </tr>
        </c:when>
        
        <c:otherwise>
           <c:forEach items = "${list }" var="dto">
           <tr>
              <td>${dto.seq }</td>  
              <td>${dto.writer }</td>
              <td><a href="mvc.do?command=select&seq=${dto.seq }">${dto.title }</a></td>
              <td>${dto.regdate }</td>
           </tr>
           </c:forEach>
        </c:otherwise>
     </c:choose>
     
     
     
     <tr>
        <td colspan = "4" align="right">
        <input type="button" value="글작성" onclick="location.href='mvc.do?command=insertform'">
       </td>
     </tr>
  
  </table>
 
</body>
</html>