<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- HTML 주석(개발자 도구에 노출됨) -->

<%-- JSP 주석(개발자 도구에 노출되지 않음) --%>

<%-- 
	<%@ %> : 지시자 태그(페이지 정의)
	
	"charset=UTF-8" : 현재 문서가 UTF-8 문자 인코딩 형식으로 작성되어 있음
	pageEncoding="UTF-8" : 현재 문서를 해석할 때 UTF-8 문자 인코딩을 이용해서 해석
	
	<% %> : 스트립틀릿(scriptlet) : JSP에서 자바 코드를 작성할 수 있는 영역
	
	<%= %> : 표현식(Expression) : 자바 코드의 값을 HTML 형식으로 표현(출력)
 --%>

<% // 자바 코드 작성 영역(Scriptlet)

	String pizzaName = (String)request.getAttribute("pizzaName");
	int price = (int)request.getAttribute("price");
	
	// JSP에서도 요청 시 전달받은 값(Parameter)을 얻어올 수 있다
	String amount = request.getParameter("amount");
	String size = request.getParameter("size");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>피자 주문 결과</title>
</head>
<body>
	<!-- webapp 폴더 내부 html/css/jsp 등은 서버를 끄지 않고 수정 가능 -->
	<h1>주문 결과</h1>
	<ul>
		<li>피자 : <%=pizzaName %></li>
		<li>사이즈 : 
			<%if(size.equals("R")){%>
				레귤러
			<%}else{%>
				라지
			<%}%>
			<%-- <%=size %> --%>
		</li>
		<li>수량 : <%=amount %></li>
		<li>합계 : <%=price %></li>
	</ul>
	
	<%for(int i = 1; i <= 6; i++){%>
		<h<%=i %>><%=i %>번째 출력</h<%=i %>>
	<%}%>
</body>
</html>