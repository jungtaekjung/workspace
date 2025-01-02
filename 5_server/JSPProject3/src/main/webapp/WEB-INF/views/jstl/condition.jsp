<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- core 태그 : 자주 사용하는 자바 코드 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- format 태그 : 형식, 형변환 관련 코드 --%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 조건문</title>
</head>
<body>
	
    <h1>1. 단일 조건문 (c:if 태그)</h1>

    <pre>
        - 조건식이 true이면 수행 + 화면 출력

        - 별도의 else 구문이 없음

        - 속성은 test만 존재

        ** test 속성 값 작성 시 주의 사항 **
        1) 속성 값은 무조건 EL 구문으로 작성해야 된다
           ex) test="\${key == abc}"

        2) 속성 값은 무조건 true/false가 결과로 나와야 한다

        3) 속성 값을 작성하는 "" 제일 앞/뒤에는 띄어쓰기가 있으면 안 된다
           - 오류 예시 : test=" \${key == abc}" // 앞 공백
                        test="\${key == abc} " // 뒤 공백
    </pre>

    <hr>

    <h3>c:if 테스트용 변수 선언</h3>

    <!-- 변수명 : menu, 범위 : page, 값 : 카페모카 -->
    <c:set var="menu" value="카페모카" scope="page"/>
    <c:set var="price" value="45000" scope="page"/>

    <ul>
        <li>${menu}</li>
        <li>${price}</li>
    </ul>

    <c:if test="${ price == 4500 }">
        <h4>4500원입니다.</h4>
    </c:if>
    <!-- 별도의 else 구문 존재 X -->
    <c:if test="${ price != 4500 }">
        <h4>4500원이 아닙니다.</h4>
    </c:if>

    <hr>
    <%-- fmt:parseNumber 태그

    - 값을 숫자로 변환한 후 새로운 변수를 생성해서 대입
      (변수 생성은 선택 가능)

    - 속성
      1) value : 숫자로 바꾸려는 값 (필수)

      2) pattern : value의 패턴을 인식하여 변화할 수 있게 함
        ex) value="1,000,000"  pattern="0,000,000"
            -> 1000000

      3) var : 변수명
      4) scope : 범위 지정

      5) integerOnly :
        true : 정수 부분만 변환
        false : 정수가 아닌 부분(소수)도 변환 (기본값)
    --%>

    <fmt:parseNumber value="${price}" var="parsePrice "/>

    <c:if test="${parsePrice <= 50000}">
        ${menu}를 마실 수 있다.
    </c:if>
    <c:if test="${parsePrice > 50000}">
        ${menu}를 마실 수 없다.
    </c:if>

    <hr>
    <h2>2. 연속된 조건문 (c:choose / c:when / c:otherwise)</h2>
    <!-- if ~ else if ~ else -->
    <pre>
        - c:choose 태그 :  c:when / c:otherwise를 감싸는 태그
                        (이 안에 if ~ else if ~ else 구문을 쓰겠다)

        *** c:choose 태그 작성 주의 사항 ***
        choose 태그 내부에는 
            c:when, c:otherwise, JSP 주석, 공백 이외에는 
            아무것도 작성되면 안된다!!
        

        - c:when(언제) 태그 : if, else if를 나타내는 태그
                        속성은 test 밖에 없음 (c:if 참고)

        - c:otherwise(그렇지 않으면) 태그
            : else 역할의 태그로 아무런 속성이 없음!!!
    </pre>

    <hr>

    <h4>테스트 방법</h4>
    <pre>
        주소창에 값을 직접 입력한 후에
        \${param.key}를 이용해서 값 얻어오기

        ex) http://localhost:8081/JSPProject3/jstl/condition?val=100

        쿼리스트링(?val=100) 부분을 변경하면서 테스트 진행

    </pre>

    <!-- 파라미터에 val가 존재한다면 -->
    <c:if test="${ not empty param.val }">
        <c:choose>
            <c:when test="${param.val > 100}">
                ${param.val}은 100보다 크다.
            </c:when>
            <c:when test="${param.val > 50}">
                ${param.val}은 50초과 100이하입니다.
            </c:when>
            <c:otherwise>
                ${param.val}은 50보다 작다.
            </c:otherwise>
        </c:choose>
    </c:if>
</body>
</html>