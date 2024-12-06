package edu.kh.objarray.practice.model.service;

import java.util.Scanner;

import edu.kh.objarray.practice.model.vo.Student;

public class PracticeService {

	private Scanner sc = new Scanner(System.in);
	private Student[] std = new Student[10];

	public PracticeService() {}
	String input =null;
	public void start() {
		int count =0;
		boolean flag = false;

		while(true) {
			count++;
			System.out.print("학년 : ");
			int grade = sc.nextInt();
			System.out.print("반 : ");
			int ban = sc.nextInt();
			System.out.print("이름 : ");
			String name = sc.next();
			System.out.print("국어점수 : ");
			int kor = sc.nextInt();
			System.out.print("영어점수 : ");
			int eng = sc.nextInt();
			System.out.print("수학점수 : ");
			int math = sc.nextInt();
			
			for(int i =0; i<std.length;i++) {
				if(std[i]==null) {
					std[i]= new Student(grade, ban, name, kor, eng, math);
					break;
				}

			}
			if(count==10) {
				System.out.println("다찼음");
				break;
			}
			while(true) {

				System.out.println("계속 입력하시겠습니까? (y/n)");
				input = sc.next();

				if(input.equals("y")) {
					break;
				}else if(input.equals("n")) {
					flag=true;
					break;
				}else {
					System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요.");
				}
			}
			if(flag) {
				
				break;
			}
			
		}
		
		for(int i = 0;i<std.length;i++) {
			Student s = std[i];
			if(s==null) {
				break;
			}
			int sum = s.getKor()+s.getEng()+s.getMath();
			
//			System.out.println(s.getName());
			
//			System.out.println(s.toString());

			System.out.printf("%d학년 %d반 %s >> 국어 : %d, 영어 : %d, 수학 %d",
					s.getGrade(),s.getClassroom(),s.getName(),s.getKor(),s.getEng()
					,s.getMath());
		}
	}
}