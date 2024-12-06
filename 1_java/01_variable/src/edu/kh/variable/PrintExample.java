package edu.kh.variable;

public class PrintExample {
	public static void main(String[] args) {
			
		// System.out.print() : 단순 출력 (출력 후 줄바꿈 X)
		// System.out.println() : 한 줄 출력 (출력 후 줄바꿈 O)
		
		// System.out.printf() : 출력될 문자열 형식을 패턴으로 지정하는 출력구문
		
		
		System.out.print("테스트0");
		System.out.println("테스트1");
		System.out.println("테스트2");
		System.out.print("테스트3");
		System.out.println(); // 내용 없는 println -> 줄바꿈
		System.out.println("테스트4");
		
		System.out.println();
		
		int iNum1 = 10;
		int iNum2 = 20;
		
		// 10 + 20 = 30
		System.out.println (iNum1 + "+" +iNum2+ "=" + (iNum1 + iNum2));
		
		// 20 + 10 * 10 / 2 = 70
		System.out.println (iNum2 + " + " + iNum1 + " * " + iNum1 + " / 2 = " + (iNum2 + iNum1 * iNum1 / 2) );
			
			
		// System.out.printf("패턴" , 패턴에 들어갈 값);
		System.out.printf("%d + %d * %d / %d = %d", iNum2, iNum1, iNum1, 2, (iNum2 + iNum1 * iNum1 / 2) );
		
		// System.out.println(); // 줄바꿈
		
		int iNum3 = 21; // \n : 줄바꿈
		System.out.printf("%d\n", iNum3);
		System.out.printf("%6d\n", iNum3);
		System.out.printf("%-6d\n", iNum3);
		
		// 소수점 자리 제어 (반올림처리)
		// ctrl + alt + 화살표 아래 방향 키 : 한 줄 복사
		System.out.printf("%f\n", 10 / 3.0);
		System.out.printf("%.2f\n", 10 / 4.0);
		System.out.printf("%.0f\n", 10 / 4.0);
		
		// 문자, 문자열, boolean
		char ch = 'A';
		String str = "점메추"; // String은 참조형(기본 자료형을 뺀 나머지)
	    boolean isTrue = false;
		
	    System.out.printf("%c / %s / %b\n", ch, str, isTrue);
		
		
	    // escape 문자 : 일반 문자가 아닌 특수 문자 표현
	    System.out.println("a\tb\tc\td"); // tap 출력
	    System.out.println("\\"); // 백슬래시 출력
	    System.out.println("\""); // 쌍따옴표 단순 문자 출력
	    System.out.println('\''); // 홑따옴표 단순 문자 출력
	    System.out.println("\u0041"); // 유니코드(16진수)번호로 출력
	    
	    
	    
	    
	    
		}
}
