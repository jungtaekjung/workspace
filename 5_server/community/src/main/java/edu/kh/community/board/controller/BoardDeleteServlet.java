package edu.kh.community.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.community.board.model.service.BoardService;
import edu.kh.community.member.model.dto.Member;
import edu.kh.community.member.model.service.MemberService;

@WebServlet("/board/delete")
public class BoardDeleteServlet extends  HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	
		try {
			int boardNo = Integer.parseInt(req.getParameter("no"));
			int type = Integer.parseInt(req.getParameter("type"));
			
			
			int result =new BoardService().deleteBoard(boardNo);
			
			HttpSession session = req.getSession();
			
			String path = null;
			
			if(result>0) {
				session.setAttribute("message", "게시글이 삭제되었습니다");
				path = "list?type=" + type;
			}else {
				session.setAttribute("message", "게시글 삭제 실패");
				// path = "detail?type=" + type +"&no=" + boardNo;
				path = req.getHeader("referer");
				// 상세 페이지 == 이전 페이지 요청 주소 == referer
				
				
			}
			
			resp.sendRedirect(path);
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
}
