
<%@page import="org.apache.tomcat.util.log.SystemLogHandler"%>
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
</head>
<body>

<% //여기부터 로그인을 하자는 java 코드들!!!!
//컨트롤러니까 사용자로부터 입력받는 것부터 시작. 

String command = request.getParameter("command"); 
//request.getParameter: 사용자요청의 파라미터 값(입력값)을 받아온다.
//사용자가 입력한 로그인 폼 내용 = HTTP 요청의 파라미터(parameter)
//HTTP 요청의 파라미터 값을 얻기 위해 사용하는 것이 request.getParameter(네임값)
//파라미터는 form 안의 내용물들의 name을 보고 값을 불러올 수 있다
//<input type="text" name="id">가 있다면,
//String myId = request.getParameter("id"); 방식으로 id를받아옴.
System.out.println("["+command+"]");  
//regist누르면 ->[registform], 
//id중복체크 누르면 ->[idchk]
//가입 누르면 ->[insertuser]..........이런식으로 콘솔창에 표현되도록 하기 위해 



LoginDao dao = new LoginDao(); 
//새로운 LoginDao클래스를  dao 에저장. /dao한번만 쓸 거 아니니까.

// 컨트롤러의 실행순서  //
	 /*1.전달된 값 있으면 받기 
	   2.필요하다면 db와 연결 
	   3.보내줄 값 있다면 request에 담기 
	   4.보내기*/
if(command.equals("login")){ //cmd가 login으로 가고싶다고 요청
//////////////1 전달된 값 잇으면 받기 
//* login  할 때 id와 pw를 받음 
	String myid = request.getParameter("myid"); //사용자가 입력한 myid 값을 담은 애가 myid
	String mypw = request.getParameter("mypw"); //사용자가 입력한 mypw 값을 담은 애가 mypw 

	
	////////////////2 필요하다면 db와 연결 
	// --->입력받은 id와 pw를 가지고 db에 연결해서 정보 가져올거니까 연결필요 
	LoginDto dto = dao.login(myid,mypw); 
	//입력받은 mhyid,mypw를 가지고,dao의 login에 입력해서 리턴된 값을 dto에 담음.
	//(컨트롤러 -> 다오 ->디티오 -> db ->dto ->dao ->controller 이렇게 가야 하니까.)
	
	if(dto != null){ 
//만약 dto 가 null이 아니라면, = id,pw를 입력했을 때 그 id,pw 에 맞는 데이터가 db에 존재한다면,=db에서 cnrller로  보내줄 값이 존재,

//( 참고 )dto 가 null 이 아닐 때 ?? ->dao에서 LoginDto dto = new LoginDto(); 이렇게 써 놓으면

//무조건 참이 되어버림.! (= dto = null )이 되어버림!  -- side effect ,라고 함, 따라서
//따라서 코드를 칠 때 다음 단계의 코드가 어떻게 될 것인지 생각하면서 쳐야 함. 


/////////////3.보내줄 값 있다면 request에 담기 
		//session scope에 담기 -세션 생성 
        session.setAttribute("dto",dto); //세션 생성 !!!!!
        //서버를 통해 dto란 객체를 전달해주는데, 이름을 dto라고한다.
        //"list": 이름,
    	//list: object객체(=부모타입(최상위)의 객체)
    	//setAttribute:요소의 속성과,속성값을 설정, 
    	//"NAME"의 명칭으로 VALUE 데이터를 저장  
	 	
       //특정 시간동안 활동이 없으면 session 완료되도록 설정. 
       session.setMaxInactiveInterval(10 * 60); //<---여기 안에 숫자 default 가 뭔지, 어떤 식으로 들어가야 하는지 .
	   //session: 만료되기 전까지 어디서든사용할 수 있따. 
	   //setMaxInactiveInterval() <-()안에 세션 만료되기까지에 대한 서로다른값을 지정해줄 수 있다.단위:second
       
////////////4.보내자.
	    if (dto.getMyrole().equals("ADMIN")){ //만약 dto의 getmyrole의 값이 admin 이라면, 
	    	response.sendRedirect("adminmain.jsp"); //adminmain.jsp 로 이동되도록 하자. 
	    	//adminmain.jsp 로 이동되도록 하자. 라는 말은,
	    	//controller에서 요청받고 응답되다가 adminmain.jsp 로 다시 요청 해서  adminmain.jsp에서 요청되도록하자.
	    	
	    	//(참고)response.sendRedirect : 응답되다가 다시 요청됨. 
	    	//sendRedirect는 값을 저장하지 않는데 여기서는 값이 잇음 
	    	
	    }else if(dto.getMyrole().equals("USER")){ //만약 dto의 getmyrole의 값이 user라면,
	    	response.sendRedirect("usermain.jsp"); //usermain.jsp로 이동되도록하자.
	    }
       
	}else{
   //dto가 null이 아닌게 아니라면,=
  //dto가  null이라면,바로 아래 <script></script>이용해서
   //login 실패가 뜨도록 하자 .
   
   
   
   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   //얘는 되는데.....
   request.setAttribute("msg","로그인실패 ");
   RequestDispatcher dis = request.getRequestDispatcher("error.jsp");
   dis.forward(request, response);
///////////////////////////////////////////////////
   
   
   %>

   <script type="text/javascript">
   alert("login  실패에..");
   location.href="index.html"; 
   </script>

<% 


	} //여기까지 login 성공 실패에 대한 코드.
	
	
//----------------------------------------------------------------------------
}else if(command.equals("logout")){ //cmd가 logout으로 가고싶다고 요청
	
	session.invalidate();  //세션 무효화: 모든 세션의 속성을 제거하는 함수 
    response.sendRedirect("index.html");

	
	
//----------------------------------------------리스트로 가고싶다고 요청 	
}else if(command.equals("userlistall")){
	//1
	//2
     List<LoginDto> list = dao.selectAllList();
	//3
	request.setAttribute("list",list);
	//4어디로 보낼건데? -> userlistall.jsp 로 보냄 
    pageContext.forward("userlistall.jsp");
	
	
	
	
	
	
	//---------------------------------------------------------------
}else if(command.equals("userlistenabled")){
	//1.이전에 보낸거잇는가?
		List<LoginDto>list = dao.selectEnabledList();
	//2.연결할거 잇는가?
		request.setAttribute("list",list);
	//보내줄 것잇는가? - list 란 이름으로 담아서보냄 ;
		pageContext.forward("userlistenabled.jsp");
		
		
		//---------------------------------------------------------------
		
}else if(command.equals("updateroleform")){
	//1보내준 것잇는가? - 잇음 - 보내준거 받는 코드 .->
	int myno = Integer.parseInt(request.getParameter("myno"));
	//2
	LoginDto dto = dao.selectOne(myno);
	//3
	request.setAttribute("dto",dto);
	//4
	pageContext.forward("updateroleform.jsp");


}else if(command.equals("updaterole")){
	
	
	int myno =Integer.parseInt(request.getParameter("myno"));
	String myrole =request.getParameter("myrole");
	
	int res =dao.updateRole(myno, myrole);
	if(res>0){
		
		//---------------------------------------------------------------
%>
		
		<script type="text/javascript">
		 alert("회원등급 조정 성공");
		 location.href="logincontroller.jsp?command=userlistenabled";
		</script>

<%
}else{
%>
		
		
		
		
		<script type="text/javascript">
		alert("회원등급 조정 실패");
		location.href="logincontroller.jsp?command=updateroleform&myno=<%=myno%>";
		</script>
		
		


<%
  }
}else if(command.equals("registform")){
	response.sendRedirect("regist.jsp");
	
	
}else if(command.equals("idchk")){
	
	String myid = request.getParameter("myid");
	LoginDto dto = dao.idCheck(myid);
	
	
	//dao.idCheck(myid) 의 myid 로 되어있는 값이 없으면, 
	boolean idnotused = true;
	//id사용한적 없는것 이 참.;
	
	
	
			if(dto != null){
			//중복체크할 때 건네준 값(myid)가 null 이 아니면 -> 사용한 적 있다면. 
				idnotused = false;
			}
			///////////////////////
	
	response.sendRedirect("idchk.jsp?idnotused="+idnotused);
}

%>










     <h1 style="color: red;">잘못왔다......</h1>
      <p>
      내가 보이면....<br>
      커멘드확인!!!! <br>
      </p>  
</body>
</html>







