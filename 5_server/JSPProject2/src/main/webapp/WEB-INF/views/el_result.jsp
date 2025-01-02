<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- JSP import 구문(EL 사용 시 필요없음)  --%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL 결과 페이지</title>
</head>
<body>
	<h1>EL을 이용해서 파라미터 출력하기</h1>
	<h4>${param.inputDate}</h4>
	<h4>JSP표현식 : <%= request.getParameter("inputDate") %></h4>

	<hr>
	<h1>EL을 이용해서 세팅된 속성(Attribute)값 출력하기</h1>

	<ul>
		<li>[작성법] : \${ key }</li>
		<li>기본 자료형 : ${ score }</li>
		<li>String(객체) : ${ color }</li>
		<li>
			List(객체) : ${ strList }
			<ul>
				<li>${ strList[0] }</li>
				<li>${ strList[1] }</li>
				<li>${ strList[2] }</li>
				<li>${ strList[3] }</li>
	
				<li><%= ((List)request.getAttribute("strList")).get(0) %></li>
			</ul>
		</li>
		<li>
			DTO(객체) : ${ person }
			<ul>
				<li>이름 : ${ person.getName() }</li>

				<%-- ${key.필드명} : getter를 자동으로 호출하여 값을 얻어옴 --%>
				<li>이름 : ${person.name}</li>
				<li>나이 : ${person.age}</li>
				<li>키 : ${person.height}</li>
			</ul>
		</li>
	</ul>

	<hr><hr>
	<h1>empty 연산자</h1>
	<pre>
		- \${empty 객체 | 배열 | 리스트}

		- null 또는 빈 칸 또는 비어있으면 true 아니면 false
		
		- EL은 null을 빈 칸으로 표현 -> null == ""(빈 칸) == 비어있음(출력할 게 없어서 빈 칸)
	</pre>
	<ul>
		<li>String test1 = null; -> ${empty test1}</li>
		<li>String test2 = ""; -> ${empty test2}</li>
		<li>List&lt;String&gt; test3 = null; -> ${empty test3}</li>
		<li>List&lt;String&gt; test4 = new ArrayList<>(); -> ${empty test4}</li>
		<li>List&lt;String&gt; test5 = new ArrayList<>(); -> ${empty test5}</li>
	</ul>

</body>
</html>