console.log('myPage.js');

// 내 정보(수정) 페이지
const memberNickname = document.getElementById('memberNickname');
const memberTel = document.getElementById('memberTel');
const updateInfo = document.getElementById('updateInfo');


// 내 정보 수정 form 태그가 존재할 때 (== 내 정보 페이지)
if(updateInfo != null){


    // 전역 변수로 수정 전 닉네임/전화번호를 저장
    initNickname = memberNickname.value;
    initTel =memberTel.value;


    // 닉네임 유효성 검사
    memberNickname.addEventListener('input',()=>{
        // 정규 표현식으로 유효성 검사
        const regExp = /^[a-zA-Z0-9가-힣]{2,10}$/;

        // 변경 전 닉네임과 입력한 값이 같은 경우
        if(memberNickname.value == initNickname){

            memberNickname.removeAttribute("style");
            return;
        }

        if(regExp.test(memberNickname.value)){
            memberNickname.style.color = 'blue';
        
        }else{
            memberNickname.style.color = 'red';

        }
        
    })

    // 전화번호 유효성 검사
    memberTel.addEventListener('input',()=>{
        // 정규 표현식으로 유효성 검사
        const regExp = /^0(1[01]|2|[3-6][1-5]|70)\d{7,8}$/;

        // 변경 전 전화번호와 입력한 값이 같은 경우
        if(memberTel.value == initTel){

            memberTel.removeAttribute("style");
            return;
        }

        if(regExp.test(memberTel.value)){
            memberTel.style.color = 'green';
        
        }else{
            memberTel.style.color = 'red';

        }
        
    })

    // form 태그 제출 시 유효하지 않은 값이 있을 경우 제출 X
    updateInfo.addEventListener("submit",e =>{
        
        // 닉네임이 유효하지 않은 경우
        if(memberNickname.style.color == "red"){
            alert("닉네임이 유효하지 않습니다.");
            memberNickname.focus();
            e.preventDefault();
            return;

        }

        // 전화번호가 유효하지 않은 경우
        if(memberTel.style.color == "red"){
            alert("닉네임이 유효하지 않습니다.");
            memberTel.focus();
            e.preventDefault();
            return;
            
        }

    })

}

// 비밀번호 변경 제출 시 
const changePwFrm = document.getElementById("changePwFrm");
const currentPw = document.getElementById("currentPw");
const newPw = document.getElementById("newPw");
const newPwConfirm = document.getElementById("newPwConfirm");

if (changePwFrm != null) {
    changePwFrm.addEventListener("submit", e => {
        // 현재 비밀번호 미작성 시
        if (currentPw.value == "") {
            alert("현재 비밀번호가 입력되지 않았습니다.");
            e.preventDefault();
            currentPw.focus();
            currentPw.value = ""; 
            return;
        }

        // 새 비밀번호 작성 시
        const regEx = /^[A-Za-z0-9!@#\-_]{6,20}$/;
        if (!regEx.test(newPw.value)) {
            alert("비밀번호가 유효하지 않습니다. (6~20자의 영문, 숫자, 특수문자만 허용)");
            e.preventDefault();
            newPw.focus();
            newPw.value = ""; 
            return;
        }

        // 새 비밀번호와 새 비밀번호 확인이 일치하지 않은 경우
        if (newPw.value != newPwConfirm.value) {
            alert("비밀번호가 일치하지 않습니다.");
            e.preventDefault();
            newPwConfirm.focus();
            newPwConfirm.value = ""; 
            return; 
        }

        // 전부 유효한 경우 -> 제출
    });
}
const secessionFrm  = document.getElementById("secessionFrm");
const memberPw = document.getElementById("memberPw");
const agree = document.getElementById('agree');

// 회원 탈퇴 페이지인 경우
if(secessionFrm !=null){
    secessionFrm.addEventListener("submit",e=>{
        
        // 비밀번호 미작성
        if(memberPw.value ==""){
            alert("현재 비밀번호가 입력되지 않았습니다.");
            e.preventDefault();
            memberPw.focus();
            memberPw.value = ""; 
            return;
        }
        // 약관동의가 체크되지 않은 경우
        if (!agree.checked) {
            alert("약관 동의 후 탈퇴 버튼을 클릭해주세요.")
            e.preventDefault();
            return;
        }
        // 탈퇴 버튼 클릭 시 "정말로 탈퇴하시겠습니까?"
        if (!confirm("정말 탈퇴하시겠습니까?")) {
            // 이 때, 취소 클릭 시 "탈퇴 취소" 알림창
            alert("탈퇴취소");
            e.preventDefault();
        }

    })


}




