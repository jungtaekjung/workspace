package edu.kh.control.practice;

import java.util.Scanner;

public class ControlPractice {


	// 실습문제 1
	// 키보드로 입력 받은 정수가 양수이면서 짝수일 때만 “짝수입니다.”를 출력하고
	// 짝수가 아니면 “홀수입니다.“를 출력하세요.
	// 양수가 아니면 “양수만 입력해주세요.”를 출력하세요.

	public void practice1() {

		Scanner sc = new Scanner(System.in);

		System.out.print("숫자를 한 개 입력하세요 : ");
		int number = sc.nextInt();

		/*	
		     String result; // 결과 저장용 변수
		      if(number > 0) { // 양수

		         if(number % 2 == 0) {
		            result = "짝수 입니다.";
		         }else {
		            result = "홀수 입니다.";
		         }

		      } else { // 양수 아님
		         result = "양수만 입력해주세요.";
		      }

		      System.out.println(result); 
		 */
		/*
		      if(number > 0) { // 양수

		         if(number % 2 == 0) {
		            System.out.println("짝수 입니다.");
		         }else {
		            System.out.println("홀수 입니다.");
		         }

		      } else { // 양수 아님
		         System.out.println("양수만 입력해주세요.");
		      }
		 */

		// if, else-if, else
		if(number > 0 && number % 2 == 0) { // 양수 + 짝수
			System.out.println("짝수 입니다.");
				
		} else if (number > 0 && number % 2 == 1) { // 양수 + 홀수
			System.out.println("홀수 입니다.");

		} else {
			System.out.println("양수만 입력해주세요");
		}
	}

	public void practice2(){

		Scanner sc = new Scanner(System.in);

		System.out.print("국어 점수 : ");
		int kor = sc.nextInt();

		System.out.print("수학 점수 : ");
		int eng = sc.nextInt();

		System.out.print("영어 점수 : ");
		int math = sc.nextInt();

		//합계
		int sum = kor + math + eng;

		//평균
		double avg = sum / 3.0;

		if(kor >= 40 && math >= 40 && eng >= 40 && avg >= 60) { // 합격
			System.out.println("국어 : " + kor);
			System.out.println("수학 : " + math);
			System.out.println("영어 : " + eng);
			System.out.println("합계 : " + sum);
			System.out.println("평균 : " + avg);

		}else { // 불합격
			System.out.println("불합격입니다.");
		}



	}

	public void practice3() {

		// 실습문제 3
		// 1~2 사이의 수를 입력 받아 해당 달의 일수를 출력하세요. (2월 윤달은 생각하지 않습니다.)
		// 잘못 입력한 경우 "OO월은 잘못 입력된 달입니다."를 출력하세요. (switch문 사용)

		// [실행화면 1]
		// 1~12 사이의 정수 입력:
		// 8월은 31일까지 있습니다.

		// [실행화면 2]
		// 1~12 사이의 정수 입력:
		// 99월은 잘못 입력된 달입니다.

		Scanner sc = new Scanner(System.in);

		System.out.print("1~12 사이의 정수 입력 : ");
		int month = sc.nextInt();

		int day = 0; // 해당하는 달의 마지막 일(날짜)을 저장하는 변수


		switch(month){
		case 1 : case 3: case 5 : case 7 : case 8: case 10: case 12: day=31; break;
		case 4 : case 6: case 9 : case 11 : day = 30; break;
		case 2 : day = 28; break;
		}

		if(day != 0) { // 1~12 사이가 입력 되었을 때
			System.out.printf("%d월은 %d일까지 있습니다.", month, day);

		}else { // 1~12 사이가 입력되지 않았을 때
			System.out.println(month + "월은 잘못 입력된 달입니다.");
		}


		//System.out.printf("%d월은 %d일까지 있습니다.", month, day); // month는 왼쪽 %d월에 , day는 %d일에 들어감 왼쪽은 왼쪽 오른쪽은 오른쪽

	}

	public void practice4() {

		/* 키, 몸무게를 double로 입력 받고 BMI지수를 계산하여 계산 결과에 따라
			 저체중/정상체중/과체중/비만을 출력하세요.

			 BMI = 몸무게 / (키(m) * 키(m))
			 BMI가 18.5미만일 경우 저체중 / 18.5이상 23미만일 경우 정상체중
			 BMI가 23이상 25미만일 경우 과체중 / 25이상 30미만일 경우 비만
			 BMI가 30이상일 경우 고도 비만

			 [실행 화면]
			 키(m)를 입력해주세요 :
			 몸무게(kg)를 입력해주세요 :
			 BMI 지수 : 21.45087235996327
			 정상체중
		 */

		Scanner sc = new Scanner(System.in);


		System.out.print("키(m)를 입력해 주세요 : ");
		double height = sc.nextDouble();

		System.out.print("몸무게(kg)를 입력해 주세요 : ");
		double weight = sc.nextDouble();

		double bmi = weight / (height * height);

		String str; // 결과 저장용 변수

		if(bmi < 18.5) {
			str = "저체중";
		}else if (bmi < 23) {
			str = "정상체중";
		}else if (bmi < 25) {
			str = "과체중";
		}else if (bmi < 30) {
			str = "비만";
		}else {
			str = "고도비만";
		}

		System.out.println("BMI 지수 : "+ bmi);
		System.out.println(str);


	}

	public void practice5() {

		/* 중간고사, 기말고사, 과제점수, 출석횟수를입력하고 Pass 또는Fail을출력하세요.
           평가비율은중간고사20%, 기말고사30%, 과제30%, 출석20%로이루어져있고
           이때, 출석횟수는총강의횟수20회중에서출석한날만따진값으로계산하세요.
           70점이상일경우Pass,70점미만이거나전체강의에30% 이상결석시Fail을출력하세요.
           [출력예시는다음3장참고]
			
			[실행화면1]
			
			중간고사점수: 80
			기말고사점수: 30
			과제점수: 60
			출석횟수: 18
 			================= 결과=================
			중간고사점수(20) : 16.0
			기말고사점수(30) : 9.0
			과제점수(30) : 18.0
			출석점수(20) : 18.0
			총점: 61.0
 			Fail [점수미달]

			 [실행화면2]
			 
			중간고사점수: 80
			기말고사점수: 90
			과제점수: 50
			출석횟수: 15
 			================= 결과=================
			중간고사점수(20) : 16.0
			기말고사점수(30) : 27.0
			과제점수(30) : 15.0
			출석점수(20) : 15.0
			총점: 73.0
 			PASS

			[실행화면3]
			
			중간고사점수: 100
			기말고사점수: 80
			과제점수: 40
			출석횟수: 10
 			================= 결과=================
 			Fail [출석횟수부족(10/20)]	
		   */

		Scanner sc = new Scanner(System.in);
		
		System.out.print("중간 고사 점수 : ");
		int midTerm = sc.nextInt();
		
		System.out.print("기말 고사 점수 : ");
		int finalTerm = sc.nextInt();
		
		System.out.print("과제 점수 : ");
		int homework = sc.nextInt();
		
		System.out.print("출석 횟수 : ");
		int attendance = sc.nextInt();
		
		System.out.println("================= 결과 =================");
		
		// 출석 횟수가 부족한 경우 (6번 이상 결석 == 14번 이하 출석)
		if(attendance <= 20 * (1 - 0.3)) {
			System.out.printf("Fail [출석 횟수 부족 (%d/20)]", attendance);
			
		}else { // 출석은 잘 했을 때
			
			//점수 환산
			double midScor = midTerm * 0.2;
			double finalScor = finalTerm * 0.3;
			double homeworkScor = homework * 0.3;
			double attScor = attendance * 0.2 * 5; // == attendance
			
			// 총점
			double sum = midScor + finalScor + homeworkScor + attScor;
			
			System.out.println("중간 고사 점수(20) : " + midScor);
			System.out.println("기말 고사 점수(30) : " + finalScor);
			System.out.println("과제 점수    (30) : " + homeworkScor);
			System.out.println("출석 점수    (20) : " + attScor);
			System.out.println("총점 : " + sum);
			
			if(sum >= 70) {
				System.out.println("PASS");
			}else {
				System.out.println("Fail [점수 미달]");
			}
		}
		
		
		}
		
		
	}


