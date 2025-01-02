<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% // 자바 코드 작성 영역

   // 이곳은 JSP -> Servlet으로부터 전송받은 req, resp가 있는 상태
   // -> req, resp 사용 가능함(대신 request, response로 바뀜)
   
   // getAttribute("key 값")
   // 반환형 Object이기 때문에 원래 타입으로 강제 형변환
   String r = (String)request.getAttribute("res");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 결과 화면</title>
</head>
<body>
	<!-- 위에 선언된 변수 r에 저장된 값 출력 -->
	<h1><%=r %></h1>
	
</body>
</html>