package edu.kh.project.board.model.service;

import java.util.List;
import java.util.Map;

import edu.kh.project.board.model.dto.Board;
import edu.kh.project.member.model.dto.Member;

public interface BoardService {

	/** 게시판 종류 목록 조회
	 * @return boardTypeList
	 */
	List<Map<String, Object>> selectBoardTypeList();

	/** 게시글 목록 조회
	 * @param boardCode
	 * @param cp
	 * @return map
	 */
	Map<String, Object> selectBoardList(int boardCode, int cp);

	/** 게시글 상세 조회
	 * @param map
	 * @return board
	 */
	Board selectBoard(Map<String, Object> map);

	//int boardLikeCheck(int boardNo, Member loginMember);

	/** 좋아요 여부확인
	 * @param map
	 * @return
	 */
	int boardLikeCheck(Map<String, Object> map);

	/** 좋아요 처리 서비스
	 * @param paramMap
	 * @return count
	 */
	int like(Map<String, Integer> paramMap);

	
	/** 조회수 증가 서비스
	 * @param boardNo
	 * @return count
	 */
	int updateReadCount(int boardNo);

	
	
	/** 게시글 목록 조회(검색)
	 * @param paramMap
	 * @param cp
	 * @return boardList
	 */
	Map<String, Object> selectBoardList(Map<String, Object> paramMap, int cp);

	Map<String, Object> autocomplete(Map<String, Object> paramMap);

	/** 이미지 목록 조회
	 * @return imageList
	 */
	List<String> selectImageList();

	   /** 헤더 검색
	    * @param query
	    * @return list
	    */
	   List<Map<String, Object>> headerSearch(String query);









}
