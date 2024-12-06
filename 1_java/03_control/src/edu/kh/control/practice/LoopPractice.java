package edu.kh.control.practice;

import java.util.Scanner;

public class LoopPractice {

	public void practice1() {

		Scanner sc = new Scanner(System.in);

		System.out.print("1이상의 숫자를 입력하세요 : ");
		int input = sc.nextInt();

		if(input<1) {
			System.out.print("1이상의 숫자를 입력해주세요");
		}else {	
			for(int i=1;i<=input;i++) {
				System.out.print(i+" ");
			}
		}
	}





	public void practice2() {

		Scanner sc = new Scanner(System.in);
		System.out.print("1이상의 숫자를 입력하세요 : ");
		int input = sc.nextInt();

		if(input<1) {
			System.out.println("1이상의 숫자를 입력해주세요.");
		}else {

			for(int i=input;i>=1;i--) {
				System.out.print(i+" ");
			}
		}

	}		
	public void practice3() {

		Scanner sc = new Scanner(System.in);

		System.out.println("정수를 하나 입력하세요 : ");
		int input = sc.nextInt();
		int sum=0;
		for(int i=1;i<=input;i++) {

			if(i==input) { //마지막 바퀴
				System.out.print(i+"=");
			}else {
				System.out.print(i+"+");
			}
			sum+=i;
			System.out.print(i+"+");   ///+를 지우는 법 
		}
		System.out.println(sum);
	}

	public void practice4() {

		Scanner sc = new Scanner(System.in);

		System.out.print("첫 번째 숫자 : ");
		int num1 = sc.nextInt();

		System.out.print("두 번째 숫자 : ");
		int num2 = sc.nextInt();

		if(num1<1||num2<1) {
			System.out.println("1 이상의 숫자를 입력해주세요.");

		} else if(num1>num2) {
			for(;num2<=num1;num2++) {
				System.out.print(num2+" ");
			}	
		} else{
			for(;num1<=num2;num1++) {
				System.out.print(num1+" ");
			}

		}
	} 	// if(input1>input2)
	//	int temp = input1;
	//	input1 = input2;
	//	input2 = temp;


	public void practice5() {

		Scanner sc = new Scanner(System.in);

		System.out.print("숫자 : ");
		int dan = sc.nextInt();

		System.out.println("====="+dan+"단"+"=======");
		for(int i=1;i<=9;i++) {
			System.out.printf("%2d*%2d = %2d\n",dan,i,dan*i);
		}



	}

	public void practice6() {

		Scanner sc = new Scanner(System.in);

		System.out.print("숫자 : ");
		int dan = sc.nextInt();
		if(dan<=1||dan>9) {
			System.out.println("2~9 사이 숫자만 입력해주세요."); //8
		}else {
			for(int x = dan; x<=9;x++) {
				System.out.println("========="+x+"단"+"========");

				for(int i=1;i<=9;i++) {
					System.out.printf("%d * %d = %d\n",x,i,x*i);
				}

			}
		}
	}

	public void practice7() {

		Scanner sc = new Scanner(System.in);

		System.out.print("정수 입력 : ");
		int input = sc.nextInt();
		for(int x =1;x<=input;x++) {
			for(int y =1; y<=x;y++){
				System.out.print("*");
			}
			System.out.println();
		}
	}	
	public void practice8() {

		Scanner sc = new Scanner(System.in);

		System.out.print("정수 입력 : ");
		int input = sc.nextInt();
		for(int x = input;x>=1;x--) {
			for(int y = 1;y<=x;y++) {
				System.out.print("*");
			}
			System.out.println();
		}

	}
	public void practice9() {

		Scanner sc = new Scanner(System.in);

		System.out.print("정수 입력 : ");
		int input =sc.nextInt();

		for(int x=1;x<=input;x++) {


			// * 1개 출력 전에 띄어쓰기 3번
			// * 2개 출력 전에 띄어쓰기 2번
			// * 3개 출력 전에 띄어쓰기 1번
			// * 4개 출력 전에 띄어쓰기 0번

			/*
			for(int i=1; i <=input-x; i++) {
				System.out.print(" ");
			}
			for(int i=1;i<=x;i++) {
				System.out.print("*");
			}
			System.out.println();
			 */


			//2 ) for +if else 
			for(int i=1;i<=input; i++) {

				if(i<=input-x) {
					System.out.print(" ");
				}else {
					System.out.print("*");
				}
			}
			System.out.println();

		}	

	}

	public void practice10() {

		Scanner sc = new Scanner(System.in);
		System.out.print("정수 입력 : ");
		int input =sc.nextInt();

		//위쪽 삼각형
		for(int x=1;x<=input;x++) {
			for(int y=1; y<= x;y++) {
				System.out.print("*");
			}
			System.out.println(); // 줄바꿈
		}
		// 아랫쪽 삼각형
		for(int y=input-1;y>=1;y--) {
			for(int i=1;i<=y;i++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
	public void practice11() {

		Scanner sc = new Scanner(System.in);
		System.out.print("정수 입력 : ");
		int input = sc.nextInt();

		for (int i = 1; i <= input; i++) { //입력 받은 input만큼 줄 출력
			for (int j = input-i; j>=1; j--) { //공백 출력
				System.out.print(" ");
			}
			//* 출력 for문
			// 1 3 5 7 9
			for (int j = 1; j <= i * 2 - 1; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

	public void practice12() {

		Scanner sc = new Scanner(System.in);
		System.out.print("정수 입력 : ");
		int input = sc.nextInt();

		// row : 행(줄)
		// col(column) : 열(칸)

		for(int row =1;row<=input;row++) { //행


			for(int col=1;col<=input;col++) {//열
				// 테두리만 * 출력
				// row또는 col 1 또는 input인 경우

				if(row==1||row==input||col==1||col==input) {
					System.out.print("*");
				} else { //내부
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}

	public void practice13() {

		Scanner sc = new Scanner(System.in);

		System.out.print("자연수 하나를 입력하세요 : ");
		int input = sc.nextInt();
		int count = 0;

		for(int i =1;i<=input;i++) {
			// i가 2의 배수 또는 3의 배수
			if(i%2==0||i%3==0) {
				System.out.print(i+" ");
				if(i%2==0&&i%3==0) {
					count++;
				}
				//2와 3의 공배수인 경우
			}
		}
		System.out.println("\ncount: "+ count);
	}

	
			
			
		
	

}

















