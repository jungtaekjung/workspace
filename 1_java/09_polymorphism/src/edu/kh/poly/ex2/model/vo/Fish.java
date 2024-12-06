package edu.kh.poly.ex2.model.vo;

public class Fish extends Animal {

	private int gill; // 아가미 수
	
	// 생성자
	// -> 미작성 시 컴파일러가 자동으로 기본 생성자 추가해줌
	public Fish() {
		super();
		// 추상 클래스는 단독 객체로 생성은 안되지만
		// 상속 받은 자식의 일부분으로써는 생성 가능
		// -> 미완성 부분은 자식 객체가 오버라이딩으로 완성하기 때문에
	}
	
	public Fish(String type, String eatType, int gill) {
		super(type, eatType);
		this.gill = gill;
	}

	public int getGill() {
		return gill;
	}

	public void setGill(int gill) {
		this.gill = gill;
	}

	// 추상 메소드는 상속 받으면 오버라이딩이 강제된다.
	@Override
	public void eat() {
		System.out.println("입을 뻐끔뻐끔 거리면서 먹는다.");
	}

	@Override
	public void breath() {
		System.out.println("물고기 : 아가미로 호흡한다.");
	}

	@Override
	public String toString() {
		return super.toString() + " / " + gill;
	}
}
