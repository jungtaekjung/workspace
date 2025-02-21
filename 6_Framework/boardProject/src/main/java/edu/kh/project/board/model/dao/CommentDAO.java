package edu.kh.project.board.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.project.board.model.dto.Comment;

@Repository
public class CommentDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	// 댓글 목록 조회
	public List<Comment> selectList(int boardNo) {
									// board-mapper.xml에 작성된 select 이용
		return sqlSession.selectList("boardMapper.selectCommentList",boardNo);
	}

	// 댓글 삽입
	public int insertComment(Comment comment) {
		return sqlSession.insert("commentMapper.insertComment",comment);
	}

	public int updateComment(Comment comment) {
		return sqlSession.update("commentMapper.updateComment",comment);
	}

	public int deleteComment(Comment comment) {
		return sqlSession.update("commentMapper.deleteComment",comment);
	}
}
