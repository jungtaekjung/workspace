package edu.kh.project.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.project.board.model.dao.CommentDAO;
import edu.kh.project.board.model.dto.Comment;
import edu.kh.project.common.utility.Util;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentDAO dao;

	//댓글 목록 조회
	@Override
	public List<Comment> selectList(int boardNo) {
		return dao.selectList(boardNo);
	}

	// 댓글 삽입
	@Override
	public int insertComment(Comment comment) {
		
		// XSS 방지 처리
		comment.setCommentContent(Util.XSSHandling(comment.getCommentContent()));
		
		return dao.insertComment(comment);
	}

	// 댓글 수정
	@Override
	public int updateComment(Comment comment) {
		
		// XSS 방지 처리
		comment.setCommentContent(Util.XSSHandling(comment.getCommentContent()));
		
		return dao.updateComment(comment);
	}

	@Override
	public int deleteComment(Comment comment) {
		return dao.deleteComment(comment);
	}
}
