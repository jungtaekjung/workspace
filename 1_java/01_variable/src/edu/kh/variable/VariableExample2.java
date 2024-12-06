package edu.kh.variable;

public class VariableExample2 {
	
	public static void main(String[] args) {
		/*자바 기본 자료형 8가지
		 * 
		 * 논리형 : boolean(1byte)
		 * 정수형 : byte(1byte), short(2byte), int(4byte), long(8byte)
		 * 실수형 : float(4byte), double(8byte)
		 * 문자형 : char(2byte, 유니코드)
		 * 
		 */
		
		// 변수 선언 : 메모리의 값을 저장할 공간을 할당하는 것
		// 변수 값 대입(집어넣기) : 변수에 값을 집어 넣는 것
		
		// * 카멜(낙타) 표기법 : 연결되는 두 단어 중 후속 단어의 첫 문자를 대문자로 표기
		
		// 자료형 변수명      ;
		boolean booleanData;
		// 메모리에 논리 값(t/f)을 저장할 공간을 1byte 할당하고
		// 할당된 공간을 boolreanData라고 부르겠다.
		
		booleanData = true; // booleanDate 변수에 true 값을 집어 넣기(대입했다)
		
		byte byteData = 127; // 변수 선언 및 초기화
		// 메모리에 정수 값을 저장할 공간을 1byte 할당하고
		// 할당된 공간을 byteData라고 부르겠다.
		// 선언된 byteData 변수에 처음으로 127을 집어 넣음
		// ---> 초기화 : 처음 변수에 값을 대입
		
		short shortNumber = 32767;
		// 자료형   변수명    = 리터럴
		// 메모리에 정수 값을 저장할 공간을 2byte 할당하고
		// 할당된 공간을 shortNumber라고 부르겠다.
		// ** 리터럴 : 변수에 대입되거나 작성되어지는 값 자체
		// + 자료형에 따라서 리터럴 표기법이 다름
		
		
		// 정수 자료형의 기본형
		int intNumber = 2147483647; // 변수의 선언 및 초기화
		
		long longNumber = 10000000000L; // 또는 소문자 l
		// The literal 10000000000 of type int is out of range
		
		float floatNumber = 1.2345f; 
		// Type mismatch: cannot convert from double to float
		
		double doubleNumber = 3.141592;
		// double은 실수형 중에서 기본형
		
		// 리터럴 표기법이 없는 정수/실수는 기본형인 int/double로 인식
		
		// 문자형 리터럴 표기법 : '' (홑따옴표)
		// -> 문자 하나
		char ch = 'A';
		char ch2 = 66; // B
		// ** char 자료형에 숫자가 대입될 수 있는 이유
		// - 컴퓨터에는 문자표가 존재하고 있는데
		// 숫자에 따라 지정된 문자 모양이 매핑되어 있고
		// 'B' 문자 그대로가 대입이 되면 변수에 숫자 66으로 변환되어 저장
		// -> 반대로 생각하면 변수에 애초에 66이라는 숫자를 저장하는 것이 가능하다.
		
		System.out.println("ch : " + ch);
		System.out.println("ch2 : " + ch2);
		
		
	}

}
