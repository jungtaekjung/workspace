console.log("signUp.js연결");

//회원가입
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

// 이메일 유효성 검사

// 유효성 검사 진행 여부 확인 객체
//-> 모든 value가 true인 경우에만 회원 가입 진행
const checkObj = {
    'memberEmail' : false,
    'memberPw' : false,
    'memberPwConfirm' : false,
    'memberNickname' : false,
    'memberTel' : false,
    'authKey' : false
}

// JS 객체 : {K:V, K:V} (Map 형식)

// 1) 원하는 value 얻어오는 방법
// - 객체명.key  ex) checkObj,memberEmail
// - 객체명["key"]

// 2) 객체에 특정 Key가 존재하지 않으면 추가 가능
// ex) const obj = {'a':1, 'b':2};
//      obj.c=3; -> {'a':1, 'b':2,'c':3};

// 3) 객체에 특정 key 삭제 가능(delete 연산자)
// ex) const obj = {'a':1, 'b':2};
//     delete obj.b; -> {'a':1};






// 입력된 이메일이 없을 경우
// "메일을 받을 수 있는 이메일을 입력해주세요."
const memberEmail=document.getElementById("memberEmail");
const emailMessage=document.getElementById("emailMessage");
// 있는 경우

memberEmail.addEventListener("input",function(){
    if(memberEmail.value.length==0){
        emailMessage.innerText="메일을 받을 수 있는 이메일을 입력해주세요"
        emailMessage.classList.remove("confirm","error");
        checkObj.memberEmail=false;
        return;
    }
    // 입력 받은 이메일과 정규칙 일치 여부 판별

// 유효한 경우 : 유효한 형식입니다. 초록글씨
    const regEx = /^[A-Za-z\d\-\_]{4,}@\w+(\.\w+){1,3}$/;
    if(regEx.test(memberEmail.value)){ //유효한 경우

        //*************************************** */
        /* fetch() API를 이용한 ajax */

        // Get 방식 ajax 요청(쿼리스트링으로 파라미터 전달!)

        fetch("/dupCheck/email?email="+ memberEmail.value)
        .then(response => response.text()) //응답 객체 -> 파싱( parsing, 데이터 형태 변환)
        .then(count => {
            //count : 중복이면 1, 아니면 0
            if(count ==0){

                emailMessage.innerText="사용 가능한 이메일입니다."
                emailMessage.classList.add("confirm");
                emailMessage.classList.remove("error");
                checkObj.memberEmail=true;
            }
        else{
        
            // 유효하지 않는 경우 : 유효하지 않은 이메일 형식입니다. 빨간글씨
                    emailMessage.innerText="이미 사용중인 이메일 형식입니다."
                    emailMessage.classList.add("error");
                    emailMessage.classList.remove("confirm")
                    checkObj.memberEmail=false;
                }
        })
        .catch(err => console.log(err)) //예외 처리






    }else{
        
// 유효하지 않는 경우 : 유효하지 않은 이메일 형식입니다. 빨간글씨
        emailMessage.innerText="유효하지 않은 이메일 형식입니다."
        emailMessage.classList.add("error");
        emailMessage.classList.remove("confirm")
        checkObj.memberEmail=false;
    }

})


//----------------------------------------
// 이메일 인증
//----------------------------------------
// 인증번호 발송
const sendAuthKeyBtn = document.getElementById("sendAuthKeyBtn");
const authKeyMessage = document.getElementById("authKeyMessage");
let authTimer;
let authMin = 4;
let authSec = 59;


// 인증번호를 발송한 이메일 저장
let tempEmail;


sendAuthKeyBtn.addEventListener("click", function(){
    authMin = 4;
    authSec = 59;


    checkObj.authKey = false;


    if(checkObj.memberEmail){ // 중복이 아닌 이메일인 경우

      
            clearInterval(authTimer); //기존 타이머 삭제
       




        /* fetch() API 방식 ajax */
        fetch("/sendEmail/signUp?email="+memberEmail.value)
        .then(resp => resp.text())
        .then(result => {
            if(result > 0){
                console.log("인증 번호가 발송되었습니다.")
                tempEmail = memberEmail.value;
            }else{
                console.log("인증번호 발송 실패")
            }
        })
        .catch(err => {
            console.log("이메일 발송 중 에러 발생");
            console.log(err);
        });
       


        alert("인증번호가 발송 되었습니다.");


       
        authKeyMessage.innerText = "05:00";
        authKeyMessage.classList.remove("confirm");


        authTimer = window.setInterval(()=>{


            authKeyMessage.innerText = "0" + authMin + ":" + (authSec<10 ? "0" + authSec : authSec);
           
            // 남은 시간이 0분 0초인 경우
            if(authMin == 0 && authSec == 0){
                checkObj.authKey = false;
                clearInterval(authTimer);
                return;
            }


            // 0초인 경우
            if(authSec == 0){
                authSec = 60;
                authMin--;
            }




            authSec--; // 1초 감소


        }, 1000)


    } else{
        alert("중복되지 않은 이메일을 작성해주세요.");
        memberEmail.focus();
    }


});




// 인증 확인
const authKey = document.getElementById("authKey");
const checkAuthKeyBtn = document.getElementById("checkAuthKeyBtn");


checkAuthKeyBtn.addEventListener("click", function(){


    if(authMin > 0 || authSec > 0){ // 시간 제한이 지나지 않은 경우에만 인증번호 검사 진행
        /* fetch API */
        const obj = {"inputKey":authKey.value, "email":tempEmail}
        const query = new URLSearchParams(obj).toString()
        
        fetch("/sendEmail/checkAuthKey?" + query)
        .then(resp => resp.text())
        .then(result => {
            if(result > 0){
                clearInterval(authTimer);
                authKeyMessage.innerText = "인증되었습니다.";
                authKeyMessage.classList.add("confirm");
                checkObj.authKey = true;


            } else{
                alert("인증번호가 일치하지 않습니다.")
                checkObj.authKey = false;
            }
        })
        .catch(err => console.log(err));




    } else{
        alert("인증 시간이 만료되었습니다. 다시 시도해주세요.")
    }


});




















//비밀번호/비밀번호 확인 유효성 검사

const memberPw=document.getElementById("memberPw");
const memberPwConfirm=document.getElementById("memberPwConfirm");
const pwMessage=document.getElementById("pwMessage");


memberPw.addEventListener("input",function(){

    //입력된 비밀번호가 없을 경우
    if(memberPw.value.length==0){
        pwMessage.innerText="영어,숫자,특수문자(!,@,#,-,_) 6~20글자 사이로 입력해주세요."
        pwMessage.classList.remove("confirm","error");
        checkObj.memberPw=false;
        return;
    }


    const regEx = /^[A-Za-z0-9!@#\-\_]{6,20}$/;
    if(regEx.test(memberPw.value)){ //입력한 비밀번호가 유효한 경우
        checkObj.memberPw=true;

        //비밀번호가 유효하게 작성된 상태에서 비밀번호 확인이 입력되지 않았을 때
        if(memberPwConfirm.value==""){
            pwMessage.innerText="유효한 형식입니다."
            pwMessage.classList.add("confirm");
            pwMessage.classList.remove("error");
        }else{ //비밀번호 확인이 입력되었을 때
            if(memberPw.value==memberPwConfirm.value){
                pwMessage.innerText="비밀번호가 일치합니다."
                pwMessage.classList.add("confirm");
                pwMessage.classList.remove("error");
                checkObj.memberPwConfirm=true;
    
            }else{    // 다른 경우
        // 비밀번호가 일치하지 않습니다. 빨간글씨
                pwMessage.innerText="비밀번호가 일치하지 않습니다."
                pwMessage.classList.add("error");
                pwMessage.classList.remove("confirm");
                checkObj.memberPwConfirm=false;
                
            }
        }
    }else{
        
        pwMessage.innerText="유효하지 않은 비밀번호 형식입니다."
        pwMessage.classList.add("error");
        pwMessage.classList.remove("confirm")
        checkObj.memberPw=false;
    }
})

//비밀번호 확인 유효성 검사
memberPwConfirm.addEventListener("input",()=>{

    //비밀번호가 유효하게 작성된 경우
    if(checkObj.memberPw){
          // 비밀번호 == 비밀번호 확인 (같을 경우)
    // 비밀번호가 일치합니다. 초록글씨
        if(memberPw.value==memberPwConfirm.value){
            pwMessage.innerText="비밀번호가 일치합니다."
            pwMessage.classList.add("confirm");
            pwMessage.classList.remove("error");
            checkObj.memberPwConfirm=true;

        }else{    // 다른 경우
    // 비밀번호가 일치하지 않습니다. 빨간글씨
            pwMessage.innerText="비밀번호가 일치하지 않습니다."
            pwMessage.classList.add("error");
            pwMessage.classList.remove("confirm");
            checkObj.memberPwConfirm=false;
            
        }
    }else{ //비밀번호가 유효하지 않은 경우
        checkObj.memberPwConfirm=false;
    }


})

const memberNickname = document.getElementById("memberNickname");
const nickMessage = document.getElementById("nickMessage");

memberNickname.addEventListener("input",function(){
       //닉네임 미작성시
       if(memberNickname.value.length==0){
        nickMessage.innerText="영어/숫자/한글2~10글자 사이로 작성해주세요"
        nickMessage.classList.remove("confirm","error");
        checkObj.memberNickname=false;
        return;
    }

    //닉네임 정규식
    const regEx=/^[a-zA-z가-힣0-9]{2,10}$/;
    if(regEx.test(memberNickname.value)){ //사용 가능한 닉네임 일때

        fetch("/dupCheck/nickname?nickname="+ memberNickname.value)
        .then(response => response.text())
        .then(count => {

            if(count==0){
                nickMessage.innerText="사용 가능한 닉네임 입니다."
                nickMessage.classList.add("confirm");
                nickMessage.classList.remove("error");
                checkObj.memberNickname=true;

            }else{
                nickMessage.innerText="이미 사용중인 닉네임 입니다"
                nickMessage.classList.add("error");
                nickMessage.classList.remove("confirm");
                checkObj.memberNickname=false;
            }


        })



    }else{
        nickMessage.innerText="유효하지 않는 닉네임 형식입니다"
        nickMessage.classList.add("error");
        nickMessage.classList.remove("confirm");
        checkObj.memberNickname=false;
    }

})


//전화번호 유효성검사
const memberTel = document.getElementById("memberTel");
const telMessage = document.getElementById("telMessage");

memberTel.addEventListener("input",function(){
    
    //전화번호 미작성시
    if(memberTel.value.length==0){
        telMessage.innerText="전화번호를 입력해주세요.(- 제외)"
        telMessage.classList.remove("confirm","error");
        checkObj.memberTel=false;
        return;
    }

    //전화번호 정규식
    const regExp=/^0(1[01]|2|[3-6][1-5]|70)\d{7,8}$/;

    //전화번호가 유효한 경우
    if(regExp.test(memberTel.value)){
        telMessage.innerText="유효한 전화번호 형식입니다."
        telMessage.classList.add("confirm");
        telMessage.classList.remove("error");

        checkObj.memberTel=true;
    }else{
        telMessage.innerText="유효하지 않는 전화번호 형식입니다."
        telMessage.classList.add("error");
        telMessage.classList.remove("confirm");
        checkObj.memberTel=false;
        
    }
})







// 회원 가입 form 태그가 제출 되었을 때
document.getElementById('signUpFrm').addEventListener('submit', e=>{

    // checkObj에 모든 value가 true인지 검사

    // 배열용 향상된 for문(for ... of)
    // -> iterator(반복자) 속성을 지닌 배열, 유사 배열 사용 가능

    // 객체용 향상된 for문(for ... in)
    // -> JS 객체가 가지고 있는 key를 순서대로 하나씩 꺼내는 반복문

    for(let key in checkObj){
        if(!checkObj[key]){ // 유효하지 않은 경우

            switch(key){
                case 'memberEmail' : alert("이메일이 유효하지 않습니다."); break;
                case 'memberPw' : alert("비밀번호가 유효하지 않습니다."); break;
                case 'memberPwConfirm' : alert("비밀번호 확인이 유효하지 않습니다."); break;
                case 'memberNickname' : alert("닉네임이 유효하지 않습니다."); break;
                case 'memberTel' : alert("전화번호가 유효하지 않습니다."); break;
                case 'authKey' : alert("이메일 인증번호가 유효하지 않습니다.");break;
            }

            // 유효하지 않은 input 태그로 focus
            // -> key와 input의 id를 똑같이 지정
            document.getElementById(key).focus();
            
            // form태그 기본 이벤트 제거
            e.preventDefault();
            return; // 함수 종료
        }
    }
})


/*
memberEmail.addEventListener("input",function(){

    fetch("/dupCheck_test/email?email=" + memberEmail.value)  
    .then(response => response.text())  
    .then(check=> {if(check!=""&&checkObj.memberEmail==true){
        emailMessage.innerText="중복임"
    }else if(check==""&&checkObj.memberEmail==true){
         emailMessage.innerText="중복아님"
    }
}) 
    .catch( e => {console.log(e)}) 
})

*/



