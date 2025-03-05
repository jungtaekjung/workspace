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
		// -offset 만큼 건너 뛰고
		// 그 다음 지정된 행의 개수(limit) 만큼 조회
		
		// 1) offset 계산
		int offset = (pagination.getCurrentPage()-1 )* pagination.getLimit();
		
		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset,pagination.getLimit());
		
		// 3) selectList("nameSpace.id",파라미터,RowBounds) 호출
		
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
	 * @param map
	 * @return
	 */
	public int boardLikeCheck(Map<String, Object> map) {
		return sqlSession.selectOne("boardMapper.boardLikeCheck",map);
	}



	/** 좋아요 테이블 삽입 insert
	 * @param paramMap
	 * @return
	 */
	public int insertLike(Map<String, Integer> paramMap) {
		return sqlSession.insert("boardMapper.insertLike",paramMap);
	}



	/** 좋아요 테이블 삭제 delete
	 * @param paramMap
	 * @return
	 */
	public int deleteLike(Map<String, Integer> paramMap) {
		return sqlSession.delete("boardMapper.deleteLike",paramMap);
	}



	public int selectLike(Map<String, Integer> paramMap) {
		return sqlSession.selectOne("boardMapper.selectLike",paramMap);
	}



	/** 조회수 증가
	 * @param boardNo
	 * @return count
	 */
	public int updateReadCount(int boardNo) {
		return sqlSession.update("boardMapper.updateReadCount",boardNo);
	}



	public int deleteBoard(Board board) {
		return sqlSession.update("boardMapper.deleteBoard",board);
	}



	/** 게시글 수 조회(검색)
	 * @param paramMap
	 * @return listCount
	 */
	public int getListCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("boardMapper.getSearchListCount",paramMap);
	}



	/** 게시글 목록 조회(검색)
	 * @param paramMap
	 * @param pagination
	 * @return boardList
	 */
	public List<Board> selectBoardList(Map<String, Object> paramMap, Pagination pagination) {
		int offset = (pagination.getCurrentPage()-1 )* pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset,pagination.getLimit());
		
		
		return sqlSession.selectList("boardMapper.searchBoardList", paramMap, rowBounds);
	}


	/*자동완성
	 * */
	public List<Board> autocomplete(Map<String, Object> paramMap) {
		return sqlSession.selectList("boardMapper.autocomplete",paramMap);
	}



	public List<String> selectImageList() {
		return sqlSession.selectList("boardMapper.selectImageListAll");
	}



	   /** 헤더 검색
	    * @param query
	    * @return list
	    */
	   public List<Map<String, Object>> headerSearch(String query) {
	      return sqlSession.selectList("boardMapper.headerSearch", query);
	   }
}
