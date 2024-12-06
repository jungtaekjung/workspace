package edu.kh.variable;

public class variableExample5 {
	
	public static void main(String[] args) {
		// main method : 자바 애플리케이션(프로그램)을 실행하기 위해 반드시 필요한 메소드
		
		/* 강제 형변환
		 * 
		 * - 기존 자료형을 원하는 자료형으로 강제로 변환 시키는 것
		 * 
		 * 1) [값의 범위]가 큰 자료형을 작은 자료형으로 변환할 때 사용
		 * 2) 출력되는 데이터의 표기법을 변화시키고 싶을 때
		 * 
		 * *강제 형변환 방법
		 * - 자료형을 변환시키고 싶은 값 또는 변수 앞에 (자료형) 작성
		 * */
		
		double dNum = 3.84;
		int iNum = (int)dNum; // Type mismatch: cannot convert from double to int
		
		System.out.println("dNum :" + dNum);
		System.out.println("iNum :" + iNum);
		// 실수 -> 정수형 변환 시 소수점 버림 처리(데이터 손실)
		
		// int -> byte 강제 형변환
		int iNum2 = 290;
		byte bNum2 = (byte)iNum2;
		
		System.out.println("iNum2 : "+ iNum2);
		System.out.println("bNum2 : "+ bNum2);
		// 같은 정수형끼리 변환 시에도
		// 값의 범위 차이 때문에 데이터 손실이 발생
		
		// char -> int
		char ch3 = 'A'; //65
			
		int iNum3 = ch3; // 자동 형변환 이용
		System.out.println(iNum3);
		
		// 강제 형변환 이용
		System.out.println((int)ch3);
		
		
		// int -> char 강제 형변환
		int iNum4 = 44033;
		System.out.println(iNum4 + "번째 문자 :" + (char)iNum4);
		
		// 소문자 'a' 보다 10칸 뒤에 있는 문자는 무엇일까요?
		char ch5 = 'a';
		
		int iNum5 = ch5 +10;
		System.out.println((char)iNum5);
		
		System.out.println((char)(ch5 + 10));
		
		
		
		
		
	}

}
