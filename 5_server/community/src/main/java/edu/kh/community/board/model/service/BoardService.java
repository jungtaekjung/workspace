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
import edu.kh.community.common.Util;


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

	/** 게시글 등록 service
	 * @param detail
	 * @param imageList
	 * @param boardCode
	 * @return boardNo
	 * @throws Exception
	 */
	public int insertBoard(BoardDetail detail, List<BoardImage> imageList, int boardCode) throws Exception{

		Connection conn = getConnection();


		// 1. 다음 작성할 게시글 번호 얻어오기
		// -> BOARD 테이블 INSERT / BOARD_IMG 테이블 INSERT / 반환값
		int boardNo = dao.nextBoardNo(conn);

		// 2. 게시글 부분만 삽입(detail, boardCode 사용)
		detail.setBoardNo(boardNo); // 조회된 다음 게시글 번호 세팅

		// 2-1 XSS 방지 처리(제목, 내용)

		detail.setBoardTitle(Util.XSSHandling(detail.getBoardTitle()));
		detail.setBoardContent(Util.XSSHandling(detail.getBoardContent()));

		// 2-2 개행문자 처리(내용)
		detail.setBoardContent(Util.newLineHandling(detail.getBoardContent()));

		int result = dao.insertBoard(detail,boardCode,conn);

		if(result >0) { // 게시글 삽입 성공 시

			// 3. 이미지 정보만 삽입(imageList 사용)
			for(BoardImage image : imageList) { // 하나씩 꺼내서 DAO 수행
				image.setBoardNo(boardNo); // 게시글 번호 세팅

				result = dao.insertBoardImage(conn, image);

				if(result==0) break; // 이미지 삽입 실패 시

			} // for문 끝

		} // if문 끝

		// 트랜잭션 제어
		if(result > 0) {
			commit(conn);
		}else { // 2, 3번에서 한번이라도 실패한 경우
			rollback(conn);
			boardNo = 0; // 게시글 번호를 0으로 바꿔서 실패했음을 컨트롤러로 전달
		}

		close(conn);

		return boardNo;
	}

	/** 게시글 삭제
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteBoard(int boardNo) throws Exception{

		Connection conn = getConnection();

		int result = dao.deleteBoard(boardNo, conn);

		if(result !=0) commit(conn);
		else		   rollback(conn);

		close(conn);
		return result;
	}

	/** 게시글 수정
	 * @param detail
	 * @param imageList
	 * @param deleteList
	 * @return result
	 * @throws Exception
	 */
	public int updateBoard(BoardDetail detail, List<BoardImage> imageList, String deleteList) throws Exception{

		Connection conn =getConnection();


		// 1. 게시글 부분(제목, 내용, 마지막 수정일) 수정

		// 1-1) XSS 방지 처리(제목, 내용)
		detail.setBoardTitle(Util.XSSHandling(detail.getBoardTitle()));
		detail.setBoardContent(Util.XSSHandling(detail.getBoardContent()));

		// 1-2) 개행문자 처리(내용)
		detail.setBoardContent(Util.newLineHandling(detail.getBoardContent()));

		// 1-3) DAO 호출
		int result = dao.updateBoard(detail, conn);

		if(result >0) { //게시글 수정 성공 시

			// 2. 이미지 부분 수정 (기존-> 변경, 없다가 -> 추가)

			for(BoardImage img : imageList ) {
				// 게시글 번호 세팅
				img.setBoardNo(detail.getBoardNo());

				// img(변경명, 원본명, 게시글번호, 이미지레벨)
				result = dao.updateBoardImage(conn,img);
				// result == 1 : 수정 성공
				// result == 0 : 수정 실패 -> 기존에 없다가 새로 추가된 이미지 -> insert 진행

				if(result ==0) {
					result = dao.insertBoardImage(conn, img);
				}

			}

			// 3. 이미지 삭제
			// 삭제된 이미지 레벨이 기록되어 있을 때만 삭제
			// deleteList(값 : "1,2,3" / 값 : ""빈 문자열)
			if(!deleteList.equals("")) {
				result = dao.deleteBoardImage(conn, detail.getBoardNo(),deleteList);

			}

		}

		if(result>0) commit(conn);
		else 		 rollback(conn);

		close(conn);
		return result;
	}


}
