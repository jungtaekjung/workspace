console.log("boardList.js연결");

//글쓰기 버튼 클릭 시

// ?. Optional Chaining(옵셔널 체이닝)
// ?. '앞'의 대상이 null 이거나 undefined면 undefined를 반환
// -> 에러 발생하지 않음
// 따라서 ?. 은 존재하지 않아도 괜찮은 대상에 사용

document.getElementById("insertBtn")?.addEventListener("click",()=>{
    //JS BOM 객체 중 location

    // location.href = '주소'
    // -> 해당 주소로 요청(GET방식)

    location.href = `/board2/${location.pathname.split('/')[2]}/insert`;
                        // /board2/1/insert
    
})

//검색창에 이전 검색 기록 남기기
const searchKey = document.getElementById("searchKey");
const searchQuery = document.getElementById("searchQuery");
const options = document.querySelectorAll("#searchKey > option"); //option 태그 4개

(()=>{
    const params = new URL(location.href).searchParams;

    const key = params.get("key"); // t,c,tc,w 중 하나
    const query = params.get("query"); //검색어

    console.log(key);
    console.log(query);

    //검색 했을 때
    if(key !=null){
         // option 태그에 selected 속성 추가
         // -> option 태그를 하나씩 접근해서 value가 key와 같으면 속성 추가
         for(let option of options){
            if(option.value==key){
                option.selected = true;
            }

         }

    }

    // 검색어 화면에 출력
    searchQuery.value=query;
})();


//검색어 입력 없이 제출된 경우
document.getElementById("boardSearch").addEventListener("submit",function(e){
    if(searchQuery.value.trim().length==0){
        // form 기본 이벤트 제거
        e.preventDefault();
        
    // 해당 게시판 1페이지로 이동
        location.href=location.pathname;
        //location.pathname : 쿼리스트링을 제외한 실제 주소
    }
})





