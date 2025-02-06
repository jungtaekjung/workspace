console.log("signUp.js");

/* 정규 표현식(Regular Expression)
    https://regexper.com/
    https://regexr.com/
    https://developer.mozilla.org/ko/docs/Web/JavaScript/Guide/%EC%A0%95%EA%B7%9C%EC%8B%9D

    - 특정한 규칙을 가진 문자열 집합을 표현하는데 사용하는 형식 언어
    - 문자열에 대한 검색, 일치 여부, 치환 등을 수행할 수 있음


    *** JS 정규표현식 객체 생성 방법 ***

    1.  const regEx = new RegExp("정규표현식");
    2.  const regEx = /정규표현식/;


    *** 정규표현식 객체가 제공하는 메서드(함수) ***
    1.  regEx.test(문자열)
        -> 문자열이 정규표현식 패턴에 부합하면 true, 아니면 false

    2.  regEx.exec(문자열)
        -> 문자열이 정규표현식 패턴에 부합하면
            첫 번째 매칭되는 문자열을 반환,
            없으면 null 반환


     *** 정규 표현식의 메타 문자***
        
    문자열의 패턴을 나타내는 문자.
    문자마다 지정된 특별한 뜻이 담겨있다.
    
    a (일반문자열) : 문자열 내에 a라는 문자열이 존재하는 검색 
    [abcd] : 문자열 내에 a,b,c,d 중 하나라도 일치하는 문자가 있는지 검색
    ^(캐럿) : 문자열의 시작을 의미
    $(달러) : 문자열의 끝을 의미

    \w (word, 단어) : 아무 글자(단, 띄어쓰기, 특수문자, 한글 X)
    \d (digit, 숫자) : 아무 숫자(0~9 중 하나)
    \s (space, 공간) : 아무 공백 문자(띄어쓰기, 엔터, 탭 등)

    [0-9]  : 0부터 9까지 모든 숫자
    [ㄱ-힣] : ㄱ부터 힣까지  모든 한글

    [가-힣] : 가부터 힣까지  모든 한글(자음만, 모음만 있는 경우 제외)

    [a-z] : 모든 영어 소문자
    [A-Z] : 모든 영어 대문자

    * 특수문자의 경우 각각을 입력하는 방법밖엔 없음
    단, 메타문자와 중복되는 특수문자는 
    앞에 \(백슬래시)를 추가하여 탈출 문자(Escape)로 만들어 사용

    * 수량 관련 메타 문자
    a{5} : a문자가 5개 존재 == aaaaa
    a{2,5} : a가 2개 이상 5개 이하 ==  aa, aaa, aaaa, aaaaa
    a{2,} : a가 2개 이상
    a{,5} : a가 5개 이하


    * : 0개 이상 == 0번 이상 반복 == 있어도되고, 없어도 되고

    + : 1개 이상 == 1번 이상 반복

    ? : 0개 또는 1개

    . : 1칸 (개행문자를 제외한 문자 하나)
*/


// 유효성 검사 진행 여부 확인 객체
// -> 모든 value가 true인 경우에만 회원 가입 진행
const checkObj = {
    "memberEmail": false,
    "memberPw": false,
    "memberPwConfirm": false,
    "memberNickname": false,
    "memberTel": false
};

// JS 객체 : {K:V, K:V} (Map 형식)

// 1) 원하는 value 얻어오는 방법
// - 객체명.key
// - 객체명["key"]

// 2) 객체에 특정 Key가 존재하지 않으면 추가 가능
// ex) const obj = {'a' : 1, 'b' : 2};
//      obj.c = 3; -> {'a' : 1, 'b' : 2, 'c' : 3};

// 3) 객체에 특정 key 삭제 가능(delete 연산자)
// ex) const obj = {'a' : 1, 'b' : 2};
//      delete obj.b; -> {'a':1}

// 이메일 유효성 검사

// 입력된 이메일이 없을 경우
// "메일을 받을 수 있는 이메일을 입력해주세요."

// 있는 경우
// 입력 받은 이메일과 정규식 일치 여부 판별

// 유효한 경우 : 유효한 형식입니다. 초록글씨
// 유효하지 않는 경우 : 유효하지 않은 이메일 형식 입니다. 빨간글씨

const memberEmail = document.getElementById("memberEmail");
const emailMessage = document.getElementById("emailMessage");
const regEx = /^[A-Za-z\d\-\_]{4,}@\w+(\.\w+){1,3}$/;

memberEmail.addEventListener("input", function () {

    if (memberEmail.value.length == 0) {
        emailMessage.innerText = "메일을 받을 수 있는 이메일을 입력해주세요."
        emailMessage.classList.remove("error", "confirm");
        checkObj.memberEmail = false;
        return;
    }

    if (regEx.test(memberEmail.value)) {
        emailMessage.classList.add('confirm');
        emailMessage.innerText = "유효한 형식입니다.";
        emailMessage.classList.remove("error");
        checkObj.memberEmail = true;

    } else {
        emailMessage.classList.add('error');
        emailMessage.innerText = "유효하지 않은 이메일 형식입니다.";
        emailMessage.classList.remove("confirm");
        checkObj.memberEmail = false;
    }
})

// 비밀번호/비밀번호 확인 유효성 검사
const memberPw = document.getElementById("memberPw");
const memberPwConfirm = document.getElementById("memberPwConfirm");
const pwMessage = document.getElementById("pwMessage");

memberPw.addEventListener("input", function () {

    // 입력된 비밀번호가 없을 경우
    if (memberPw.value.length == 0) {
        memberPw.innerText = "영어,숫자,특수문자(!,@,#,-,_) 6~20글자 사이로 입력해주세요."
        memberPw.classList.remove("error", 'confirm');
        checkObj.memberPw = false;
        return;
    }

    // 있는 경우 입력 받은 비밀번호와 정규식 일치 여부 판별
    const regEx = /^[A-Za-z0-9!@#\-\_]{6,20}$/;

    if (regEx.test(memberPw.value)) { // 입력한 비밀번호가 유효한 경우
        checkObj.memberPw = true;

        // 비밀번호가 유효하게 작성된 상태에서 
        // 비밀번호 확인이 입력되지 않았을 때
        if (memberPwConfirm.value == '') {
            pwMessage.classList.add('confirm');
            pwMessage.innerText = "유효한 형식입니다.";
            pwMessage.classList.remove("error");

        } else { // 비밀번호 확인이 입력되었을 때

            // 비밀번호 == 비밀번호 확인 (같을 경우)
            if (memberPw.value == memberPwConfirm.value) {
                pwMessage.classList.add('confirm');
                pwMessage.innerText = "비밀번호가 일치합니다.";
                pwMessage.classList.remove("error");
                checkObj.memberPwConfirm = true;

            } else {// 다른 경우
                pwMessage.classList.add('error');
                pwMessage.innerText = "비밀번호가 일치하지 않습니다.";
                pwMessage.classList.remove("confirm");
                checkObj.memberPwConfirm = false;
            }
        }

    } else {
        pwMessage.classList.add('error');
        pwMessage.innerText = "유효하지 않은 비밀번호 형식입니다.";
        pwMessage.classList.remove("confirm");
        checkObj.memberPw = false;
    }
})

// 비밀번호 확인 유효성 검사
memberPwConfirm.addEventListener('input', () => {

    // 비밀번호가 유효하게 작성된 경우
    if (checkObj.memberPw) {

        // 비밀번호 == 비밀번호 확인 (같을 경우)
        if (memberPw.value == memberPwConfirm.value) {
            pwMessage.classList.add('confirm');
            pwMessage.innerText = "비밀번호가 일치합니다.";
            pwMessage.classList.remove("error");
            checkObj.memberPwConfirm = true;

        } else {// 다른 경우
            pwMessage.classList.add('error');
            pwMessage.innerText = "비밀번호가 일치하지 않습니다.";
            pwMessage.classList.remove("confirm");
            checkObj.memberPwConfirm = false;
        }
    } else { // 비밀번호가 유효하지 않은 경우
        checkObj.memberPwConfirm = false;
    }
})

// 닉네임 유효성 검사
const memberNickname =document.getElementById("memberNickname");
const nickMessage = document.getElementById("nickMessage");

memberNickname.addEventListener("input",function(){
    
    if(memberNickname.value.length ==0){
        nickMessage.innerText ="영어,숫자,한글 2~10글자"
        nickMessage.classList.remove("error","confirm");

        checkObj.memberNickname = false;
        return;
    }

    const regExp = /^[a-zA-Z0-9가-힣]{2,10}$/;

    if(regExp.test(memberNickname.value)){

        nickMessage.classList.add('confirm');
        nickMessage.innerText = "사용 가능한 닉네임 입니다.";
        nickMessage.classList.remove("error");
        checkObj.memberNickname = true;
    }else{
        nickMessage.innerText = "유효하지 않은 닉네임 형식입니다.";
        nickMessage.classList.add('error');
        nickMessage.classList.remove("confirm");
        checkObj.memberNickname = false;
    }
})

// 전화번호 유효성 검사

const memberTel = document.getElementById("memberTel");
const telMessage = document.getElementById("telMessage");

memberTel.addEventListener("input",function(){

    if (memberTel.value.length == 0) {
        telMessage.innerText = "전화번호를 입력해주세요.(-제외)";
        telMessage.classList.remove("error", "confirm");
        checkObj.memberTel = false;
        return;
    }

    const regExp = /^0(1[01]|2|[3-6][1-5]|70)\d{7,8}$/;

    if (regExp.test(memberTel.value)) {
        telMessage.classList.add('confirm');
        telMessage.innerText = "유효한 전화번호 형식입니다.";
        telMessage.classList.remove("error");

        checkObj.memberTel = true;
    } else {
        telMessage.classList.add('error');
        telMessage.innerText = "유효하지 않은 전화번호 형식입니다.";
        telMessage.classList.remove("confirm");

        checkObj.memberTel = false;
    }


})





// 회원 가입 form 태그가 제출 되었을 때
document.getElementById('signUpFrm').addEventListener('submit', e => {


    // checkObj에 모든 value가 true인지 검사

    // 배열용 향상된 for문(for... of )
    // -> iterator(반복자) 속성을 지닌 배열, 유사 배열 사용 가능

    // 객체용 향상된 for문(for ... in)
    // -> JS 객체가 가지고있는 key를 순서대로 하나씩 꺼내는 반복문


    let str = "";

    for (let key in checkObj) {

        if (!checkObj[key]) { //유효하지 않은 경우

            switch (key) {
                case "memberEmail": str = "이메일이"; break;
                case "memberPw": str = "비밀번호가"; break;
                case "memberPwConfirm": str = "비밀번호 확인이"; break;
                case "memberNickname": str = "닉네임이"; break;
                case "memberTel": str = "전화번호가"; break;
            }

            // 0000가 유효하지 않습니다. 알림창
            str += " 유효하지 않습니다.";
            alert(str);

            // 유효하지 않은 input 태그로 focus
            document.getElementById(key).focus();

            // form태그 기본 이벤트 제거
            e.preventDefault();
            return; // 함수 종료
        }
    }
})
