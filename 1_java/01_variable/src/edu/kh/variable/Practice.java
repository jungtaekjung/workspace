package edu.kh.variable;

import java.util.Scanner;

public class Practice {

		public static void main(String[] args) {
			
		Scanner sc = new Scanner(System.in);
		
	    System.out.print("이름 :");
	    String name = sc.next();
	    System.out.print("나이 :");
	    int age = sc.nextInt();
	    System.out.print("성별 :");
	    String gender = sc.next();
	    System.out.print("직업 :");
	    String job = sc.next();
	    System.out.print("키 :");
	    double height = sc.nextDouble();
	    
	    System.out.printf("%s님은 %d세 이고 성별은 %s 이며 직업은 %s 입니다 그리고 키는 %.1f 입니다"
	    					, name , age , gender , job , height);
			
			
		
		}
}
