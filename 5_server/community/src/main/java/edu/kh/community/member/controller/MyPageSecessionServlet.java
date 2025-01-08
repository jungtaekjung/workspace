package edu.kh.community.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.community.member.model.dto.Member;
import edu.kh.community.member.model.service.MemberService;

@WebServlet("/member/myPage/secession")
public class MyPageSecessionServlet extends HttpServlet{

	// 회원 탈퇴 화면 전환
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = "/WEB-INF/views/member/myPage-secession.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}

	// 회원 탈퇴
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String memberPw = req.getParameter("memberPw");
		HttpSession session = req.getSession();
		
		Member loginMember = (Member)session.getAttribute("loginMember");

		int memberNo = loginMember.getMemberNo();
		
		try {

			MemberService service = new MemberService();

			int result = service.secession(memberPw,memberNo);
			
			String path = null;
			if(result!=0) {
				// 회원 탈퇴 성공 시 : 로그아웃된 상태로 메인페이지
				
				
				// 로그아웃 방법 1
				// 로그아웃 요청으로 리다이렉트
				//path = req.getContextPath() + "/member/logout";
				
				
				// 로그아웃 방법 2
				
				session.invalidate(); // 세션 무효화
				// -> 세션이 무효화 돼서 메세지가 전달되지 않는 문제가 발생
				
				// [해결방법]
				// 새로운 세션을 얻어와서 메세지 세팅
				session = req.getSession(); // 무효화 후 새로 생성된 세션 얻어오기
				
				
				session.setAttribute("message", "탈퇴 되었습니다");
				path = req.getContextPath(); // 메인 페이지 
				
				// 아이디 저장 쿠키 제거
				Cookie c = new Cookie("saveId", ""); // 쿠키 생성
				
				c.setMaxAge(0); // 쿠키 수명 0초 == 삭제
				c.setPath(path); // 쿠키 적용 경로
				resp.addCookie(c); // 클라이언트에게 쿠키 전송
				
				
				// resp.sendRedirect(req.getContextPath());
			}else {
				session.setAttribute("message","비밀번호가 일치하지 않습니다");
				// 회원 탈퇴 실패 시 : "비밀번호가 일치하지 않습니다." 알림창, 회원탈퇴 페이지 재요청
				path = "secession";
			}
			
			resp.sendRedirect(path);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
