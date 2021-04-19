<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@page import=" com.login.dao.LoginDao" %>
   <%@page import=" com.login.dto.LoginDto" %>
   <%@page import="java.util.List" %>
  <% request.setCharacterEncoding("UTF-8"); %>
  <% response.setContentType("texthtml; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

function idCheckConfirm(){
	var chk = document.getElementsByName("myid")[0].title;
	if(chk == "n"){
		alert("id 중복체크를 먼저 해주세요.");
		document.getElementsByName("myid")[0].focus();
	}
   }
	function idCheck(){
		var doc = document.getElementsByName("myid")[0];
		if(doc.value.trim() == "" || doc.value == null){
			alert("id를 입력해주세요");
		}else {
			open ("logincontroller.jsp?command=idchk&myid="+doc.value,"", "width=200, height=200");
		}
	}
	
	
</script>


</head>
<body>


<h1>Register form</h1>
<form action="logincontroller.jsp" method="post">
   <input type="hidden" name="command" value="insertuser">
<!--hidden:
타입은 화면에서 입력한 어떠한 폼도 보이지 않고 숨겨진 value 값을 서버로 전송되는 요소를 말
 -->
   <table border="1">
          <tr>
              <th>ID</th>
              <td>
              <input type="text" name="myid" title="n" required="required" >  <!-- html title:웹페이지의 제목 정  공부! -->
              <input type="button" value="중복체크" onclick="idCheck();"> <!-- 중복체크 하는애  -->
              </td>
              
          </tr>
          <tr>
              <th>PW</th>
              <td><input type="text" name="mypw" required="required" onclick="idCheckConfirm();"></td>
          </tr>
          <tr>
              <th>NAME</th>
              <td><input type="text" name="myname" required="required" onclick="idCheckConfirm();"></td>
          </tr>
          <tr>
              <th>ADDRESS</th>
              <td><input type="text" name="myaddr" required="required" onclick="idCheckConfirm();"></td>
          </tr>
          <tr>
              <th>PHONE</th>
              <td><input type="text" name="myphone" required="required" onclick="idCheckConfirm();" ></td>
          </tr>
          <tr>
              <th>EMAIL</th>
              <td><input type="text" name="myemail" required="required" onclick="idCheckConfirm();"></td>
          </tr>
          <tr>
            <td colspan="2">
            <input type="submit" value="가입">
            </td>
         </tr>
          
   </table>
</form>


</body>
</html>