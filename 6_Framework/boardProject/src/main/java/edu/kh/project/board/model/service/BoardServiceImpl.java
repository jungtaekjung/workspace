package edu.kh.project.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.board.model.dao.BoardDAO;
import edu.kh.project.board.model.dto.Board;
import edu.kh.project.board.model.dto.Pagination;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDAO dao;
	
	// 게시판 종류 목록 조회
	@Override
	public List<Map<String, Object>> selectBoardTypeList() {
		return dao.selectBoardTypeList();
	}
	
	// 게시글 목록조회
	@Override
	public Map<String, Object> selectBoardList(int boardCode, int cp) {
		
		// 1. 특정 게시판의 삭제되지 않은 게시글 수 조회
		int listCount = dao.getListCount(boardCode);
		
		
		// 2. 1번의 조회 결과 + cp를 이용해서 Pagination 객체 생성
		// -> 내부 필드에 계산되어 초기화됨
		Pagination pagination = new Pagination(cp, listCount);
		
		// 3. 특정 게시판에서 현재 페이지에 해당하는 부분에 대한 게시글 목록 조회
		// 어떤 게시판(boardCode)에서
		// 몇 페이지(pagination.currentPage)에 대한
		// 게시글 몇개(pagination.limit) 조회
		
		List<Board> boardList = dao.selectBoardList(boardCode,pagination);
		
		
		// 4. pagination, boardList를 Map에 담아서 반환
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardList", boardList);
		map.put("pagination", pagination);
		
		return map;
	}

	// 게시글 상세 조회
	@Override
	public Board selectBoard(Map<String, Object> map) {
		return dao.selectBoard(map);
	}
	// 좋아요 여부 확인
	@Override
	public int boardLikeCheck(int memberNo, int boardNo) {
		return dao.boardLikeCheck(memberNo,boardNo);
	}

	// 좋아요 처리
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int like(Map<String, Integer> paramMap) {
		int result =0;
		int check = paramMap.get("check");
		
		if(check==0) { // 좋아요 상태 X
			result = dao.likeInsert(paramMap);
			// BOARD_LIKE 테이블 INSERT
			
		}else { // 좋아요 상태 O
			// BOARD_LIKE 테이블 DELETE
			result = dao.likeDelete(paramMap);
		}
		
		// SQL 수행 결과가 0 == DB 또는 파라미터에 문제가 있음
		// -> 에러를 나타내는 임의의 값을 반환(-1)
		if(result ==0) return -1;
		
		// 현재 게시글의 좋아요 개수 조회
			 
			
		return dao.boardLikeCount(paramMap);
	}

	// 조회수 증가 서비스
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int updateReadCount(int boardNo) {
		return dao.updateReadCount(boardNo);
	}

	
}
