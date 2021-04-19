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


  <h1> User Update</h1>
  <h2>If you need to update your info, please update here</h2> 
  <form action="logincontroller.jsp" method="post" >
  <input type="hidden" name="command" value="updateuser">
  
  <table border="1">
    <tr>
      <th>ID</th>
      <td>
      <input type="text" name="myid" required="required">
      <td>
    </tr>
    <tr>
      <th>PW</th>
      <td>
      <input type="text" name="mypw" required="required">
      <td>
    </tr>
    <tr>
      <th>NAME</th>
      <td>
      <input type="text" name="myname" required="required">
      <td>
    </tr>
    <tr>
      <th>ADDR</th>
      <td>
      <input type="text" name="myaddr" required="required">
      <td>
    </tr>
    <tr>
      <th>PHONE</th>
      <td>
      <input type="text" name="myphone" required="required">
      <td>
    </tr>
    <tr>
      <th>EMAIL</th>
      <td>
      <input type="text" name="myemail" required="required">
      <td>
    </tr>
    <tr>
     <td colspan="2">
     <input type="submit" value="수정완료">
     </td>
    </tr>
  
  </table>
  </form>

</body>
</html>