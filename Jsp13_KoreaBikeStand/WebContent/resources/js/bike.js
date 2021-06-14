$(function() {
   getJsonDate();
});







/*
ajax를 이용하여 비동기 통신을 진행하는데, 
url과 data를 결합하여 querystirng 형식의 get방식으로 
데이터를 해당 URL로 매핑된 Controller 메소드에 전달하여, 
return data type을 Json으로 하였다.

Json 데이터는 msg라는 콜백함수의 매개변수로 리턴값을 던져주며
success 콜백함수에서 이를 활용하여 비동기 통신으로 가져온 다음, 
성공하면 for문
실패하면 error 
*/


//    jQuery.getJson(url, data, success) : 리턴되는 값이 ajax의 한줄 축약형 /응답이 json으로 되어있는 ajax라고 생각하자. 
function getJsonDate() {     
   $.getJSON("resources/json/bike.json", function(mydata){   
                        // resources/json/bike.json을 mydata라는 변수로 가져옴, function(mydata)은 success일 때 가져온 값
   
     

 $.ajax({         // mydata로 다시 한번 또 ajax하고 있다. !!!!!!!!!!!!        
         url:"bike.do",            // 비동기 방식으로 보낼 위치(Servlet)
         method:"post",      
         data: {"command": "getdata", "mydata": JSON.stringify(mydata)},   //stringify: 왜해? 문자열로 보낼거라서! 
                                    //JSON.stringify(mydata): bike.json파일 가져와서 문자열로 바꿈 
   // JSON.parse     : json형태의 문자열을 -> json 객체(javascript object)  :왜??? 응답된 형태는 문자열이기 때문에 안에 k:v 로 되어있는 걸 마음대로 값을 가져오기가 힘들다...split 하기도어렵고,, 그래서 변수.key 를 넣어주면 해당 키의 value가 나오도록 json객체로 바꿔준다.   
   // JSON.stringify : json 객체에서 -> json 형태의 문자열로 변환 
   //Client와 server 사이에서 request, response할 때 객체는 문자열이다. 
              // bike.js를 mydata로 가지고 와서 JSON.stringify(mydata)} :  자바스크립트값이나 객체를 JSON문자열로 변환   
              //  data : 서버에 요청할 때 보낼 매개변수 설정(전송할 데이터)               
         dataType: "json",              //리턴되는 값이 text인데, json형태의 text라서 , json으로 바꿔서 받을 것이다! 라는 것. 
         success: function(msg){      // 서블릿에서 받은 msg가 list에 담긴다 
            var $tbody = $("tbody");
            var list = msg.result;
            for (var i = 0; i < list.length; i++) {
               var $tr = $("<tr>");
               
               // append : 매개 변수로 지정된 콘텐츠를 일치하는 요소 집합의 각 요소 끝에 삽입
                                                         //객체.키값 :  msg.name
               $tr.append($("<td>").append(list[i].name));
               $tr.append($("<td>").append(list[i].addr));
               $tr.append($("<td>").append(list[i].latitude));
               $tr.append($("<td>").append(list[i].longitude));
               $tr.append($("<td>").append(list[i].bike_count));
               
               $tbody.append($tr);
               
            }
         },
         error: function(){
            alert("통신실패");
         }
      });
   });
}