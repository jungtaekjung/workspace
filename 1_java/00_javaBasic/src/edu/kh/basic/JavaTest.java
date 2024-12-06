package edu.kh.basic;

// (한줄)주석 : 컴파일러가 해석하지 않는 부분, 주로 코드 설명 작성

// 컴파일러 : 코드를 2진수로 변환하는 번역기

// Java 코드 실행 순서
// 코드 작성 -> ctrl + f11(실행 : 컴파일러가 2진수로 번역 -> JVM이 실행)

/* 범위
   주석 */
public class JavaTest {
	// class : 자바 코드가 작성되는 영역
	
	public static void main(String[] args){
		// main method(메소드 '기능') : 자바 애플리케이션(프로그램)을 실행하기 위해 반드시 필요한 메소드
		System.out.println("Hello World!!");
		System.out.println("자바 배우는 중~");
		System.out.println("2024-10-16");
		
		// ctrl + D : 한줄 삭제
		// ctrl + S : 저장
		// ctrl + X : 잘라내기
		// ctrl + A : 전체선택
		// ctrl + N : 이클립스에서 프로젝트, 클래스 만들기
		
		System.out.println("---------------------------------");
		
		// 숫자 연산
		System.out.println("1 + 2");
		System.out.println(1 + 2);
		
		// "" 안에 작성된 코드는 단순한 문자열로 인식
		// "" 안에 작성되지 않은 코드는 숫자, 변수로 인식
		
		System.out.println(100 - 78);
		System.out.println(45 * 12);
		System.out.println(56 / 4);
		
		// "문자열" + 숫자 함께 작성
		System.out.println("100 - 78 = " + 22);
		System.out.println("100 - 78 = " + (100 -78) );
		
		// + 기호 역할
		// - 문자열 + 숫자 
		// 또는 문자열 + 문자열 -> 이어쓰기
		// - 숫자 + 숫자 = 덧셈 연산 결과
		
		// 자바는 사칙연산의 우선순위를 그대로 따른다
		
		System.out.println("23 * 11 = " + 23 * 11);
		
		System.out.println("20241205 테스트용");
		
		
	}
	
}
