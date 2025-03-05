package edu.kh.project.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.project.board.model.dao.CommentDAO;
import edu.kh.project.board.model.dto.Comment;
import edu.kh.project.common.utility.Util;

@Service
public class CommentServicelmpl implements CommentService{
	
	@Autowired
	private CommentDAO dao;

	//댓글 조회
	@Override
	public List<Comment> select(int boardNo) {
		
		return dao.select(boardNo);
	}






	//댓글 삽입
	@Override
	public int insert(Comment comment) {
		//XSS 방지 처리
		comment.setCommentContent(Util.XSSHandling(comment.getCommentContent()));
		
		int result=dao.insert(comment);
		
		//댓글 삽입 성공 시 댓글 번호 반환
		if(result>0) result=comment.getCommentNo();
		
		return result;
	}





	//댓글 수정
	@Override
	public int update(Comment comment) {
		//XSS 방지 처리
		comment.setCommentContent(Util.XSSHandling(comment.getCommentContent()));
		return dao.update(comment);
	}





	//댓글 삭제
	@Override
	public int delete(Comment comment) {
		return dao.delete(comment);
	}








}
