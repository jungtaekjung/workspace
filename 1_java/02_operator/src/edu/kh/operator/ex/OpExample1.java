package edu.kh.operator.ex;

public class OpExample1 {

	// System.out.println(); 자동완성
	// syso, sysout -> ctrl + spacebar
	
	public static void main(String[] args) {
		
		/* 증감 연산자 : ++ / -- 
		 * - 피연산자(값)를 1씩 증가 또는 감소 시키는 연산자
		 *   (피연산자 : 연산을 당하는 변수나 값)
		 * 
		 * 전위 연산 : 연산자가 앞쪽에 배치 ex) ++3, --2
		 * -> 다른 연산자보다 먼저 증가/감소
		 * 
		 * 후위 연산 : 연산자가 뒤쪽에 배치 ex) 5++, 4--
		 * -> 다른 연산자보다 나중에 증가/감소
		 * */
		
		System.out.println("[전위 연산]");
		
		System.out.println("증가 연산 테스트");
		int num = 10;
		
		System.out.println(num); // 10
		System.out.println(++num); // 11
		System.out.println(++num); // 12
		
		System.out.println("감소 연산 테스트");
		
		System.out.println(--num); // 11
		System.out.println(--num); // 10
		System.out.println(--num); // 9
		
		System.out.println("----------------------------------");
		
		System.out.println("[후위 연산]");
		// ** 후위 연산은 우선 순위가 제일 마지막이다! **
		
		System.out.println("증가 연산 테스트");
		
		int num2 = 30;
		System.out.println(num2); // 30
		System.out.println(num2++); // 30 출력 후 31로 증가
		System.out.println(num2++); // 31 출력 후 32로 증가
		System.out.println(num2); // 32
		
		System.out.println("감소 연산 테스트");
		
		System.out.println(num2--); // 32 출력 후 31로 감소
		System.out.println(num2--); // 31 출력 후 30로 감소
		System.out.println(num2); // 30
		
		System.out.println("----------------------------------");
		
		// 연습 문제
		int a = 3; // 4
		int b = 5; // 4
		
		int c = a++ + --b; //7
				
		// 최종적으로 a, b, c는 각각 얼마인가 ?
		System.out.printf("a = %d, b = %d, c = %d", a, b, c);
		
		
		
		
	}
}
