console.log("chatting.js loaded");

let selectChattingNo; // 선택한 채팅방 번호
let selectTargetNo ; // 현재 채팅 대상
let selectTargetName; // 대상의 이름
let selectTargetProfile; // 대상의 프로필

const addTarget = document.getElementById("addTarget"); // 추가버튼
const addTargetPopupLayer = document.getElementById("addTargetPopupLayer"); // 팝업 레이어
const closeBtn = document.getElementById("closeBtn"); // 닫기 버튼
const targetInput = document.getElementById("targetInput"); // 회원 검색
const resultArea = document.getElementById("resultArea"); // 회원 검색



//채팅방 목록에 이벤트를 추가하는 함수
function roomListAddEvent(){
    const chattingItemList = document.getElementsByClassName("chatting-item");

    for(let item of chattingItemList){
        console.log(item)

        item.addEventListener("click",()=>{
            // 전역변수에 채팅방 번호, 상대 번호, 상대 프로필, 상대 이름 저장
            selectChattingNo= item.getAttribute("chat-no")
            selectTargetNo = item.getAttribute("target-no");

            // selectTargetProfile = item.querySelector('.list-profile').getAttribute("src");
            selectTargetProfile = item.getElementsByClassName("list-profile")[0].getAttribute("src");
            selectTargetName = item.children[1].children[0].children[0].innerText;

            //알림이 존재하는 경우 해당 알림 지우기

            if(item.querySelector(".not-read-count") !=null){ //요소가 존재 한다면
                item.querySelector(".not-read-count").remove();
                console.log(item.querySelector(".not-read-count"))
            }

            //모든 채팅방에서 select 클래스 제거

            for (let it of chattingItemList) {
                it.classList.remove("select");
            }
            // 현재 클릭한 채팅방에 select 클래스 추가
            item.classList.add("select");

            //비동기로 메세지 목록을 조회하는 함수 호출
            selectMessageList();


            

        })
    }

}



// 추가 버튼 클릭 시
// 검색 팝업 레이어 열기

addTarget.addEventListener("click",()=>{
    addTargetPopupLayer.classList.toggle("popup-layer-close");
    // toggle() : 해당 클래스가 있으면 제거, 없으면 추가
    targetInput.focus();
})

//검색 팝업 레이어 닫기

closeBtn.addEventListener("click",()=>{
    addTargetPopupLayer.classList.toggle("popup-layer-close");
    resultArea.innerHTML="";

})

//사용자 검색(AJAX)

targetInput.addEventListener("input",e=>{
    const query =e.target.value.trim();

    //입력 된게 없을 경우
    if(query.length==0){
        resultArea.innerHTML=""; // 이전 검색 결과 지우기
       return; 
    }

    //입력된게 있을 경우
    fetch("/chatting/selectTarget?query=" + query)
    .then(resp => resp.json())
    .then(list => {
        //console.log(list);
        resultArea.innerHTML = ""; // 이전 검색 결과 비우기

         if(list.length == 0){
            const li = document.createElement("li");
            li.classList.add("result-row");
            li.innerText = "일치하는 회원이 없습니다";
            resultArea.append(li);
            return;
         }

         for(let member of list){
            // li요소 생성(한 행을 감싸는 요소)
            const li = document.createElement("li");
            li.classList.add("result-row");
            li.setAttribute("data-id", member.memberNo);

            // 프로필 이미지 요소
            const img = document.createElement("img");
            img.classList.add("result-row-img");
            
            // 프로필 이미지 여부에 따른 src 속성 선택
            if(member.profileImage == null) img.setAttribute("src", "/resources/images/user.png");
            else   img.setAttribute("src", member.profileImage);

            let nickname = member.memberNickname;
            let email = member.memberEmail;

            const span = document.createElement("span");
            span.innerHTML = `${nickname} ${email}`.replace(query, `<mark>${query}</mark>`);

            // 요소 조립(화면에 추가)
            li.append(img, span);
            resultArea.append(li);

            // li요소에 클릭 시 채팅방에 입장하는 이벤트 추가
            li.addEventListener('click', chattingEnter);
         }

    })
    .catch(e => console.log(e))

})

//채팅방 입장 함수
function chattingEnter(e){
    console.log(e.target); // 실제 클릭된 요소
    console.log(e.currentTarget); // 이벤트 리스너가 설정된 요소

    const targetNo = e.currentTarget.getAttribute("data-id");

    fetch("/chatting/enter?targetNo=" + targetNo)
    .then(resp => resp.text())
    .then(chattingNo =>{
        // console.log(chattingNo)

        selectRoomList(); // 채팅방 목록 조회

        setTimeout(()=>{

            //존재하는 채팅방이 있으면 클릭해서 입장
            const itemList = document.querySelectorAll(".chatting-item");
            for(let item of itemList){
                
                //목록에 채팅방이 존재한다면
                if(item.getAttribute("chat-no")== chattingNo){
    
                    // 팝업 닫기
                    addTargetPopupLayer.classList.add("popup-layer-close");
              
                    // 사용자 검색 결과 삭제
                    resultArea.innerHTML="";
                    // 검색어 삭제
                    targetInput.value="";
    
                    // 해당 채팅방 클릭
                    item.click();
    
                    return; //일치하는거 어짜피 하나라 바로 리턴
                }
                
            }
        },150);

    })
    .catch(e => console.log(e))

}

//비동기로 채팅방 목록 조회
function selectRoomList(){

    fetch("/chatting/roomList")
    .then(resp => resp.json())
    .then(roomList =>{
        console.log(roomList)

        

        // 채팅방 목록 출력 영역 선택
        const chattingList = document.querySelector(".chatting-list");


        // 채팅방 목록 지우기
        chattingList.innerHTML = "";


        // 조회한 채팅방 목록을 화면에 추가
        for(let room of roomList){
            const li = document.createElement("li");
            li.classList.add("chatting-item");
            li.setAttribute("chat-no", room.chattingNo);
            li.setAttribute("target-no", room.targetNo);


            if(room.chattingNo == selectChattingNo){
                li.classList.add("select");
            }


            // item-header 부분
            const itemHeader = document.createElement("div");
            itemHeader.classList.add("item-header");


            const listProfile = document.createElement("img");
            listProfile.classList.add("list-profile");


            if(room.targetProfile == undefined)
                listProfile.setAttribute("src", "/resources/images/user.png");
            else                                
                listProfile.setAttribute("src", room.targetProfile);


            itemHeader.append(listProfile);


            // item-body 부분
            const itemBody = document.createElement("div");
            itemBody.classList.add("item-body");


            const p = document.createElement("p");


            const targetName = document.createElement("span");
            targetName.classList.add("target-name");
            targetName.innerText = room.targetNickName;
           
            const recentSendTime = document.createElement("span");
            recentSendTime.classList.add("recent-send-time");
            recentSendTime.innerText = room.sendTime;
           
           
            p.append(targetName, recentSendTime);
           
           
            const div = document.createElement("div");
           
            const recentMessage = document.createElement("p");
            recentMessage.classList.add("recent-message");


            if(room.lastMessage != undefined){
                recentMessage.innerHTML = room.lastMessage;
            }
           
            div.append(recentMessage);


            itemBody.append(p,div);


            // 현재 채팅방을 보고있는게 아니고 읽지 않은 개수가 0개 이상인 경우 -> 읽지 않은 메세지 개수 출력
            if(room.notReadCount > 0 && room.chattingNo != selectChattingNo ){
                           const notReadCount = document.createElement("p");
                notReadCount.classList.add("not-read-count");
                notReadCount.innerText = room.notReadCount;
                div.append(notReadCount);
            }else{


                // 현재 채팅방을 보고있는 경우
                // 비동기로 해당 채팅방 글을 읽음으로 표시
                setTimeout(()=>{

                    fetch("/chatting/updateReadFlag",{
                        method : "PUT",
                        headers : {"Content-Type" :  "application/json"},
                        body : JSON.stringify({"chattingNo" : selectChattingNo , 
                                                "memberNo" : loginMemberNo})
                    })
                    .then( resp => resp.text())
                    .then(result => console.log(result))
                    .catch(err=> console.log(err))

                },300)
            }
           


            li.append(itemHeader, itemBody);
            chattingList.append(li);
        }


        roomListAddEvent(); //채팅방 목록에 클릭 이벤트 함수 호출



    })
    .catch(e => console.log(e))

}


//비동기로 메세지 목록 조회하는 함수
function selectMessageList(){
    fetch("/chatting/selectMessageList?chattingNo=" + selectChattingNo + "&memberNo=" + loginMemberNo)
    .then(resp => resp.json())
    .then(messageList => {
       // console.log(messageList)
        const ul = document.querySelector(".display-chatting");


        ul.innerHTML = ""; // 이전 내용 지우기


        // 메세지 만들어서 출력하기
        for(let msg of messageList){
            //<li>,  <li class="my-chat">
            const li = document.createElement("li");


            // 보낸 시간
            const span = document.createElement("span");
            span.classList.add("chatDate");
            span.innerText = msg.sendTime;


            // 메세지 내용
            const p = document.createElement("p");
            p.classList.add("chat");
            p.innerHTML = msg.messageContent; // br태그 해석을 위해 innerHTML


            // 내가 작성한 메세지인 경우
            if(loginMemberNo == msg.senderNo){
                li.classList.add("my-chat");
               
                li.append(span, p);
               
            }else{ // 상대가 작성한 메세지인 경우
                li.classList.add("target-chat");


                // 상대 프로필
                // <img src="/resources/images/user.png">
                const img = document.createElement("img");
                img.setAttribute("src", selectTargetProfile);
               
                const div = document.createElement("div");


                // 상대 이름
                const b = document.createElement("b");
                b.innerText = selectTargetName; // 전역변수


                const br = document.createElement("br");


                div.append(b, br, p, span);
                li.append(img,div);


            }


            ul.append(li);
            ul.scrollTop = ul.scrollHeight; //스크롤 제일 밑으로
            
        }

    }

    )
    .catch(e=>console.log(e))
}


//----------------------------------------------------
// sockJS를 이용한 Websocket 구현

let chattingSock;


// 로그인이 되어 있는 경우
// /chattingSock 이라는 요청 주소로 통신할 수 있는 WebSocket 생성
if(loginMemberNo !=""){
    chattingSock = new SockJS("/chattingSock");

}

//채팅 입력 시

const send=document.getElementById("send");

const sendMessage = () =>{
    const inputChatting = document.getElementById("inputChatting");

    if(inputChatting.value.trim() != ""){ // 채팅 내용이 있는 경우
        var obj = {
            "senderNo" : loginMemberNo,
            'targetNo' : selectTargetNo,
            chattingNo : selectChattingNo,
            messageContent : inputChatting.value
        };
        console.log(obj);

        // JS객체 -> JSON으로 변환하여 전송
        chattingSock.send( JSON.stringify(obj) );

        

       
        const content = `<strong>${loginMemberNickname}</strong>님이  채팅을 보냈습니다. <br>${inputChatting.value}`;

        const url = `${location.pathname}?chat-no=${selectChattingNo}`;
        console.log(url);
        // type, url,pkNo,content
            sendNotification(
                "chatting",
                url, 
                selectTargetNo,
                content
            );
            inputChatting.value=""; // 기존 메세지 내용 삭제


        
    }else{ // 없는 경우
        alert("채팅을 입력해 주세요.");
        inputChatting.value="";
    }
}

// 엔터 시 메세지 제출
// shift + enter 시 줄 바꿈
inputChatting.addEventListener("keyup",e=>{
    //console.log(e.key); // 입력한 값 출력
    //console.log(e.shiftKey) // shift+enter인 경우 true 반환

    if(e.key == "Enter"){
        if(!e.shiftKey){
            sendMessage();
        }
    }
})


// WebSocket 객체 chattingSock이
// 서버로부터 메세지를 받으면 자동으로 실행 될 콜백 함수
chattingSock.onmessage = e=>{
    // 전달 받은 값(JSON)을 JS 객체로 변환해서 obj 변수에 저장
    const msg= JSON.parse(e.data);
    //console.log(msg);
    // 현재 채팅방을 보고있는 경우
    if(selectChattingNo == msg.chattingNo){




        const ul = document.querySelector(".display-chatting");
   
        // 메세지 만들어서 출력하기
        //<li>,  <li class="my-chat">
        const li = document.createElement("li");
   
        // 보낸 시간
        const span = document.createElement("span");
        span.classList.add("chatDate");
        span.innerText = msg.sendTime;
   
        // 메세지 내용
        const p = document.createElement("p");
        p.classList.add("chat");
        p.innerHTML = msg.messageContent; // br태그 해석을 위해 innerHTML
   
        // 내가 작성한 메세지인 경우
        if(loginMemberNo == msg.senderNo){
            li.classList.add("my-chat");
           
            li.append(span, p);
           
        }else{ // 상대가 작성한 메세지인 경우
            li.classList.add("target-chat");
   
            // 상대 프로필
            // <img src="/resources/images/user.png">
            const img = document.createElement("img");
            img.setAttribute("src", selectTargetProfile);
           
            const div = document.createElement("div");
   
            // 상대 이름
            const b = document.createElement("b");
            b.innerText = selectTargetName; // 전역변수
   
            const br = document.createElement("br");
   
            div.append(b, br, p, span);
            li.append(img,div);
   
        }
   
        ul.append(li)
        ul.scrollTop = ul.scrollHeight; //스크롤 제일 밑으로
        
    }
    selectRoomList();
    


}






//채팅 클릭함수
function clickFirstChatItem() {
    
    const firstItem = document.querySelector('.chatting-list li');
    if (firstItem) {
        firstItem.click();
    }
}
//-------------------------------------------------------

//문서 로딩 완료 후 수행할 기능

// document.addEventListener("DOMContentLoaded", roomListAddEvent());


document.addEventListener("DOMContentLoaded", ()=>{
    //채팅방 목록에 클릭 이벤트 추가
    roomListAddEvent();

    //보내기 버튼에 클릭 이벤트 추가
    send.addEventListener('click', sendMessage);

    //채팅 알림을 클릭해서 채팅 페이지로 이동한 경우
    const params =new URLSearchParams(location.search);
    const chatNo= params.get("chat-no");
    if(chatNo !=null){
        const chatItems= document.querySelectorAll(".chatting-item");
        chatItems.forEach(item => {
            if(item.getAttribute("chat-no")==chatNo)
                item.click();
            return;
        })

    }else{ // 아닌 경우 == 채팅방 입장 시
        document.querySelector(".chatting-item")?.click();
        //채팅방 입장 시 채팅 목록이 존재하는 경우
    //가장 최근 채팅방에 입장
    }

    //clickFirstChatItem();
    



});



//-------------------------------------------

//테마 변경
const changeTheme = document.getElementById("changeTheme"); // checkbox

// localStorage : 브라우저에 key-value 값을 Storage에 저장할 수 있다.
// 이 때, 저장한 데이터는 세션간 공유됨

const isUserColorTheme = localStorage.getItem('color-theme');
const isOsColorTheme = window.matchMedia('(prefers-color-scheme: pink)').matches ? 'pink' : 'light';
// prefers-color-scheme : CSS 미디어 특성을 이용해 사용자의 OS가 사용하는 테마를 감지

// 사용자의 테마를 얻어오는 함수
const getUserTheme = () =>(isUserColorTheme ? isUserColorTheme : isOsColorTheme);

//문서의 모든 콘텐츠가 로드된 경우
window.onload= ()=>{
    if(getUserTheme() == 'light'){ // light모드인 경우
        document.documentElement.setAttribute('color-theme','light'); // html태그에 color-theme 속성 추가
        localStorage.setItem('color-theme','light');
    }else{ // pink 모드인 경우
        document.documentElement.setAttribute('color-theme','pink');
        localStorage.setItem('color-theme','pink');
        changeTheme.setAttribute("checked",true);
    }

}

// 테마 변경 버튼 클릭 시
changeTheme.addEventListener("click",e=>{
    if(e.target.checked){ //체크된 경우
        document.documentElement.setAttribute('color-theme','pink');
        // html태그에 color-theme 속성 추가

        localStorage.setItem('color-theme','pink');

    }else{//체크 해제된 경우
        document.documentElement.setAttribute('color-theme','light');
        localStorage.setItem('color-theme','light');
    } 
})

//테마 변경
