package edu.kh.inheritance.practice.model.service;

import java.util.Scanner;

import edu.kh.inheritance.practice.model.vo.Employee;
import edu.kh.inheritance.practice.model.vo.Person;
import edu.kh.inheritance.practice.model.vo.Student;

public class PracticeService {

	private Scanner sc = new Scanner(System.in);

	public void homework() {
		// 3명의 학생 정보를 기록할 수 있게 객체 배열 할당
		Student[] student = new Student[3];

		// 위의 사용데이터 참고하여 3명의 학생 정보 초기화

		student[0] = new Student("카리나", 20, 168.2, 70.0, 1, "정보시스템공학과");
		student[1] = new Student("정해인", 21, 187.3, 80.0, 2, "경영학과");
		student[2] = new Student("박서준", 22, 179.0, 45.0, 4, "정보통신공학과");

		// 위의 학생 정보 모두 출력 --> 향상된 for문 이용해서
		for(Student std : student) {
			System.out.println(std.toString());
		}


		// 최대 10명의 사원 정보를 기록할 수 있게 배열을 할당
		Employee[] emp = new Employee[10];
		//사원들의 정보를 키보드로 계속 입력받고 
		int count =0;
		boolean flag = true;
		while(flag) {
			System.out.print("이름 : ");
			String name = sc.next();

			System.out.print("나이 : ");
			int age= sc.nextInt();

			System.out.print("신장 : ");
			double height = sc.nextDouble();

			System.out.print("몸무게 : ");
			double weight = sc.nextDouble();

			System.out.print("급여 : ");
			int salary = sc.nextInt();

			System.out.print("부서: ");
			String dept = sc.next();
			
			// 계속 추가할 것인지 물어보고, 대소문자 상관없이 'y'이면 계속 객체 추가
			// 한 명씩 추가 될 때마다 카운트함
			emp[count] = new Employee(name,age, height, weight, salary, dept);
			count++;

			System.out.print("계속 입력 하시겠습니까?(y/n : ");
			char input = sc.next().toLowerCase().charAt(0);
									//소문자
			if(input !='y') {
				break;
			}

		}
		for(Employee e : emp){
			if(e !=null) {
				
				System.out.println(e);
			}
		}
	}
}
