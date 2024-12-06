package edu.kh.io.model.dto;

import java.io.Serializable;

// DTO(Data Transfer Object) : 데이터 전송 객체
// -> 주로 데이터베이스와 애플리케이션 간의 데이터 전송을 위해 사용

// VO(Value Object) : 값 객체, 불변성을 가지기 때문에 값 변경 불가
// -> 주로 비즈니스 로직에서 사용

// Serializable 인터페이스 : 해당 클래스가 객체로 만들어지면 직렬화가 가능함을 나타냄(마커 인터페이스)
public class Member implements Serializable {

	private String id;
	private String pw;
	private String name;
	private int point;

public Member() {}

public Member(String id, String pw, String name, int point) {
	super();
	this.id = id;
	this.pw = pw;
	this.name = name;
	this.point = point;
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getPw() {
	return pw;
}

public void setPw(String pw) {
	this.pw = pw;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public int getPoint() {
	return point;
}

public void setPoint(int point) {
	this.point = point;
}

@Override
public String toString() {
	return "Member [id=" + id + ", pw=" + pw + ", name=" + name + ", point=" + point + "]";
}



}
