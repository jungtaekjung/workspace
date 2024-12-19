// 추가 버튼 클릭 시
const cont = document.getElementById("container");
document.getElementById("add").addEventListener("click", function(){

    // div 요소 생성
    const div = document.createElement("div");

    // div에 row 클래스 추가
    div.classList.add("row");

    //----------------------------------------------------------------------

    // input 요소 생성
    const input = document.createElement("input");

    // input에 in 클래스 추가
    input.classList.add("in");

    // input에 "type" 속성, "number" 속성값 추가(type="number")
    // - 요소.setAttribute("속성", "속성값") : 요소에 속성/속성값 추가
    input.setAttribute("type", "number");

    //----------------------------------------------------------------------

    // span 요소 생성
    const span = document.createElement("span");
    
    // span의 내용으로 "X" 추가
    span.innerText = "X";

    // span에 remove 클래스 추가
    span.classList.add("remove");

    // span이 클릭 되었을 때에 대한 이벤트 동작 추가
    span.addEventListener("click", function(){
        this.parentElement.remove();
    })

    //----------------------------------------------------------------------
    
    // div 내부에(자식) input, span 순서대로 추가
    div.append(input);
    div.append(span);

    // #container에 div를 마지막 자식으로 추가
    cont.append(div);
})

// 계산 버튼 클릭 시 이벤트 동작
document.getElementById("calc").addEventListener("click", function(){

    // 합계 저장용 변수
    let sum = 0;

    // in 클래스 요소 전부 얻어오기
    const list = document.getElementsByClassName("in");

    for(let input of list){
        // sum에 입력한 값 누적
        // input에 작성된 값은 모두 string -> 형변환 필요
        sum += Number(input.value);

        // Number("") == 0 // 빈 칸은 0으로 변환됨
    }

    alert("합계 : " + sum);
})