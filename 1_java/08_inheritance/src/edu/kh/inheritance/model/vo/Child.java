package edu.kh.inheritance.model.vo;

// 상속 키워드 : extends
// public class 자식클래스 extends 부모클래스{}
public class Child extends Parent {
	
	// The type Child cannot subclass the final class Parent
	// final 클래스인 Parent는 Child 클래스를 자식으로 가질 수 없다.

	private String car;

	public Child() {

		System.out.println("child() 기본 생성자로 자식 객체 생성");
	}

	public Child(String car) {
		super(); // super 생성자 == 부모(Parent) 생성자
		
		this.car = car;
		System.out.println("child(String) 매개변수 생성자로 자식 객체 생성");
	}

	
	

	public String getCar() {
		return car;
	}

	public void setCar(String car) {
		this.car = car;
	}
	
	@Override
	public String toString() {
		return super.toString() + "/" + car;
				
	}

	// 부모로 부터 상속 받은 getMoney() 재정의
	@Override
	public int getMoney() {
		System.out.println("오버라이딩한 getMoney()");
		return super.getMoney()*2;
				// 부모의 getMoney
		// Cannot override the final method from Parent
	
	}


}
