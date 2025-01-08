console.log("main.js loaded")

// 로그인 시 이메일(아이디)/비밀번호 입력 확인

// -> 미작성시 alert() 이용해서 메세지 출력
//    로그인 form 태그 제출을 막는 기본 이벤트 제거 진행

function loginValidate(){ // 로그인 유효성 검사

    // 이메일 입력 input 요소 
    const inputEmail= document.getElementsByName("inputEmail")[0];

    // 비밀번호 입력 input 요소

    const inputPw= document.getElementsByName("inputPw")[0];

    // 이메일이 입력되지 않은 경우
    if(inputEmail.value.trim() == ""){
        // 문자열.trim() : 문자열 양쪽 공백 제거 

        alert("이메일을 입력해주세요");
        inputEmail.focus(); // 이메일 input에 초점 맞추기
        inputEmail.value = "";
        return false; // 기본 이벤트 제거를 위해 false 반환

    }

    // 비밀번호가 입력되지 않은 경우


    if(inputPw.value.trim().length == 0){

        alert("비밀번호를 입력해주세요");
        inputPw.focus(); 
        inputPw.value = "";
        return false; 

    }
}
// 화면에 아이디 저장 체크박스가 있는 경우
if(document.getElementsByName("saveId")[0] != null){

    // 아이디 저장 체크박스가 체크되었을 때 이벤트 처리
    document.getElementsByName("saveId")[0].addEventListener("change",function(){

        // 체크 여부 확인
        
        // this : 이벤트가 발생한 요소
        // 체크박스요소.checked : 체크 여부 반환(t/f)
        
        if(this.checked){ //체크박스가 체크된경우
            const str = "개인 정보 보호를 위해 개인 PC에서의 사용을 권장합니다. 개인 PC가 아닌 경우 취소를 눌러주세요."
            
            if(!confirm(str)){ // 취소를 눌렀을 때
                this.checked = false;// 체크 해제 
            }
        }
    })
}