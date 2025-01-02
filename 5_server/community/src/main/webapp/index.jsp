<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>17_시맨틱태그.html</title>
    <link rel="stylesheet" href="resources/css/main-style.css">
    <script src="https://kit.fontawesome.com/5f900fff15.js" crossorigin="anonymous"></script>
</head>
<body>
    <main>
        
        <header>
            <section>
                <a href="#">
                    <img src="resources/images/logo.jpg" id="main-logo">
                </a>
            </section>
            
            <!-- 검색창 -->
            <section>
                <div class="search-area">
                    
                    <!-- form 내부 input 태그 값을 서버 또는 페이지로 전달 -->
                    <form action="#" name="search-form">
                     
                        <!-- fieldset : form태그 내부에서 input을 종류별로 묶는 용도로 많이 사용 -->
                         <fieldset>

                             
                             
                             <!-- form태그 내부 input은 name 속성이 존재해야 값 전달 가능함! -->
                             <input type="text" name="query" id="query"
                             autocomplete="off" placeholder="검색어를 입력해주세요">
                             
                             <button id="search-btn" class="fa-solid fa-magnifying-glass"></button>
                         </fieldset>
                    </form>
                </div>
            </section>
            
            <section></section>
        </header>

        <nav>
                <ul>
                    <li><a href="#">공지사항</a></li>
                    <li><a href="#">자유 게시판</a></li>
                    <li><a href="#">질문 게시판</a></li>
                    <li><a href="#">FAQ</a></li>
                    <li><a href="#">1:1문의</a></li>
                </ul>
        </nav>
        <section class="content">
            <section class=content-1>
            	
            	loginMember : ${sessionScope.loginMember}
            	
            	<hr>
            	
            	message : ${sessionScope.message }
            
            </section>

            <section class="content-2">
            
            <c:choose>
            	<%-- choose 내부에는 jsp 주석만 사용 가능!! --%>
            	<c:when test="${empty sessionScope.loginMember }">
	                <form action="member/login" name="login-form" method="post">
	                    <!-- 아이디/비밀번호/로그인버튼 영역 -->
	                    <fieldset id="id-pw-area">
	                        <section>
	                            <input type="text" placeholder="아이디" name="inputEmail">
	                            <input type="password" placeholder="비밀번호" name="inputPw">
	                        </section>
	                        <section>
	                            <button>로그인</button>
	                        </section>
	                    
	                    </fieldset>
	        
	                    <!-- 회원가입 / ID/PW 찾기 영역 -->
	                    <article id="signup-find-area">
	                        <a href="#">회원가입</a>
	                        <span>|</span>
	                        <a href="#">ID/PW 찾기</a>
	                    </article>
	        			
	        			<label>
	        				<input type="checkbox">아이디 저장
	        			</label>
	                </form>
            	</c:when>
				
				<%-- 로그인이 되어있는 경우 --%>
				<c:otherwise>
                    <article class="login-area">
                        <!-- 회원 프로필 이미지-->
                         <a href="#">
                            <img src="resources/images/profile.png" id="member-profile">
                         </a>
                         <!-- 회원 정보 + 로그아웃 버튼 -->
                         <div class="my-info">
                            <div>
                                <a href="#" id="nickname">${loginMember.memberNickname}</a>
                                <a href="/community/member/logout" id="logout-btn">로그아웃</a>
                            </div>
                            <p>
                                ${loginMember.memberEmail}
                            </p>
                         </div>
                    </article>
				</c:otherwise>            	
            </c:choose>
            </section>
        </section>
    </main>
    <footer>
        <p>Copyright &copy; KH Information Educational Institute G-Class</p>
        <section>
            <a href="#">프로젝트 소개</a>
            <span>|</span>
            <a href="#">이용약관</a>
            <span>|</span>
            <a href="#">개인정보처리방침</a>
            <span>|</span>
            <a href="#">고객센터</a>
        
        </section>
    </footer>
</body>

<!-- 
        시맨틱(Semantic, 의미) 태그 (의미있는 태그)

        - 기존 영역 분할에 주로 사용되던 div, span 등의 태그는
        태그 이름만 봤을 때 나눈다는 것 이외의 의미를 파악할 수 없음
        -> 태그만 봤을 때 태그의 목적을 할 수 없어
           id 또는 class를 반드시 추가해야 했다.

        이런 문제점을 해결하고자
        태그 이름만으로 어느정도 어떤 역할을 하는지 알 수 있고
        추가적으로 웹 접근성 향상에 도움이 되는
        시맨틱 태그가 추가됨 (HTML5)

        [제공하는 태그]

        header 태그 : 문서의 제목, 머리말 영역

        footer 태그 : 문서의 하단부분, 꼬리말, 정보 작성 영역

        nav 태그 : 나침반 역할(다른 페이지, 사이트 이동)의 링크 작성 영역

        main 태그 : 현재 문서의 주된 콘텐츠 작성 영역

        section 태그 : 구역 구문을 위한 영역

        article(작은 토막) 태그 : 본문과 독립된 콘텐츠를 작성하는 영역

        aside 태그 : 사이드바(보통 양쪽), 광고 영역

     -->
</html>