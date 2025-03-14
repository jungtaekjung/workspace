package edu.kh.community.board.model.service;


import static edu.kh.community.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import edu.kh.community.board.model.dao.ReplyDAO;
import edu.kh.community.board.model.dto.Reply;
import edu.kh.community.common.Util;

public class ReplyService {

	private ReplyDAO dao = new ReplyDAO();

	

	/** 댓글 목록 조회
	 * @param boardNo
	 * @return replyList
	 * @throws Exception
	 */
	public List<Reply> selectReplyList(int boardNo) throws Exception{
		
		Connection conn = getConnection();
		
		List<Reply> replyList = dao.selectReplyList(conn,boardNo);
		
		close(conn);
		
		return replyList;
	}



	


	/** 댓글 등록
	 * @param reply
	 * @return result
	 * @throws Exception
	 */
	public int insertReply(Reply reply) throws Exception{
		
		Connection conn = getConnection();
		
		// Cross Site Scripting 공격 방지 처리
		reply.setReplyContent(Util.XSSHandling(reply.getReplyContent()));
		// 개행문자 변경 처리
		// textarea 줄바꿈 문자 입력 시 \n, \r, \r\n, \n\r 중 하나로 입력됨(브라우저, OS 따라 다름)
		// 이 문자들을 HTML에서 줄바꿈으로 인식할 수 있도록 "<br>" 태그로 변경
		
		//reply.getReplyContent().replaceAll("정규표현식", "바꿀 문자열");
		// reply.setReplyContent(reply.getReplyContent().replaceAll("(\n|\r|\r\n|\n\r)", "<br>"));
		// -> 댓글 등록/수정, 게시글 등록/수정 에서 사용
		// -> static으로 선언해둔 개행 문자 변경 메소드 사용 
			
		reply.setReplyContent(Util.newLineHandling(reply.getReplyContent()));
		
		
		
		int result = dao.insertReply(conn,reply);
		
		
		if(result >0) commit(conn);
		else		  rollback(conn);
		close(conn);
		
		
		return result;
	}






	/** 댓글 삭제
	 * @param replyNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteReply(int replyNo) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.deleteReply(conn,replyNo);
		
		if(result >0) commit(conn);
		else		  rollback(conn);
		
		close(conn);
		
		return result;
		
	}






	/** 댓글 수정 
	 * @param replyNo
	 * @param replyContent
	 * @return result
	 * @throws Exception
	 */
	public int updateReply(int replyNo, String replyContent) throws Exception{
		
		
		Connection conn = getConnection();
		
		//XSS 방지처리
		replyContent = Util.XSSHandling(replyContent);
		// 개행 처리
		replyContent = Util.newLineHandling(replyContent);
		
		int result = dao.updateReply(conn,replyNo,replyContent);
		
		if(result >0) commit(conn);
		else		  rollback(conn);
		
		close(conn);
		
		return result;
		
		
	} 
}
