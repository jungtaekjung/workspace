package edu.kh.project.common.interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import edu.kh.project.board.model.service.BoardService;

public class BoardTypeInterceptor implements HandlerInterceptor{
	
	@Autowired
	private BoardService service;
	// Interceptor : 요청/응답을 가로채는 객체
	// Client -> Filter -> Dispatcher Servlet -> Interceptor -> Controller (*참고: 반대로도 가능)
	
	/* proHandle (전처리)  : Dispatcher Servelt -> Controller 사이
	 * postHandle (후처리) : Controller -> Dispatcher Servlet 사이
	 * afterCompletion(뷰 완성 후) : View Resolver -> Dispatcher Servlet 사이
	 * 
	 * */
	
	//전처리
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// application scope 내장 객체 얻어오기
		ServletContext application = request.getServletContext();
		
		// application scope에 BOARD_TYPE이 조회되어 세팅되지 않았다면
		// ==서버 시작 후 누구도 요청한 적 X
		if(application.getAttribute("boardTypeList")==null) {
			
			// 조회 서비스 호출 후
			System.out.println("BOARD_TYPE 조회 서비스 호출");
			
			List<Map<String, Object>> boardTypeList= service.selectBoardTypeList();
			
			System.out.println(boardTypeList);
			
			// application scope에 세팅
			application.setAttribute("boardTypeList", boardTypeList);
		}
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	
	
	
	// 필터(Filter) vs 인터셉터 (Interceptor) 차이
	
	/*						필터						인터셉터
	 *관리되는 컨테이너		서블릿 컨테이너				 스프링 컨테이너
	 *
	 *스프링의 예외처리 여부		X						  O
	 *
	 * 용도	  이미지/데이터 압축 및 문자열 인코딩	세부적인 보안 및 인증/인가 공통 작업
	 *		  공통된 보안 및 인증/인가 관련 작업	API 호출에 대한 로깅 또는 감사
	 *		  Spring과 분리되어야 하는 기능		Controller로 넘겨주는 정보의 가공
	 *		  모든 요청에 대한 로깅 또는 감사 
	 * 
	 * */
	
	

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	
	

}
