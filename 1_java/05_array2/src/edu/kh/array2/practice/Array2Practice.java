package edu.kh.array2.practice;

import java.util.Arrays;
import java.util.Scanner;

public class Array2Practice {


	public void practice1() {

		String[][] arr = new String[3][3];
		for(int row=0;row<arr.length;row++) { //행 반복
			for(int col=0;col<arr[row].length;col++) { //열 반복
				arr[row][col] = "("+row+","+col+")";
				System.out.print(arr[row][col]);
			}
			System.out.println();
		}
	}

	public void practice2() {
		int[][] arr = new int[4][4];
		int count=1;
		for(int row=0;row<arr.length;row++) { // 행 반복(0~3)
			for(int col=0;col<arr[row].length;col++) {// 열 반복(0~3)
				arr[row][col]= count++;
				System.out.printf("%3d",arr[row][col]);
			}
			System.out.println();
		}
	}
	public void practice3() {
		int[][] arr = new int[4][4];
		int count = 16;
		for(int row =0; row<arr.length;row++) {
			for(int col=0;col<arr[row].length;col++) {
				arr[row][col]=count--;
				System.out.printf("%3d",arr[row][col]);

			}
			System.out.println();
		}

	}
	public void practice4() {
		int[][] arr=new int[4][4];

		final int ROW_LAST_INDEX = arr.length-1; //행 마지막 인덱스
		final int COL_LAST_INDEX = arr[0].length-1; //열 마지막 인덱스



		for(int row = 0;row<arr.length;row++) { //행 반복

			for(int col=0;col<arr[row].length;col++) {//열 반복

				// 마지막행, 마지막 열이 아닌 경우
				if(row!=ROW_LAST_INDEX&&col!=COL_LAST_INDEX) {
					int random = (int)(Math.random()*10+1); //1~10 난수

					arr[row][col]=random;

					// 각 행의 마지막 열에 난수를 누적
					arr[row][COL_LAST_INDEX] += arr[row][col];

					//각 열의 마지막 행에 난수를 누적
					arr[ROW_LAST_INDEX][col]+= arr[row][col];

					//생성된 모든 난수를 마지막 행, 마지막 열에 누적
					arr[ROW_LAST_INDEX][COL_LAST_INDEX]+=arr[row][col];
				}
				System.out.printf("%4d",arr[row][col]);
			}//열 반복 끝
			System.out.println(); //줄 바꿈
		}//행 반복 끝
	}

	public void practice5() {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.print("행 크기 : ");
			int input1 = sc.nextInt();
			System.out.print("열 크기 : ");
			int input2 = sc.nextInt();
			if((input1<1||input1>10||input2<1||input2>10)) {
				System.out.println("반드시 1~10사이의 정수를 입력해야 합니다.");
			}else {
				char[][] arr = new char[input1][input2];

				for(int row=0;row<input1;row++ ) {
					for(int col=0;col<input2;col++) {
						int random =(int)(Math.random()*26+65);
						arr[row][col]=(char)random;
						System.out.printf("%3s",arr[row][col]);
					}
					System.out.println();
				}
			}
			break;
		}
	}
	public void practice6() {

		Scanner sc = new Scanner(System.in);
		System.out.print("행의 크기 : ");
		int input = sc.nextInt();


		char[][] arr =new char[input][];

		char ch = 'a';

		//열의 크기를 정하는 for문
		for(int i =0;i<arr.length;i++) {
			//for(col=0;col<arr[row].length;col++) {
			System.out.print(i+"열의 크기 : ");
			int	col=sc.nextInt();
			arr[i] = new char[col];
		}
		//출력용 for문
		for(int row=0;row<arr.length;row++) { //행 반복.
			for(int col=0; col<arr[row].length;col++) {//열 반복
				arr[row][col]=ch++;
				System.out.print(arr[row][col]);
			}
			System.out.println(); //줄바꿈
		}
	}


	public void practice7() {

		String[] students = {"강건강", "남나나", "도대담", "류라라", "문미미", "박보배", 
				"송성실", "윤예의", "진재주", "차천축", "피풍표", "홍하하"};


		String[][] arr = new String[3][2];
		String[][] arr2 = new String[3][2];

		int index=0; // student 배열에서 0부터 1씩 증가하며 학생들을 선택하는 용도의 변수


		System.out.println("===분단===");
		for(int row=0;row<arr.length;row++) {
			for(int col=0;col<arr[row].length;col++) {
				arr[row][col]=students[index];
				//students 배열에서 index번째 학생을 arr[row][col]에 대입

				index++;
				System.out.print(arr[row][col]+" ");
			}
			System.out.println();
		}
		System.out.println("===2분단===");
		for(int row=0;row<arr2.length;row++) {
			for(int col=0;col<arr2[row].length;col++) {
				arr2[row][col]=students[index];
				//students 배열에서 index번째 학생을 arr[row][col]에 대입

				index++;
				System.out.print(arr2[row][col]+" ");
			}
			System.out.println();
		}
	}

	public void practice8() {

		Scanner sc = new Scanner(System.in);

		String[] students = {"강건강", "남나나", "도대담", "류라라", "문미미", "박보배", 
				"송성실", "윤예의", "진재주", "차천축", "피풍표", "홍하하"};
		System.out.println("== 1분단 ==");
		String[][] arr1 = new String[3][2];
		String[][] arr2 = new String[3][2];

		int count=0;

		for(int row = 0;row<arr1.length;row++) {
			for(int col=0;col<arr1[row].length;col++) {
				arr1[row][col]=students[count];
				count++;
				System.out.print(arr1[row][col]+ " ");
			}
			System.out.println();
		}
		System.out.println("== 2분단 ==");
		for(int row = 0;row<arr2.length;row++) {
			for(int col=0;col<arr2[row].length;col++) {
				arr2[row][col]=students[count];
				count++;
				System.out.print(arr2[row][col]+ " ");
			}
			System.out.println();
		}
		System.out.println("===================================");
		System.out.print("검색할 학생 이름을 입력하세요 : ");
		String input = sc.nextLine();
		for(int row=0;row<arr1.length;row++) {
			for(int col=0;col<arr1[row].length;col++) {
				if(input.equals(arr1[row][col])) {
					if(col==0) {
						System.out.printf("검색하신 %s 학생은 1분단 %d번째 줄 왼쪽에 있습니다.",input,row+1);
					}else {
						System.out.printf("검색하신 %s 학생은 1분단 %d번째 줄 오른쪽에 있습니다.",input,row+1);
					}
				}
			}
		}
		for(int row=0;row<arr2.length;row++) {
			for(int col=0;col<arr2[row].length;col++) {
				if(input.equals(arr2[row][col])) {
					if(col==0) {
						System.out.printf("검색하신 %s 학생은 1분단 %d번째 줄 왼쪽에 있습니다.",input,row+1);
					}else {
						System.out.printf("검색하신 %s 학생은 1분단 %d번째 줄 오른쪽에 있습니다.",input,row+1);
					}

				}
			}
		}
	}
	public void practice9() {

		Scanner sc = new Scanner(System.in);

		String[][] arr = new String[6][6];
		System.out.print("행 인덱스 입력 : ");
		int rowIndex = sc.nextInt();

		System.out.print("열 인덱스 입력 : ");
		int colIndex = sc.nextInt();

		int num1= 0;
		int num2=0;

		for(int row = 0;row<arr.length;row++) { // row = 0,1,2,3,4,5

			for(int col=0;col<arr[row].length;col++) { // col = 0,1,2,3,4,5

				if(row==0&& col!=0) {
					arr[row][col] = num1++ +" ";
					//숫자 + 문자열 = 문자열(이어쓰기됨)

				}else if(col==0&&row!=0) {
					arr[row][col] = num2++ +" ";
				}else {
					arr[row][col] = " ";
				}
				arr[rowIndex+1][colIndex+1] = "X";
				System.out.print(arr[row][col]);
			}
			System.out.println();
		}
	}
	public void practice10(){

		Scanner sc = new Scanner(System.in);
		String[][] arr = new String[6][6];
		int input1 =0;
		int input2 =0;



		// 기본 실행화면 출력용 for문	


		for(int row=0;row<arr.length;row++) {
			for(int col=0;col<arr[row].length;col++) {
				if(row==0&&col!=0) {
					arr[row][col]= input1++ + " ";

				}else if(row!=0&&col==0) {
					arr[row][col]= input2++ +" ";
				}else {
					arr[row][col]= " ";
				}

				//System.out.print(arr[row][col]);
			}
			//	System.out.println();
		}


		while(true) {
			System.out.print("행 인덱스 입력 : ");
			int rowIndex = sc.nextInt();

			if(rowIndex==99) {
				System.out.println("\n프로그램 종료");
				break;
			}
			System.out.print("열 인덱스 입력 : ");
			int colIndex = sc.nextInt();
			arr[rowIndex+1][colIndex+1] = "X";
			// 화면에 보이는행, 열에 X값 저장

			for(int row=0; row<arr.length;row++) {
				for(int col=0; col<arr[row].length;col++) {
					System.out.print(arr[row][col]);

				}
				System.out.println();
			}

		}
	}

	public void practice11() {

		Scanner sc =new Scanner(System.in);

		System.out.print("빙고판 크기 지정 : ");
		int size = sc.nextInt();

		//1차원 배열로 빙고판에 입력될 값 생성 + 중복 제거
		int[] randomArr = new int[size*size];
		//-> 빙고판의 크기는 가로,세로의 곱 만큼의 공간이 필요
		//중복 제거하면서 랜덤값 집어넣기
		for(int i = 0;i<randomArr.length;i++) {
			randomArr[i] =(int)(Math.random()*(size*size)+1);

			//중복 제거 
			for(int x = 0;x<i;x++) {
				if(randomArr[i]==randomArr[x]) {
					i--;
					break;
				}
			}
		}
		// 위에서 만들어진 중복제거한 1차원 배열(randomArr)을 2차원 배열에 넣기
		// String 배열로 변경해서 진행
		// 왜? 빙고가 된 부분을 "★"로 변경하기 위해서
		String[][] bingoBoard=new String[size][size];

		int index=0; // 1차원 배열 인덱스 지정을 위한 변수 

		for(int i=0;i<bingoBoard.length;i++) {
			for(int j=0;j<bingoBoard.length;j++) {
				bingoBoard[i][j]=randomArr[index++]+"";

				//랜덤 배치된 빙고판 출력
				System.out.printf("%4s",bingoBoard[i][j]);
			}
			System.out.println();
		}
		System.out.println("=======빙고게임 시작========");
		while(true) {

			System.out.print("정수를 입력하시오 : ");
			String input = sc.next();
			
			boolean flag =true;// 검색된 값이 빙고판에 있는지 확인용 변수
			// flag == true : 값 X
			// flag == false : 값 O
			
			
			
			for(int i=0;i<size;i++) {
				for(int j=0;j<size;j++) {

					//입력된 값과 일치하는 곳을 ★로 변환
					if(input.equals(bingoBoard[i][j])) {
						bingoBoard[i][j]="★";
						flag=false;
					}
					System.out.printf("%3s",bingoBoard[i][j]);
				}
				System.out.println(); //줄 바꿈
			}
			
			if(flag) { //값 존재 X
					System.out.println("잘못 입력하셨습니다. 다시 입력하세요.");
					continue;
			}
			//빙고 검사

			//빙고 기준이 되는 문자열 생성
			//ex)4*4 크기 빙고  => 한 줄이 "★★★★"면 빙고

			String bingoLine = "";
			for(int i=0;i<size;i++) {
				bingoLine+="★";
			}
			int bingoCount=0; // 빙고 수를 저장할 변수

			//가로(행) 또는 세로(열)의 문자열을 더하여 하나의 문자열로 저장
			//	-> 저장된 문자열의 모양이 "★★★★"인 경우 == 빙고
			//	-> bingoCount 증가

			for(int i=0;i<bingoBoard.length;i++) {
				// 매 반복 시 마다 sum,sum1을 빈 문자열로 초기화
				// 왜? 바깥쪽 for문이 반복될 때 마다 검사하는 행과 열이 이동하므로
				//		빙고 여부를 검사하기 위한 sum,sum1을 빈문자열로 초기화 해야함
				String sum="";
				String sum1="";
				for(int j=0;j<bingoBoard.length;j++) {
					sum+=bingoBoard[i][j]; //현재 행의 문자를 모두 더함

					sum1+=bingoBoard[j][i]; // i,j(행렬)를 반대로 하여 열의 모든 문자를 더함
				}
				if(sum.equals(bingoLine)) { //가로 빙고가 존재할 경우
					bingoCount++;
				}

				if(sum1.equals(bingoLine)) { //새로 빙고가 존재할 경우
					bingoCount++;
				}
			}
			
			//대각선 빙고 여부
			//대각선 : diagonal
			
			//대각선은 빙고판에서 두 개만 존재
			// -> 대각선 문자를 더해 저장할 변수 두개 선언 및 빈문자열로 초기화
			
			String dia1Line ="";
			String dia2Line ="";
			
			for(int i =0;i<bingoBoard.length;i++) {
				dia1Line+=bingoBoard[i][i]; //좌상 우하
				dia2Line+=bingoBoard[i][bingoBoard.length-i-1]; //우상 좌하
			}
			if(dia1Line.equals(bingoLine)) {
				bingoCount++;
			}
			if(dia2Line.equals(bingoLine)) {
				bingoCount++;
				
			}
			
			
			//빙고 카운트 출력
			System.out.println("현재 "+bingoCount+"빙고");
			
			if(bingoCount>=3) { //빙고 개수가 3개 이상인 경우
				System.out.println("************BINGO**************");
				break;
			}
			
		}
	
	}









}
