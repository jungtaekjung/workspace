<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
                <input type="text" name="boardTitle" placeholder="제목을 입력해주세요.">
             </h1>

             <!-- 썸네일 -->
              <h5>썸네일</h5>
              <div class="img-box">
                <div class="boardImg thumbnail">
                    <label for="img0">
                        <img class="preview">
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
                        <img class="preview">
                    </label>
                    <input type="file" class="inputImage" id="img1" name="1" accept="image/*">
                    <span class="delete-image">&times;</span>
                    <!-- &times : x 모양의 문자 -->
                </div>
              
                <div class="boardImg">
                    <label for="img2">
                        <img class="preview">
                    </label>
                    <input type="file" class="inputImage" id="img2" name="2" accept="image/*">
                    <span class="delete-image">&times;</span>
                </div>

                <div class="boardImg">
                    <label for="img3">
                        <img class="preview">
                    </label>
                    <input type="file" class="inputImage" id="img3" name="3" accept="image/*">
                    <span class="delete-image">&times;</span>
                </div>

                <div class="boardImg">
                    <label for="img4">
                        <img class="preview">
                    </label>
                    <input type="file" class="inputImage" id="img4" name="4" accept="image/*">
                    <span class="delete-image">&times;</span>
                </div>
            </div>

            <!-- 내용 -->
             <div class="board-content">
                <textarea name="boardContent"></textarea>
             </div>

             <!-- 버튼 영역 -->
              <div class="board-btn-area">
                <button type="submit" id="writeBtn">등록</button>
                <button type="button" id="goToListBtn">목록으로</button>
              </div>

              <!-- 숨겨진 값(hidden) -->

              <!-- 동작 구분 -->
               <input type="hidden" name="mode" value="${param.mode}">

               <!-- 게시판 구분 -->
                <input type="hidden" name="type" value="${param.type}">
       </form>

    </main>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>

    <!-- boardWriteForm.js 연결 -->
     <script src="../resources/js/board/boardWriteForm.js"></script>
     
     <!-- board.js 연결 -->
     <script src="../resources/js/board/board.js"></script>
</body>
</html>