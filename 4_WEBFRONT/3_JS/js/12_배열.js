// 배열 확인 1
document.getElementById("btn1").addEventListener("click",function(){

    // 배열 선언 방법
    const arr1 = new Array(3); // 3칸짜리 배열 생성
    const arr2 = new Array(); // 0칸짜리 배열(비어있는 배열) 생성

    console.log(arr1);
    console.log(arr2);

    // 다른 자료형 대입
    arr1[0] = "라자냐";
    arr1[1] = 19800;
    arr1[2] = true;
    console.log(arr1);

    // 0칸 배열에 대입 -> 배열 길이 자동 증가
    arr2[0] = "라자냐";
    arr2[1] = 19800;
    arr2[2] = true;
    console.log(arr2);

    // 배열 선언 방법 2
    const arr3 = []; // 비어있는 배열(0칸)
    const arr4 = ["딸기","오렌지","블루베리"]; // 선언 및 초기화

    // for문으로 배열 요소 반복 접근하기
    // 1. 일반 for문

    // 2. 향상된 for문(for ... of) : 배열용 향상된 for문
    
    for(fruit of arr4){
        console.log(fruit);
    }

})
document.getElementById("btn2").addEventListener("click",function(){

    // 비어있는 배열 생성
    const arr =[];

    // push() : 배열의 마지막 요소로 추가
    arr.push("미나리 삼겹살");
    arr.push("초밥");
    arr.push("곱창");
    arr.push("갈비");
    arr.push("냉면");

    console.log(arr);

    // pop() : 배열의 마지막 요소를 꺼내옴
    console.log("꺼내온 요소 : " + arr.pop());
    console.log(arr);

    // 배열.indexOf("값") : 일치하는 값을 가진 요소의 index 반환
    console.log("초밥 인덱스 : " + arr.indexOf("초밥")); // 1
    console.log("갈비 인덱스 : " + arr.indexOf("갈비")); // -1

    // 배열.sort([정렬기준 함수]) : 배열 내 요소를 오름차순 정렬(문자열)
    const arr2 = [5,3,7,4,10,1];

    console.log(arr.sort()); // 문자열
    console.log(arr2.sort()); // 숫자 -> 정상적으로 정렬되지 않음
                                // 왜? 문자열 정렬 기준이 기본값

    // 숫자 오름차순 정렬
    console.log(arr2.sort(function(a,b){return a-b;}));
    
    console.log(arr.toString());
    console.log(arr.join(" / "));

})