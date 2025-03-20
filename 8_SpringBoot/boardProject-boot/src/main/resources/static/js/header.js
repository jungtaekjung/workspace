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


