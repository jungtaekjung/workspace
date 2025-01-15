// 댓글 목록 조회 (ajax)
function selectReplyList(){
    // contextPath, boardNo, memberNo 전역 변수 사용

    $.ajax({
        url : contextPath + "/reply/selectReplyList",
        data : {"boardNo" : boardNo},
        dataType : "JSON", // JSON 형태의 문자열 응답 데이터를 JS 객체로 자동 변환
        success : function(rList){
            
            const replyList = document.getElementById("reply-list");
            replyList.innerHTML = "";

            for (let reply of rList) {
                const li = document.createElement("li");
                li.classList.add("reply-row");

                const writer = document.createElement("p");
                writer.classList.add("reply-writer");

                const img = document.createElement("img");
                if(reply.profileImage != null){
                    img.setAttribute("src", contextPath + reply.profileImage)
                }else{
                    img.setAttribute("src", contextPath + "/resources/images/profile.img");
                }
                
                const span1 = document.createElement("span");
                span1.innerText = reply.memberNickname;

                const span2 = document.createElement("span");
                span2.classList.add("reply-date");
                span2.innerText = reply.createDt;

                writer.append(img, span1, span2);

                const content = document.createElement("p");
                content.classList.add("reply-content");
                content.innerHTML = reply.replyContent;

                li.append(writer, content);
                
                if(loginMemberNo == reply.memberNo){
                    const btnArea = document.createElement("div");
                    btnArea.classList.add("reply-btn-area");

                    const editBtn = document.createElement("button");
                    editBtn.setAttribute("onclick", `showUpdateReply(${reply.replyNo}, this)`);
                    editBtn.innerText = "수정";

                    const deleteBtn = document.createElement("button");
                    deleteBtn.setAttribute("onclick", `deleteReply(${reply.replyNo})`);
                    deleteBtn.innerText = "삭제";
                    
                    btnArea.append (editBtn, deleteBtn);

                    li.append(btnArea);
                }

                replyList.append(li);
            }
        },
        error : function(){
            console.log("에러 발생")
        }
    })
}

// 댓글 등록
const replyContent = document.getElementById("replyContent");
const addReply = document.getElementById("addReply");

addReply.addEventListener("click", () => {

    // 로그인 여부 확인
    if(loginMemberNo.length == 0){
        alert("로그인 후 이용해주세요.");
        return;
    }

    // 댓글 내용 작성 여부 확인
    if(replyContent.value.trim().length == 0){
        alert("댓글을 작성해주세요.")
        replyContent.focus();
        return;
    }

    // AJAX를 이용하여 댓글 내용 DB에 저장
    $.ajax({
        url : contextPath + "/reply/insert",
        data : {"memberNo": loginMemberNo,
                "replyContent" : replyContent.value,
                "boardNo" : boardNo},
        type : "POST",
        success : function(result){
            if(result > 0){
                alert("댓글 등록 성공");

                selectReplyList();

                replyContent.value = "";
            }else{
                alert("댓글 등록 실패");
            }
        },
        error : function(){
            console.log("댓글 등록 실패");
            console.log(req.responseText);
        }
    })

})

// 댓글 삭제
function deleteReply(replyNo){
    
    if(confirm("정말로 삭제하시겠습니까?")){
        // 요청주소 : /community/reply/delete
        // 파라미터 : key : "replyNo", value : 매개변수 replyNo
        // 전달방식 : "GET"
        // success : 삭제 성공 시 -> "삭제되었습니다." alert로 출력 후
        //                          댓글 목록 비동기 조회 함수 호출 

        //          삭제 실패 시 -> "삭제 실패" alert로 출력

        // error : 앞 error 코드 참고

        $.ajax({
            url : contextPath + "/reply/delete",
            data : {"replyNo" : replyNo},
            success : function(res){
                if(res != 0){
                    alert("삭제되었습니다.");
                    selectReplyList();
                }else{
                    alert("삭제 실패");
                }
            },
            error : function(){
                console.log("삭제 실패");
                console.log(req.responseText);
            }
        })
    }
}

// 댓글 수정 화면 전환

let beforeReplyRow; // 수정 전 원래 행의 상태를 저장할 변수

function showUpdateReply(replyNo, btn){ // 댓글 번호, 이벤트 발생 요소(수정 버튼)

    // ** 댓글 수정이 한 개만 열릴 수 있도록 만들기 **
    const updateTA = document.getElementsByClassName("update-textarea");

    if(updateTA.length > 0){ // 수정이 한 개 이상 열려 있는 경우
        if(confirm("다른 댓글이 수정 중입니다. 현재 댓글을 수정하시겠습니까?")){
            updateTA[0].parentElement.innerHTML = beforeReplyRow;
            // reply-row                           // 백업한 댓글
            // -> 백업 내용을 덮어 씌워지면서 textarea 사라짐

        }else{ // 취소
            return;

        }

    }

    // 1. 댓글 수정이 클릭된 행을 선택
    const replyRow = btn.parentElement.parentElement; // 수정버튼의 부모의 부모

    // 2. 행 내용 삭제 전 현재 상태 저장(백업) ->  전역변수 beforeReplyRow 이용
    beforeReplyRow = replyRow.innerHTML;
    
    // 3. 댓글에 작성되어 있던 내용만 얻어오기 -> 새롭게 생성된 textarea에 추가 예정
    let beforeContent = replyRow.children[1].innerHTML;
    // btn.parentElement.previousElementSibling.innerHTML

    // 4. 댓글 행 내부 내용을 모두 삭제
    replyRow.innerHTML = "";

    // 5. textarea 요소 생성 + 클래스 추가 + 내용 추가
    const textarea = document.createElement("textarea");
    textarea.classList.add("update-textarea");
    // ------------------------------------------------------------------------
    // XSS 방지 처리 해제
    beforeContent = beforeContent.replaceAll("&amp;", "&");
    beforeContent = beforeContent.replaceAll("&lt;", "<");
    beforeContent = beforeContent.replaceAll("&gt;", ">");
    beforeContent = beforeContent.replaceAll("&quot;", "\"");
    
    // 개행문자 처리 해제
    beforeContent = beforeContent.replaceAll("<br>", "\n");
    // ------------------------------------------------------------------------
    textarea.value = beforeContent;

    // 6. replyRow에 생성된 textarea 추가
    replyRow.append(textarea);

    // 7. 버튼 영역 + 수정 + 취소 버튼 생성
    const div = document.createElement("div");
    div.classList.add("reply-btn-area");

    const btn1 = document.createElement("button");
    btn1.innerHTML = '수정';
    btn1.setAttribute('onclick', `updateReply(${replyNo}, this)`);
    const btn2 = document.createElement("button");
    btn2.innerHTML = '취소';
    btn2.setAttribute('onclick', 'updateCancel(this)');

    div.append(btn1, btn2);

    // 8. replyRow에 생성된 버튼 영역 추가
    replyRow.append(div);
}

// 댓글 수정 취소
function updateCancel(btn){ // 클릭된 취소 버튼
    if(confirm("댓글 수정을 취소하시겠습니까?")){
        btn.parentElement.parentElement.innerHTML = beforeReplyRow;
        // beforeReplyRow(전역변수) : 수정 전 원래 행(댓글)을 저장한 변수
    }

}

// 댓글 수정(ajax)

// 댓글 수정 성공 시 : 댓글이 수정되었습니다. 알림창 출력 후 댓글 목록 조회
// 댓글 수정 실패 시 : 댓글 수정 실패 알림창

function updateReply(replyNo, btn){

    $.ajax({
        url : contextPath + "/reply/update",
        data : {"replyNo" : replyNo, "replyContent" : btn.parentElement.previousElementSibling.value},
        success : function(result){
            if(result != 0){
                alert("댓글이 수정되었습니다.");
                selectReplyList();
            }else{
                alert("댓글 수정 실패");
            }
        },
        error : function(){
            console.log("댓글 수정 실패");
            console.log(req.responseText);
        }
    })
}