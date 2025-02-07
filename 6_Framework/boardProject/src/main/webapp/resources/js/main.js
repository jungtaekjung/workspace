console.log('main.js')

const loginFrm = document.getElementById("loginFrm");

const memberEmail = document.getElementsByName("memberEmail")[0];
const memberPw = document.getElementsByName("memberPw")[0];
if (loginFrm != null) {
    // 로그인 시도를 할 때
    loginFrm.addEventListener("submit", e => {

        // 이메일이 입력되지 않은 경우
        if (memberEmail.value.trim().length == 0) {
            //"이메일을 입력해주세요" 알림창
            alert("이메일을 입력해주세요")
            // 제출 못하게 막기
            e.preventDefault();
            // 이메일 input 태그에 초점 맞추기
            memberEmail.focus();
            // 잘못 입력된 값 제거
            memberEmail.value = "";
            return;

        }

        // 비밀번호가 입력되지 않은 경우
        if (memberPw.value.trim().length == 0) {
            //"이메일을 입력해주세요" 알림창
            alert("비밀번호를 입력해주세요")
            // 제출 못하게 막기
            e.preventDefault();
            // 이메일 input 태그에 초점 맞추기
            memberPw.focus();
            // 잘못 입력된 값 제거
            memberPw.value = "";
        }
    })
}

// 비동기로 이메일이 일치하는 회원의 닉네임 조회

function selectNickname(email) {

    fetch("/selectNickname?email=" + email)    // 지정된 주소로 GET방식 비동기 요청(ajax)
        // 전달하고자 하는 파라미터를 주소 뒤 쿼리스트링으로 추가
        .then(response => response.text()) // 요청에 대한 응답 객체(response)를 필요한 형태로 파싱

        .then(nickname => { console.log(nickname) }) // 첫 번째 then에서 파싱한 데이터를 이용한 동작 작성

        .catch(e => { console.log(e) }) // 예외 발생 시 처리할 내용을 작성


}

// 닉네임이 일치하는 회원의 전화번호 조회
const inputNickname = document.getElementById("inputNickname");
const btn1 = document.getElementById("btn1");
const result1 = document.getElementById("result1");

btn1.addEventListener("click", () => {
    const nickname = inputNickname.value;
    // fetch() API를 이용한 ajax(비동기 통신)
    fetch("/selectTel?nickname=" + nickname)
        .then(response => response.text())
        // response : 응답 객체
        // response.text() : 응답 객체 내용을 문자열로 변환하여 반환

        .then(tel => {
            // tel : 파싱되어 반환된 값이 저장된 변수

            // 비동기 요청 후 수행할 코드
            result1.innerText = tel; // 조회 결과를 result1에 출력
        })
        .catch(e => {
            console.log(e);
            result1.innerText = '조회 실패';
        });
});

//************************************************************** */
// fetch() API를 이용한 POST 방식 요청

// 이메일을 입력 받아 일치하는 회원의 정보 조회
const inputEmail = document.getElementById("inputEmail");
const btn2 = document.getElementById("btn2");
const result2 = document.getElementById("result2");

btn2.addEventListener("click", () => {
    //POST 방식 비동기 요청

    // JSON.stringify() : JS 객체 -> JSON
    // JSON.parse()     : JSON -> JS 객체
    fetch("/selectMember", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ "email": inputEmail.value })


    })
        .then(resp => resp.json()) // 응답 객체를 매개변수로 얻어와 파싱

        .then(member => { // 파싱한 데이터를 이용해서 비동기 처리 후 동작 코드 작성
            console.log(member);

            // ul 내부 내용 모두 없애기
            result2.innerText = "";

            const li1 = document.createElement("li");
            li1.innerText = `회원번호 : ${member.memberNo}`;

            const li2 = document.createElement("li");
            li2.innerText = `이메일 : ${member.memberEmail}`;

            const li3 = document.createElement("li");
            li3.innerText = `닉네임 : ${member.memberNickname}`;

            const li4 = document.createElement("li");
            li4.innerText = `전화번호 : ${member.memberTel}`;

            const li5 = document.createElement("li");
            li5.innerText = `주소 : ${member.memberAddress}`;

            const li6 = document.createElement("li");
            li6.innerText = `가입일 : ${member.enrollDate}`;

            result2.append(li1, li2, li3, li4, li5, li6);

        })
        .catch(err => {
            console.log(err)
            result2.innerText = "일치하는 회원이 없습니다."

        })
})

// 이메일이 일부라도 일치하는 모든 회원 조회
const input = document.getElementById("input");
const btn3 = document.getElementById("btn3");
const result3 = document.getElementById("result3");

btn3.addEventListener("click", () => {

    fetch("/selectMemberList", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ "email": input.value })


    })
        .then(resp => resp.json()) // 응답 객체를 매개변수로 얻어와 파싱

        .then(members => { // 파싱한 데이터를 이용해서 비동기 처리 후 동작 코드 작성
            console.log(members);

            result3.innerHTML = "";

            if (members.length != 0) {

                members.forEach(member => {
                    const tr = document.createElement("tr");

                    const td1 = document.createElement("td");
                    td1.innerText = member.memberNo;

                    const td2 = document.createElement("td");
                    td2.innerText = member.memberEmail;

                    const td3 = document.createElement("td");
                    td3.innerText = member.memberNickname;

                    tr.append(td1, td2, td3);
                    result3.appendChild(tr);

                })
            } else {
                result3.innerHTML = "<tr><td colspan='3'>일치하는 회원이 없습니다.</td></tr>"
            }


        })

        .catch(err => {
            console.log(err)

        })



})