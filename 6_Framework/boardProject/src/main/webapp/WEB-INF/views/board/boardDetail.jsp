<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>

<c:forEach items="${boardTypeList}" var="boardType">
    <c:if test="${boardType.BOARD_CODE == boardCode}" >
        <c:set var="boardName" value="${boardType.BOARD_NAME}"/>
    </c:if>
</c:forEach>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${boardName}</title>

    <link rel="stylesheet" href="/resources/css/board/boardDetail-style.css">
    <link rel="stylesheet" href="/resources/css/board/comment-style.css">

    <script src="https://kit.fontawesome.com/9d18722475.js" crossorigin="anonymous"></script>
</head>
<body>
    <main>
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>

        <section class="board-detail">  
            <!-- 제목 -->
           
            <h1 class="board-title">${board.boardTitle}  <span> - ${boardName}</span>    </h1>

            <!-- 프로필 + 닉네임 + 작성일 + 조회수 -->
            <div class="board-header">
                <div class="board-writer">
                    <!-- 프로필 이미지 -->
           


                    <c:if test="${empty board.profileImage}">
                        <img src="/resources/images/user.png">
                    </c:if>
                    <c:if test="${!empty board.profileImage}">
                        <img src="${board.profileImage}">
                    </c:if>
                    
                    <span>${board.memberNickname}</span>

                    
                    <!-- 좋아요 하트 -->
                    <span class="like-area">
                        <!-- 좋아요를 누른 적이 없을 경우 -->
                            <c:if test="${empty likeCheck}">
                                <i class="fa-regular fa-heart" id="boardLike"></i>
                            </c:if>
                        <!--좋아요를 누른 적이 있을 경우-->
                            <c:if test="${!empty likeCheck}">
                                <i class="fa-solid fa-heart" id="boardLike"></i> 
                            </c:if>

                        <span id="likeCount">${board.likeCount}</span>
                    </span>

                </div>

                <div class="board-info">
                    <p> <span>작성일</span> ${board.boardCreateDate} </p>   

                    <!-- 수정한 게시글인 경우 -->
                     <c:if test="${!empty board.boardUpdateDate}">
                         <p> <span>마지막 수정일</span>   ${board.boardUpdateDate} </p>   
                     </c:if>

                    <p> <span>조회수</span>  ${board.readCount} </p>                    
                </div>
            </div>

            
            
            
            
            
            <!-- 이미지가 있을 경우 -->

            <c:if test="${!empty board.imageList}">
                
                <c:if test="${board.imageList[0].imageOrder ==0}">
                    <!-- 썸네일 영역(썸네일이 있을 경우) 
                        ->이미지는 IMG_ORDER 오름차순 정렬
                        ->IMG_ORDER의 값이 0인 이미지가 썸네일
                    -->
                    <h5>썸네일</h5>
                    <div class="img-box">
                        <div class="boardImg thumbnail">
                            <img src="${board.imageList[0].imagePath}${board.imageList[0].imageReName}">
                            <a href="${board.imageList[0].imagePath}${board.imageList[0].imageReName}"
                            download="${board.imageList[0].imageOriginal}"> 다운로드</a>         
                        </div>
                    </div>
                </c:if>

             </c:if>


             <!-- 썸네일이 있을 경우 -->
             <c:if test="${board.imageList[0].imageOrder ==0}">
                <c:set var="start" value="1"></c:set>
             </c:if>


            <!-- 썸네일이 없을 경우 -->
             <c:if test="${board.imageList[0].imageOrder !=0}">
                <c:set var="start" value="0"></c:set>
             </c:if>

            <!-- 일반  이미지가 있는 경우 -->
             <c:if test="${fn:length(board.imageList)> start}">

                 <!-- 업로드 이미지 영역 -->
     
                      <h5>업로드 이미지</h5>
                      <div class="img-box">

                        <c:forEach var="i" begin="${start}" end="${fn:length(board.imageList)-1}">
                            
                               <div class="boardImg">
                                <img src="${board.imageList[i].imagePath}${board.imageList[i].imageReName}">
                              <a href="${board.imageList[i].imagePath}${board.imageList[i].imageReName}"
                              download="${board.imageList[i].imageOriginal}">다운로드</a>                
                                 </div>

                        </c:forEach>

          
                          
          
                         
          
                      </div>

             </c:if>



            <!-- 내용 -->
            <div class="board-content">${board.boardContent}</div>


            <!-- 버튼 영역-->
            <div class="board-btn-area">

                <!-- 로그인한 회원과 게시글 작성자 번호가 같은 경우-->
                 <c:if test="${loginMember.memberNo == board.memberNo}">

                     <button id="updateBtn">수정</button>
                     <button id="deleteBtn">삭제</button>
                 </c:if>


                <button id="goToListBtn">목록으로</button>
            </div>


        </section>

        <!-- 댓글 include-->
        <jsp:include page="comment.jsp"/>
    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>


    <!--
        로그인한 회원 번호 얻어오는 방법 3가지
        1) ajax로 session에 있는 loginMember의 memberNo를 반환
        2) HTML 요소에 로그인한 회원의 번호를 숨겨놓고 JS로 얻어오기
            ex) input type='hidden',데이터 속성 이용
        3) jsp 파일 제일 위에있는 script 태그에 JS+ EL 이용해서 전역변수 선언해두기
    -->




    <script>
        //JSP 해석 우선 순위 : java/EL/JSTL > HTML,CSS,JS

        //로그인한 회원 번호, 게시글 번호를 전역 변수로 선언
        //이 떄, 작성한 EL 구문이 null일 경우 빈칸으로 출력되어
        //변수에 값이 대입되지 않는 문제가 발생할 수 있다.
        //-> 해결 방법 : EL구문을 '', "" 문자열로 감싸준다.

        const loginMemberNo = "${loginMember.memberNo}";

        const boardNo = "${board.boardNo}";
        const boardCode = "${boardCode}";

        // 로그인한 회원의 닉네임
        const memberNickname = '${loginMember.memberNickname}'

        //게시글 제목
        const boardTitle="${board.boardTitle}"

    </script>

    <script src="/resources/js/board/boardDetail.js"></script>
    <script src="/resources/js/board/comment.js"></script>


</body>
</html>