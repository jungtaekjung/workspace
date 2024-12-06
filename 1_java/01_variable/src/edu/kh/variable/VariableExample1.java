package edu.kh.variable;

public class VariableExample1 {
	
	public static void main(String[] args) {
		
		/* 변수 (Variable)
		 * - 메모리(RAM)에 값을 기록하는 공간
		 * -> 공간에 기록되는 값이(Data)이 변할 수 있어서 변수라고 한다.
		 * 
		 * - 변수는 여러 종류 존재(저장되는 값의 형태, 크기가 다름)
		 * 
		 * 변수 사용의 장점
		 * 1. 코드의 길이 감소
		 * 2. 가독성 증가(읽기 편해짐)
		 * 3. 재사용성 증가(한번 만든 변수를 계속 사용)
		 * 4. 유지보수성 증가(코드 수정이 간단해짐)		 
		 * */
		
		// 변수 사용 X 
		System.out.println(2 * 3.141592653589793 * 7);
		System.out.println(3.141592653589793 * 7 * 7);
		System.out.println(3.141592653589793 * 7 * 7 * 20);
		System.out.println(4* 3.141592653589793 * 7 * 7);
		
		// 변수 사용 O
		double pi = 3.141592653589793; // 원주율
		int r = 7; // 반지름(radius)
		int h = 20; // 높이(height)
		// = -> 오른쪽에서 왼쪽으로 대입
		
		System.out.println("-------------------");
		System.out.println(2 * pi * r); // 원의 둘레
		System.out.println(pi * r * r); // 원의 넓이
		System.out.println(pi * r * r * h); // 원기둥의 부피
		System.out.println(4 * pi * r *r); // 구의 겉넓이
		
	}
}
