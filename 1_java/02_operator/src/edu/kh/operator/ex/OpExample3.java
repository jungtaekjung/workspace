package edu.kh.operator.ex;

import java.util.Scanner;

public class OpExample3 {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
	/* [배수 확인 프로그램]
	 * 확인할 수 : 15
	 * 배수 : 7
	 * 
	 * 15는 7의 배수 입니까? false
	 * 
	 * ---------------------------------
	 * [배수 확인 프로그램]
	 * 확인할 수 : 9
	 * 배수 : 3
	 * 
	 * 9는 3의 배수 입니까? true
	 * 
	 * */
		
		
		System.out.println("[배수 확인 프로그램]");
		
		System.out.print("확인할 수 : ");
		int num1 = sc.nextInt();
		
		System.out.print("배수 : ");
		int num2 = sc.nextInt();
		
		System.out.printf("%d는 %d의 배수 입니까?: %b \n", num1, num2, num1 % num2 == 0 );
		
		
		
		
}

}