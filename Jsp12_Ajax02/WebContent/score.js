

//순수 javascript로 ajax를 사용해서 비동기통신하기.


function getParameterValues(){
	var name = document.getElementById("name").value;
	var kor  = document.getElementById("kor").value;
	var eng  = document.getElementById("eng").value;
	var math = document.getElementById("math").value;
	
	return "?name=" + encodeURIComponent(name) + "&kor=" +kor+ "&eng=" +eng+ "&math=" + math;
 //이름,국영수 가지고 와서 쿼리스트링 만들기. 
} 



function load(){
  var url = "score.do" + getParameterValues(); //그리고,getParameterValues함수로 url만들기.

		  httpRequest = new XMLHttpRequest();       //XMLHttpRequest = XHR :서버와 통신하기 위한 객체(javascript object) ->http를 통해 데이터 송수신 지원 /jqXHR을 jquery.com에서 찾아보자  
		  httpRequest.onreadystatechange=callback;  //callback function: call이 들어와야만, back(함수실행)한다.  
        //on이 붙었기 때문에 event를 의미. (onclick,onchang...) - readystate가 change되는 이벤트가 발생할 때마다,callback을 호출한다.
		  httpRequest.open("GET", url, true);  //get방식으로 ,url 로 연결한 것이다. true:비동기/false:동기 
		  httpRequest.send();  //get방식: send() 로 하고, /post방식 : send(data)으로 데이터를 전달하여 통신해준다.

       //아래 3줄에 의해서 onreadystatechange가 0에서 1로 change돼서 콜백 
}



function callback(){
	alert("readystate: " + httpRequest.readyState);  //readystate :1,2,3
	if(httpRequest.readyState == 4){             //readystate가 4가 되면,
		alert("status: " + httpRequest.status);  //동작 = 통신돼서,요청해서 응답된것이 200이라면,
		if(httpRequest.status == 200){       //통신돼서,요청해서 응답된것이 200이라면, = 통신성공했다면,
			var obj = JSON.parse(httpRequest.responseText);//responseText = 응답받은 데이터(doc) 가져와라 
			
			document.getElementById("result").innerHTML = decodeURIComponent(obj.name)
			+ "<br>총점: " +obj.sum +"<br>평균: " + obj.avg;
		}else{
			alert("통신 실패");
		}
	}
}
/*





XMLHttpRequest(XHR)

Ajax 는 Asynchronous JavaScript and XML의 약자 비동기 통신 페이지

전체를 로드하지 않고 일부분만 로드하여 DOM을 사용하여 내용을 추가하거나 바꿀 수 있다.



XHR의 순서


1. XHR선언
2. 옵션 ( 콜백함수 설정)
3. 서버에 HTTP요청
4. 콜백함수에서 서버의 응답 받아와서 처리


//resayState

0 : open() 이 아직 호출되지 않음
1 : send() 가 호출되지 않음
2: 서버로부터 응답이 없음
3: 데이터를 받는 중
4: 서버로부터 응답을 다 받았음


//status


"200" ; Section 10.2.1 : Ok
"204" ; Section 10.2.5 : No Content
"400" ; Section 10.4.1 : Bad Request
"500" ; Section 10.5.1 : Internal Server Error
"503" ; Section 10.5.4 : Service Unavailable




------------------------------------------------------------
<onreadystatechange>

-readystate
0: uninitialized(실행 -load 되지 않음.)
1:loading (실행중)
2: loaded(실행완료)
3: interactive(통신됨)
4:complete(통신완료)



-status (httpRequest.status == 200)
응답될 때의 숫자가 
200: 성공
400: bad request
401: unauthorized
403: forbidden
404: not found
500: ineternal sever error


*/