package edu.kh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletEx2 extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// System.out.println("주문한 음료 : " + req.getParameter("coffee"));

		// getParameter()는 전달된 input 태그의 name이 하나일 때만 가능

		// -> 같은 name이 여러 개면 String[]로 반환하는 
		//    getParameterValues()를 사용
		String[] coffee = req.getParameterValues("coffee");
		
		String orderer = "";
		// 체크된 메뉴가 있는 경우
		if(coffee != null) {
			orderer = req.getParameter("orderer");
			System.out.println("주문자 : " + orderer);
			
			for(String cof : coffee) {
				System.out.println(cof);
			}
		}
		
		// HttpServletRequest : 클라이언트 정보 + 전달된 값
		// HttpServletResponse : 서버가 클라이언트에게 응답할 방법을 제공
		
		// Wtire : 서버가 클라이언트에게 쓰다 == 출력
		// resp.getWrite() : 서버가 클라이언트에게 응답할 수 있는 출력 전용 스트림을 resp에서 얻어옴
		
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		// out.print("응답이 될까?");
		
		// ** 스트림을 통해서 그냥 문자열을 내보면 정상 출력되지 않는 문제 발생 **
		// -> 전달되는 응답 데이터가 어떤 형식인지, 문자 인코딩은 어떤건지 지정해주지 않았기 때문
		
		// *********************************************************************
		/* Dynamic Web Project(동적 웹 프로젝트)
		 * 
		 * - 요청에 따라서 응답되는 화면(HTML)을 실시간으로 만들어내서(동적)
		 *   클라이언트에게 응답하는 프로젝트
		 * */
		// *********************************************************************
		
		// HTML 코드를 자바(Servlet)에서 작성하여 클라이언트와 연결된 응답 출력용 스트림(out)을 이용해서 출력
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		
		out.print("<head>");
		out.print("<title>" + orderer + "님의 주문 내역</title>");
		out.print("</head>");
		
		out.print("<body>");
		out.print("<ul>");
		if(coffee != null) {
			for(String c : coffee) {
				out.println("<li>" + c + "</li>");
			}
		}
		out.print("</ul>");
		out.print("</body>");
		
		out.print("</html>");
		
		
		
	}
}
