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

//----------------------------------------------------------

// 프로필 이미지 추가/변경/삭제
const profileImage =document.getElementById('profileImage'); //img 태그
const deleteImage =document.getElementById('deleteImage'); //x버튼
const imageInput =document.getElementById('imageInput'); //input태그

let initCheck; // 초기 프로필 이미지 상태를 저장하는 변수
               // false == 기본 이미지, true == 이전 업로드 이미지

let deleteCheck=-1;
//프로필 이미지가 새로 업로드 되거나 삭제 되었음을 나타내는 변수
// -1==초기값, 0==프로필 삭제(x버튼), 1== 새 이미지 업로드

let originalImage; //초기 프로필 이미지 파일 경로 저장

//화면에 imageInput이 있을 경우
if(imageInput!=null){

    // 프로필 이미지가 출력되는 img 태그의 src 속성 저장
    originalImage = profileImage.getAttribute("src");
    // 현재 회원의 프로필 이미지 상태 확인
    if(originalImage=="/resources/images/user.png"){
        initCheck =false;
    }else{ //기본 이미지가 아닌 경우
        initCheck=true;
    }
    

    // change 이벤트 : 값이 변했을 때
    // -input type = "file" , "checkbox", "radio"에서 많이 사용
    // -text/number 형식 사용 가능 
    //-> 이 때 input 값 입력 후 포커스를 잃었을 때, 이전 값과 다르다면 change 이벤트 발생
    imageInput.addEventListener("change",e=>{

        //2MB로 최대 크기 제한

        const maxSize=1*1024*1024*2;// 파일 최대 크기 지정(바이트 단위)

        //console.log(e.target); // input
        //console.log(e.target.value); // 업로드한 파일의 경로
        //console.log(e.target.files); // 업로드한 파일의 정보가 담긴 배열

        const file=e.target.files[0]; // 업로드한 파일의 정보가 담긴 객체

        // 파일을 한 번 선택한 후 취소했을 때
        if(file==undefined){
            console.log("파일 선택 취소");
            deleteCheck = -1; //파일 취소 == 파일 없음 == 초기상태

            //취소 시 기존 프로필 이미지로 변경
            profileImage.setAttribute("src",originalImage);
            return;
        }

        //선택된 파일의 크기가 최대 크기(maxSize)를 초과한 경우
        if(file.size>maxSize){
            alert("2MB 이하의 이미지를 선택해주세요.");
            imageInput.value="";
            // input type="file" 태그의 value는 빈칸("")만 대입 가능

            deleteCheck = -1; //파일 취소 == 파일 없음 == 초기상태

            //기존 프로필 이미지로 변경
            profileImage.setAttribute("src",originalImage);
            return;
        }
        
        const reader = new FileReader(); 
        //JS에서 파일을 읽는 객체
        // - 파일을 읽고 클라이언트 컴퓨터에 파일을 저장할 수 있음

        reader.readAsDataURL(file);
        //매개변수에 작성된 파일을 읽어서 저장 후
        //파일을 나타내는 URL을 result 속성으로 얻어옴

        //파일을 다 읽었을 때
        reader.onload = e=>{
            // console.log(e.target.result); // 읽어온 파일의 URL
            // console.log(e.target);
           
            //프로필 이미지 태그에 src 속성으로 추가
                profileImage.setAttribute("src",e.target.result);
                deleteCheck=1;

        }
    })

    // x 버튼 클릭 시

    deleteImage.addEventListener("click",()=>{

        //프로필 이미지를 기본 프로필 이미지로 변경
        profileImage.setAttribute("src","/resources/images/user.png");
        imageInput.value="";
        deleteCheck=0;
    })


    //#profileFrm이 제출 되었을 때

    document.getElementById("profileFrm").addEventListener("submit",e=>{


        //제출 여부 확인 변수 생성
        let flag= false;
        //프로필 이미지가 없다 -> 있다
        if(!initCheck && deleteCheck == 1) flag=true;

        //이전 프로필 이미지가 있다 -> 삭제
        if(initCheck && deleteCheck==0) flag=true;
        
        //이전 프로필 이미지가 있다 -> 새 이미지
        if(initCheck && deleteCheck==1) flag=true;

        // 위 3가지 경우가 아니라면 제출X
        if(!flag){
            alert("이미지 변경 후 클릭하세요.");
            e.preventDefault(); //form 태그 기본 이벤트 제거
        }
    })


}
