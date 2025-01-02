<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 반복문</title>
</head>
<body>
    <h1>c:forEach 태그</h1>

    <pre>
      - 일반 for + 추가 기능
    
      - 속성
      1) var   : 현재 반복 횟수에 해당하는 변수 (int i)
      2) begin : 반복 시 var 시작 값
      3) end   : 반복이 종료될 var 값
      4) step  : 반복 시 마다 var의 증가 값 (기본값 1)
      
      5) items : 반복 접근한 객체(배열, 컬렉션 객체)
      
      6) varStatus : 현재 반복 상태와 관련된 정보를 제공하는 변수 선언
      
        varStatus="변수명"
        -> c:forEach 구문 내에서 "변수명"을 통해 원하는 값을 얻을 수 있다.
      
        * varStatus에서 제공되는 값
        - current : 현재 반복 횟수(현재 var 값) 
        또는 현재 반복 접근 중인 객체(컬렉션/배열 요소)
  
        - index : 현재 인덱스값 반환 (0부터 시작)
        
        - count : 현재 몇바퀴째인지 반복 횟수 반환 (1부터 시작)
        
        - first : 첫 번째 반복이면 true, 아니면 false
        
        - last : 마지막 반복이면 true, 아니면 false
    </pre>
  
    <hr>

    <h1>일반 for문 형식으로 사용하기</h1>
    <ul>
        <c:forEach var="i" begin="1" end="10" step="2">
            <li>${i}</li>
        </c:forEach>
    </ul>

    <hr>

    <c:forEach var="num" begin="1" end="6">
        <h${num}>현재 num 값 : ${num}</h${num}>
    </c:forEach>

    <hr>

    <h3>c:forEach의 step은 양수만 가능하다</h3>

    <!-- h6 -> h1 반복 출력 -->
    <c:forEach var="num" begin="1" end="6">
        <h${7-num}>현재 num 값 : ${7-num}</h${7-num}>
    </c:forEach>

    <hr>

    <h3>bookList를 일반 for문으로 다루기</h3>
    <pre>
        접두사 fn (JSTL-EL)
        - fn(function, 기능) : 문자열,컬렉션 관련 메서드를
                              제공하는 접두사
    
        - 태그 형식이 아닌 EL 형식 ->  \${fn:메서드명(코드)}
    
        - \${fn:length(배열 또는 컬렉션 또는 문자열)} : 길이 반환
    </pre>

    <h4>bookList의 길이 : ${fn:length(bookList)}</h4>

    <c:forEach var="i" begin="0" end="${fn:length(bookList)-1}">
        <p>${i}번째 인덱스 책 이름 : ${bookList[i].title}</p>

        가격 : 
        <c:choose>
            <c:when test="${bookList[i].price >= 18000}">
                <span style="color:red">${bookList[i].price}원</span>
            </c:when>
            <c:otherwise>
                <span>${bookList[i].price}원</span>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <hr>

    <h1>향상된 for문 형식으로 사용하기</h1>

    <ul>
    <c:forEach items="${bookList}" var="book">
        <li>${book}</li>
    </c:forEach>
    </ul>

    <hr>

    <h3>varStatus 속성 사용</h3>

    <c:forEach items="${bookList}" var="book" varStatus="vs">
        <!-- vs.first : 첫 번째 반복이면 true -->
        <c:if test="${vs.first}">
            <p>----- 시작 -----</p>
        </c:if>

        <ul>
            <li>현재 인덱스 번호 : ${vs.index}</li>
            <li>현재 반복 수(count) : ${vs.count}</li>
            <li>현재 요소(var 속성 값) : ${book}</li>
            <li>현재 요소(vs.current) : ${vs.current}</li>
        </ul>

        <!-- vs.last : 마지막 반복이면 true -->
        <c:if test="${vs.last}">
            <p>----- 종료 -----</p>
        </c:if>
    </c:forEach>

    <hr>

    <table border="1">
        <thead>
            <tr>
                <th>인덱스</th>
                <th>제목</th>
                <th>저자</th>
                <th>가격(원)</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${bookList}" var="book" varStatus="vs">
                <tr>
                    <td>${vs.index}</td>
                    <!-- 데이터셋 속성(data-) : html의 변수 역할
                         -> 해당 요소에 고유한 커스텀 값을 지정하고 싶은 경우 사용 
                    -->
                    <td data-price="${book.price}" class="book-title">${book.title}</td>
                    <td>${book.writer}</td>
                    <td>${book.price}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <script src="/JSPProject3/resources/js/book.js"></script>
</body>
</html>