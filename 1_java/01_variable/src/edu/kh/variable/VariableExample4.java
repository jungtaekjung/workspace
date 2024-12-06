package edu.kh.variable;

public class VariableExample4 {
	
	public static void main(String[] args) {
		
		/* 형변환(Casting) : 값의 자료형을 변환하는 것 (단, boolean 제외)
		 * 
		 * - 자동 / 강제 형변환이 존재
		 * 
		 * - 형변환이 필요한 이유: 컴퓨터는 기본적으로 같은 자료형 끼리만 연산 가능
		 * 						즉, 다른 자료형과 연산 시 오류 발생
		 * 						-> 이러한 상황을 해결하기 위해서 필요한 기술이 형변환
		 * 
		 * 자동 형변환
		 * - [값의 범위]가 큰 자료형과
		 *   [값의 범위]가 작은 자료형의 연산 시
		 *   작은 자료형 -> 큰 자료형으로
		 *   컴파일러에 의해서 자동적으로 변환되는 것
		 * */
		
		int intNumber = 5;
		double dNum = 2.7;
		
		System.out.println("자동 형변환 결과 : " + (intNumber + dNum));
		// 원래는 에러가 발생해야 하지만 "자동 형변환"이 일어나서 발생하지 않음
		
		int i1 = 3;
		double d1 = i1;
		// double은 실수만을 저장할 수 있는 자료형
		// 정수가 대입되는 연산이 수행되면
		// int -> double 변경 후 대입
		
		System.out.println("i1 : " + i1);
		System.out.println("d1 : " + d1);
		
		System.out.println(d1 + dNum);
		
		// int -> long 형변환
		int i2 = 2_100_000_000; // 21억
		long l2 = 10_000_000_000L; // 100억
		long result2 = i2 + l2;
				// int + long
				// long + long
				// long
		
		System.out.println("result2 : " + result2);
		
		// char -> int 형변환
		char ch3 = 'U';
		int i3 = ch3; // 대입도 연산.
	// int 변수 = char 값
	// 			 int (자동 형변환)
		
		System.out.println("i3 : " + i3);
		
		//(가 > 나 > 갸 > 각) 한글은 뒤쪽에 번호 배치
		char ch4 = '불';
		int i4 = ch4;
		System.out.println("i4 : " +i4);
		
		// long -> float 자동 형변환
	    long l5 = 123456789123456789L;
		float f5 = l5 * 100;
		// float = long * int
		//		   long * long
		//		   long (결과)
		// float = float (자동 형변환)
		
		System.out.println(f5); // -6.1010652E18 (오버플로우)
		
		// 오버플로우 현상은 컴퓨터가 미리 예측할 수 없다.
		// -> 개발자가 미리 예측 해야 함
		
		int i6 = 2147483647; // int 최대값
		int result6 = i6 + 1;
		System.out.println(result6);
				
		
		
		
		
	}

}
