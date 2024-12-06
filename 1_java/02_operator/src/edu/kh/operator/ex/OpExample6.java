package edu.kh.operator.ex;

import java.util.Scanner;

public class OpExample6 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[홀짝 검사기 v2]");
		
		System.out.println("정수를 입력하세요 : ");
		int input = sc.nextInt();
		
		// 짝수, 홀수, 0입니다
		String result = input == 0 ? "0입니다" : (input % 2 == 0 ? "짝수" : "홀수");
		
		System.out.println(result);
		
		
	}
	
}
