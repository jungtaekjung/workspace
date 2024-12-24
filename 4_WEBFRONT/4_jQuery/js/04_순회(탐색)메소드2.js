$(document).ready(function(){
    // id가 test 요소의 테두리를 2px 빨간 실선, 글자를 파란색으로 변경
    // $("#test").css('border','2px solid red').css('color','blue')

    // 자바스크립트 객체를 매개변수로 전달
    // js객체의 key는 무조건 string이기 때문에 "",'' 또는 생략 가능
    $("#test").css({'border' : '2px solid red', color : 'blue'})
})

// css()의 매개변수로 사용할 코드를 미리 변수에 저장하기
const style1 = {'border' : '2px solid red', color:'red'};
const style2 = {'border' : '2px solid orange', color:'orange'};
const style3 = {'border' : '2px solid yellow', color:'yellow'};
const style4 = {'border' : '2px solid blue', color:'blue'};
const style5 = {'border' : '2px solid green', color:'green'};

$(function(){
    $('test').css(style4);
})

//------------------------------------------------------

// 클래스가 wrap인 요소의 자식 스타일을 style1로 설정
$('.wrap').children().css(style1);

// 클래스가 wrap인 요소의 후손 ul, p 태그의 스타일을 style2로 설정
$('.wrap').children().children().css(style2);

// 클래스가 type인 요소의 자식 중 ul태그의 스타일을 style3로 설정
$('.type').children('ul').css(style3);

// 클래스가 wrap인 요소를 기준으로 li태그의 스타일을 style4로 설정
$('.wrap').children('.type').children('ul').children('li').css(style4);

// 클래스가 wrap인 요소의 후손 중 모든 span 태그의 스타일을 style5로 설정
$('.wrap').find('span').css(style5);