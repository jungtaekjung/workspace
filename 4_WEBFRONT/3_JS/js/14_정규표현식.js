// 정규 표현식 객체 생성 + 확인하기
document.getElementById("check1").addEventListener("click",function(){

    // 정규 표현식 객체 생성
    const regExp1 = new RegExp("script");
                // "script"와 일치하는 문자열이 있는지 검사하는 정규 표현식
    const regExp2 = /java/;
            // "java"와 일치하는 문자열이 있는지 검사하는 정규 표현식
            
    // 확인하기
    const str1 = "저희는 지금 javascript 공부중 입니다."
    
    const str2 = "조만간 servlet/jsp(java server page)도 배웁니다."

    const str3 = "java는 이미 기억속에 없습니다... 기억을 java..";

    console.log("regExp1.test(str1 : " + regExp1.test(str1));
    console.log(regExp1.exec(str1));

    console.log("regExp2.test(str2 : " + regExp2.test(str2));
    console.log(regExp2.exec(str2));

    // 일치하는게 없는 경우
    console.log("regExp1.test(str2 : " + regExp1.test(str2)); // false
    console.log(regExp1.exec(str2)); // null


    // 일치하는게 여러 개 있을 경우
    console.log("regExp2.test(str3 : " + regExp2.test(str3));
    console.log(regExp2.exec(str3));
})

document.getElementById("btn1").addEventListener("click",function(){
    
    const div1 = document.getElementById("div1");

    // a(일반 문자열) : 문자열 내에 a라는 문자열이 존재하는지 검색
    const regExp1 = /a/;

    div1.innerHTML = "/a/, apple : " + regExp1.test("apple") + "<hr>";
    div1.innerHTML += "/a/, int : " + regExp1.test("int") + "<hr>";

    // [abcd] : 문자열 내에 a,b,c,d 중 하나라도 일치하는 문자가 있는지 검색
    const regExp2= /[abcd]/;
    div1.innerHTML += "/[abcd]/, apple : " + regExp2.test("apple") + "<hr>";
    div1.innerHTML += "/[abcd]/, int : " + regExp2.test("int") + "<hr>";

    // ^(캐럿) : 문자열의 시작을 의미
    const regExp3 = /^java/; // 문자열의 시작이 "java"인지 확인
    div1.innerHTML += "/^java/, javascript : " + regExp3.test("javascript") + "<hr>";
    div1.innerHTML += "/^java/, 내손을java : " + regExp3.test("내손을java") + "<hr>";

    // $(달러) : 문자열의 끝을 의미
    const regExp4 = /java$/; // 문자열의 시작이 "java"인지 확인
    div1.innerHTML += "/java$/, javascript : " + regExp4.test("javascript") + "<hr>";
    div1.innerHTML += "/java$/, 내손을java : " + regExp4.test("내손을java") + "<hr>";
})





// 이름 유효성 검사
document.getElementById("inputName").addEventListener("keyup",function(){
    
    const span = document.getElementById("result");
    
    // 한글 2~5글자 만족하는 경우
    const regExp = /^[가-힣]{2,5}$/; // 한글(단일 자음, 모음 제외)
    
    // 유효성 검사
    if(regExp.test(this.value)){
        // 초록글씨 : 유효한 이름 형식 입니다.
        span.innerHTML="유효한 이름 형식입니다."
        span.style.color="green";
    
    }else{
        //아닌 경우
        //빨간 글씨 : 유효하지 않은 이름 형식입니다.
        
        span.innerHTML="유효하지 않은 이름 형식입니다."
        span.style.color="red";
    }
    // 빈칸이라면 span 작성된 내용 초기화(삭제)
    if(this.value==''){
        span.innerHTML="";
    }
})



document.getElementById("input2").addEventListener("keyup",function(){
    
    const span = document.getElementById("input2Result");
    
    // 미작성인 경우 : 주민등록번호를 작성해주세요. (까만글씨)
    if(this.value==''){
        span.innerHTML="주민등록번호를 작성해주세요.";
        span.classList.remove("error");
        return; // 함수 종료
    }
    
    // 주민등록번호 정규식
    // 생년월일(6)- 고유번호(7)
    // const regExp = /^\d{6}-[0-9]{7}$/;
                    // 연도(2) 월(2) 일(2)

    // 업그레이드
    const regExp=/^\d{2}(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])-[1-4]\d{6}$/

    // () : 포획 괄호, 괄호 내부에 대응되는 부분을 찾아서 기억함
    // | : 또는 

    // 월
    // 01~09 ->0[1~9]
    // 10~12 ->1[0~2]

    // (0[1-9]|1[0-2]) : 괄호 내  | 기호를 기준으로 구분되며
    // 0이 먼저 입력된 경우 다음자리는 1~9
    // 1이 먼저 입력된 경우 다음자리는 0~2

    // 일
    // 01 ~ 09
    // 10 ~ 19
    // 20 ~ 29
    // 30 ~ 31
    (0[1-9]|1[0-9]|2[0-9]|3[0-1])

    // 주민등록번호 유효성 검사
    // 요소.classList : 요소가 가지고 있는 클래스를 배열로 반환
    // 요소.classList.add("클래스명") : 요소에 특정 클래스 추가
    // 요소.classList.remove("클래스명") : 요소의 특정 클래스 제거

    if(regExp.test(this.value)){
        // 유효한 경우 : 유효한 주민등록번호 형식입니다. (초록글씨)
        span.innerHTML = "유효한 주민등록번호 형식입니다.";
        span.classList.add("confirm"); // confirm 클래스 추가
        span.classList.remove("error"); // error 클래스 제거
    }else{
        // 아닌 경우 : 유효하지 않는 주민등록번호 형식입니다. (빨간글씨)
        span.innerHTML="유효하지 않는 주민등록번호 형식입니다.";
        span.classList.add("error");
    }
    if(this.value==''){
        span.innerHTML="";
    }
})
