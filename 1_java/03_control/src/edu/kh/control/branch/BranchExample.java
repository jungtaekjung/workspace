package edu.kh.control.branch;

import java.util.Scanner;

public class BranchExample {


	//분기문
	// break : (가장 가까운) 반복문을 멈춤
	// continue : (가장 가까운)반복문의 시작 부분으로 이동(다음 반복 진행)


	public void ex1() {
		for(int i=1;i<=10000;i++) {
			// 홀수인 경우 출력 x
			// i가 20인 경우 반복을 멈춤
			if(i%2==1) {
				continue;
			}
			System.out.print(i+" ");
			//i가 20인 경우 반복을 멈춤
			if(i==20) {	
				break;
			}
		}
	}
	//1~100까지 1씩 증가하며 출력하는 반복문
	// 단, 5의 배수는 건너뛰고
	// 증가하는 값이 40이 되었을 때 반복을 멈춤

	public void ex2() {

		for(int i=1;i<=100;i++) {

			if(i==40) {
				break;
			}
			if(i%5==0) {
				continue;
			}
			System.out.print(i+" ");
		}
	}
	// 중첩 반복문 내부에서 break 사용하기

	//구구단 2~9단까지 모두 출력
	//단, 2단은 x2까지, 3단은 x3까지 .....9단은 x9까지만 출력
	public void ex3() {
		for(int x = 2;x<=9;x++) {
			for(int y=1;y<=9;y++) {
				System.out.printf("%d X %d = %d ",x,y,x*y);

				if(x==y) { //단과 곱해지는 수가 같을경우
					break;
					//분기문이 중첩 반복문 내에서 사용되면
					//가장 가까운 반복문에 작용!
				}
			}
			System.out.println();
		}
	}

	// col이 3의 배수인 경우 출력x           
	// row가 3일 때 반복 종료
	public void ex4() {
		for(int row=1;row<=6;row++) {
			if(row==3) { // row가 3일 때 반복 종료
				break;
			}
			for(int col=1;col<=10;col++) {
				if(col%3==0) { // col이 3의 배수인 경우 출력x  
					continue;
				}
				System.out.printf("(%d, %d)",row,col);
			}
			System.out.println();
		}
	}

	//0이 입력될 때 까지 모든 정수의 합 구하기
	public void ex5() {
		Scanner sc = new Scanner(System.in);

		int sum=0;
		int input=0;

		//방법 1. while(input!=0) { // 대신 input에 0이 아닌 다른값
		//방법 2. do-while

		//방법 3. while(무한루프) + break	

		while(true) { //무한 반복(무한 루프)
			System.out.print("정수 입력 : ");
			input = sc.nextInt();

			if(input ==0) {
				break; //0 입력 시 반복문 종료
			}
			sum+=input;
		}

		System.out.println("합계 : "+sum); 
		// Unreachable code : 도달할 수 없는 코드

	}

	public void upDownGame() {

		Scanner sc = new Scanner(System.in);

		//Math.random() : 0.0이상 1.0 미만의 난수를 반환
		int answer =(int)(Math.random()*100+1); // 1~100
		//	System.out.println(answer); //답안 임시 확인

		int count=1; //입력 횟수 저장용 변수


		while(true) { // 언제 끝날지 모르니 무한 반복
			System.out.print(count+"번째 입력 : ");
			int	input = sc.nextInt();

			//잘못 입력된 경우
			if(input<1||input>100) {
				System.out.println("1~100 사이의 수를 입력해주세요\n");
				continue;
			}

			//제대로 입력한 경우
			if(input<answer)	{ //입력한 값이 작은 경우
				System.out.println("UP");

			}else if(input>answer) { //입력값이 정답보다 큰 경우
				System.out.println("DOWN");

			}else {
				System.out.println("[정답!!!]");
				System.out.printf("총 입력 횟수 : %d회",count);
				break; //while문 반복종료
			}
			count++;
		}
	}

	//입력 받은 모든 문자열을 누적
	// 단, "end" 입력 시 문자열 누적을 종료하고 결과 출력

	public void ex6() {

		Scanner sc = new Scanner(System.in);

		String str=""; // 빈 문자열

		while(true) {

			System.out.print("문자열 입력(end! 입력 시 종료) : ");
			String input = sc.nextLine();

			str +=input+"\n";
			// next() : 다음 한 단어(띄어쓰기 포함X/띄어쓰기, 엔터를 만나면 입력종료)만 얻어옴
			// nextLine() : 다음 한 줄(띄어쓰기 포함O/엔터를 만나면 입력 종료)

			// ** next()로 문장을 작성 시 결과가 이상한 이유 **
			// 1) next()는 한 단어만 읽어옴
			// 2) 입력 -> 입력 버퍼에 저장 -> nextXXX() 통해 버퍼 내용을 읽어옴

			//*next(),nextInt(),nextDouble() 등
			//	모두 입력 버퍼에서 (엔터)를 제외하고 내용만 읽어옴
			//-> 이후 nextLine() 사용 시 입력받지 못하고 종료됨
			//-> why? 입력 버퍼에 남아있는 (엔터)를 읽었기 때문

			//[해결 방법] 
			//입력을 위한 nextLine() 수행 전 
			//입력 버퍼에서 (엔터)를 제거
			// -> 빈 공간에 sc.nextLine() 구문을 한번 작성하면 (엔터)가 제거됨
			System.out.println(str);


			// 입력 받는 문자열이 "end"면 반복 종료
			//if(input == "end!")  X
			if(input.equals("end!")) {
				//String 자료형은 비교 연산자(==)로 같은 문자열인지 판별 X

				//비교 연산자는 보통 기본 자료형끼리 연산에만 사용 가능
				// ->String은 기본 자료형이 아닌 참조형

				//**[해결 방법] : 문자열1.equals(문자열2)**
				break;
			}
		}
	}
	// 가위 바위 보 게임

	// 몇판? : 3


	public void RPSGame() {


		// 1번째 게임
		// 가위/바위/보 중 하나를 입력 해주세요 :  가위
		// 컴퓨터는 [보]를 선택했습니다.
		// 플레이어 승!
		// 현재 기록 : 1승 0무 0패

		// 2번째 게임
		// 가위/바위/보 중 하나를 입력 해주세요 :  보
		// 컴퓨터는 [보]를 선택했습니다.
		// 비겼습니다.
		// 현재 기록 : 1승 1무 0패

		// 3번째 게임
		// 가위/바위/보 중 하나를 입력 해주세요 :  가위
		// 컴퓨터는 [바위]를 선택했습니다.
		// 졌습니다ㅠㅠ
		// 현재 기록 : 1승 1무 1패
		Scanner sc = new Scanner(System.in);

		int rps = (int)(Math.random()*4);
		int count = 1;
		int count1=0;
		int count2=0;
		int count3=0;
		String result = null; //참조하는 게 없다 null : 아무것도 참조하고 있지 않음
		
		switch(rps) {
		case 1: result = "가위";break;
		case 2: result = "바위";break;
		case 3: result = "보";break;
		//default : System.out.println();// 값 초기화 하면 default 안적어도됨
		}
		//System.out.println(result);
		while(true) {
			System.out.println(count+"번째 게임\n");
			System.out.println("가위/바위/보 중 하나를 입력 해주세요 : ");
			String str = sc.next();
			int rps2=0;
			
			switch(rps2) {
			case 1: str = "가위";break;
			case 2: str = "바위";break;
			case 3: str = "보";break;
			//default : str = "";break; // 값 초기화 하면 default 안적어도됨
			}
			if(rps==rps2) {
				System.out.println("비겼습니다.");
				count1++;
			}else if((rps==1&&rps2==3)||(rps==2&&rps2==1)||(rps==3&&rps2==2)) {
				System.out.println("플레이어 승!");
				count2++;
			}else {
				System.out.println("졌습니다 ㅠㅠ");
				count3++;
				if(str.equals("끝!")) {
					break;

				}


			}
			System.out.printf("컴퓨터는 [%s]를 선택했습니다.\n",result);
			System.out.printf("현재 기록 : %d승 %d무 %d패\n",count2,count1,count3);

			count++;
		}
	} // 컴퓨터와 플레이어(기준) 가위 바위 보 판별

	
	
	//배열 선언 : 메모리에 배열을 참조하는 변수 공간을 할당
	//			(값 직접 저장X, 배열의 주소를 저장)
	
	//배열 할당 : 실제 값을 저장할 수 있는 배열을 메모리에 생성
	
	
	
	
}








