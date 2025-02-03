package edu.kh.project.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// @Controller : 현재 클래스가 컨트롤러임을 명시 + Bean 등록
//				 -> 요청, 응답 처리

// instance : new 클래스명(); -> 객체 생성을 개발자가 직접함

//Bean : 프레임워크(Spring Container)가 객체를 생성하고 관리
//			->이 때 생성된 객체를 Bean이라고 함
//IOC(Inversion Of Control, 제어의 역전)

@Controller
public class MainController {
	
	@RequestMapping("/") // 요청 주소가 "/" 인 경우 해당 메소드와 연결
	public String mainForward() {
		
		// main.jsp로 화면 전환
		
		
		//Spring에서 forward하는 방법
		// -> webapp 폴더를 기준으로 요청 위임할 JSP 파일 경로를 return 하면 된다.
		
		//단, servlet-context.xml에 작성된 prefix, suffix부분은 제외하고 작성한다!
		//<beans:property name="prefix" value="/WEB-INF/views/" />
		//<beans:property name="suffix" value=".jsp" />
		
		//prefix + 리턴 값 + suffix로 경로 완성
		// -> View Resolver
		
		
		return "common/main";
		
	}

}
