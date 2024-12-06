package edu.kh.oop.cls.model.vo;

//	접근제한자 public : 같은 프로젝트 내에 어떤 클래스에서든 import할 수 있음

public class Student { // 클래스 선언부
// [접근제한자] [예약어] class 클래스명

	
	//	1.	필드(field == 멤버 변수) : 객체의 속성	
	
	// 인스턴스 변수 : 인스턴스(객체) 생성 시 메모리에 할당되는 변수
	
	// 클래스 변수(== static 변수) : 필드에 static 예약어가 작성된 변수
	// 왜? 같은 클래스로 만들어진 객체가 값을 공유할 수 있기 때문에 
	
	//	[작성법]
	//	[접근제한자] [예약어] 자료형 변수명 [= 초기값];
	
	//  접근제한자의 종류
	
	// + public 	: 같은 패키지 + 다른 패키지 (모두, 어디서든) 접근 가능
	// # protected 	: 같은 패키지 + 다른 패키기 중 상속 관계 클래스에서만 접근 가능
	// ~ (default)	: 같은 패키지 내부에서만 접근 가능
	// - private 	: 현재 클래스(정확히는 현재 객체) 내부에서만 접근 가능
	
	public int t1 = 10;
	protected int t2 = 20;
	int t3 = 30;
	private int t4 = 40;
	
	// 접근 제한자 예제
	public void ex() {
		System.out.println("같은 클래스 내부");
		System.out.println(t1);
		System.out.println(t2);
		System.out.println(t3);
		System.out.println(t4);
	}
	
	//------------------------------------------------------
	
	// static 예약어
	public static String schoolName = "KH유치원";
	
	private String name; // 캡슐화 원칙 때문에 private - > 간접 접근 방법 필요
	{	// 초기화 블록 : 객체 생성 시 필드 값 초기화
		name ="홍길동";
	}
	//getter / setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	// -----------------------------------------------------------------------
	
	//	2.	생성자(constructor)
	
	
	/*	- 	new 연산자를 통해서 객체를 생성할 때
	 * 		생성된 객체의 필드 값 초기화 + 지정된 기능을 수행하는 역할
	 * 	
	 *  -	생성자 작성 규칙
	 *  	1) 생성자의 이름은 반드시 클래스명과 같아야 한다.
	 *  	2) 반환형이 존재하지 않는다.
	 * 
	 *	-	생성자 종류
	 *		1) 기본 생성자
	 *		2) 매개변수 생성자
	 * 
	 * 
	 */
	// 기본 생성자
	//	[접근제한자] 클래스명() { 코드 }
	public Student() {
		// 객체가 생성될 때 수행할 코드
		System.out.println("기본 생성자에 의해서 Student 객체 생성");
	}
	
	//	public void ex1(){}
	//	-> void가 반환형임
	//	* 생성자는 반환형이 존재하지 않음 *
	
	
	
	
	
	//	3.	메소드(method)

}
