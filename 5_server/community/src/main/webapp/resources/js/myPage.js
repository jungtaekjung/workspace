console.log("myPage.js");

function infoValidate() {
    memberNickname = document.getElementById("memberNickname");
    memberTel = document.getElementById("memberTel");


    const regExp1 = /^[a-zA-Z0-9가-힣]{2,10}$/;
    const regExp2 = /^0(1[01]|2|[3-6][1-5]|70)\d{7,8}$/

    // 닉네임 미작성시
    if (memberNickname.value.trim().length == 0) {
        alert("닉네임을 입력해주세요");
        memberNickname.focus();
        return false;
    }

    // 닉네임이 유효하지 않은 경우
    if (!regExp1.test(memberNickname.value)) {
        alert("닉네임은 영어/숫자/한글 2~10글자 사이로 작성해주세요.");
        memberNickname.focus();
        return false;
    }

    // 전화번호 미작성시
    if (memberTel.value.trim().length == 0) {
        alert("전화번호를 입력해주세요(-제외)");
        memberTel.focus();
        return false;
    }

    // 전화번호가 유효하지 않은 경우
    if (!regExp2.test(memberTel.value)) {
        alert("전화번호 형식이 올바르지 않습니다.");
        memberTel.focus();
        return false;
    }
    return true;
}

// 경고 출력 + 포커스 + return false 함수
function printFocus(msg, el) {
    alert(msg);
    el.focus();
    return false;

}

// 비밀번호 변경 제출 시 유효성 검사
function passwordValidate() {
    const currentPw = document.getElementById("currentPw");
    const newPw = document.getElementById("newPw");
    const newPwConfirm = document.getElementById("newPwConfirm");

    const regExp1 = /^[a-zA-Z0-9!@#\-_]{6,30}$/;

    // 현재 비밀번호 미작성시
    if (currentPw.value == "") {
        return printFocus("현재 비밀번호를 입력해주세요.", currentPw);
    }

    // 새 비밀번호 미작성시
    if (currentPw.value == "") {
        return printFocus("현재 비밀번호를 입력해주세요.", newPw);
    }

    // 새 비밀번호 확인 미작성 시
    if (currentPw.value == "") {
        return printFocus("새 비밀번호 확인을 입력해주세요.", newPwConfirm);
    }

    // 새 비밀번호가 유효하지 않은 경우
    if (!regExp1.test(newPw.value)) {
        return printFocus("영어, 숫자, 특수문자(!,@,#,-,_) 6~30글자 사이로 작성해주세요.", newPw);
    }

    // 새 비밀번호 != 새 비밀번호 확인
    if (newPw.value != newPwConfirm.value) {
        return printFocus("새 비밀번호가 일치하지 않습니다.", newPwConfirm)
    }

}




/*if(currentPw.value.trim().length ==0){
    alert("현재 비밀번호를 입력해주세요.");
    currentPw.focus();
    return false;
}
 
if(newPw.value.trim().length == 0){
    alert("새 비밀번호를 입력해주세요.");
    newPw.focus();
    return false;
}
if(!regExp1.test(newPw.value)){
    alert("영어, 숫자, 특수문자(!,@,#,-,_) 6~30글자 사이로 작성해주세요.");
    newPw.focus();
    return false;
}
if(newPwConfirm.value.trim().length == 0){
    alert("새 비밀번호 확인을 입력해주세요.");
    newPwConfirm.focus();
    return false();
}
if(newPw.value !== newPwConfirm.value){
    alert("새 비밀번호가 일치하지않습니다.");
    newPwConfirm.focus();
    return false;
}
return true; */

function secessionValidate() {
    const memberPw = document.getElementById('memberPw');
    const agree = document.getElementById('agree');

    // 현재 비밀번호 미작성시
    if (memberPw.value == "") {
        return printFocus("비밀번호를 입력해주세요.", memberPw);

    }

    // 약관 동의 체크박스
    if (!agree.checked) {
        alert("약관 동의 후 탈퇴 버튼을 클릭해주세요.")
        return false;
    }
    if (!confirm("정말 탈퇴하시겠습니까?")) { // 취소를 누른 경우
        return false;
    }

}

//-------------------------------------------------------------

// 회원 프로필 이미지 변경(미리보기)
const inputImage = document.getElementById("input-image");
if (inputImage != null) { // inputImage 요소가 화면에 존재할 때


    // input type='file' 요소는 파일이 선택될 때 change 이벤트 발생함
    inputImage.addEventListener("change", function () {
        // this : 이벤트가 발생한 요소 (input type = 'file')

        // files : input type='file'만 사용 가능한 속성으로
        // 선택된 파일 목록(배열)을 반환

        // console.log(this.files);
        //console.log(this.file[0]); // 파일 목록에서 첫 번쨰 파일 객체를 선택

        if (this.files[0] != undefined) { // 파일이 선택되었을 때
            const reader = new FileReader();
            // 자바스크립트의 FileReader
            // - 웹 애플리케이션이 비동기적으로 데이터를 읽기 위하여 사용하는 객체

            reader.readAsDataURL(this.files[0]);
            // FileReader.readAsDataURL(파일)
            // - 지정된 파일의 내용을 읽기 시작
            // - 읽어오는게 완료되면 result 속성 data:에
            // 읽어온 파일의 위치를 나타내는 URL이 포함된다.
            // -> 해당 URL을 이용해 브라우저에서 이미지 보기 가능

            // reader.onload = function(){}
            // -> FileReader가 파일을 다 읽어온 경우 함수 수행
            reader.onload = function (e) { // 고전 이벤트 모델
                // e : 이벤트 발생 객체
                // e.target : 이벤트가 발생한 요소(FileReader 객체)
                // e.target.result : FileReader가 읽어온 파일의 URL

                console.log(e.target.result);

                // 프로필 이미지의 src 속성 값을 FileReader가 읽어온 파일의 URL로 변경
                const profileImage = document.getElementById("profile-image");
                profileImage.setAttribute("src", e.target.result);
                // -> setAttribute() 호출 시 중복되는 속성이 존재하면 덮어쓰기

                document.getElementById('delete').value = 0;
                // 새로운 이미지가 선택되었기 때문에 1 -> 0(안눌러짐 상태)으로 변경 
            }
        }

    })
}

// 프로필 유효성 검사

function profileValidate() {
    const inputImage = document.getElementById("input-image");

    const del = document.getElementById('delete'); // hidden
    if (inputImage.value == "" && del.value == 0) { 
        
        // 파일이 선택 X  && X 버튼을 안누른 경우( =del의 값이 0)
        
        alert("이미지를 선택한 후 변경 버튼을 클릭해주세요.");
        
        return false;

    }
}

// 프로필 이미지 삭제 시
document.getElementById("delete-btn").addEventListener("click", () => {
    // 0 : 안눌러짐
    // 1 : 눌러짐

    const del = document.getElementById("delete"); // hidden 타입

    if (del.value == 0) { // 눌러지지 않은 경우 



        // 1. 프로필 이미지를 기본 이미지로 변경
        document.getElementById('profile-image').setAttribute('src', contextPath + '/resources/images/profile.png');

        // 2. input type ='file'에 저장된 값(value)에 ''대입
        document.getElementById('profile-image').value = '';

        del.value = 1; // 눌러진걸로 인식

    }
})
