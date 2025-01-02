<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%	// dispatcher가 forward 할 때 req, resp를 매개변수로 보냄
	// 따라서 JSP에서도 req, resp 사용 가능
	
	// 전달받은 파라미터
	String memberId = request.getParameter("memberId");
	String memberPw = request.getParameter("memberPw");
	String memberName = request.getParameter("memberName");
	String intro = request.getParameter("intro");
	
	String message = (String)request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=memberName %>님 가입 결과</title>
</head>
<body>
	<ul>
		<li>아이디 : <%=memberId %></li>
		<li>비밀번호 : <%=memberPw %></li>
		<li>이름 : <%=memberName %></li>
		<li>자기소개 : <%=intro %></li>
	</ul>
	
	<h1><%=message %></h1>
</body>
</html>