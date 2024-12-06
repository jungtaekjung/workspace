package edu.kh.variable;

import java.util.Scanner;

public class ScannerExmple3 {

	
		public static void main(String[] args) {
			
			// 문자열(String) 입력
			
			// 문자열을 3번 입력 받아서 한 줄로 출력하기
			
			// next() : 다음 입력된 한 단어를 읽어옴
			
			// ex)
			// 입력 1 : 오늘
			// 입력 2 : 점심
			// 입력 3 : 뭐먹지?
			// 오늘 점심 뭐먹지?
			
			Scanner sc = new Scanner (System.in);
			
			System.out.print("입력 1 :");
			String input1 = sc.next();
			
			System.out.print("입력 2 :");
			String input2 = sc.next();
		    
			System.out.print("입력 3 :");
			String input3 = sc.next();
			
			System.out.printf("%s %s %s", input1, input2, input3);
			
			
			
		}
}
