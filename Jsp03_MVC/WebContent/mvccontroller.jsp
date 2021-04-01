<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.mvc.dto.MVCDto" %>
<%@ page import="com.mvc.biz.MVCBiz" %>
<%@ page import="com.mvc.biz.MVCBizImpl" %>
<%@ page import="java.util.List" %>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("texthtml; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
String command = request.getParameter("command"); 
System.out.println("<" + command+ ">");

MVCBiz biz = new MVCBizImpl();




if(command.equals("list")){  //커맨드가 리스트로 가고싶다고 요청. 
 /////////// 컨트롤러의 실행순서 
 /*1.전달된 값 있으면 받기 
   2.필요하다면 db와 연결 
   3.보내줄 값 있다면 request에 담기 
   4.보내기*/

    //1.전달된 값이 있으면 받아주자.
   //2필요하다면 db랑 연결하자 -> select할 거니까 db와 연결필요
	List<MVCDto> list= biz.selectList();

   //3 보내줄 값이 잇다면 , request  에 담자. 
   // = request 에 attribte를 set 하겠다. 
	request.setAttribute("list",list);
	//"list": 이름, list: object객체(=부모타입(최상위)의 객체),
	//setAttribute void setAttribute(String name,Object o);
	//왜 값을 담을 때 object로 담을까? -object는 젤 큰 범위이기 때문에, 가장 제일 위에 있는 조상. 
	//얘를 리턴하면 object 타입이 리턴됨. -> 따라서 select.jsep에서는 이를 형변환 해준다. 
   
	//4보내자 (포워드로 mvclist.jsp로 보내겟다.)
     pageContext.forward("mvclist.jsp");
	//포워드 
	
	
	
	//--------------------------------------------------------
    }else if(command.equals("insertform")){
	//1.
	//2.
	//3
	//4 값 보내줄 거 없어 인서트 폼이니까.
	//여기가 insert 해서 값 보내주는 게 아니라 그냥 입력받는 '폼' 만 이니까 
	//전달 받을것도 ,  전달 해줄것도 없음. 
	//***mvcinsert.jsp 로 sendredirect 해라.
	response.sendRedirect("mvcinsert.jsp");
	
	
   }else if(command.equals("insertres")){
	//1.
	String writer = request.getParameter("writer");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	//2. 한거를비즈로 보내쟈 
	MVCDto dto = new MVCDto();
	dto.setWriter(writer);
	dto.setTitle(title);
	dto.setContent(content);
	int res = biz.insert(dto);
	
	//3, 보내줄 값이 잇으면 담자.
	//4. 전달하자 
	if(res>0){

   %>

		<script type="text/javascript">
		alert("글 작성 성공! ");
		location.href="mvccontroller.jsp?command=list";
		</script>


<%
	}else{
%>

		<script type="text/javascript">
		alert("글 작성 실패 ! ");
		location.href="mvccontroller.jsp?command=insertform";
		</script>


	<%
		}
	 }else if(command.equals("detail")){
		//1
		int seq = Integer.parseInt(request.getParameter("seq"));
		//2
		MVCDto dto = biz.selectOne(seq);
		//3
		request.setAttribute("dto", dto);
		//4
		pageContext.forward("mvcselect.jsp");
	
	 //--------------------------------------------------
	 }else if(command.equals("updateform")){
		 //1.
		 int seq = Integer.parseInt(request.getParameter("seq"));
		 //2
		 MVCDto dto = biz.selectOne(seq);
		 //3
		 request.setAttribute("dto",dto);
		 //4
		 pageContext.forward("mvcupdate.jsp");
	
		 
	 
		 
		 
	}else if (command.equals("updateres")){
		//1.
	int seq = Integer.parseInt(request.getParameter("seq"));
    String title = request.getParameter("title");
    String content = request.getParameter("content");
		//2.
		MVCDto dto = new MVCDto();
		dto.setSeq(seq);
		dto.setTitle(title);
		dto.setContent(content);
		int res = biz.update(dto);
		//res = 수정에 성공했거나, 수정에 실패했따.
				//성공했으면 list로 가야함! 
				//성공 -> 했으면 컨트롤러로 감. 컨트롤러로 안가고 페이지로 가도 되는데,
				//바로 패이지로 가면 안보임! 
		if (res > 0){
			//3.
			List<MVCDto> list = biz.selectList();
			request.setAttribute("list",list);
			//4.
			pageContext.forward("mvclist.jsp");
	
		
		
		}else{
			//3.
			MVCDto updateDto = biz.selectOne(seq);
			request.setAttribute("dto", updateDto);
			//4.
			pageContext.forward("mvcselect.jsp");
			////////////이거 3,4, 3,4,  두가지 방법에 대해서 공부하기 . 
		}		
	
	
	}else if(command.equals("delete")){
		//1.
		int seq = Integer.parseInt(request.getParameter("seq"));
		//2.
		int res = biz.delete(seq);
		//3.
		//4
		if(res > 0){
	%>
	
		<script type="text/javascript">
		alert("삭제 성공 ");
		location.href="mvccontroller.jsp?command=list";
		</script>
		
	
	<%
		}else{
	%>
		
			<script type="text/javascript">
			alert("삭제 실패");
			location.href= "mvccontroller.jsp?command=detail&seq=<%=seq%>";
			</script>
	
	
	<%
		}
	}
	%>
	
	
<h1>잘못 와벌임ㅠㅠㅠㅠㅠ</h1>
</body>
</html>
