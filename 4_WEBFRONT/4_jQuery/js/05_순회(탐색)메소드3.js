const style1 = {'border' : '2px solid red', color:'red'};
const style2 = {'border' : '2px solid orange', color:'orange'};
const style3 = {'border' : '2px solid yellow', color:'yellow'};
const style4 = {'border' : '2px solid green', color:'green'};
const style5 = {'border' : '2px solid blue', color:'blue'};

// h2태그의 형제 요소에 스타일을 style1으로 설정
$('h2').siblings().css(style1);

// h2 태그의 형제 요소 중 p 태그만 style2를 적용
$('h2').siblings('p').css(style2);

// span 태그 다음에 오는 형제 요소의 스타일을 style3로 설정
$('span').next().css(style3);

//h2 태그 다음에 오는 모든 형제 요소의 스타일 style4로 설정
$('h2').nextAll().css(style4);

// span 태그부터 h3태그까지 폰트 크기를 24px로 변경
$('p').nextUntil('p').css('fontSize','24px');

// is("css선택자")
// 지정된 범위 내에 매개변수로 지정된 선택자와 일치하는 요소가
// 존재하는지 확인하는 메소드
// 존재하면 true, 없으면 false 반환

// h2 태그 이전에 있는 모든 형제 요소 중 p 태그가 있는지 확인
console.log($('h2').prevAll().is('p'));

// h2 태그 이전에 있는 모든 형제 요소 중 pre태그가 있는지 확인
console.log($('h2').prevAll().is('pre'));

$('span').on('click',function(){
    console.log('span 태그 클릭');

    // console.log($('span'))

    // console.log(this);

    console.log($(this));
})

// h2 태그를 클릭했을 때
// 클릭한 요소의 이전 요소 배경색을 red, 글자를 white로 변경

$('h2').on('click',function(){
    $(this).prev().css({'backgroundColor' : 'red',color : 'white' })
})
