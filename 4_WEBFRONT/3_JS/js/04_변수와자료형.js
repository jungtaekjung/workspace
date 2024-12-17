// 변수 선언 위치에 따른 구분
var num1 = 10; // 전역 변수
num2 =5; // 전역 변수

var num1= 20;

console.log("num1 :" + num1);
console.log("num2 :" + num2);

function test(){
    var num3 =3; // function 지역 변수
    num4 =4; // 전역 변수

    if(true){
        var num5 = 5; // function 지역 변수
        num6 =6; // 전역 변수
    }

    console.log("num5 : " + num5);


}

test();
// console.log("num3 : " + num3); -> error 발생
console.log("num4 : " + num4); // function 종료 후에도 사용 가능

// console.log("num5 : " + num5); -> error
console.log("num6 : " + num6); // function 내부 if문 종료 후에도 사용 가능

// 자료형 확인
function typeTest(){
    const typeBox= document.getElementById("typeBox");

    let temp; // 선언 후 값을 초기화 하지 않은 상태 == undefined
    typeBox.innerText = "temp : " + temp;

    // string
    const name = "홍길동";

    // typeof 변수명 : 해당 변수의 자료형을 검사하는 연산자 
    typeBox.innerHTML += "<br>name : " + name + " / " + typeof name;

    const gender = 'm';
    typeBox.innerHTML += "<br>gender : " + gender + " / " + typeof gender;
    // 자바스크립트는 char 자료형 존재 X
    // "", '' 모두 string 리터럴 표기법

    // number
    const age = 30;
    const height = 178.9;
    typeBox.innerHTML += "<br>age : " + age + " / " + typeof age;
    typeBox.innerHTML += "<br>height : " + height + " / " + typeof height;

    // boolean
    const isTrue = true;
    typeBox.innerHTML += "<br>isTrue : " + isTrue + " / " + typeof isTrue;

    // object
    // java ({} 사용)
    // int [] arr = {1,2,3};

    // javascript([] 사용)
    const arr = [10,30,50];
    typeBox.innerHTML += "<br>arr : " + arr + " / " + typeof arr;

    for(let i=0; i<arr.length; i++){
        typeBox.innerHTML += "<br>arr[" + i+ "] : " + arr[i];
    }

    // 자바스크립트 객체는 K:V (Map 형식)
    const user ={"id" : "user01", "pw" : "pass01"};
    typeBox.innerHTML += "<br>user : " + user + " / " + typeof user; 

    // 객체 내용 출력 방법 1 
    typeBox.innerHTML += "<br>user.id : " + user.id;    
    typeBox.innerHTML += "<br>user.pw : " + user.pw;
    
    // 객체 내용 출력 방법 2 (객체 전용 for문  for ... in)
    for(let key in user){
        // user 객체의 키(id, pw)를 반복할 때 마다 하나씩 얻어와 
        // key 변수에 저장
        typeBox.innerHTML += "<br>user["+key+"] : " + user[key];
    }

    console.log(user);

    // function (js에서는 함수도 자료형!)

    // 1) 변수명 == 함수명
    const sumFn = function(n1,n2){ // 익명함수
        return n1 + n2;
    }

    // 함수명만 작성한 경우 -> 함수에 작성된 코드가 출력됨
    typeBox.innerHTML += "<br>sumFn : " + sumFn + " / " + typeof sumFn;

    // 함수명() 괄호를 포함해서 작성하는 경우 -> 함수 호출(수행)
    typeBox.innerHTML += "<br>sumFn(5,10) : " + sumFn(5,10);
    
    typeBox.innerHTML += "<br>tempFn(15,sumFn) : " + tempFn(15,sumFn);

}

function tempFn(n3,fn){
    return n3+fn(10,30);
}
    
