package edu.kh.oarr.view;

import java.util.Scanner;

import edu.kh.oarr.model.service.StudentService;
import edu.kh.oarr.model.vo.Student;

public class StudentView { // 화면용 클래스


	// 필드

	private Scanner sc =new Scanner(System.in);
	private StudentService service = new StudentService();



	// 메뉴 표시 메소드
	public void displayMenu() {
		int input =0; // 메뉴 번호 입력

		do {
			System.out.println("\n------ 학생 관리 프로그램 -------");
			System.out.println("1. 학생 추가");
			System.out.println("2. 학생 전체 조회");
			System.out.println("3. 학생 1명 정보 조회(인덱스)");
			System.out.println("4. 학생 1명 점수 조회(점수, 합계, 평균");
			System.out.println("5. 학생 정보 수정(인덱스)");
			System.out.println("0. 종료");


			System.out.print("메뉴 선택 : ");
			input = sc.nextInt();

			switch(input) {
			case 1: System.out.println(addStudent());break;
			case 2: selectAll();break;
			case 3: selectIndex();break;
			case 4: selectScore();break;
			case 5: updateStudent();break;
			case 0: System.out.println("프로그램 종료");break;
			default : System.out.println("잘못 입력하셨습니다.");
			} 
		}while(input!=0);

	}


	// 학생 추가 화면
	private String addStudent() {

		System.out.println("\n----- 학생 추가 -----");

		System.out.print("학년 : ");
		int grade = sc.nextInt();

		System.out.print("반 : ");
		int ban = sc.nextInt();

		System.out.print("번호 : ");
		int number = sc.nextInt();

		System.out.print("이름 : ");
		String name = sc.next();

		// 학년, 반, 번호, 이름을 StudentService로 전달하여 
		// 객체 배열에 추가
		// 성공 시 true, 실패시 false 반환

		boolean result	= service.addStudent(grade, ban, number, name);

		// 학생 추가 결과에 따라 수행
		if(result) { // true
			return    name+"학생이 추가되었습니다.";
		}else { // false
			return " 학생을 더 이상 추가할 수 없습니다.";
		}
	}

	// 학생 전체 조회
	private void selectAll() {
		System.out.println("\n----- 학생 전체 조회 -----");

		// StudentService에서 학생 배열(studentArr)을 반환 받아 오기
		Student[] arr = service.selectAll();

		// arr : 학생 객체 배열 참조
		// arr[0] : 학생 객체 배열 중 0번 인덱스에 있는 참조 변수 
		//			-> 학생 객체 하나를 참조

		// 향상된 for문
		//	- for( 배열 요소 하나를 저장할 변수 : 배열명 )
		//	- 배열 내 모든 요소를 접근하는 용도의 for문
		//	- 반복이 될 때 마다 인덱스가 자동으로 하나씩 증가

		for(Student std :arr) {
			if(std==null) {
				break;
			}
			System.out.println(std.toString());
		}


		// 일반 for문
		/*for(int i = 0; i<arr.length;i++) {
			if(arr[i]==null) { // 참조하는 학생 객체가 없는 참조 변수인 경우
				break;
			}

			// 학생 객체 핗드에 있는 모든 값을 출력
			System.out.println(arr[i].toString());
		}*/


	}

	// 학생 1명 정보 조회(인덱스)	
	private void selectIndex() {
		System.out.println("\n----- 학생 1명 정보 조회(인덱스) -----");

		System.out.print("조회할 학생의 인덱스 번호를 입력하세요 : ");
		int index = sc.nextInt();

		// StudentService로 index를 전달하여 
		// 해당하는 index에 학생 객체가 있으면 그 객체의 주소를 반환
		// but index에 학생이 없거나 범위가 초과되면 null 반환

		Student std	=service.selectIndex(index);

		if(std ==null) { // 범위 초과 또는 학생 없음
			System.out.println("해당 인덱스에 학생 정보가 존재하지 않습니다.");
		}else {
			System.out.println("학년 : "+std.getGrade());
			System.out.println("반 : "+std.getBan());
			System.out.println("번호 : "+std.getNumber());
			System.out.println("이름 : "+std.getName());
			System.out.println("국어점수 : "+std.getKor());
			System.out.println("수학점수 : "+std.getMath());
			System.out.println("영어점수 : "+std.getEng());

		}
	}
	private void selectScore() {

		// 인덱스를 입력 받아 서비스로 전달하여
		// 반환 받은 문자열을 출력


		// (서비스)
		// 인덱스 범위를 초과하거나 참조하는 학생 객체가 없으면
		// "해당 인덱스에 학생 정보가 존재하지 않습니다."

		// 있으면
		// "[홍길동] 국어:30, 영어:40, 수학:50, 합계:120, 평균 40.0"
		// 문자열(String)을 반환
		System.out.println("\n----- 학생 1명 점수 조회(점수, 합계, 평균) -----");

		System.out.print("점수를 조회할 학생 인덱스 입력: ");
		int index = sc.nextInt();

		String str =service.selectScore(index);

		System.out.println(str);
	}

	// 학생 정보 수정(인덱스)
	public void updateStudent() {
		// 인덱스 번호를 입력 받고
		// 해당 학생이 존재하면 국어, 영어, 수학 점수를 입력 받아 수정 후
		// "수정 되었습니다" 출력

		// 존재하지 않으면 
		// "해당 인덱스에 학생 정보가 존재하지 않습니다." 출력

		System.out.println("\n----- 학생 정보 수정-----");

		System.out.print("정보를 수정할 학생 인덱스 입력 : ");
		int index = sc.nextInt();

		/*	String str=service.updateStudent(index);

	System.out.println(str);*/

		// 입력 받은 인덱스에 학생이 있는지 확인
		Student std = service.selectIndex(index);
		// 이미 존재하는 service의 selectIndex(index) 메소드를 이용

		if(std == null) {
			System.out.println("해당 인덱스에 학생 정보가 존재하지 않습니다.");
			return;
		}
		System.out.print("수정할 국어 점수 : ");
		int kor = sc.nextInt();
				
		System.out.print("수정할 영어 점수 : ");
		int eng = sc.nextInt();
		
		System.out.print("수정할 수학 점수 : ");
		int math = sc.nextInt();
		
		// 학생 점수 수정 서비스 호출
		service.updateStudent(kor, eng, math, std);
		System.out.println("수정 되었습니다.");
	
	
	}













}