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

@WebServlet("/member/login")
public class LoginServlet extends HttpServlet{
   
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      // POST 방식 요청 시 문자 인코딩이 서버 기본값으로 지정 -> 문자 인코딩 변경
      // req.setCharacterEncoding("UTF-8");
      
      // 모든 doGet(), doPost() 시 req, resp의 문자 인코딩을 UTF-8로 변경(Filter)
      String inputEmail = req.getParameter("inputEmail");
      String inputPw = req.getParameter("inputPw");
      
      System.out.println(inputEmail);
      System.out.println(inputPw);
      
      // 파라미터를 DTO에 세팅(롬복 확인)
      Member mem = new Member();
      
      mem.setMemberEmail(inputEmail);
      mem.setMemberPw(inputPw);
      
      try {
         
         // 서비스 객체 생성
         MemberService service = new MemberService();
         
         // 이메일, 비밀번호가 일치하는 회원을 조회하는 서비스 호출 후 결과 반환받기
         Member loginMember = service.login(mem);
         
         // * 로그인 * 
         // ID/PW가 일치하는 회원정보를 Session Scope에 세팅
         
         // Session 객체 얻어오기
         HttpSession session = req.getSession();
         
         // 로그인 성공/실패에 따른 처리
         if(loginMember != null) { // 로그인 성공
            
            // 회원 정보 Session 세팅
            session.setAttribute("loginMember", loginMember); 
            
            // 특정 시간동안 요청이 없으면 세션 만료
            session.setMaxInactiveInterval(3600); // 3600초 == 1시간
            // -> 초 단위 작성
            
            // -------------------------------------------------------------------
            
            // 아이디 저장(Cookie)
            
            /* Cookie : 클라이언트(브라우저)에서 관리하는 파일
             * - 특정 주소 요청 시 마다 해당 주소와 연관된 쿠키 파일을 브라우저가 알아서 읽어옴
             *   -> 읽어온 쿠키 파일 내용을 서버에 같이 전달
             *   
             *   [생성 및 사용 방법]
             *   1) 서버가 요청에 대한 응답을 할 때 쿠키를 생성한 후 응답에 쿠키를 담아 클라이언트에게 전달
             *   2) 응답에 담긴 쿠키가 클라이언트에 파일 형태로 저장
             *   3) 이후 특정 주소 요청 시 쿠키 파일을 브라우저가 찾아 자동으로 요청에 실어 보냄
             *   4) 서버는 요청에 실려온 쿠키 파일을 사용함
             * */
            
            // 쿠키 객체 생성
            // Cookie c = new Cookie("클라이언트 쪽에 저장될 쿠키 이름", "쿠키 내용")
            Cookie c = new Cookie("saveId", inputEmail);
            
            // 아이디 저장이 체크된 경우
            if(req.getParameter("saveId") != null) {
               // 쿠키 파일을 30동안 유지
               c.setMaxAge(60 * 60 * 24 * 30); // 30일(1초 단위)
            }else {
            	// 쿠키 파일을 0초 동안 유지
            	// -> 기존에 존재하던 쿠키 파일에 유지 시간을 0초로 덮어씌움
            	// == 삭제
            	c.setMaxAge(0);
               
            }
            // 해당 쿠키 파일이 적용될 주소를 지정
            c.setPath(req.getContextPath());
            // req.getContextPath() : 최상위 주소(/community)
            // -> / community로 시작되는 주소에서만 쿠키를 적용
            
            // 응답 객체를 이용해서 클라이언트로 전달
            resp.addCookie(c); // 코드가 해석되는 순간 바로 전달
            
            // ===============================================
         }else { // 로그인 실패
            session.setAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
         }
         
         // 클라이언트 요청 -> 서버 요청 처리(Servlet) -> 응답 화면 생성(JSP 위임)
         
         // 1. forward(요청 위임)
         // - Servlet으로 응답 화면 만들기 불편
         // -> JSP로 req, resp 객체를 위임하여 요청에 대한 응답 화면을 대신 만듦
         // * 화면이 변경돼도 요청 주소가 유지된다.
         
         // 2. Redirect(재요청)
         // - 현재 Servlet에서 응답 페이지를 만들지 않고 응답 페이지를 만들 수 있는
         //   다른 요청의 주소로 클라이언트를 이동 시킴(재요청)
         
         resp.sendRedirect(req.getContextPath());
         // req.getContextPath() : 최상위 주소
         
         // 클라이언트 재요청
         // -> 기존 HttpServletRequest/Response 제거
         // -> 새로운 HttpServletRequest/Response 생성
         
         // --> 리다이렉트 시 request 객체가 유지되지 않기 때문에
         //       특정 데이터를 전달하거나 유지하고 싶으면
         //      session 또는 application 범위에 세팅해야 한다
         
         // forward
         // req.getRequestDispatcher("../index.jsp").forward(req, resp);
      }catch(Exception e) {
         e.printStackTrace();
      }
   }

}
