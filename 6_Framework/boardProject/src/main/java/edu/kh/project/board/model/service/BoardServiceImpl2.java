package edu.kh.project.board.model.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import edu.kh.project.board.model.dao.BoardDAO2;
import edu.kh.project.board.model.dto.Board;
import edu.kh.project.board.model.dto.BoardImage;
import edu.kh.project.board.model.exception.FileUploadException;
import edu.kh.project.common.utility.Util;

@Service
public class BoardServiceImpl2 implements BoardService2{

	@Autowired
	private BoardDAO2 dao;

	// 게시글 삽입
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int boardInsert(Board board, List<MultipartFile> images, String webPath, String filePath) throws IllegalStateException, IOException {

		// 0. XSS 방지 처리
		board.setBoardTitle(Util.XSSHandling(board.getBoardTitle()));
		board.setBoardContent(Util.XSSHandling(board.getBoardContent()));


		// 1. BOARD 테이블 insert (제목, 내용, 작성자, 게시판코드)
		// -> boardNo(시퀀스로 생성한 번호) 반환 받기

		int boardNo = dao.boardInsert(board);


		// 2. 게시글 삽입 성공 시
		// 업로드된 이미지가 있다면 BOARD_IMG 테이블에 삽입하는 dao 호출
		if(boardNo !=0) {
			// List<MultipartFile> images
			// -> 업로드된 파일이 담긴 객체
			// -> 단, 업로드된 파일이 없어도 MultipartFile 객체는 존재(5개)
			List<BoardImage> uploadList = new ArrayList<BoardImage>();

			// images에 담겨 있는 파일 중 실제로 업로드된 파일만 분류
			for(int i=0; i<images.size(); i++) {

				if(images.get(i).getSize()>0) { // 업로드한 파일이 있다면
					BoardImage img = new BoardImage();
					img.setImagePath(webPath);

					String fileName = images.get(i).getOriginalFilename();

					img.setImageReName(Util.fileRename(fileName)); // 파일 변경명
					img.setImageOriginal(fileName); // 파일 원본명

					img.setImageOrder(i); // 이미지 순서
					img.setBoardNo(boardNo); // 게시글 번호

					// uploadList에 추가
					uploadList.add(img);

				}
			} // 분류 for문 종료

			// 분류 작업 후 uploadList가 비어있지 않는 경우
			// == 업로드한 파일이 존재
			if(!uploadList.isEmpty()) {

				// BOARD_IMG 테이블에 insert 하기
				int result = dao.insertImageList(uploadList);
				// result == 삽입된 행의 개수

				// 삽입된 행의 개수와 uploadList의 개수가 같다면
				// == 전체 insert 성공
				if(result == uploadList.size()) {
					// 서버에 파일 저장(transferTo())

					for(int i=0; i<uploadList.size(); i++) {
						int index = uploadList.get(i).getImageOrder();
						
						// 변경명
						String rename = uploadList.get(i).getImageReName();
						
						images.get(index).transferTo(new File(filePath + rename));
					}

				}else { // 일부 또는 전체 insert 실패
					// * 웹 서비스 수행 중 1개라도 실패하면 전체 실패 *
					// -> rollback 필요
					
					// @Transactional(rollbackFor = Exception.class)
					// -> 예외가 발생해야만 롤백함
					
					// 따라서 예외를 강제로 발생 시켜서 rollback 해야함
					// -> 사용자 정의 예외 생성
					throw new FileUploadException(); // 예외 강제로 발생
				}

			}

		}
		return boardNo;
	}
}