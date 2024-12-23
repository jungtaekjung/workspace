// JS와 jQuery 차이점

// 배경 : pink
// 글자색 : white
// 글자크기 : 24px

document.querySelector("#jsBtn").addEventListener("click",function(){
    this.style.backgroundColor="pink";
    this.style.color="white";
    this.style.fontSize='24px';

})

// jQuery
$("#jQueryBtn").on('click',function(){
    $(this).css("backgroundColor","lightblue").css("color","white").css("fontSize","24px");
})

// window.onload 확인
window.onload = function(){ // 문서 로딩이 완료된 후 수행
    console.log(1);
}

console.log(2);

// window.onload 중복 작성
window.onload = function(){ // 앞서 작성한 window.onload 덮어씌움
    console.log(3);
}

console.log(2);

// ready() 함수 확인
$(document).ready(function(){
    $('#readyTest').on("click",function(){
        alert("ready 함수 확인")
    })
})

// ready() 중복 작성 -> 작성된 모든 내용 적용
$(document).ready(function(){
    $('#readyTest').css('color','red')
})

// ready() 함수의 다른 형태
$(function(){
    console.log("ready() 함수의 다른 형태")
})

// ready() + 화살표 함수
$(() =>{console.log("화살표 함수도 가능")})