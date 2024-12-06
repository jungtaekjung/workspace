package edu.kh.variable;

public class VariableExample3 {
	
	public static void main(String[] args) {
	
		//변수 명명 규칙
		
		
		// 1. 대소문자 구분, 길이제한 X
		int abcdefghijklmnopqrstuvwxyz;
		int abcdefghijklmnopqrstuvwxyZ; // Z 다름
		
		// 2 . 예약어 사용 X
		// double double;
		
		// 3. 숫자 시작 X
		// float 1fNumber;
		
		// 4. 특수문자 $, _ 만 사용 가능 (but 안씀)
		int $intNumber; // 문제는 없지만 개발자가 직접 작성하진 않음
		int int_number; // 자바는 카멜표기법 사용
		                // DB에서는 _ 표기법 사용
		
		// 5. 카멜 표기법
		// -> 변수명 작성 시 여러 단어를 이어쓰는 경우
		//	  띄어쓰지 않고 후속 단어의 첫 글자를 대문자로 작성
		//	  단 , 첫 시작 글자는 소문자로 하는 것이 관례
		char helloWorldLunchDinner;
		
		//6. 변수명은 언어를 가리지 않음
		int 정수숫자;
		double 실수1번 = 3.14;
		// 실수1번에 3.14 리터럴 초기화 시키기
		// 이것을 바탕으로 출력하기
		System.out.println (실수1번);
		
		// ---------------------------------------
		
		/* 상수(항상 같은 수)
		 * 
		 * - 변수의 한 종류
		 * - 한번 값이 대입되면 다른 값을 대입할 수 없음
		 * - 자료형 앞에 final 키워드 작성 (마지막 대입되는 값)
		 * - 모두 대문자 , 여러 단어 작성 시 "_" 사용
		 * 
		 * - 상수를 사용하는 경우
		 * 1) 변하면 안되는 고정된 값을 저장할 때
		 * 2) 특정한 값에 의미를 부여하는 경우
		 * 
		 * */
		
		int number = 5;
		System.out.println("number : " + number); // 5
		
		number = 7;
		System.out.println(" number : " + number); // 7
		
		
		// ----------------------------------------------
		
		final double PI_VALUE = 3.14;
		// PI_VALUE = 1.23; 에러(대입 불가)
		
		final int LEFT_MOVE = -1;
		final int RIGHT_MOVE = 1;
		
	
		
		
		
	}

	
	
}
