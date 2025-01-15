<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%--JSTL은 사용되는 JSP 파일마다 작성되어야 한다 --%>

        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

            <!-- 문자열 관련 함수(메소드) 제공 JSTL (EL형식으로 작성) -->
            <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

                <div id="reply-area">
                    <!-- 댓글 목록 -->
                    <div class="reply-list-area">
                        <ul id="reply-list">
                            <c:forEach var="reply" items="${rList}">
                                <li class="reply-row">
                                    <p class="reply-writer">

                                        <!-- 프로필 이미지가 있는 경우 -->
                                        <c:if test="${!empty reply.profileImage}">
                                            <img src="${contextPath}${reply.profileImage}">
                                        </c:if>
                                        <!-- 프로필 이미지가 있는 경우 -->
                                        <c:if test="${empty reply.profileImage}">
                                            <img src="${contextPath}/resources/images/profile.png" alt="">
                                        </c:if>
                                        <span>${reply.memberNickname}</span>
                                        <span class="reply-date">${reply.createDate}</span>
                                    </p>
                                    <p class="reply-content">${reply.replyContent}</p>

                                    <!-- 댓글 작성자인 경우 -->
                                    <c:if test="${reply.memberNo == loginMember.memberNo}">
                                        <div class="reply-btn-area">
                                            <button onclick="showUpdateReply(${reply.replyNo},this)">수정</button>
                                            <button onclick="deleteReply(${reply.replyNo})">삭제</button>
                                        </div>
                                    </c:if>
                                </li>

                            </c:forEach>



                        </ul>
                    </div>

                    <!-- 댓글 작성 부분 -->
                    <div class="reply-write-area">
                        <textarea id="replyContent"></textarea>
                        <button id="addReply">
                            댓글<br>
                            등록
                        </button>
                    </div>
                </div>