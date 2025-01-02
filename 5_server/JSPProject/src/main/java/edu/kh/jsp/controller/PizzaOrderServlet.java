package edu.kh.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pizza/order")
public class PizzaOrderServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// a 태그 요청 == Get 방식
		
		// 별도의 처리 없이 바로 JSP로 요청 위임
		
		// RequestDispatcher : 요청 발송자
		// -> 지정된 JSP로 요청 정보(req), 응답 정보(resp)를 전송(발송)하는 역할
		
		// JSP 경로는 webapp을 기준으로 작성한다
		// RequestDispatcher dispatcher = req.getRequestDispatcher("JSP경로");
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/pizza_order.jsp");
		
		// forward : JSP로 요청, 응답을 보내는 기능
		dispatcher.forward(req, resp);
	}
	
	// form 태그 POST 방식 제출 시 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// * POST 방식의 한글 깨짐 문제 *
		
		// - 데이터 전달 방식 차이점
		// GET : 주소(URL)를 통해서 데이터 전달
		//		 이 때, 문자 인코딩은 제출된 HTML파일의 문자 인코딩(charset)을 따름
		
		// POST : HTTP Body를 통해서 데이터 전달
		//		  이 때, 문자 인코딩은 서버의 기본 문자 인코딩을 따름
		//		  우리 서버(Tomcat) -> ISO-8859-1이 기본 문자 인코딩
		
		// * 해결 방법 *
		// - POST 방식으로 전달받은 데이터의 문자 인코딩을 UTF-8로 변경
		req.setCharacterEncoding("UTF-8");
		
		
		String pizza = req.getParameter("pizza");
		String size = req.getParameter("size");
		int amount = Integer.parseInt(req.getParameter("amount"));
		
		// 피자 이름, 가격 나누기
		String[] arr = pizza.split("-"); // "-" 구분자로 쪼개서 String[]로 반환
		// arr = {"치즈 피자", "8000"}
		
		String pizzaName = arr[0]; // "치즈 피자"
		int price = Integer.parseInt(arr[1]); // 8000
		
		// L 사이즈인 경우 4000원 추가
		if(size.equals("L")) price += 4000;
		
		// 피자 수량만큼 price에 곱하기
		price *= amount;
		// price = price * amount;
		
		// JSP로 요청 위임(forward)
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/pizza_result.jsp");
		
		// JSP로 전달하는 req에는 파라미터가 담겨있다
		// 하지만, Servlet에서 만든 변수 pizzaName, price는 없다
		
		// [해결 방법]
		// req에 속성(Attribute)로 추가하면 JSP에서 꺼낼 쓸 수 있음
		// (주의) forward 하기 전에 추가해줘야 함
		req.setAttribute("pizzaName", pizzaName);
		req.setAttribute("price", price);
		
		dispatcher.forward(req, resp);
		
	}
}
