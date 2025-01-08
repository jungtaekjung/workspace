package edu.kh.community.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.community.member.model.dto.Member;
import edu.kh.community.member.model.service.MemberService;

@WebServlet("/member/myPage/changePw")
public class MyPageChangePwServlet extends HttpServlet{

	// get 방식 : 비밀번호 변경 화면
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path= "/WEB-INF/views/member/myPage-changePw.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}

	// post 방식 : 비밀번호 변경
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String currentPw = req.getParameter("currentPw");
		String newPw = req.getParameter("newPw");

		// 성공 시 : 내 정보 페이지로 재요청
		// 실패 시 : 비밀번호 변경 페이지로 재요청

		HttpSession session = req.getSession();

		Member loginMember = (Member)session.getAttribute("loginMember");

		int memberNo = loginMember.getMemberNo();

		// ** Service에 전달할 값이 많은데 DTO로 해결할 수 없는 경우 **
		// 1. 매개변수로 하나씩 전달한다. -> 최대 4개를 넘지 않는 것을 권장
		
		// 2. DTO 새로 만들기 -> 단, 일회성으로 사용하면 비효율적
		
		// 3. Map
		

		try {

			MemberService service = new MemberService();

			int result = service.changePw(currentPw,newPw,memberNo);

			String path =null;
			if(result!=0) {

				session.setAttribute("message","비밀번호 변경 성공");
				path = req.getContextPath() +"/member/myPage/info";
			}else {
				session.setAttribute("message","현재 비밀번호가 일치하지 않습니다");
				
				// path = "changePw";
				path = req.getContextPath() +"/member/myPage/info";
			}
			resp.sendRedirect(path);
		}catch(Exception e) {
			e.printStackTrace();
		}


	}
}
