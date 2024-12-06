package edu.kh.inheritance.model.service;

import java.util.Scanner;

import edu.kh.inheritance.model.vo.Child;
import edu.kh.inheritance.model.vo.Employee;
import edu.kh.inheritance.model.vo.Parent;
import edu.kh.inheritance.model.vo.Person;
import edu.kh.inheritance.model.vo.Student;

public class InheritanceService {

	public void ex1() {

		//상속 확인
		// - Person을 상속받은 Student가 
		//	 Person의 필드, 메소드를 사용할 수 있을까?

		Person p = new Person();

		p.setName("이사이");
		p.setAge(42);
		p.setNationality("인도네이사이아");

		System.out.println(p.getName());
		System.out.println(p.getAge());
		System.out.println(p.getNationality());

		System.out.println("---------------------------------------");

		// Student 객체 생성
		Student std = new Student();
		
		// Student만의 고유한 메소드
		std.setGrade(2);
		std.setClassroom(4);
		
		
		// Person 클래스로 부터 상속받은 메소드
		std.setName("사이");
		std.setAge(24);
		std.setNationality("사이판");
	
		System.out.println(std.getGrade());
		System.out.println(std.getClassroom());
		
		// Student가 Person의 메소드뿐만 아니라 필드도 상속 받았는지 확인
		System.out.println(std.getName());
		System.out.println(std.getAge());
		System.out.println(std.getNationality());
	
		System.out.println("---------------------------");
	
		//Employee 객체 생성
		Employee emp = new Employee();
		
		//Employee만의 고유 메소드
		emp.setCompany("이사이집센터");
		
		//Person 클래스로부터 상속받은 메소드
		emp.setName("이사");
		emp.setAge(242);
		emp.setNationality("멕사이코");
		
		System.out.println(emp.getCompany());
	
		System.out.println(emp.getName());
		System.out.println(emp.getAge());
		System.out.println(emp.getNationality());
	
		System.out.println("----------------------");
		
		// 추가된 breath() 확인하기
		p.breath();
		std.breath();
		emp.breath();
	}
	
	public void ex2() {
		// super() 생성자 사용방법
		
		// Student 매개변수 5개 짜리 생성자
		Student std = new Student("이사이",24,"사이판",4,2);
		
		System.out.println(std.getName());
		System.out.println(std.getAge());
		System.out.println(std.getNationality());
		System.out.println(std.getGrade());
		System.out.println(std.getClassroom());
	}

	public void ex3() {
		// 오버라이딩 확인 예제
		
		Student std =new Student();
		Employee emp = new Employee();
		
		std.move(); // 오버라이딩 X -> Person의 메소드 수행
		emp.move(); // 오버라이딩 O -> Employee 메소드 수행
		
		
	}
	public void ex4() {
		// 모든 클래스는 Object 클래스의 후손
		// == 모든 클래스의 최상위 부모는 Object
	
		// 1) Object 상속 여부 확인
		Person p = new Person(); // Object를 상속 받은 Person 객체 생성
		
		Student std = new Student(); // Person을 상속 받은 Student 객체 생성
		// Object - Person - Student
		
		Scanner sc = new Scanner(System.in);
		
		String str = "abc";
		// * Object 상속과 단계적인 상속 확인
		System.out.println(p.hashCode()); // Object에서 물려 받은 hashCode()
		System.out.println(std.getAge()); // Person에서 물려받은 getAge()
		System.out.println(std.hashCode());
		// Person이 Object에서 물려 받은 hashCode()를
		// Student가 물려 받아서 또 사용
		
		// -> 상속의 상속의 상속의 ... 상속 가능
		
		// * Object가 모든 클래스의 최상위 부모인지 확인
		System.out.println(sc.hashCode());
		// Object - hashCode()
		
		System.out.println(str.hashCode());
		// String - hashCode()
		// -> String이 Object에게 물려 받은 hashCode()를 오버라이딩함 
	}
	public void ex5() {
		Person p = new Person("이사이",24,"사이판");
		
		System.out.println(p.toString());
		System.out.println(p);
		// print 구문 수행 시 참조 변수명을 작성하면 
		// 자동으로 toString() 메소드를 호출해서 출력한다!
		
		System.out.println("-------------------------------------------");
		
		Student std = new Student("사이",42,"인도네이사이아",2,4);
		System.out.println(std.toString());
		// 1) Student 클래스 toString() 오버라이딩 전
		// Person으로 부터 상속 받은 오버라이딩된 toString() 수행
		
		// 2) Student 클래스 toString() 오버라이딩 진행 후
		// Student의 toString() 수행
		
		Employee emp = new Employee("이사이",242,"멕사이코","공방");
		
		System.out.println(emp);
		
		
	
		
		
	}
		
	public void ex6() {
		// 부모 객체 생성
		Parent p1 = new Parent();
		Parent p2 = new Parent(1000000,"이");
		
		System.out.println("--------------------------------------");
		
		// 자식 객체 생성
		Child c1 = new Child();
		Child c2 = new Child("지바겐");
		
		System.out.println("--------------------------------------");
		
		System.out.println(p2.toString());
		System.out.println(c2);
		
		System.out.println("--------------------------------------");
		
		c2.getMoney();
		// stactOverflowError
		
	
	}	
	
		
		
	
	
	
	
	
	
	
	
	
}
