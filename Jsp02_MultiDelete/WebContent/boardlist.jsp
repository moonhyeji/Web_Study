<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "com.muldel.biz.MDBoardBiz" %>
<%@ page import= "com.muldel.biz.MDBoardBizImpl" %>  
<%@ page import= "com.muldel.dto.MDBoardDto" %>
  <%@ page import= "com.muldel.dto.MDBoardDto" %>
  <%@ page import="java.util.List" %>
  
  <% request.setCharacterEncoding("UTF-8"); %>
  <% response.setContentType("texthtml; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.slim.min.js"></script>
<script type ="text/javascript">

  $(function(){
	  // .submit: submit 이벤트가 발생될 때 실행
	 $("#muldelform").submit(function(){
		 if($("#muldelform input:checked").length == 0){
			 alert("하나 이상 체크해주세요!");  // 유효성검사 .
			  return false;
		 }
	 });
  });
  
  //이름이  all 인 checkbox의 값을 가지고 , 모든 chk의 checkox의 값들을 변경!(true/false)
  
 fucntion allCheck(bool){
	  var chks = document.getElementsByName("chk");
	  for(var i =0; i<chks.length; i++){
		  chks[i].checked = bool;
		  
	  }
  }
  
  
  
  
</script>


</head>
<body>

<%
  MDBoardBiz biz = new MDBoardBizImpl();
  List<MDBoardDto> list = biz.selectList();
%> 


     <%@ include file="./form/header.jsp" %>
<!-- header.jsp 가 boardlist.jsp 에종속되어버린 것. 
boardlist.jsp 의  css 가 되어버림 . -->


<h1>LIST</h1>
<form action="muldel.jsp" method="post" id="muldelform">
	   <table border ="1">
	      <col width="30px">
	      <col width="50px">
	      <col width="100px">
	      <col width="500px">
	      <col width="100px">
	     <tr>
	         <th><input type="checkbox" name="all" value=""></th>
	         <th>번호</th>
	         <th>작성자</th>
	         <th>제목</th>
	         <th>작성일</th>
	     </tr> 
	     
	     <!-- 향상된 for 문  -->
   <%     
   for(MDBoardDto dto : list){
 	%> 
   <tr>
      <th><input type="checkbox" name="chk" value="<%=dto.getSeq() %>"></th>
      <td><%=dto.getSeq() %></td>
      <td><%=dto.getWriter() %></td>
      <td><a href="boardselect.jsp?seq=<%=dto.getSeq()%>"><%=dto.getTitle() %></a></td>
      <td><%=dto.getRegdate() %></td>
   </tr>
	     
    <%
    }
    %> 
	      
	     <tr>
	        <td colspan="5" align="right">
	           <input type="submit" value="선택삭제">
	           <input type="button" value="글작성" onclick="location.href='boardinsert.jsp?'"> <!-- /insert페이지에서는 ?해서 값 받을 것 없음. 입력만 보낼거니까. -->
	        </td>
	     </tr>
	     
	       
	   </table>
   </form>
   
   <%@ include file= "./form/footer.jsp" %>
   <!-- /:root (최상위 디렉토리 )
   ./ : 나와 같은 디렉토리 
   ../ -->
   
</body>
</html>