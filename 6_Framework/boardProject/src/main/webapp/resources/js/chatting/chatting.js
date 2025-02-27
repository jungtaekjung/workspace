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

    fetch("/chatting/enter?targetNo="+targetNo)
    .then(resp=>resp.text())
    .then(chattingNo => {

        selectRoomList(); // 채팅방 목록 조회

        setTimeout(() =>{

            
            // 존재하는 채팅방이 있으면 클릭해서 입장
            const itemList = document.querySelectorAll(".chatting-item");
            for(let item of itemList){
                if(item.getAttribute("chat-no") == chattingNo){
                    // 팝업 닫기
                    addTargetPopupLayer.classList.toggle("popup-layer-close")
                    // 사용자 검색 결과 삭제
                    resultArea.innerHTML="";
                    // 검색어 삭제
                    targetInput.value ="";
                    
                    item.click();
                    
                    return;
                }
            }
        },150);
    })
    .catch(e=> console.log(e))
}











//문서 로딩 완료 후 수행할 기능

// document.addEventListener("DOMContentLoaded", roomListAddEvent());


document.addEventListener("DOMContentLoaded", ()=>{
    //채팅방 목록에 클릭 이벤트 추가
    roomListAddEvent();
});


// 비동기로 채팅방 목록 조회
function selectRoomList(){
    fetch("/chatting/roomList")
    .then(resp => resp.json())
    .then(roomList =>{
        console.log(roomList);
        

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
                      headers : {"Content-Type" : "application/json"},
                      body : JSON.stringify({"chattingNo" : selectChattingNo , "memberNo" : loginMemberNo})
                      
                    })
                    .then(resp =>resp.text())
                    .then(result => console.log(result))
                    .catch(err => console.log(err))
                }, 300)
            }
           


            li.append(itemHeader, itemBody);
            chattingList.append(li);
        }

        
        roomListAddEvent();


    })
    .catch(e=> console.log(e))
}

