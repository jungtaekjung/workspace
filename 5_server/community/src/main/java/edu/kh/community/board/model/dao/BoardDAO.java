package edu.kh.community.board.model.dao;


import static edu.kh.community.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.community.board.model.dto.Board;
import edu.kh.community.board.model.dto.BoardDetail;
import edu.kh.community.board.model.dto.BoardImage;
import edu.kh.community.board.model.dto.Pagination;
import edu.kh.community.member.model.dao.MemberDAO;
public class BoardDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private Properties prop;

	public BoardDAO() {
		try {
			prop = new Properties();

			String filePath 
			= BoardDAO.class.getResource("/edu/kh/community/sql/board-sql.xml").getPath();

			prop.loadFromXML(new FileInputStream(filePath));

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**	게시판 이름 조회
	 * @param type
	 * @param conn
	 * @return boardName
	 * @throws Exception
	 */
	public String selectBoardName(int type, Connection conn) throws Exception{
		String boardName = null;
		try {
			String sql = prop.getProperty("selectBoardName");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, type);

			rs = pstmt.executeQuery();

			if(rs.next()) {
				boardName = rs.getString(1);
			}

		}finally {
			close(rs);
			close(pstmt);
		}



		return boardName;
	}

	/** 특정 게시판 전체 게시글 수 조회
	 * @param type
	 * @param conn
	 * @return listCount
	 * @throws Exception
	 */
	public int getListCount(int type, Connection conn) throws Exception{
		int listCount= 0;
		try {
			String sql = prop.getProperty("getListCount");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, type);

			rs = pstmt.executeQuery();

			if(rs.next()) {
				listCount = rs.getInt(1);
			}

		}finally {
			close(rs);
			close(pstmt);
		}


		return listCount;
	}

	/** 게시글 목록 조회
	 * @param conn
	 * @param pagination
	 * @param type
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> selectBoardList(Connection conn, Pagination pagination, int type) throws Exception{
		List<Board> boardList = new ArrayList<>();

		try {
			String sql = prop.getProperty("selectBoardList");

			// BETWEEN 구문에 들어갈 범위 계산
			int start = (pagination.getCurrentPage() -1) * pagination.getLimit()+1;
			int end = start + pagination.getLimit()-1;

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, type);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				Board board = new Board();
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setMemberNickname(rs.getString("MEMBER_NICK"));
				board.setCreateDate(rs.getString("CREATE_DT"));
				board.setReadCount(rs.getInt("READ_COUNT"));
				board.setThumbnail(rs.getString("THUMBNAIL"));

				boardList.add(board);
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		return boardList;
	}



	/** 게시글 상세조회
	 * @param conn
	 * @param boardNo
	 * @return detail
	 * @throws Exception
	 */
	public BoardDetail selectBoardDetail(Connection conn, int boardNo) throws Exception{

		BoardDetail detail = null;

		try {
			String sql = prop.getProperty("boardDetail");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, boardNo);

			rs = pstmt.executeQuery();

			if(rs.next()) {
				detail = new BoardDetail();
				detail.setBoardNo(rs.getInt("BOARD_NO"));
				detail.setBoardTitle(rs.getString("BOARD_TITLE"));
				detail.setBoardContent(rs.getString("BOARD_CONTENT"));
				detail.setCreateDate(rs.getString("CREATE_DT"));
				detail.setUpdateDate(rs.getString("UPDATE_DT"));
				detail.setReadCount(rs.getInt("READ_COUNT"));
				detail.setMemberNo(rs.getInt("MEMBER_NO"));
				detail.setMemberNickname(rs.getString("MEMBER_NICK"));
				detail.setProfileImage(rs.getString("PROFILE_IMG"));
				detail.setBoardName(rs.getString("BOARD_NM"));
			}

		}finally {
			close(rs);
			close(pstmt);

		}
		return detail;
	}

	/** 게시글에 첨부된 이미지 조회
	 * @param conn
	 * @param boardNo
	 * @return imageList
	 * @throws Exception
	 */
	public List<BoardImage> selectImageList(Connection conn, int boardNo) throws Exception{
		List<BoardImage> imageList = new ArrayList<>();

		try {
			String sql = prop.getProperty("selectImageList");

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				BoardImage boardImage = new BoardImage();

				boardImage.setImageNo(rs.getInt("IMG_NO"));
				boardImage.setImageRename(rs.getString("IMG_RENAME"));
				boardImage.setImageOriginal(rs.getString("IMG_ORIGINAL"));
				boardImage.setImageLevel(rs.getInt("IMG_LEVEL"));
				boardImage.setBoardNo(boardNo);

				imageList.add(boardImage);
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return imageList;
	}

	/** 다음 게시글 번호 조회
	 * @param conn
	 * @return boardNo
	 * @throws Exception
	 */
	public int nextBoardNo(Connection conn) throws Exception{

		int boardNo = 0;

		try {
			String sql = prop.getProperty("nextBoardNo");

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			if(rs.next()) {
				boardNo = rs.getInt(1);
			}

		}finally {
			close(rs);
			close(stmt);
		}


		return boardNo;
	}

	/** 게시글 등록
	 * @param detail
	 * @param boardCode
	 * @param conn 
	 * @return result
	 */
	public int insertBoard(BoardDetail detail, int boardCode, Connection conn) throws Exception{
		
		int result = 0;
		System.out.println(detail);
		try {
			String sql = prop.getProperty("insertBoard");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, detail.getBoardNo());
			pstmt.setString(2, detail.getBoardTitle());
			pstmt.setString(3, detail.getBoardContent());
			pstmt.setInt(4, detail.getMemberNo());
			pstmt.setInt(5, boardCode);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertBoardImage(Connection conn, BoardImage image) throws Exception{
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertBoardImage");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, image.getImageRename());
			pstmt.setString(2, image.getImageOriginal());
			pstmt.setInt(3, image.getImageLevel());
			pstmt.setInt(4, image.getBoardNo());
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		return result;
	}

	/** 게시글 삭제
	 * @param boardNo
	 * @param conn 
	 * @return result
	 * @throws Exception
	 */
	public int deleteBoard(int boardNo, Connection conn) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("deleteBoard");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,boardNo);
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}

	/** 게시글 수정
	 * @param detail
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int updateBoard(BoardDetail detail, Connection conn) throws Exception{
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateBoard");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, detail.getBoardTitle());
			pstmt.setString(2, detail.getBoardContent());
			pstmt.setInt(3, detail.getBoardNo());
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 게시글 이미지 수정
	 * @param conn
	 * @param img
	 * @return result
	 * @throws Exception
	 */
	public int updateBoardImage(Connection conn, BoardImage img) throws Exception{

		int result = 0;
		
		try {
			String sql = prop.getProperty("updateBoardImage");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, img.getImageRename());
			pstmt.setString(2, img.getImageOriginal());
			pstmt.setInt(3, img.getBoardNo());
			pstmt.setInt(4, img.getImageLevel());
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 게시글 이미지 삭제
	 * @param conn
	 * @param boardNo
	 * @param deleteList
	 * @return result
	 * @throws Exception
	 */
	public int deleteBoardImage(Connection conn, int boardNo, String deleteList) throws Exception{
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("deleteBoardImage") + deleteList +")";
							// 완성되지 않은 SQL
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		
		return result;
	}



}
