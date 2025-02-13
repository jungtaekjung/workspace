package edu.kh.project.board.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.project.board.model.dto.Board;
import edu.kh.project.board.model.dto.Pagination;

@Repository
public class BoardDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	/** 게시판 종류 목록 조회
	 * @return boardTypeList
	 */
	public List<Map<String, Object>> selectBoardTypeList() {
		return sqlSession.selectList("boardMapper.selectBoardTypeList");
	}

	

	

	/** 특정 게시판의 삭제되지 않은 게시글 수 조회
	 * @param boardCode
	 * @return listCount
	 */
	public int getListCount(int boardCode) {
		
		return sqlSession.selectOne("boardMapper.getListCount",boardCode);
	}

	/** 특정 게시판에서 현재 페이지에 해당하는 부분에 대한 게시글 목록 조회
	 * @param boardCode
	 * @param pagination
	 * @return boardList
	 */
	public List<Board> selectBoardList(int boardCode, Pagination pagination) {
		
		// RowBounds 객체
		// - 마이바티스에서 페이징 처리를 위해 제공하는 객체
		// - offset 만큼 건너 뛰고
		//   그 다음 지정된 행의 개수(limit)만큼 조회
		
		// 1) offset 계산
		int offset = (pagination.getCurrentPage() -1) * pagination.getLimit();
		
		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		// 3) selectList("namespace.id", 파라미터, RowBounds) 호출
		return sqlSession.selectList("boardMapper.selectBoardList", boardCode, rowBounds);
	
	}





	/** 게시글 상세 조회
	 * @param map
	 * @return board
	 */
	public Board selectBoard(Map<String, Object> map) {
		return sqlSession.selectOne("boardMapper.selectBoard",map);
	}





	/** 좋아요 여부 확인
	 * @param memberNo
	 * @param boardNo
	 * @return result
	 */
	public int boardLikeCheck(int memberNo, int boardNo) {
		Board board = new Board();
		board.setMemberNo(memberNo);
		board.setBoardNo(boardNo);
		return sqlSession.selectOne("boardMapper.boardLikeCheck",board);
	}





	/** 좋아요 테이블 삽입
	 * @param paramMap
	 * @return result
	 */
	public int likeInsert(Map<String, Integer> paramMap) {
		
		return sqlSession.insert("boardMapper.likeInsert",paramMap);
	}





	/** 좋아요 테이블 삭제
	 * @param paramMap
	 * @return result
	 */
	public int likeDelete(Map<String, Integer> paramMap) {
		return sqlSession.delete("boardMapper.likeDelete",paramMap);
	}





	/** 좋아요 갯수 조회
	 * @param paramMap
	 * @return count
	 */
	public int boardLikeCount(Map<String, Integer> paramMap) {
		return sqlSession.selectOne("boardMapper.boardLikeCount",paramMap);
	}





	/** 조회수 증가
	 * @param boardNo
	 * @return count
	 */
	public int updateReadCount(int boardNo) {
		return sqlSession.update("boardMapper.updateReadCount",boardNo);
	}
}
