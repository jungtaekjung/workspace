package edu.kh.oarr.practice.model.service;

import java.util.Scanner;

import edu.kh.oarr.practice.model.vo.Employee;



public class EmployeeService {

	private Scanner sc = new Scanner(System.in);

	private Employee[] empArr = new Employee[3];

	public  EmployeeService() {
		empArr[0] = new Employee(10, "유관순", "마케팅팀", "부장", 5000000);
		empArr[1] = new Employee(20, "홍길동", "개발팀", "대리", 3500000);
		empArr[2] = new Employee(30, "이순신", "총무팀", "사원", 2000000);
	}

	public void displayMenu() {

		int menuNum=0;

		do {
			System.out.println("=== 직원 관리 프로그램 ===");
			System.out.println("1. 직원 정보 입력(3명)");
			System.out.println("2. 모든 직원 정보 출력");
			System.out.println("3. 특정 직원 정보 출력(이름 검색)");
			System.out.println("4. 특정 직원 급여/연봉 출력(사번 검색)");
			System.out.println("5. 모든 직원 급여 합/연봉 합 출력");
			System.out.println("6. 모든 직원중 급여가 가장 높은 직원의 이름, 부서, 급여 출력");
			System.out.println("0. 종료");

			System.out.println("메뉴 선택 >> ");
			menuNum = sc.nextInt();

			switch(menuNum) {
			case 1	: info();break;
			case 2	: infoAll();break;
			case 3	:infoOne();break;
			case 4	:infoTwo();break;
			case 5	:infoThree();break;
			case 6	:infoFour();break;

			case 0	:System.out.println("프로그램 종료");break;
			default : 	System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");break;
			}



		}while(menuNum!=0);



	}
	public void info() {
		for(int i =0; i<empArr.length;i++) {

			System.out.printf("=== %s번째 사원 정보 입력===\n",i+1);

			System.out.println("사번 : ");
			int inputNum = sc.nextInt();

			System.out.println("이름 : ");
			String inputName = sc.next();

			System.out.println("부서 : ");
			String inputTeam = sc.next();

			System.out.println("직급 : ");
			String inputLevel = sc.next();

			System.out.println("급여 : ");
			int inputPay= sc.nextInt();
			empArr[i] = new Employee(inputNum, inputName, inputTeam, inputLevel, inputPay);
		}
	}
	public void infoAll() {
		for(int i =0; i<empArr.length;i++) {
			System.out.printf("사번 : %d, 이름 : %s, 부서: %s, 직급: %s, 급여 :%d\n",
					empArr[i].getEmployeeNum(),empArr[i].getEmployeeName(),
					empArr[i].getEmployeeTeam(),empArr[i].getEmployeeLevel(),empArr[i].getEmployeePay());
		}
	}
	public void infoOne() {
		System.out.print("이름 입력 : ");
		String inputName = sc.next();
		boolean flag =true;

		for(int i =0; i<empArr.length;i++) {
			if(empArr[i].getEmployeeName().equals(inputName)) {
				System.out.printf("사번 : %d, 이름 : %s, 부서 : %s, 직급 : %s, 급여 : %d\n",
						empArr[i].getEmployeeNum(),empArr[i].getEmployeeName(),
						empArr[i].getEmployeeTeam(),empArr[i].getEmployeeLevel(),empArr[i].getEmployeePay());
				flag = false;
			}
		}
		if(flag) {
			System.out.println("일치하는 이름의 사원이 없습니다.");
		}
	}
	public void infoTwo() {
		System.out.println("==급여/연봉 조회==");
		System.out.print("사번 입력 : ");
		int inputNum = sc.nextInt();
		boolean flag = true;

		for(int i =0; i<empArr.length;i++) {
			if(inputNum==empArr[i].getEmployeeNum()) {
				System.out.printf("급여 : %d / 연봉 : %d\n",empArr[i].getEmployeePay(),
						12*empArr[i].getEmployeePay());
				flag =false;
			}
		}
		if(flag) {
			System.out.println("사번이 일치하는 직원 없습니다.");
		}
	}
	public void infoThree() {
		int pay =0;

		System.out.print("=== 모든 사원 급여 합/연봉 합 ===\n");
		for(int i =0; i<empArr.length;i++) {
			pay += empArr[i].getEmployeePay();
		}
		System.out.println("전 직원 급여 합 : "+ pay);
		System.out.println("전 직원 연봉 합 : "+ 12*pay);
	}
	public void infoFour() {
		int max=0;
		for(int i =0;i<empArr.length;i++) {
			if(empArr[i].getEmployeePay()>max) {
				max = empArr[i].getEmployeePay();
			}

		}
		for(int i=0;i<empArr.length;i++) {
			if(max==empArr[i].getEmployeePay()) {
				System.out.println("\n이름 : " + empArr[i].getEmployeeName()
						+ ", 부서 :" + empArr[i].getEmployeeTeam()
						+ ", 급여 :" + empArr[i].getEmployeePay());
			}
		}
	}
}
