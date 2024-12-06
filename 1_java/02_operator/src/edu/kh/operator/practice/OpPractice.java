package edu.kh.operator.practice;

import java.util.Scanner;

public class OpPractice {

	/* 나이를 키보드로 입력 받아서 어린이(13세 이하)인지,
	 * 청소년(13세 초과 ~ 19세 이하)인지, 
	 * 성인(19세 초과)인지 출력하시오.
	 * 
	 * [실행 화면]
	 * 나이 : 19
	 * 
	 * 청소년
	 * */
		
		public static void main(String[] args) {
			
			Scanner sc = new Scanner(System.in);
			
			
			System.out.println("나이 : ");
			int age = sc.nextInt();
			
			String result = age <= 13 ? "어린이" : (age <= 19 ? "청소년" : "성인");
			// age가 15인 경우
		    // 어린이를 찾는 조건(age <= 13)을 만족하지 못하므로
			// 이후에는 age가 13 이하인 경우를 범위에서 제외하면 된다.
			
			// -> 청소년을 찾는 조건을 age <= 19 이라고 작성해도
			// 이미 13 이하를 제외했기 때문에
			// age > 13 && age <= 19 조건과 동일한 조건이 성립한다.
			
			System.out.println(result);
			
			
			
			
			
		}
	
	
	
	
}
