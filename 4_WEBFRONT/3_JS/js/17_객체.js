// 객체 생성 1
document.getElementById("btn1").addEventListener("click", function(){
    const div1 = document.getElementById("div1");
    
    // {} 객체 리터럴 표기법으로 객체 생성
    
    // * 중요 *
    // 자바스크립트 객체의 Key는 무조건 String(묵시적)
    // "Key" 또는 'Key' 또는 Key (따옴표가 없어도 string으로 인식)
    const brand = "스타벅스";
    const product = {
        "pName" : "케이크", 
        'brand' : "투썸플레이스", 
        flavor : ["초코", "딸기", "티라미수"],
        price : 14500,

        // 기능(메소드)
        buy : function(){
            console.log("혜선쌤을 위해 케이크를 사온다.")
        },
        information : function(){
            console.log(brand); // 객체 외부 변수 호출

            // 같은 객체 내부의 다른 속성을 호출하고 싶은 경우
            // 현재 객체를 뜻하는 this를 붙여줘야 함
            console.log(this.pName);
            console.log(this.brand);
            console.log(this.flavor);
            console.log(this.price);
        }

    };
    
    // 결과 출력
    div1.innerHTML = "product.pName : " + product.pName + "<br>";
    div1.innerHTML += "product.brand : " + product.brand + "<br>";
    div1.innerHTML += "product.flavor : " + product.flavor + "<br>";
    div1.innerHTML += "product.price : " + product.price + "<br>";

    div1.innerHTML += "<hr>";

    // 자바스크립트 객체용 향상된 for문
    // -> 객체 내부에 작성된 key를 순서대로 하나씩 꺼내옴
    for(let key in product){
        div1.innerHTML += product[key] + "<br>";
    }

    // 객체의 메소드 호출
    product.buy();
    product.information();
})

//---------------------------------------------------------------------------------

// 생성자 함수 (자바의 생성자를 함수로 작성하는 모양)

// 1. 생성자 함수 정의 (생성자 함수명은 대문자로 시작)
function Student(name, grade, classroom){

    // 속성
    // this == 생성되는 객체
    this.name = name; // 생성되는 객체의 name에 매개변수 name 대입
    this.grade = grade;
    this.classroom = classroom;

    // 기능()
    this.intro = function(){
        console.log(grade + "학년 " + classroom + "반 " + name + "입니다.")
    }

}

// 2. 생성자 함수 호출(new 연산자)
document.getElementById("btn2").addEventListener("click", function(){
    const std1 = new Student("고민시", 3, 1);
    const std2 = new Student("박보영", 2, 7);
    const std3 = new Student("박서준", 1, 9);
    
    console.log(std1);
    console.log(std2);
    console.log(std3);

    // 생성자 함수 사용 이유 : 같은 형태의 객체가 다수 필요한 경우에 사용
    // (코드 길이 감소, 재사용성 증가)

    console.log(std1.name);
    std1.intro();
})