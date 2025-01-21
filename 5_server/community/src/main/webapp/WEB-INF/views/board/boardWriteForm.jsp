<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%--JSTL은 사용되는 JSP 파일마다 작성되어야 한다 --%>

        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

            <!-- 문자열 관련 함수(메소드) 제공 JSTL (EL형식으로 작성) -->
            <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

                <!DOCTYPE html>
                <html lang="en">

                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>게시판 작성</title>

                    <link rel="stylesheet" href="${contextPath}/resources/css/boardWriteForm-style.css">
                    <link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">

                </head>

                <body>
                    <main>
                        <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

                        <form action="write" method="POST" enctype="multipart/form-data" class="board-write"
                            onsubmit="return writeValidate()">

                            <!-- 제목 -->
                            <h1 class="board-title">
                                <input type="text" name="boardTitle" placeholder="제목을 입력해주세요."
                                    value="${detail.boardTitle}">
                            </h1>

                            <!-- imageList에 존재하는 이미지 레벨을 이용해서 변수 생성 -->
                            <c:forEach items="${detail.imageList}" var="boardImage">
                                <c:choose>

                                    <c:when test="${boardImage.imageLevel==0}">
                                        <c:set var="img0" value="${contextPath}${boardImage.imageRename}" />
                                    </c:when>

                                    <c:when test="${boardImage.imageLevel==1}">
                                        <c:set var="img1" value="${contextPath}${boardImage.imageRename}" />
                                    </c:when>

                                    <c:when test="${boardImage.imageLevel==2}">
                                        <c:set var="img2" value="${contextPath}${boardImage.imageRename}" />
                                    </c:when>

                                    <c:when test="${boardImage.imageLevel==3}">
                                        <c:set var="img3" value="${contextPath}${boardImage.imageRename}" />
                                    </c:when>

                                    <c:when test="${boardImage.imageLevel==4}">
                                        <c:set var="img4" value="${contextPath}${boardImage.imageRename}" />
                                    </c:when>

                                </c:choose>

                            </c:forEach>

                            <!-- 썸네일 -->
                            <h5>썸네일</h5>
                            <div class="img-box">
                                <div class="boardImg thumbnail">
                                    <label for="img0">
                                        <img class="preview" src="${img0}">
                                    </label>
                                    <input type="file" class="inputImage" id="img0" name="0" accept="image/*">
                                    <span class="delete-image">&times;</span>
                                    <!-- &times : x 모양의 문자 -->
                                </div>
                            </div>

                            <!-- 업로드 이미지 -->
                            <h5>업로드 이미지</h5>
                            <div class="img-box">
                                <div class="boardImg">
                                    <label for="img1">
                                        <img class="preview" src="${img1}">
                                    </label>
                                    <input type="file" class="inputImage" id="img1" name="1" accept="image/*">
                                    <span class="delete-image">&times;</span>
                                    <!-- &times : x 모양의 문자 -->
                                </div>

                                <div class="boardImg">
                                    <label for="img2">
                                        <img class="preview" src="${img2}">
                                    </label>
                                    <input type="file" class="inputImage" id="img2" name="2" accept="image/*">
                                    <span class="delete-image">&times;</span>
                                </div>

                                <div class="boardImg">
                                    <label for="img3">
                                        <img class="preview" src="${img3}">
                                    </label>
                                    <input type="file" class="inputImage" id="img3" name="3" accept="image/*">
                                    <span class="delete-image">&times;</span>
                                </div>

                                <div class="boardImg">
                                    <label for="img4">
                                        <img class="preview" src="${img4}">
                                    </label>
                                    <input type="file" class="inputImage" id="img4" name="4" accept="image/*">
                                    <span class="delete-image">&times;</span>
                                </div>
                            </div>

                            <!-- 내용 -->
                            <div class="board-content">
                                <!-- XSS 방지 처리로 인해서 &lt;와 같은 형태로 변한 문자들은
                    html 문서에 출력될 때 해석된 형태인 "<"로 출력된다.
                    -> 별도로 XSS 처리 해제 코드 작성하지 않아도 된다.
                    
                    하지만 개행문자 <br> -> \n으로 변경하는 코드는 필요함
                -->
                                <textarea name="boardContent">${detail.boardContent}</textarea>
                            </div>

                            <!-- 버튼 영역 -->
                            <div class="board-btn-area">
                                <button type="submit" id="writeBtn">등록</button>

                                <c:if test="${param.mode == 'insert'}">
                                    <!-- insert 모드 -->
                                    <button type="button" id="goToListBtn">목록으로</button>
                                </c:if>
                                <c:if test="${param.mode == 'update'}">
                                    <!-- update 모드 -->
                                    <button type="button" onclick="location.href='${header.referer}'">이전으로</button>
                                </c:if>
                            </div>

                            <!-- 숨겨진 값(hidden) -->

                            <!-- 동작 구분 -->
                            <input type="hidden" name="mode" value="${param.mode}">

                            <!-- 게시판 구분 -->
                            <input type="hidden" name="type" value="${param.type}">

                            <!-- 게시판 번호 -->
                            <input type="hidden" name="no" value="${param.no}">

                            <!-- 현재 페이지 -->
                            <input type="hidden" name="cp" value="${param.cp}">

                            <!-- 존재하던 이미지가 제거되었음을 기록하여 전달하는 input-->
                            <!--  DELETE FROM BOARD_IMG
                                  WHERE BOARD_NO =1500
                                  AND IMG_LEVEL IN(0,1,2)
                            -->
                            <input type="hidden" name="deleteList" id="deleteList" value="">

                        </form>

                    </main>
                    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>

                    <!-- boardWriteForm.js 연결 -->
                    <script src="../resources/js/board/boardWriteForm.js"></script>

                    <!-- board.js 연결 -->
                    <script src="../resources/js/board/board.js"></script>
                </body>

                </html>