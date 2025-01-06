<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   

<!-- 문자열 관련 함수(메소드) 제공 JSTL (EL형식으로 작성) -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>KH 커뮤니티</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/myPage-style.css">
    <script src="https://kit.fontawesome.com/5f900fff15.js" crossorigin="anonymous"></script>
</head>
<body>
    <main>
        
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>

        <nav>
                <ul>
                    <li><a href="#">공지사항</a></li>
                    <li><a href="#">자유 게시판</a></li>
                    <li><a href="#">질문 게시판</a></li>
                    <li><a href="#">FAQ</a></li>
                    <li><a href="#">1:1문의</a></li>
                </ul>
        </nav>
       <!-- 마이페이지 - 내정보 -->
        <section class="myPage-content">

            <!-- 왼쪽 사이드 메뉴 -->
            <section class="left-side">
                <ul class="list-group">
                    <li><a href="#">프로필</a></li>
                    <li><a href="#">내 정보</a></li>
                    <li><a href="#">비밀번호 변경</a></li>
                    <li><a href="#">회원 탈퇴</a></li>
                </ul>
            </section>
            
            <!-- 오른쪽 마이페이지 주요 내용 부분 -->
            <section class="myPage-main">
                <h1 class="myPage-title">내 정보</h1>
                <span class="myPage-explanation">원하는 회원 정보를 수정할 수 있습니다.</span>
            
                <form action="info" method="POST" name="myPage-form">

                    <div class="myPage-row">
                        <label for="memberNickname">닉네임</label>
                        <input type="text" name="memberNickname" id="memberNickname" value="${loginMember.memberNickname}" maxlength="10">
                    </div>

                    
                    <div class="myPage-row">
                        <label for="memberTel">전화번호</label>
                        <input type="text" name="memberTel" id="memberTel" value="${loginMember.memberTel}" maxlength="11">
                    </div>

                    <!-- 주소 -->       <!-- fn:split(문자열,'구분자')-->
                    <c:set var="addr" value="${fn:split(loginMember.memberAddress, ',,')}"/>
                    
                    <div class="myPage-row info-title">
                        <span>주소</span>
                    </div>

                       
                    <div class="myPage-row info-address">
                        <input type="text" name="memberAddress" value="${addr[0]}" maxlength="6">
                        <button type="button" id="info-address-btn">검색</button>
                    </div>

                    <div class="myPage-row info-address">
                        <input type="text" name="memberAddress" value="${addr[1]}">
                    </div>

                    <div class="myPage-row info-address">
                        <input type="text" name="memberAddress" value="${addr[2]}">
                    </div>
                    <button id="info-update-btn">수정하기</button>
                </form>
            </section>

        </section>
    </main>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>


</html>