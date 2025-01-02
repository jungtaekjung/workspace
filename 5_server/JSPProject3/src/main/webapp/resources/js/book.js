// 제목 클릭 시 alert(OOO은 XXX원 입니다.) 출력
console.log("book.js 연결");

const bookTitleList = document.querySelectorAll(".book-title");
// bookTitleList == 배열
// -> 요소를 하나씩 꺼낸 경우 == 제목 td 요소
// -> 요소를 하나씩 꺼내서 클릭된 경우라는 이벤트 리스너 추가

// 향상된 for문
for(let title of bookTitleList){
    title.addEventListener("click", (e) => {
        
        // 제목
        const t = e.target.innerText;
        
        // 가격
        // const p = e.target.nextElementSibling.nextElementSibling.innerText;
        
        // data-price 속성 값 얻어오기
        const p = e.target.getAttribute("data-price");

        // alert(t + "은/는" + p + "원 입니다.");

        // `${백틱}`
        alert(`${t}은/는 ${p}원입니다.`)
    })
}
