<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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

<form action="./mymvc.do" method="post">
 <!--  get :  read
 post : create
 put : update
 delete : delete
  이렇게 써라 라고 정해져 잇는데 이걸 잘 정해서 쓰는 것을 rest 혹은 restful 이라고 한다. -->
 <!-- 지금 이 페이지는 insert를 create 하려고 하니까 post를 써준다. --> 
 <input type="hidden" name="command" value="insertres">
 <!-- 이 윗부분은 ' 어디로 가고 싶은지 ' 를 위해서 써줌   -->
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
    <input type ="button" value="취소" onclick="location.href='mymvc.do?command=list'">
    </td>
 </tr>
 
 
 
 </table>
</form>
</body>
</html>