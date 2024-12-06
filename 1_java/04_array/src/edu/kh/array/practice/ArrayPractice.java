package edu.kh.array.practice;

import java.util.Scanner;

public class ArrayPractice {

	public void practice1() {

		int[] arr = new int[9];
		int sum=0;
		for(int i =0;i<arr.length;i++) {
			arr[i] =i+1;
			System.out.print(arr[i]+" ");

			if(i%2==0) {
				sum+=arr[i];

			}
		}
		System.out.print("\n짝수 번째 인덱스 합 : "+sum);
	}



	public void practice2() {

		int[] arr= new int [9];
		int sum=0;
		for(int i=0;i<arr.length;i++) {
			arr[i]=arr.length-i;
			System.out.print(arr[i]+ " ");

			if(i%2!=0) {
				sum+=arr[i];
				System.out.print("\n홀수 번째 인덱스 합 :"+sum);
			}
		}
	}

	public void practice3() {
		Scanner sc = new Scanner(System.in);
		System.out.print("양의 정수");
		int input = sc.nextInt();
		int[] arr = new int[input];

		for(int i =0;i<arr.length;i++) {
			arr[i]=i+1;
			System.out.println(arr[i]+" ");
		}
	}

	public void practice4() {
		Scanner sc = new Scanner(System.in);

		int[] arr = new int[5];

		//while(true) {
		for(int i =0;i<arr.length;i++) {
			System.out.print("입력 "+ i +" : ");
			arr[i] = sc.nextInt();
		}
		for(int i =0;i<arr.length;i++) {
			System.out.println("검색할 값 : ");
			int input = sc.nextInt();
			if(input==arr[i]) {
				System.out.println("인덱스 "+i);
				break;
			}else {
				System.out.println("일치하는 값이 존재하지 않습니다.");
			}

		}
	}
	public void practice5() {

		Scanner sc = new Scanner(System.in);
		System.out.print("문자열 입력 : ");
		String input = sc.next();

		char[] arr = new char[input.length()];
		for(int i=0;i<arr.length;i++) {
			arr[i]= input.charAt(i);

		}
		System.out.println("문자 : ");
		char ch = sc.next().charAt(0);
		int count=0;
		int count2=0;

		for(int i = 0;i<arr.length;i++) {

			if(arr[i]==ch) {
				count2+=i;
				count++;
				System.out.print(count2);
			}
			System.out.printf("%s에 존재하는 %s가 존재하는 위치(인덱스) : ",input,ch);
			System.out.printf("\n%s의 개수 : %d",ch,count);
		}
	}
	public void practice6() {

		Scanner sc = new Scanner(System.in);
		System.out.print("정수 : ");
		int input = sc.nextInt();
		int[] arr= new int[input];
		int sum = 0;
		for(int i = 0;i<arr.length;i++) {
			System.out.print("배열 "+i+"번째 인덱스에 넣을 값 : \n");
			arr[i] = sc.nextInt();
		}
		for(int i =0;i<arr.length;i++) {
			System.out.print(arr[i]+" ");
			sum+=arr[i];

		}
		System.out.println("\n총 합 : "+ sum);
	}


	public void practice7() {

		Scanner sc = new Scanner(System.in);

		System.out.print("주민등록번호(-포함) : ");
		String input = sc.next();
		char[] ch = new char[input.length()];
		for(int i=0;i<ch.length;i++) {
			ch[i]=input.charAt(i);
			if(i>=8&&i<=13) {
				System.out.print("*");
			}else {
				System.out.print(ch[i]);
			}
		}
	}

	public void practice8() {
		Scanner sc = new Scanner(System.in);
		while(true) { //3 이상의 홀수가 입력 될 때까지 무한 반복
			System.out.print("정수 :");
			int input =sc.nextInt();
			if(input%2==0||input<3) { // 3 미만 또는 짝수인 경우 -> 반복
				System.out.print("다시 입력하세요");

			}else {
				//입력 받은 정수 만큼의 크기를 가지는 배열 생성	
				int[] arr = new int[input];

				int num =1; //arr 배열에 대입될 값
				for(int i =0;i<arr.length;i++) {
					if(i<=arr.length/2) { //중간 이전 까지 - > 증가
						arr[i]=num++;

					}else { //중간 이후-> 감소
						arr[i]=--num-1;
					}
					// 출력시 , 추가(단, 마지막 제외)
					if(i==arr.length-1) { //마지막 바퀴
						System.out.print(arr[i]);
					}else {
						System.out.print(arr[i]+", ");
					}
				}
				break; //while문 반복 종료
			}
		}
	}
	public void practice9() {
		int[] arr = new int[10];
		
		System.out.print("발생한 난수 : ");
		for(int i =0;i<arr.length;i++) {
			arr[i] = (int)(Math.random()*10+1);
			
			System.out.print(arr[i]+" ");
		}
	}
	
	
	
	
	
	
	
	
	
}

