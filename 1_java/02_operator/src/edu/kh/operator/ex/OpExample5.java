package edu.kh.operator.ex;

import java.util.Scanner;

public class OpExample5 {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner (System.in);
		
		System.out.println("[홀짝 검사기]");
		
		System.out.print("정수를 입력하세요 : ");
		int input = sc.nextInt();
		
		// 삼항 연산자
		// 조건식 (true/false) ? true인 경우 : false인 경우;
		
		String result = input % 2 == 0 ? "짝수" : "홀수" ;
		// 조건식이 true이면 "짝수"
		// 조건식이 false이면 "홀수"
		// 를 result 변수에 저장
		
		System.out.println(result);
		
		System.out.println("--------------------------");
		
		// 입력 받은 정수가 음수인지 양수인지 구분
		// 단, 0은 양수로 처리
		
		// ex)
		// 정수 입력 : 5
		// 양수 입니다.
		
		// 정수 입력 : -5
		// 음수 입니다.
		
		System.out.print("정수 입력 : ");
		int num = sc.nextInt();
		
		String result2 = num >= 0 ? "양수 입니다." : "음수 입니다.";
		System.out.println(result2);
		
		
		
		
	}

}
