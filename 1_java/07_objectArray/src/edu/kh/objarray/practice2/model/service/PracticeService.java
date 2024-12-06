package edu.kh.objarray.practice2.model.service;

import java.util.Scanner;

import edu.kh.objarray.practice2.model.vo.Student;

public class PracticeService {

	public void start() {

		// 1) 최대 10명의 학생 정보를 기록할 수 있게 배열을 할당한다.
		Student[] std = new Student[10];

		// 2)while문을 사용하여 학생의 정보를 입력 받고
		// 한 명씩 추가 될때마다 카운트함
		// 계속 추가할 것인지 물어보고, 'y'이면 계속 객체 추가
		Scanner sc = new Scanner(System.in);
		int count = 0;
		boolean flag = true; // 바깥쪽 while문 제어용
		while(flag) {
			System.out.print("학년 : ");
			int grade = sc.nextInt();

			System.out.print("반 : ");
			int classroom = sc.nextInt();

			System.out.print("이름 : ");
			String name = sc.next();

			System.out.print("국어점수 : ");		
			int kor = sc.nextInt();

			System.out.print("영어점수 : ");
			int eng = sc.nextInt();

			System.out.print("수학점수 : ");
			int math = sc.nextInt();

			std[count]= new Student(grade, classroom, name, kor, eng, math);
			count++;

			while(true) {

				System.out.print("계속 입력 하시겠습니까?(y/n) : ");
				char input = sc.next().toUpperCase().charAt(0); // charAt(0) 쓰기!
				//toUpperCase() -> 모두 대문자로 변경
				// 3) 10명을 입력한 경우 모두 입력하거나, 
				//	계속 추가할 것인지 물어볼때 'n'을 입력한 경우
				// 학생 정보 입력을 멈춤
				if(count==10||input == 'N') {
					flag = false;
					break;
				}

				// 4) 'y' 또는 'n'을 입력하지 않은 경우
				// "잘못 입력하셨습니다. 다시 입력해 쥬세요" 출력 후
				// 다시 계속 추가할지 여부를 물어봄.
				if(input != 'Y') {
					System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요.");
				}else {
					break; // 가장 가까운 반복문 탈출
				}
			}
		}
				// 5) 입력된 모든 학생들의 정보 + 평균 점수를 출력
				for(int i =0; i<count;i++) {
					System.out.println(std[i].toString());
				}
	
	
	}
}
