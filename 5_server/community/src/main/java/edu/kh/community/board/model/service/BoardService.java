package edu.kh.community.board.model.service;

import static edu.kh.community.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.kh.community.board.model.dao.BoardDAO;
import edu.kh.community.board.model.dto.Board;
import edu.kh.community.board.model.dto.BoardDetail;
import edu.kh.community.board.model.dto.BoardImage;
import edu.kh.community.board.model.dto.Pagination;


public class BoardService {
	private BoardDAO dao = new BoardDAO();

	/**	게시글 목록 조회
	 * @param cp
	 * @param type
	 * @return map
	 * @throws Exception
	 */
	public Map<String, Object> selectBoardList(int cp, int type) throws Exception{

		// 1. Connection 얻어오기
		Connection conn = getConnection();

		// 2. 게시판 이름 조회 DAO 호출
		String boardName = dao.selectBoardName(type,conn);

		// 3-1. 특정 게시판 전체 게시글 수 조회 DAO 호출
		int listCount = dao.getListCount(type,conn);

		// 3-2. 전체 게시글 수 + 현재 페이지(cp)를 이용해 페이지네이션 객체 생성
		Pagination pagination = new Pagination(cp, listCount);

		// 4. 게시글 목록 조회
		List<Board> boardList = dao.selectBoardList(conn,pagination,type);

		// 5. Map 객체를 생성하여 결과 객체 모두 저장
		Map<String, Object> map = new HashMap<>();
		map.put("boardName", boardName);
		map.put("pagination", pagination);
		map.put("boardList", boardList);

		// 6. 사용한 자원 반환
		close(conn);
		// 7. 결과 반환
		return map;




	}

	/** 게시글 상세조회
	 * @param boardNo
	 * @return detail
	 */
	public BoardDetail selectBoardDetail(int boardNo) throws Exception{

		Connection conn = getConnection();

		// 1) 게시글(Board 테이블) 관련 내용만 조회
		BoardDetail detail = dao.selectBoardDetail(conn,boardNo);

		if(detail != null) {// 게시글 상세조회 결과가 있을 경우

			// 2) 게시글에 첨부된 이미지(BOARD_IMG 테이블) 조회
			List<BoardImage> imageList = dao.selectImageList(conn,boardNo);


			// 3) 조회된 imageList를 BoardDetail 객체에 세팅
			detail.setImageList(imageList);

		}

		close(conn);

		return detail;
	}

	
}
