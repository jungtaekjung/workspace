package edu.kh.poly.ex2.model.vo;

public class Person extends Animal{
	// Animal의 추상 메소드를 오버라이딩 하지 않으면 오류 발생
	
	private String name;
	
	
	// 생성자
	public Person() {
		super(); // == Animal 기본 생성자
	}
	
	public Person(String type, String eatType, String name) {
		super(type, eatType);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	@Override
	public void eat() {
		System.out.println("숟가락, 젓가락 등을 이용해서 먹는다.");
	}

	@Override
	public void breath() {
		System.out.println("사람 : 입과 코로 숨을 쉰다. ");
	}
	
	@Override
	public String toString() {
		return super.toString() +" / " + name;
	}
	
}
