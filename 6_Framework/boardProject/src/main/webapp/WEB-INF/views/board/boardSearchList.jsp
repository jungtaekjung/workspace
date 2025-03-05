<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<!-- map에 저장된 값들을 각각 변수에 저장 -->
<c:set var="boardList" value="${map.boardList}"/>
<c:set var="pagination" value="${map.pagination}"/>


<!-- 게시판이름 변수에 저장 -->
<!--<c:forEach items="${boardTypeList}" var="boardType">
    <c:if test="${boardType.BOARD_CODE == boardCode}">
        <c:set var="boardName" value="${boardType.BOARD_NAME}" />
    </c:if>
</c:forEach> -->
<c:set var="boardName" value="${boardTypeList[boardCode-1].BOARD_NAME}"></c:set>

<!--검색을 진행한 경우 파라미터(key,query)를 쿼리스트링 형태로 저장한 변수 선언-->
<c:if test="${!empty param.query}">
    <c:set var="qs" value="&key=${param.key}&query=${param.query}"/>
</c:if>

<!--통합 검색인 경우-->
<c:if test="${param.key=='all'}">
    <c:set var="url" value="/board/search" />
</c:if>

<c:if test="${param.key!='all'}">
    <c:set var="url" value="/board/${boardCode}" />
</c:if>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${boardName}</title>

    <link rel="stylesheet" href="/resources/css/board/boardList-style.css">

</head>
<body>
    <main>
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>

        
        <section class="board-list">

            <h1 class="board-name"><span style="color: #455ba8;">"${param.query}"</span>통합 검색 결과  (${pagination.listCount}건)</h1>
     
         


            <div class="list-wrapper">
                <table class="list-table">
                    
                    <thead>
                        <tr>
                            <th>글번호</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>작성일</th>
                            <th>조회수</th>
                            <th>좋아요</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:choose>
                            <c:when test="${empty boardList}">
                                <!-- 게시글 목록 조회 결과가 비어있다면 -->
                                <tr>
                                    <th colspan="6">게시글이 존재하지 않습니다.</th>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <!-- 게시글 목록 조회 결과가 있는 경우 -->
                                <c:forEach items="${boardList}" var="board">
                                    <tr>
                                        <td>${board.boardNo}</td>
                                        <td> 
                                            <!-- 썸네일이 있을 경우 -->
                                            <c:if test="${!empty board.thumbnail}">
                                                <img class="list-thumbnail" src="${board.thumbnail}">
                                            </c:if> 
    										
                                            <a href="/board/${board.boardCode}/${board.boardNo}?cp=${pagination.currentPage}${qs}">${board.boardTitle}</a>   
                                            [${board.commentCount}]                        
                                        </td>
                                        <td>${board.memberNickname}</td>
                                        <td>${board.boardCreateDate}</td>
                                        <td>${board.readCount}</td>
                                        <td>${board.likeCount}</td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </div>


            <div class="btn-area">

            <!-- 로그인 상태일 경우 글쓰기 버튼 노출 -->
             <c:if test="${!empty loginMember}">
                 <button id="insertBtn">글쓰기</button>                     
             </c:if>

            </div>


      
            <div class="pagination-area">
                <!-- 전체 게시글의 수가 목록에 보여질 수(10개) 보다 큰 경우 페이지 네이션 생성 -->
                <c:if test="${pagination.listCount > pagination.limit}">

                    <ul class="pagination">
                    
                        <!-- 첫 페이지로 이동 -->
                        <li><a href="${url}?cp=1${qs}">&lt;&lt;</a></li>
    
                        <!-- 이전 목록 마지막 번호로 이동 -->
                        <li><a href="${url}?cp=${pagination.prevPage}${qs}">&lt;</a></li>
    
                   
                        <!-- 특정 페이지로 이동 -->
                        <c:forEach var="i" begin="${pagination.startPage}" end="${pagination.endPage}">
    
                            <c:if test="${pagination.currentPage == i}">
                                <!-- 현재 보고있는 페이지 -->
                                <li><a class="current">${i}</a></li>
                            </c:if>
        
                            <c:if test="${pagination.currentPage != i}">
                                <!-- 현재 페이지를 제외한 나머지 -->
                                <li><a href="${url}?cp=${i}${qs}">${i}</a></li>
                            </c:if>
                            
                        </c:forEach>
                        
                        <!-- 다음 목록 시작 번호로 이동 -->
                        <li><a href="${url}?cp=${pagination.nextPage}${qs}">&gt;</a></li>
    
                        <!-- 끝 페이지로 이동 -->
                        <li><a href="${url}?cp=${pagination.maxPage}${qs}">&gt;&gt;</a></li>
    
                    </ul>
                </c:if>
            </div>




        </section>
    </main>
    
    
    <!-- 썸네일 클릭 시 모달창 출력 -->
    <div class="modal">
        <span id="modalClose">&times;</span>
        <img id="modalImage" src="/resources/images/user.png">
    </div>


    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    <script src="/resources/js/board/boardList.js"></script>

</body>
</html>