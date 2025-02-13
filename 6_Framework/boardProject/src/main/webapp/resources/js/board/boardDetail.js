console.log("boardDetail.js")

const boardLike = document.getElementById('boardLike');
// 좋아요 버튼이 클릭 되었을 때
boardLike.addEventListener('click', e=>{


    console.log(loginMemberNo)
    console.log(boardNo)

    // 로그인 여부 검사
    if(loginMemberNo == ""){
        alert("로그인 후 이용해주세요.");
        return;
    }



    let check; // 기존에 좋아요 X : 0 || 기존에 좋아요 O : 1

    // contains("클래스명") : 클래스가 있으면 true, 없으면 false
    if(e.target.classList.contains("fa-regular")){ // 좋아요 X(빈하트)
        check = 0;
    }else{ // 좋아요 O(꽉찬 하트)
        check = 1;
    }

    // ajax로 서버에 제출할 파라미터를 모아둔 JS 객체
    const data = {boardNo : boardNo,
                  'memberNo' : loginMemberNo,
                  'check' : check}; 

     
                  
    // ajax
    fetch("/board/like",{
        method : "POST",
        headers : {"Content-Type" : "application/json"},
        body : JSON.stringify(data)
    })
    .then(resp => resp.text()) // 응답 객체를 필요한 형태로 파싱하여 리턴
    .then(count => {
        // 파싱된 데이터를 받아서 처리하는 코드 작성
        //console.log("count : " + count);
        
        if(count==-1){ // INSERT,DELETE 실패 시 
            alert("좋아요 처리 실패")
            return;
        }

        // toggle() : 클래스가 있으면 없애고, 없으면 추가
        e.target.classList.toggle("fa-regular");
        e.target.classList.toggle("fa-solid");
        
        // 현재 게시글의 좋아요 수를 화면에 출력
        e.target.nextElementSibling.innerText=count;
        
    })
    .catch(err => { // 예외 발생 시 처리하는 코드 
        console.log(err);
    })
})