package edu.kh.poly.ex1.model.service;

import edu.kh.poly.ex1.model.vo.Car;
import edu.kh.poly.ex1.model.vo.Porsche;
import edu.kh.poly.ex1.model.vo.Spark;

public class PolyService {


	public void ex1() {

		// 다형성 확인 예제

		// Car 객체 생성
		Car c = new Car();
		//	부모타입 참조변수 = 부모 객체

		// Porsche 객체 생성
		Porsche p = new Porsche();
		// 		자식타입 참조변수 = 자식 객체

		//************************************************

		Car c2 = new Porsche(); // -> 오류 발생 안함
		// -> 오류 발생 안함
		// why? Porsche 객체를 참조하는 변수의 타입이 Car(부모)이기 때문에
		// Porsche 객체가 Car(부모) 객체로 변화함

		Car c3 = new Spark();
		// 부모타입 참조변수 = 자식 객체

		// 1) 자식 객체가 부모 객체로 변하였기 때문에
		//	  자식만의 고유한 필드, 메소드 사용 불가

		// 1-1)	car( 부모 = 부모)
		c.setWheel(4);
		c.setSeat(2);
		c.setFuel("휘발유");

		//	1-2) porsche (자식 = 자식)
		p.setWheel(2);
		p.setSeat(4);
		p.setFuel("휘발유");
		p.setOpenTheDoor(true);
		// porsche 메소드 모두 사용 가능

		//	1-3) c2 ( 부모 = 자식(Porsche) )
		c2.setWheel(5);
		c2.setSeat(9);
		c2.setFuel("가솔린");
		//	c2.setOpenTheDoor(true); 
		//		The method setOpenTheDoor(boolean) is undefined for the type Car

		//	1-4) c3 (부모 = 자식(Spark) )
		c3.setWheel(6);
		c3.setSeat(2);
		c3.setFuel("경유");
		//		c3.setDiscountRate(0.5);
		//		The method setDiscountRate(double) is undefined for the type Car


		// --------------------------------------------------------------

		//	2) 다형성을 이용한 객체 배열

		//	객체 배열 : 같은 객체 참조 자료형의 변수를 하나의 묶음으로 다루는 것
		//	-> 각 요소 하나가 참조 변수

		//	+ 다형성 적용 -> 부모 타입 참조 자료형의 변수를 하나의 묶음으로 다루는 것 

		Car[] arr = new Car[3]; // 부모 타입 참조 변수 배열 선언 및 할당
		// 각 배열 요소가 Car 타입 참조 변수

		arr[0] = c; // Car 주소 == Car 객체
		// Car 참조 변수

		arr[1] = c2; // Porsche 주소 == Porsche 객체
		// Car 참조 변수

		arr[2] = c3; // Spark 주소 == Spark 객체
		// Car 참조 변수  

		// 상속 + 다형성
		// 상속 특징 : 일련의 클래스들에 대한 공통적인 규약 정의
		//			-> Car를 상속받은 클래스는 모두 getWheel()을 가지고 있다를 정의

		// 다형성 (업캐스팅) : 부모타입 참조변수로 자식객체를 참조할 수 있다.
		for(int i=0; i<arr.length;i++) {
			System.out.println(i+"번째 인덱스의 바퀴 수 : "+ arr[i].getWheel());
		}


	}	
	public void ex2() {
		//	3) 다형성(업캐스팅)울 이용한 매개변수 사용법
		Porsche p = new Porsche(4,2,"고급 휘발유",true);

		Spark s = new Spark(4,4,"가솔린",0.7);

		Car c = new Car(8,2,"경유");

		printCar(p);
		printCar(s);
		printCar(c);

		System.out.println("-----------------------------------------------");

		// 4) 다형성을 이용한 반환형 사용법

		//		Car[] arr = { new Car(), new Porsche(), new Spark()};
		Car[] arr = {createCar(1),createCar(2),createCar(3)}; 
		// Car			Car			Car
arr[1] =p;
		// arr[0]; // Car
		// arr[1]; // Porsche
		// arr[2]; // Spark

		// instanceof 연산자 : 객체의 자료형을 검사하는 연산자
		//	-> 참조하는 객체가 특정 자료형이거나 부모쪽 상속 관계인지를 확인

		// arr[1]이 참조하는 객체가 Porsche이면 true, 아니면 false 
		System.out.println(arr[1] instanceof Porsche); // true

		// arr[1]이 참조하는 객체가 Porsche이면 true, 아니면 false 
		System.out.println(arr[1] instanceof Spark); // false
		
		// arr[1]이 참조하는 객체가 Porsche이면 true, 아니면 false 
		System.out.println(arr[1] instanceof Car); // true		
	
		System.out.println("----------------------------------------------");
		
		for(int i=0;i<arr.length;i++) {
			if(arr[i] instanceof Porsche) {
				System.out.println(((Porsche)arr[i]).isOpenTheDoor());
				System.out.println("Porsche 객체 입니다.");
			}else if(arr[i] instanceof Spark) {
				System.out.println("Spark 객체 입니다.");
				
			}else {
				System.out.println("Car 객체 입니다.");
			}
		}
	
	}

	// 전달받은 매개변수에 따라서 Car 또는 자식 객체를 생성하고
	// 생성된 객체의 주소를 반환

	public Car createCar(int num) {
		// 반환형은 Car이지만
		// case 2, 3인 경우 Car의 자식 객체의 주소가 반환된다.
		Car result = null;
		switch(num) {
		case 1 : result=new Car();break;
		case 2 : result=new Porsche();break;
		case 3 : result=new Spark();break;
		}

		return result;


	}


	// 전달 받은 Car 또는 자식 객체의 바퀴 수, 좌석 수, 연료를 출력하는 메소드
	// 매개변수에 부모 타입 참조변수를 작성하면 모든 자식 객체를 전달 받을 수 있다.
	public void printCar(Car poly) {
		// 매개변수에 작성된 참조형 변수에는 주소가 저장된다. (얕은 복사)

		System.out.println("바퀴 개수 : " + poly.getWheel() +"개");
		System.out.println("좌석 수 : " + poly.getSeat() +"개");
		System.out.println("연료 : " + poly.getFuel());

	}
	public void ex3() {
		// **** 다형성 중 다운 캐스팅 *****
	
		//	- 다운 캐스팅이란?
		//	부모타입 참조변수가 자식 객체를 참조하는
		//	업캐스팅 상태에서만 진행할 수 있는 기술로
		
		// 	부모 타입을 자식 타입으로 "강제 형변환"해서
		//	자식 객체의 본래 필드, 메소드를 사용 가능하게 함
		
		Car c1 = new Porsche(6,4,"휘발유",false);
		
		System.out.println(((Porsche)c1).isOpenTheDoor());
		// 주의!! "." 연산자가
		// (Porsche) 형변환 연산자보다 우선순위가 높음
		
		// 효율적인 다운캐스팅 방법
		// -> 얕은 복사를 이용!
		
		Porsche p1 = (Porsche)c1; 
	
		System.out.println(p1.isOpenTheDoor());
	
	}	
	
	public void ex4() {
		
		//	!! 다운 캐스팅 시 주의 사항 !!
	
		Car c1 = new Porsche();
		
		// Spark s1 = (Spark)c1; // 다운 캐스팅
		
		// java.lang.ClassCastException (형변환 예외)
		// -> c1이 참조하는 객체는 Porsche인데
		//	  Spark 참조변수로 Porsche를 참조하려해서 문제 발생
	
		// ## 해결 방법 : instanceof와 같이 사용하자 !
		if( c1 instanceof Spark ) {
			Spark s1 =(Spark)c1; // 다운 캐스팅
			System.out.println("다운 캐스팅 성공!");
		}else {
			System.out.println("다운 캐스팅 실패");
		}
	
	
	}
	public void ex5() {
		// 바인딩(Binding)
		// - 실제 실행할 메소드 코드와 호출하는 코드를 연결 시키는 것
		
		Car c1 =new Car(8,4, "경유");
		
		System.out.println(c1.getWheel());
		// Car 객체에 있는 getWheel() 메소드를 호출 == 바인딩
	
		// 프로그램 "실행 전"
		// - 컴파일러는 getWheel() 메소드가 Car에 있는 걸로 인식해서
		// 	 c1.getWheel() 호출 코드와
		//	 int edu.kh.poly.ex1.model.vo.Car.getWheel() 메소드 코드를 연결
		//  --> [정적 바인딩]
		System.out.println(c1.toString());
		// String edu.kh.poly.ex1.model.vo.Car.toString()
		// Car 참조변수 c1을 이용해서
		// Car 객체에 있는 오버라이딩된 toString() 메소드 호출
		
		// ** 다형성 적용 시 바인딩 **
		Car c2 = new Spark(4,2,"경유",0.5);
		// 업캐스팅 적용 -> 부모 부분만 참조 가능함
		
		System.out.println(c2.toString());
		// String edu.kh.poly.ex1.model.vo.Car.toString()
		// 참조변수 c2가 Car 타입이므로
		// toString()도 Car의 toString()을 호출 - 정적바인딩
		
		// 하지만 실행해보면 자식(Spark)의 toString()이 호출되는 것을 확인!
		// -> 컴파일 시에는 부모(Car)와 바인딩 == [정적 바인딩]
		// -> "실행 시"에는 자식(Spark)의 오버라이딩된 메소드와 바인딩 == [동적 바인딩]                     
	
	
		// ** 동적 바인딩 활용 방법 **
		Car[] arr = {new Car(), new Porsche(), new Spark()};
		 
		// arr 배열 요소가 참조하는 모든 객체의 bindingTest() 호출
		
		for(Car c : arr) {
			c.bindingTest();
		// 실행전 : edu.kh.poly.ex1.model.vo.Car.bindingTest() - 정적 바인딩
		// 실행후 : 각 객체의 오버라이딩된 toString()이 호출됨 - 동적 바인딩
		}
				
		// ** 동적 바인딩 장점 **
		// - 업캐스팅 상태의 참조 변수를
		//   별도의 다운 캐스팅 없이
		//   자식의 오버라이딩된 메소드를 수행할 수 있다.
		
		// 단, 동적 바인딩은 컴파일러로 검출이 안되기 때문에 
		// 실행 결과가 예상과 다르면 모두 개발자의 실수가 됨
		// -> 다형성이 적용된 코드를 이용할 땐
		//    동적 바인딩을 예상하면서 코드 작성하기!
	
	
	}	





















}
