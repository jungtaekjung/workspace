package edu.kh.inheritance.model.vo;

// * final 클래스 
//   -> 마지막 클래스라는 의미로 "더 이상 상속 불가"
public /*final*/ class Parent extends Object {
						// 모든 클래스의 최상위 클래스
						// 미작성 시 컴파일러가 추가
	
	private int money = 100_000_000;
	private String lastName = "사";
	
	public Parent() {
		System.out.println("Parent() 기본 생성자로 부모 객체 생성");
		
	}

	public Parent(int money, String lastName) {
		System.out.println("Parent(int,String) 매개변수 생성자로 부모 객체 생성");
		
		this.money = money;
		this.lastName = lastName;
	}

	public /*final*/ int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override // Object의 toString() 오버라이딩
	public String toString() {
		return money+ "/" + lastName;
		
	}
		

}
