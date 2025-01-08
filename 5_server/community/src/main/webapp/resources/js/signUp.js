console.log("signUp.js");

// 유효성 검사 여부를 기록할 객체 생성
const checkObj = {
    "memberEmail": false,
    "memberPw": false,
    "memberPwConfirm": false,
    "memberNickname": false,
    "memberTel": false
};

// 전화번호 유효성 검사

const memberTel = document.getElementById("memberTel");
const telMessage = document.getElementById("telMessage");

memberTel.addEventListener("input", function () {

    // 전화번호 미 작성시
    if (memberTel.value.length == 0) {
        telMessage.innerText = "전화번호를 입력해주세요.(-제외)";
        telMessage.classList.remove("error", "confirm");

        checkObj.memberTel = false;
        return;
    }

    // 전화번호 정규식
    const regExp = /^0(1[01]|2|[3-6][1-5]|70)\d{7,8}$/;

    // 전화번호 유효한 경우 : 유효한 전화번호 형식입니다. 초록글씨

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

// 이메일 유효성 검사

const memberEmail = document.getElementById("memberEmail");
const emailMessage = document.getElementById("emailMessage");

memberEmail.addEventListener("input", function () {

    if (memberEmail.value.length == 0) {
        emailMessage.innerText = "메일을 받을 수 있는 이메일을 입력해주세요"
        emailMessage.classList.remove("error", "confirm");

        checkObj.memberEmail = false;
        return;
    }

    const regExp = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    // 이메일 정규식
    // /^[\w\-]{4,}@\w+(\.\w+){1,2}$/;

    if (regExp.test(memberEmail.value)) {

        // **************** 이메일 중복 검사(ajax) 예정 **********************

        // $.ajax({ K:V, K:V}) -> jQuery ajax 기본 형태
        $.ajax({
            url: "emailDupCheck", // 필수 속성 url
            // 현재 주소 : /community/member/signUp
            // 상대 경로 : /community/member/emailDupCheck

            data: { "memberEmail": memberEmail.value },
            // data 속성 : 비동기 통신 시 서버로 전달할 값을 작성(JS 객체 형식)
            // -> 비동기 통신 시 input에 입력된 값을
            //      "memberEmail"이라는 key 값(파라미터)으로 전달 

            type: "GET", // 데이터 전달 방식(GET/POST)

            success: function (res) {
                // 비동기 통신(ajax)가 오류 없이 요청/응답에 성공한 경우

                // 매개 변수 res : servlet에서 출력된 result 값이 담겨 있음
                // res가 1 : 중복 / 0 : 중복 X
                // console. log(res)

                if (res == 1) { // 중복
                    emailMessage.classList.add('error');
                    emailMessage.innerText = "이미 사용중인 이메일입니다.";
                    emailMessage.classList.remove("confirm");
                    checkObj.memberEmail = false;
                } else {
                    emailMessage.classList.add('confirm');
                    emailMessage.innerText = "사용 가능한 이메일 입니다.";
                    emailMessage.classList.remove("error");
                    checkObj.memberEmail = true;
                }
            },
            error: function () {

                // 비동기 통신(ajax) 중 오류가 발생한 경우
                console.log("에러 발생");
            }

        })


    } else {
        emailMessage.classList.add('error');
        emailMessage.innerText = "유효하지 않은 이메일 형식입니다.";
        emailMessage.classList.remove("confirm");
        checkObj.memberEmail = false;

    }

})

// 닉네임 유효성 검사

const memberNickname = document.getElementById("memberNickname");
const nicknameMessage = document.getElementById("nicknameMessage");

memberNickname.addEventListener("input", function () {

    if (memberNickname.value.length == 0) {
        nicknameMessage.innerText = "영어/숫자/한글 2~10글자 사이로 작성해주세요."
        nicknameMessage.classList.remove("error", "confirm");

        checkObj.memberNickname = false;
        return;
    }

    const regExp = /^[a-zA-Z0-9가-힣]{2,10}$/;

    if (regExp.test(memberNickname.value)) {

        $.ajax({
            url : "nickDupCheck",

            data: {"memberNickname" : memberNickname.value},

            type: "GET",

            success : function(res){

                if (res == 1) { // 중복
                    nicknameMessage.classList.add('error');
                    nicknameMessage.innerText = "이미 사용중인 닉네임입니다.";
                    nicknameMessage.classList.remove("confirm");
                    checkObj.memberNickname = false;
                } else {
                    nicknameMessage.classList.add('confirm');
                    nicknameMessage.innerText = "사용 가능한 닉네임 입니다.";
                    nicknameMessage.classList.remove("error");
                    checkObj.memberNickname = true;
                }

            },
            error : function(){
                console.log("에러 발생");
            }
        })

        // **************** 닉게임 중복 검사(ajax) 예정 **********************


    } else {
        nicknameMessage.innerText = "유효하지 않은 닉네임 형식입니다.";
        nicknameMessage.classList.add('error');
        nicknameMessage.classList.remove("confirm");
        checkObj.memberNickname = false;
    }
})


const regExp = /^[a-zA-Z0-9!@#\-_]{6,30}$/;

// 비밀번호 유효성 검사

const memberPw = document.getElementById("memberPw");
const pwMessage = document.getElementById("pwMessage");
const memberPwConfirm = document.getElementById("memberPwConfirm");

memberPw.addEventListener("input", function () {

    // 비밀번호 미 작성시
    if (memberPw.value.length == 0) {
        pwMessage.innerText = "영어, 숫자, 특수문자(!,@,#,-,_) 6~30글자 사이로 작성해주세요.";
        pwMessage.classList.remove("error", "confirm");

        checkObj.memberPw = false;
        return;
    }

    // 비밀번호 정규식
    const regExp = /^[a-zA-Z0-9!@#\-_]{6,30}$/;

    // 비밀번호 유효한 경우 : 유효한 비밀번호 형식입니다. 초록글씨

    if (regExp.test(memberPw.value)) {
        checkObj.memberPw = true;

        if (memberPwConfirm.value == "") { // 비밀번호 유효, 비번 확인 작성 X
            pwMessage.classList.add('confirm');
            pwMessage.innerText = "유효한 비밀번호 형식입니다.";
            pwMessage.classList.remove("error");

        } else { // 비밀번호 유효, 비밀번호 확인 작성 O
            checkPw() // 비밀번호 일치 여부 확인 함수 호출
        }
    } else {
        pwMessage.classList.add('error');
        pwMessage.innerText = "유효하지 않은 비밀번호 형식입니다.";
        pwMessage.classList.remove("confirm");
        checkObj.memberPw = false;
    }
})

// 비밀번호 확인 유효성 검사
memberPwConfirm.addEventListener("input", checkPw);
// -> 이벤트가 발생 되었을 때 정의된 함수를 호출하겠다.

// 함수명() : 함수 호출 (수행)
// 함수명   : 함수에 작성된 코드를 반환


// 비밀번호 일치 여부 검사 함수
function checkPw() {
    // 비밀번호 == 비밀번호 확인
    if (memberPwConfirm.value == memberPw.value) {
        pwMessage.innerText = "비밀번호가 일치합니다.";
        pwMessage.classList.add('confirm');
        pwMessage.classList.remove("error");
        checkObj.memberPwConfirm = true;
    } else {
        pwMessage.innerText = "비밀번호가 일치하지 않습니다.";
        pwMessage.classList.add('error');
        pwMessage.classList.remove("confirm");
        checkObj.memberPwConfirm = false;
    }
}

// 회원가입 버튼 클릭 시 유효성 검사가 완료되었는지 확인하는 함수
function signUpValidate() {

    // checkObj에 있는 모든 속성에 반복 접근하여
    // false가 하나라도 있는 경우 form태그 기본 이벤트 제거

    let str = "";
    for (let key in checkObj) { // 객체용 향상된 for문

        // 현재 접근중인 key가 false인 경우
        if (!checkObj[key]) {

            switch (key) {
                case "memberEmail": str = "이메일이"; break;
                case "memberPw": str = "비밀번호가"; break;
                case "memberPwConfirm": str = "비밀번호 확인이"; break;
                case "memberNickname": str = "닉네임이"; break;
                case "memberTel": str = "전화번호가"; break;
            }

            str += " 유효하지 않습니다.";
            alert(str);

            document.getElementById(key).focus();

            return false; // form 태그 기본이벤트 제거
        }

    }

}
