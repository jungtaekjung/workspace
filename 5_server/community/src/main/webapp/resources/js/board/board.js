
// 상세조회 - 목록으로 버튼
(function () {

    const goToListBtn = document.getElementById("goToListBtn");
    if (goToListBtn != null) {
        goToListBtn.addEventListener("click", () => {

            // location 객체(BOM)

            // 문자열.substring(시작, 끝) : 시작 이상 끝 미만 인덱스까지 문자열 자르기

            // 문자열.indexOf("검색 문자열", 시작 인덱스)
            // : 문자열에서 "검색 문자열"의 위치(인덱스)를 찾아서 반환
            // 단, 시작 인덱스 이후 부터 검색

            const pathname = location.pathname; // 주소상에서 요청 경로 반환
            //    /community/board/detail

            let url = pathname.substring(0, pathname.indexOf("/", 1));

            url += "/board/list?"
            // / community/board/list?

            // URL 내장 객체 : 주소 관련 정보를 나타내는 객체
            // URL.searchParams : 쿼리스트링만 별도 객체로 반환
            // location.href : 현재 페이지 주소 + 쿼리스트링

            const params = new URL(location.href).searchParams;

            const type = "type=" + params.get("type");
           
            let cp;
            if(params.get("cp") != null){ // 쿼리스트링에 cp가 있는 경우
                cp="cp=" + params.get("cp"); // cp=2
            }else{
                cp="cp=1";
            }

            // url 완성하기

            // /community/board/list?type=1&cp=2
            url += type + "&" + cp;



            // location.href="주소"; -> 해당 주소로 이동
            location.href =url;

        })
    }

})();

// -----------------------------------------------------
(function(){
    const deleteBtn = document.getElementById("deleteBtn");

    // 삭제버튼이 화면에 존재할 때
    if(deleteBtn !=null){

        deleteBtn.addEventListener("click", ()=>{

            // 현재 : /community/board/detail?no=1509&cp=1&type=2
            
            // 목표 : /community/board/delete?no=1509&type=2
            
            let url = 'delete'; // 상대경로 형식으로 작성
            
            // 주소에 작성된 쿼리스트링에서 필요한 파라미터만 얻어와서 사용
            
            // 1. 쿼리스트링에 존재하는 모든 파라미터 얻어오기
            const params = new URL(location.href).searchParams;
            
            // 2. 원하는 파라미터만 얻어와서 변수에 저장
            const no = '?no=' + params.get("no"); // ?no=1509
            const type = '&type=' + params.get("type"); // &type=2
            
            // 3. url에 쿼리스트링 추가
            url += no + type;

            if(confirm("정말로 삭제하시겠습니까?")){
                // 4. get방식으로 url 요청
                location.href = url;
            }
        })


    }

})();

// ------------------------------------------------------

// 모달 

// 즉시 실행 함수
(function(){
    const thumbnail = document.getElementsByClassName("list-thumbnail");


    if(thumbnail.length > 0){ // 목록에 썸네일 이미지가 있을 경우에만 이벤트 추가
     
        const modal = document.querySelector('.modal');
        const modalImage = document.getElementById("modal-image");
        const modalClose = document.getElementById("modal-close");


        for(let th of thumbnail){
            th.addEventListener("click", function(){
                modalImage.setAttribute("src", th.getAttribute("src") );
               
                /* on/off 스위치 */
                // classList.toggle("클래스명") : 클래스가 없으면 추가(add)
                //                                클래스가 있으면 제거(remove)
               
                modal.classList.toggle('show'); // add
            });
        }


        // X버튼
        modalClose.addEventListener("click", function(){
           
            modal.classList.toggle('hide'); // hide 클래스 추가


            setTimeout(function(){ // 0.45초 후 동작
                modal.classList.toggle('hide'); // hide 클래스 제거


                modal.classList.toggle('show'); // remove
            }, 450);
        });




    }


})();
