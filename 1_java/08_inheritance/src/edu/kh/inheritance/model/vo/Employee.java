package edu.kh.inheritance.model.vo;

public class Employee extends Person {
						// Person 상속 받음
	// ** 상속 **
	// extends : 확장하다, 연장하다
	
	private String company;
	
	public Employee() {}

	public Employee(String name, int age, String nationality, String company) {
		
		super(name,age,nationality);
		this.company = company;
	}
	

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	// Person으로 부터 상속 받은 메소드 중
	// move() 메소드를 Employee에 맞게 재정의(==오버라이딩)
	
	//@Override 어노테이션 : 해당 메소드가 오버라이딩 되었음을 알려주는 역할
	
	//@어노테이션(Annotation) : 컴파일러에게 알려주기 위한 코드
	//						 (컴파일러 인식용 주석)
	
	@Override // 생략 가능하나 오버라이딩 시 작성해주는 것이 좋음
	public void move() {
		//기존 부모 코드 삭제 후 자식이 새롭게 재정의
		System.out.println("오버라이딩 된 move() 메소드");
		System.out.println("일을 빨리 끝내고 칼퇴한다.");
	}

	// *** 오버라이딩 성립 조건 ***
	// 1. 메소드 이름 동일 
	// 2. 반환형 동일
	// 3. 매개변수 동일
	// 4. 접근제한자 같거나 더 넓은 범위
	//		ex) (부) protected - > (자) protected 또는 public
	// 5. 예외처리 범위는 같거나 더 좁게 
	
	// + 부모의 private 메소드는 오버라이딩 불가
	// 왜? 자식이 접근할 수 없기 때문에 
	
	@Override
	public String toString() {
		return super.toString() + "/"+ company;
	}
	
	
	
	
	
	
	
	
	
	




}
