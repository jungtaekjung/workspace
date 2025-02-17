console.log("boardList.js");

// 글쓰기 버튼 클릭 시 이벤트 추가
document.getElementById("insertBtn").addEventListener("click",function(){
    // JS BOM 객체 중 location 

    // location.href  = '주소'
    // -> 해당 주소로 요청(GET방식)

    location.href = `/board2/${location.pathname.split('/')[2]}/insert`;
                   // /board2/1/insert

})