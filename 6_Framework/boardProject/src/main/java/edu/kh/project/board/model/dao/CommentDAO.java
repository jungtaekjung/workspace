package edu.kh.project.board.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.project.board.model.dto.Comment;

@Repository
public class CommentDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;





	//댓글 목록 조회
	public List<Comment> select(int boardNo) {
		return sqlSession.selectList("boardMapper.selectCommentList",boardNo);
	}








	//댓글 삽입
	public int insert(Comment comment) {
		return sqlSession.insert("commentMapper.insertComment",comment);
	}








	//댓글 수정
	public int update(Comment comment) {
		return sqlSession.update("commentMapper.updateComment",comment);
	}








	//댓글 삭제
	public int delete(Comment comment) {
		return sqlSession.update("commentMapper.deleteComment",comment);
	}





}
