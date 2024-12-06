package edu.kh.jdbc.common;

import java.io.FileInputStream;
import java.util.Properties;

public class LoadXML {
	public static void main(String[] args) {

		// 외부 XML 파일 읽어오기 (Properties 사용)

		Properties prop = new Properties();
		// Key, Value가 String으로 제한된 Map

		try {
			prop.loadFromXML(new FileInputStream("driver.xml"));

			// Property : 속성(데이터)
			// prop.getProperty : XML에서 얻어온 값 중
			// 						key가 "driver"인 entry의 value를 얻어옴

			System.out.println("driver : " + prop.getProperty("driver"));

			// 외부 파일 미사용
			String str = "oracle.jdbc.driver.OracleDriver";
			System.out.println(str);
			
			// Java는 코드가 한 글자라도 변환되면
			// 다시 처음부터 전체 내용을 컴파일(이진 코드 번역)한다 --> 비효율적
			
			// 그런데 Java 외부 파일을 읽어오는 코드는 변하지 않으므로
			// 컴파일을 다시 하지 않음 --> 효율적
			// 컴파일을 다시 하지 않아도 외부 파일 내용이 변경되면 자동으로 반영
			
			// DB 연결 정보, SQL 내용은 빈번히 변화될 예정이므로
			// XML 작성 -> 바로 실행(다시 컴파일 X, 효율적)
			// + DB정보, SQL을 한곳에 모아서 관리 (보기 좋고 관리가 쉬움)
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}



	}
}
