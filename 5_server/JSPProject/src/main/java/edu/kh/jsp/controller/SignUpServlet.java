package edu.kh.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Servlet : 웹 서비스(요청, 응답)를 위한 자바 클래스

// @WebServlet("요청 주소") : Servlet 클래스 등록 + 요청 주소 매핑
@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// POST 방식으로 데이터를 전달받을 경우 서버(Tomcat)의 기본 문자 인코딩을 따름
		// -> UTF-8로 인코딩 변경 필요
		req.setCharacterEncoding("UTF-8");
		
		String memberName = req.getParameter("memberName");
		
		String message = memberName + "님의 가입을 환영합니다!";
		
		// 요청 -> Servlet -> RequestDispatcher -> forward(req, resp) -> JSP
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/signUp_result.jsp");
		// JSP 경로는 webapp 폴더를 기준으로 작성
		
		req.setAttribute("msg", message);
		dispatcher.forward(req, resp);

	}

}
