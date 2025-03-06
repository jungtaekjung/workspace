// 문서가 모두 로딩된 후 수행

document.addEventListener("DOMContentLoaded", () => {

    // 알림
    connectSse(); // SSE 연결
    notReadCheck(); // 알림 개수 조회

    // 종 버튼(알림) 클릭 시 알림 목록 출력하기
    const notificationBtn = document.getElementById("my-element");

    notificationBtn?.addEventListener("click",()=>{
        //알림 목록
        const notificationList = document.querySelector(".notification-list");

        //알림 목록이 보이고 있을 경우 -> 알림 목록 숨기기
        if(notificationList.classList.contains("notification-show")){
            notificationList.classList.remove("notification-show");
        }else{
            // 안보이는 경우 -> 화면에 목록 보이게 하기
            notificationList.classList.add("notification-show")

            //비동기로 목록 조회
            selectNotificationList();

        }

    })

    //쿼리스트링 중 cn(댓글번호)가 존재하는 경우
    // 해당 댓글을 찾아 화면을 스크롤해서 이동하기
    
    // 쿼리스트링을 다룰 수 있는 객체
    const params= new URLSearchParams(location.search);
    const cn = params.get("cn"); // cn 값 얻어오기
    if(cn != null){ // cn 이 존재하는 경우
        const targetId= "c" + cn; // "c100" 형태로 변환

        //아이디가 일치하는 요소 얻어오기
        const target = document.getElementById(targetId);
 

        //댓글 요소가 제일 위에서 얼만큼 떨어져 있는지 반환

        const scrollPosition= target.offsetTop;
        

        //댓글 위치로 스크롤
        window.scrollTo({
            top : scrollPosition - 50, // 스크롤 할 길이
            behavior : "smooth" // 부드럽게 행동(동작)하도록 지정
                        // instant : 스크롤 즉시 적용
        })
        
    }

 




    //--------------------------------------------
    // 헤더 자동완성


    const query = document.querySelector("#query"); // 헤더 검색창


    const searchResult = document.querySelector("#searchResult"); // 검색창 자동 완성 영역




    query.addEventListener("input", e => {


        if(query.value.trim().length > 0){ // 입력된 내용이 있을 때
            fetch("/board/headerSearch?query=" + query.value.trim())
            .then(resp => resp.json())
            .then(list => {
                console.log(list);


                if(list.length > 0){ // 검색 결과가 있을 때
                    searchResult.classList.remove("close");
                   
                    // BOARD_NO, BOARD_TITLE, READ_COUNT, BOARD_CODE, BOARD_NAME


                    searchResult.innerHTML = ""; // 이전 검색 내역 삭제


                    for(let map of list){
                        const li = document.createElement("li");
                        li.setAttribute("path", `${map.BOARD_CODE}/${map.BOARD_NO}`);




                        const a = document.createElement("a");


                        map.BOARD_TITLE = map.BOARD_TITLE.replace(query.value, `<mark>${query.value}</mark>`);
                        map.BOARD_TITLE = `<b>${map.BOARD_TITLE}</b>`;


                        a.innerHTML = `${map.BOARD_TITLE} - ${map.BOARD_NAME}`;
                       
                        a.setAttribute("href", "#");


                        a.addEventListener("click", e => {
                            e.preventDefault();
                           
                            const path = e.currentTarget.parentElement.getAttribute("path");


                            location.href = "/board/" + path;
                           


                        });


                        li.append(a)
                        searchResult.append(li);




                    }


                }else{ // 검색 결과가 없다면
                    searchResult.classList.add("close");
                }
            })
            .catch(err => console.log(err));




        }else{ // 입력된 내용이 없을 때
            searchResult.classList.add("close");
        }




    })
   
});




document.addEventListener("click", e => {
    const elementList = document.querySelectorAll(".search-area, .search-area *");
    const searchResult = document.querySelector("#searchResult"); // 검색창 자동 완성 영역


    let flag = true;
    for(let element of elementList){


        if(element == e.target){
           
            flag = false;
            break;
        }
    }


    if(flag){
        searchResult.classList.add("close");
        // searchResult.innerHTML = "";
    }


});


//---------------------------------------------------

// ******* SSE(Server-Sent Events) *******

/*
    서버(요청) -> 클라이언트 ( 응답 )
    -서버가 클라이언트에게 실시간으로 데이터를 전송할 수 있는 기술
    
    -HTTP 프로토콜 기반

    -단방향 통신 (ex. 라디오)

    1) 클라이언트가 서버에 연결 요청
     -> 클라이언트가 서버로 부터 데이터를 받기 위한 대기 상태에 돌입 (EventSource 객체 이용)

    2) 서버가 연결된 클라이언트에게 데이터를 전달
     (서버 -> 클라이언트 데이터를 전달하라는 요청을 또 AJAX를 이용해서 비동기 요청)

 

 */

/* SSE 연결하는 함수
-> 연결을 요청한 클라이언트가 서버로부터 데이터가 전달될 때 까지 대기 상태(비동기)
*/

const connectSse = () => {
    //로그인이 되어있지 않은 경우 함수 종료
    if(!notificationLoginCheck) return;

    console.log("connectSse() 호출");

    //서버의 "/sse/connect" 주소로 연결 요청
    const eventSource = new EventSource("/sse/connect");

    //----------------------------------

    //서버로 부터 메세지가 왔을 경우(전달 받은 경우)

    eventSource.addEventListener("message",e=>{
        //console.log(e.data); // e.data == 전달 받은 메세지
                             // -> Spring HttpMessageConverter가 JSON으로 변환해서 응답해줌

        const obj = JSON.parse(e.data);
        //console.log(obj) //알림 받는 회원 번호, 읽지 않은 알림 개수
                            //채팅 알림인 경우 채팅방번호, 알림번호 추가로 얻어옴

        // 채팅 알림을 받았는데 해당 채팅방에 입장한 상태인 경우 알림 X
    try{

        if(selectChattingNo==obj.chattingRoomNo){
            fetch("/notification", {
                method: "DELETE",
                headers: { "Content-Type": "application/json" },
                body: obj.notificationNo
            })
            .then(resp => {
                if(!resp.ok) throw new Error("채팅 알림 삭제 실패");
            })
            .catch(err => console.log(err));
            
            return;
            
        }
    }catch(e){}


        //종 버튼에 색 추가
        const notificationBtn = document.getElementById('my-element');
        notificationBtn.classList.add("fa-solid");
        notificationBtn.classList.remove("fa-regular");

        //알림 개수 표시
        const notificationCountArea = document.querySelector(".notification-count-area");
        notificationCountArea.innerText = obj.notiCount;

        //알림 목록이 열려 있는 경우
        //알림 목록 비동기 조회
        const notificationList =document.querySelector('.notification-list');
        if(notificationList.classList.contains("notification-show")){
            selectNotificationList();
        }

    })

    //----------------------------------------


    //서버 연결이 종료된 경우(타임아웃 초과)
    eventSource.addEventListener("error",()=>{
        console.log("SSE 재연결 시도");

        eventSource.close(); // 기존 연결 닫기

        // 5초 후 재연결 시도
        setTimeout(()=>connectSse(),5000);
    })



   
}


    /* 알림 메세지 전송 함수 
        -알림을 받을 특정 클라이언트의 id가 필요

    [동작 원리]
    1) AJAX를 이용해 SseController에 요청

    2) 연결된 클라이언트 대기 명단(emitters)에서
       클라이언트 id가 일치하는 회원을 찾아 메세지 전달하는 send() 메소드를 수행
       
    3) 서버로 부터 메세지를 전달 받은 클라이언트의 eventSource.addEventListener()가 수행됨
    */



const sendNotification = (type,url,pkNo,content) =>{

    // type : 댓글, 답글, 게시글 좋아요 등을 구분하는 값
    // url  : 알림 클릭 시 이동할 페이지 주소
    // pkNo : 알림 받는 회원 번호 또는 회원 번호를 찾을 수 있는 pk값
    // content : 알림 내용

    //로그인이 되어있지 않은 경우 함수 종료
    if(!notificationLoginCheck) return;

    // 서버로 제출할 데이터를 JS 객체 형태로 저장
    const notification = {
        "notificationType" : type,
        "notificationUrl" : url,
        "pkNo" : pkNo,
        notificationContent : content
    };

    fetch("/sse/send", {
        method : "POST",
        headers : {"content-Type" : "application/json"},
        body : JSON.stringify(notification)
    })
    .then(response => {
        if(!response.ok){ //비동기 통신 실패 시
            throw new Error("알림 전송 실패");
        }
        console.log('알림 전송 성공');
    })
    .catch(err => console.log(err));
}

//비동기로 알림 목록을 조회하는 함수
const selectNotificationList = () => {

    // 로그인이 안된 경우
    if(!notificationLoginCheck) return;

    fetch("/notification")
    .then(resp => resp.json())
    .then(selectList =>{
        console.log(selectList);

        // 이전 알림 목록 삭제
      const notiList = document.querySelector(".notification-list");
      notiList.innerHTML = '';
 
      for (let data of selectList) {
 
        // 알림 전체를 감싸는 요소
        const notiItem = document.createElement("li");
        notiItem.className = 'notification-item';
 
 
        // 알림을 읽지 않은 경우 'not-read' 추가
        if (data.notificationCheck == 'N') notiItem.classList.add("not-read");
 
 
        // 알림 관련 내용(프로필 이미지 + 시간 + 내용)
        const notiText = document.createElement("div");
        notiText.className = 'notification-text';
 
 
        // 알림 클릭 시 동작
        notiText.addEventListener("click", e => {
 
          // 만약 읽지 않은 알람인 경우
          if (data.notificationCheck == 'N') {
            fetch("/notification", {
              method: "PUT",
              headers: { "Content-Type": "application/json" },
              body: data.notificationNo
            })
            // 컨트롤러 메서드 반환값이 없으므로 then 작성 X
          }
 
          // 클릭 시 알림에 기록된 경로로 이동
          location.href = data.notificationUrl;
        })
 
 
        // 알림 보낸 회원 프로필 이미지
        const senderProfile = document.createElement("img");
        if (data.sendMemberProfileImg == null) senderProfile.src = '/resources/images/user.png';  // 기본 이미지
        else senderProfile.src = data.sendMemberProfileImg; // 프로필 이미지
 
 
        // 알림 내용 영역
        const contentContainer = document.createElement("div");
        contentContainer.className = 'notification-content-container';
 
        // 알림 보내진 시간
        const notiDate = document.createElement("p");
        notiDate.className = 'notification-date';
        notiDate.innerText = data.notificationDate;
 
        // 알림 내용
        const notiContent = document.createElement("p");
        notiContent.className = 'notification-content';
        notiContent.innerHTML = data.notificationContent; // 태그가 해석 될 수 있도록 innerHTML
 
        // 삭제 버튼
        const notiDelete = document.createElement("span");
        notiDelete.className = 'notification-delete';
        notiDelete.innerHTML = '&times;';
 
 
        /* 삭제 버튼 클릭 시 비동기로 해당 알림 지움 */
        notiDelete.addEventListener("click", e => {
 
          fetch("/notification", {
            method: "DELETE",
            headers: { "Content-Type": "application/json" },
            body: data.notificationNo
          })
          .then(resp => {
            if (resp.ok){
              // 클릭된 x버튼이 포함된 알림 삭제
              notiDelete.parentElement.remove();
              notReadCheck();
              return;
            }
 
            throw new Error("네트워크 응답이 좋지 않습니다.");
          })
          .catch(err => console.error(err));
        })
 
        // 조립
        notiList.append(notiItem);
        notiItem.append(notiText, notiDelete);
        notiText.append(senderProfile, contentContainer);
        contentContainer.append(notiDate, notiContent);
    }



    })
    .catch(err=>console.log(err));
}

// 읽지 않은 알림 개수 조회 및 알림 유무 표시 여부 변경
const notReadCheck = () => {

    if(!notificationLoginCheck) return;

    fetch("/notification/notReadCheck")
    .then(resp => resp.text())
    .then(count =>{
        //console.log(count);

        //알림 개수 화면에 표시
        document.getElementsByClassName("notification-count-area")[0].innerText=count;

        //읽지 않은 알림이 존재한다면
        //알림종 노란색으로 변경
        if(count>0){
            document.getElementById("my-element").classList.remove("fa-regular")
            document.getElementById("my-element").classList.add("fa-solid")
        }else{
            //모든 알림을 읽은 경우
            //알림종 하얀색으로 변경
            document.getElementById("my-element").classList.remove("fa-solid")
            document.getElementById("my-element").classList.add("fa-regular")
        }

    })
    .catch(e => console.log(e))

}