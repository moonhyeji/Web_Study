<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

  <h1>INSERT</h1>
  
  <form action="myinsertres.jsp" method="post">
<!--   myinsertres.jsp 로 데이터를 보낼거야. name 이 myname, mytitle. 인 애의 value 값을
  method 가 post 니까.. ?,쿼리스트링은 안만들어짐.  -->
   
    <table border="1">
       <tr>
           <th>작성자</th>
           <td><input type="text" name="myname"></td>
       </tr>
       <tr>
           <th>제목</th>
           <td><input type="text" name="mytitle"></td>
       </tr>
       <tr>
           <th>내용</th>
           <td><textarea rows="10" cols="60" name="mycontent"></textarea></td>
       </tr>
       <tr>
             <td colspan="2" align="right">
               <input type="submit" value="작성">
               <input type="button" value="취소" onclick="location.href='mylist.jsp'">
             </td>
       </tr>
    </table>
  
  
  </form>


</body>
</html>