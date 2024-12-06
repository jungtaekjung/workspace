package edu.kh.control.loop;

import java.util.Scanner;

public class LoopExample1 {

	// ctrl + A == 전체선택 -> ctrl + I == 전체정렬
	
	/* for문
	 * - 끝이 정해져 있는 (횟수가 지정되어 있는) 반복문
	 * 
	 * [작성법]
	 * for(초기식; 조건식; 증감식){
	 * 		조건이 true일 경우 반복 수행할 코드;
	 * }
	 * 
	 * - 초기식 : for문을 제어하는 용도의 변수 선언
	 * 
	 * - 조건식 : for문의 반복 여부를 지정하는 식
	 * 			보통 초기식에 사용된 변수를 이용하여 조건식을 작성함
	 * 
	 * - 증감식 : 초기식에 사용된 변수를
	 * 			for문이 끝날 때 마다 증가 또는 감소 시켜
	 * 			조건식의 결과를 변하게 하는 식
	 * 
	 */

	// for 예제 1 - 안녕하세요 10번 출력하기
	public void ex1() {
		// 	   1.초기식;  2.조건식;  3.증감식
		for(int i = 1; i <= 10; i++) {
			System.out.println("안녕하세요");
		}

		// * for문 해석 순서 *
		// 1) 초기식 (반복을 세는 용도의 변수)
		// 2) 조건식 (초기식에 사용된 변수의 값에 따라 반복 여부 결정)
		// 3) for문 내부 코드
		// 4) 증감식 (초기식 변수에 값을 변화시킴)

		// 2) ~ 4) 반복 (조건식이 false일 때 까지 반복)
	}

	// for문 예제 2 - 1부터 10까지 출력하기
	public void ex2() {

		// 초기식에는 보통 시작하려는 숫자를 대입
		for(int i = 1; i <= 10; i++) {
			System.out.println(i);
		}
	}

	// for 예제 3 - 3부터 7까지 출력하기
	public void ex3() {
		for(int i = 3; i <= 7; i++) {
			System.out.println(i);
		}


	}

	// for 예제 4 - 2 부터 20까지 2씩 증가하며 출력
	public void ex4() {
		// i = i + 2 <- 누적대입
		// i += 2 <- 복합연산자

		for(int i = 2; i <= 20; i += 2) {
			System.out.println(i);
		}

	}

	// for 예제 5 - 1부터 5까지 0.5씩 증가하며 출력
	public void ex5() {
		for(double i = 1; i <= 5; i += 0.5) {
			System.out.println(i);
		}

	}

	// for 예제 6 - 영어 알파벳 A부터 Z까지 한 줄로 출력
	public void ex6() {

		// * char 자료형은 문자형이지만 실제로는 정수(문자표 번호)를 저장한다!
		for(int i = 'A'; i <= 'Z'; i ++) {
			System.out.print((char)i);
		}
		System.out.print("\n----------------------------------\n");

		for(char i = 'A'; i <= 'Z'; i++) {
			System.out.print(i);
		}
	}

	// for 예제 7 - 1부터 입력한 수까지 출력하기
	public void ex7() {

		Scanner sc = new Scanner(System.in);
		System.out.print("정수 입력 : ");
		int input = sc.nextInt();

		for(int i = 1; i <= input; i++) {
			System.out.println(i);

		}
	}

	// for 예제 8 - 1부터 10까지 정수의 합 구하기
	public void ex8() {

		int sum = 0; // 합계를 저장할 변수
		// 0으로 초기화 하는 이유 : 0은 더해도 영향이 없기 때문

		for(int i = 1; i <= 11; i++) {
			sum = + i; // i값을 sum에 누적
			//sum += i;
		}
		System.out.println("합계 : " + sum);


	}

	// for 예제 9 - 50부터 입력 받은 수 까지 모든 정수의 합 출력
	public void ex9() {

		Scanner sc = new Scanner(System.in);

		System.out.print("정수 입력 : ");
		int input = sc.nextInt();

		int sum = 0;

		if(input >= 50) {
			for(int i = 50; i <= input; i ++) {
				sum += i; // sum에 i값 누적
			}
		}else {
			for(int i = 50; i >= input; i--) {
				sum += i;
			}
		}

		System.out.println("50부터" + input + "까지의 햡: " + sum);
	}
	// for 예제 10 - 정수 5개를 입력 받아서 합계 구하기

	// ex)
	// 입력 1 : 10
	// 입력 2 : 15
	// 입력 3 : 20
	// 입력 4 : 25
	// 입력 5 : 30
	// 합계 : 100

	public void ex10() {

		Scanner sc = new Scanner(System.in);

		int sum = 0;

		for(int i = 0; i < 5; i++) {
			System.out.print("입력 " + (i+1) + " : ");
			int input = sc.nextInt();

			sum += input; // sum에 입력 받은 input 값 누적

			//System.out.print(input);
			// {} 안에서 생성된 변수는 {} 밖에서는 사용 X
			// -> {} 끝나는 시점에 사라지기 때문에
		}

		// System.out.println(input); // 에러

		System.out.println("합계 : " + sum);
	}

	// for 예제 11 - 정수를 몇 번 입력 받을지 먼저 입력 받고
	//				입력된 정수의 합계를 출력

	// ex)
	// 입력 받을 정수의 개수 : 3
	// 입력 1 : 10
	// 입력 2 : 20
	// 입력 3 : 30
	// 합계 : 60

	// ex)
	// 입력 받을 정수의 개수 : 2
	// 입력 1 : 10
	// 입력 2 : 20
	// 합계 : 30

	public void ex11() {

		Scanner sc = new Scanner(System.in);

		System.out.print("입력 받을 정수의 개수 : ");
		int number = sc.nextInt();

		int sum = 0;
		for(int i = 1; i <= number; i++) {
			System.out.print("입력 " + i + " : ");
			int input = sc.nextInt();

			sum += input;
		}

		System.out.println("합계 : " + sum);


	}

	// for 예제 12 - 시작 , 끝 , 증가할 수를 입력 받아
	//				지정된 범위까지 모두 출력 후
	//				합계도 출력

	// ex)
	// 시작 : 1
	// 끝 : 5
	// 증가할 수 : 2
	// 1 3 5
	// 합계 : 9

	public void ex12() {

		Scanner sc = new Scanner(System.in);

		System.out.print("시작 : ");
		int start = sc.nextInt();

		System.out.print("끝 : ");
		int end = sc.nextInt();

		System.out.print("증가할수 : ");
		int step = sc.nextInt();

		int sum = 0;

		for(int i = start; i <= end; i +=step) {
			System.out.print(i + " ");
			sum += i;
		}

		System.out.println("\n합계 :" + sum);

	}

	// for 예제 13 - 1부터 10까지 모든 정수 출력
	//				단, 짝수는 [] 감싸서 출력

	// 1 [2] 3 [4] 5 [6] 7 [8] 9 [10]

	public void ex13() {

		for(int i = 1; i <= 10; i++) {

			if (i % 2 == 0) { // i가 짝수인 경우
				System.out.printf("[%d] ", i);

			}else { // i가 홀수인 경우
				System.out.printf("%d ", i);
			}
		}

	}

	// for 예제 14 - 1부터 10까지 모든 정수 출력
	//				단, 홀수는 () 감싸서 출력
	//				+ 1은 "시작" 10은 "끝" 이라고 출력

	// 시작 2 (3) 4 (5) 6 (7) 8 (9) 끝

	public void ex14() {


		System.out.print("시작 ");

		for(int i=2; i<=9; i++) {
			if(i % 2 == 0) { // 짝수
				System.out.printf("%d ", i);
			}else { //홀수
				System.out.printf("(%d) ", i);
			}
		}
		System.out.println("끝");



		/*
		for(int i = 1; i <= 10; i++) {
			if(i == 1) {
				System.out.print("시작 ");

			}else if (i == 1) {
				System.out.print("끝");

			}else if (i % 2 != 0) {
				System.out.printf("(%d)", i);

			}else { //홀수
				System.out.printf("%d", i);
			}

		}
		 */
	}

	// for 예제 15 - 1 ~ 100 사이 수 중
	//				입력 받은 수의 배수를 제외한 나머지 수의 합을 출력
	
	// ex)
	// 제외할 배수 입력 : 4
	// 합계 : 1 2 3 5 6 7 ... 99 (4의 배수를 제외한 합)
	
	public void ex15() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("제외할 배수 입력 : ");
		int number = sc.nextInt();
		
		int sum = 0;
		for(int i = 1; i <= 100; i++) {
			
			if(i % number != 0) { // number의 배수가 아닌 경우
				sum += i;
			}
		}
		System.out.println("합계 : " + sum);
	}
	
	
	
	
	
	

}

