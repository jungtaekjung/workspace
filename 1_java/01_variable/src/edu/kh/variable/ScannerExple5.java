package edu.kh.variable;

import java.util.Scanner;

public class ScannerExple5 {

	public static void main(String[] args) {
		
		//main 작성후 ctrl + space -> enter : 메인 메소드 자동완성
		
		// Scanner 생성하기 sca 작성후 ctrl + space -> 알맞은 유틸을 찾아 enter : Scanner 생성 완료
		Scanner sc = new Scanner(System.in);
		
		// ex)
		// 이름 : 김태일
		// 나이 : 28
		// 성별 : 남자
		// 키  : 290.8
		// 연봉 : 10000000000
		
		// 김태일님은 28세이고 키는 290.8 , 연봉은 10000000000 입니다.
		
		String result = "";
		System.out.println(result);
		
		System.out.print("이름 :");
		String name = sc.next(); // 입력 받은 문자열을 name에 저장
		result = name + "님은 ";
		
		System.out.print("나이 :");
		int age = sc.nextInt();
		result = result + age + "세 ";
		
		System.out.print("성별 :");
		String gender = sc.next();
		result = result + gender + "이고 키는 ";
		
		System.out.print("키 :");
		double height = sc.nextDouble();
		result = result + height + "cm, 연봉은 ";
		
		
		System.out.print("연봉 :");
		Long salary = sc.nextLong();
		result = result + salary + "입니다.";
		
		System.out.println(result);
		
		System.out.printf("%s님은 %d세 %s이고 키는 %.1f, 연봉은 %d 입니다."
							, name, age, gender, height ,salary);
		
		
		
		
		
		
		
		
		
	}
}
