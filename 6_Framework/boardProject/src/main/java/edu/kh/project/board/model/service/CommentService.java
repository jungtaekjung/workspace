package edu.kh.project.board.model.service;

import java.util.List;

import edu.kh.project.board.model.dto.Comment;

public interface CommentService {

	List<Comment> selectList(int boardNo);

	int insertComment(Comment comment);

	int updateComment(Comment comment);

	int deleteComment(Comment comment);

}
