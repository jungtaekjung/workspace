package edu.kh.operator.ex;

public class OpExample2 {

	public static void main(String[] args) {
		/* 비교 연산자
		 *  >   <    >=  <=   ==    !=
		 * 초과 미만  이상  이하  같음  같지않음
		 * 
		 * - 복합 기호에서 등호(=)는 항상 오른쪽
		 *  
		 *  - 비교 연산의 결과는 논리값(true/false)
		 * */
		
		int a = 10;
		int b = 20;
		
		System.out.println(a == b); // false
		System.out.println(a != b); // true
		
		System.out.println(a <= b - 20); // false
		
		System.out.println(b <= a + a); // true
		
		System.out.println(a < b == true); // true
		
		System.out.println(a > b != false); // false
		
		System.out.println("------------------------------------");
		
		System.out.println("[짝수 검사]");
		// 짝수 == 2로 나누었을 때 나머지가 0인 수
		//	   == 2로 나누었을 때 나머지가 1이 아닌 수
		
		System.out.printf("3은 짝수입니까? %b \n", 3 % 2 == 0);
		System.out.printf("4는 짝수입니까? %b \n", 4 % 2 == 0);
		System.out.printf("6는 짝수입니까? %b \n", 6 % 2 != 1);
		
		System.out.println("[홀수 검사]");
		// 홀수 == 2로 나누었을 때 나머지가 1인 수
		//	   == 2로 나누었을 때 나머지가 0이 아닌 수
		
		System.out.printf("12345는 홀수입니까? %b \n", 12345 % 2 == 1);
		System.out.printf("5432는 홀수입니까? %b \n", 5432 % 2 != 0);
		
		System.out.println("-------------------------------");
		
		System.out.println("[3의 배수 검사]");
		// 3의 배수 == 3으로 나눴을 때 나머지가 0인 수
		
		System.out.printf("123123은 3의 배수입니까? %b \n", 123123 % 3 == 0);
		
		
		
		
		
		
		
	}
	
}
