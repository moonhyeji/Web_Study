<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.mvc.dto.MVCDto, java.util.List" %>

  <% request.setCharacterEncoding("UTF-8"); %>
  <% response.setContentType("texthtml; charset=UTF-8"); %>
  
  
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


</head>
<% MVCDto dto = (MVCDto)request.getAttribute("dto"); %>
<!-- MVCDto 로 형변환해서 닫아주기. 왜? "dto"가 object 객체이기 때문 object 타입임. 따라서,
큰타입에서 작은타입으로 되려면 명시적 형변환이 필요하기 때문에, 
다시 자식 형태인 dto 형태로 형변환 해야지 dto로 사용가능.  -->


<body>
<h1>DETAIL</h1>
<table border="1">
   <tr>
     <th>작성자</th>
     <td><%=dto.getWriter()%></td>
    </tr>
     <tr>
     <th>제목 </th>
     <td><%=dto.getTitle()%></td>
    </tr>
    <tr>
     <th>제목 </th>
     <td><textarea rows="10" cols="60" readonly="readonly"><%=dto.getContent()%></textarea>
     </tr>
    <tr>
      <td colspan="2" align="right">
        <input type="button" value="수정" onclick="location.href='mvccontroller.jsp?command=updateform&seq=<%=dto.getSeq() %>'">
        <input type="button" value="삭제" onclick="location.href='mvccontroller.jsp?command=delete&seq=<%=dto.getSeq() %>'">
        <input type="button" value="목록" onclick="location.href='mvccontroller.jsp?command=list'">
        </td>
    </tr>
    
</table>

</body>
</html>











