<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%--JSTL은 사용되는 JSP 파일마다 작성되어야 한다 --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<!-- 문자열 관련 함수(메소드) 제공 JSTL (EL형식으로 작성) -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!-- map에 저장된 값을 각각 변수에 저장 -->
<c:set var="boardName" value="${map.boardName}"/>
<c:set var="pagination" value="${map.pagination}"/>
<c:set var="boardList" value="${map.boardList}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판 목록</title>

    <link rel="stylesheet" href="../resources/css/boardList-style.css">
    <link rel="stylesheet" href="../resources/css/main-style.css">

</head>
<body>
    <main>
        <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
        
       

        <!-- 게시글 목록조회 -->
       <section class="board-list">
            <h1 class="board-name">${boardName}</h1>

            <div class="list-wrapper">
                <table class="list-table">
                    <thead>
                        <tr>
                            <th>글번호</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>작성일</th>
                            <th>조회수</th>
                        </tr>
                    </thead>

                    <tbody>

                        <c:choose>
                            <c:when test="${!empty boardList}">
                                
                                <!-- 게시글 목록 조회가 있는 경우 -->
                                <c:forEach var="board" items="${boardList}">
                                    <tr>
                                        <td>${board.boardNo}</td>
                                        <td><a href="#">${board.boardTitle}</a></td>
                                        <td>${board.memberNickname}</td>
                                        <td>${board.createDate}</td>
                                        <td>${board.readCount}</td>
                                    </tr>
                                </c:forEach>
                                    
                            </c:when>
                            <c:otherwise>
                                <!-- 게시글 목록 조회 결과가 없는 경우 -->
                                <tr>
                                    <th colspan="5">게시글이 존재하지 않습니다.</th>
                                </tr>

                            </c:otherwise>
                        </c:choose>


                    </tbody>
                </table>
            </div>
            <div class="btn-area">
                <button id="insertBtn">글쓰기</button>
            </div>

            <div class="pagination-area">
                <!-- 페이지네이션 a태그에 사용될 공통 주소를 저장한 변수 선언 -->
                <c:set var="url" value="${contextPath}/board/list?type=${param.type}&cp="/>
                <!-- <c:set var="url" value="list?type=${param.type}&cp="/> -->

                <ul class="pagination">
                    <!-- 첫 페이지로 이동-->
                    <li><a href="${url}1">&lt;&lt;</a></li>
                    
                    <!-- 이전 목록 마지막 번호로 이동-->
                    <li><a href="${url}${pagination.prevPage}">&lt;</a></li>

                    <!-- <li><a class="current">1</a></li> -->
                    <!-- <li><a href="${contextPath}/board/list?type=1&cp=2">2</a></li> -->

                    <!-- 범위가 정해진 일반 for문 사용 -->
                     <c:forEach var="i" begin="${pagination.startPage}" end="${pagination.endPage}">
                        <c:choose>
                            <c:when test="${pagination.currentPage ==i}">
                                <!-- 현재 페이지인 경우 -->
                                <li><a class="current">${i}</a></li>
                            </c:when>
                            <c:otherwise>
                                <!-- 현재 페이지가 아닌 경우 -->
                                <li><a href="${url}${i}">${i}</a></li>
                            </c:otherwise>
                        </c:choose>
                     </c:forEach>

                    <!-- 다음 목록 시작 번호로 이동 -->
                     <li><a href="${url}${pagination.nextPage}">&gt;</a></li>

                     <!-- 끝 번호로 이동 -->
                    <li><a href="${url}${pagination.maxPage}">&gt;&gt;</a></li>
                </ul>
            </div>

            <form action="#" id="boardSearch">
                <select name="key">
                    <option value="t">제목</option>
                    <option value="c">내용</option>
                    <option value="tc">제목+내용</option>
                    <option value="w">작성자</option>
                </select>

                <input type="text" name="query" placeholder="검색어를 입력해주세요.">
                <button>검색</button>
            </form>
       </section>
       <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
    </main>
    
</body>
</html>