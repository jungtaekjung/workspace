package edu.kh.community.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.kh.community.board.model.dto.Reply;
import edu.kh.community.board.model.service.ReplyService;

// Controller : 요청에 따라 알맞은 서비스를 호출하고
// 요청 처리 결과를 내보내줄(응답할) view를 선택

// *** Front Controller 패턴 *** 
// 하나의 Servlet이 여러 요청을 받아 들이고 제어하는 패턴
@WebServlet("/reply/*") // /reply로 시작하는 모든 요청을 받음
public class ReplyController extends HttpServlet{
	// /reply/selectReplyList
	// /reply/insert
	// /reply/update
	// /reply/delete

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = uri.substring(  (contextPath + "/reply/").length()  );
		// length() -> 17 ~ 끝까지 자름

		try {
			ReplyService service = new ReplyService();
			// 댓글 목록 조회 요청인 경우
			if(command.equals("selectReplyList")) {

				// 파라미터 얻어오기
				int boardNo = Integer.parseInt(req.getParameter("boardNo") );

				// 댓글 목록 조회 서비스 호출 후 결과 반환 받기
				List<Reply> replyList = service.selectReplyList(boardNo);

				new Gson().toJson(replyList,resp.getWriter());
			}

			// 댓글 작성인 경우
			if(command.equals("insert")){

				// 파라미터 얻어오기
				String replyContent = req.getParameter("replyContent");
				int memberNo = Integer.parseInt(req.getParameter("memberNo") );
				int boardNo = Integer.parseInt(req.getParameter("boardNo") );

				Reply reply =new Reply();
				reply.setBoardNo(boardNo);
				reply.setMemberNo(memberNo);
				reply.setReplyContent(replyContent);

				// 댓글 등록 서비스 호출 후 결과 반환 받기
				int result = service.insertReply(reply);

				// 서비스 호출 결과를 그대로 응답 데이터로 내보내기
				resp.getWriter().println(result);

			}
			// 댓글 삭제인 경우
			if(command.equals("delete")) {

				// 파라미터 얻어오기
				int replyNo = Integer.parseInt(req.getParameter("replyNo") );


				int result = service.deleteReply(replyNo);

				resp.getWriter().println(result);


			}
			// 댓글 수정인 경우
			if(command.equals("update")) {

				int replyNo = Integer.parseInt(req.getParameter("replyNo"));
				String replyContent = req.getParameter("replyContent");

				// 댓글 수정 서비스 호출 후 결과 반환 받기
				int result = service.updateReply(replyNo,replyContent);
				
				// 서비스 호출 결과를 응답 데이터로 보내기
				resp.getWriter().println(result);

				System.out.println(result);
			}

		}catch(Exception e) {
			e.printStackTrace();

		}

	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp); //POST로 전달된 요청을 doGet()으로 전달하여 수행

	}
}
