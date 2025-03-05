package edu.kh.project.board.model.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import edu.kh.project.board.model.dao.BoardDAO2;
import edu.kh.project.board.model.dto.Board;
import edu.kh.project.board.model.dto.BoardImage;
import edu.kh.project.board.model.exception.FileUploadException;
import edu.kh.project.board.model.exception.ImageDeleteException;
import edu.kh.project.common.utility.Util;

@Service
public class BoardServiceImpl2 implements BoardService2{

	@Autowired
	private BoardDAO2 dao;


	//게시글 삽입
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int boardInsert(Board board, List<MultipartFile> images, String webPath, String filePath) throws IllegalStateException, IOException {

		// 0. XSS 방지 처리
		board.setBoardTitle(Util.XSSHandling(board.getBoardTitle()));
		board.setBoardContent(Util.XSSHandling(board.getBoardContent()));


		// 1. BOARD 테이블 insert (제목,내용,작성자,게시판코드)
		// -> boardNo(시퀀스로 생성한 번호) 반환 받기


		int boardNo = dao.boardInsert(board);

		//2. 게시글 삽입 성공 시
		//	 업로드된 이미지가 있다면 BOARD_IMG 테이블에 삽입하는 dao 호출
		if(boardNo !=0) {
			// List<MultipartFile> images
			// -> 업로드된 파일이 담긴 객체
			// -> 단, 업로드된 파일이 없어도 MultipartFile 객체는 존재(5개)


			//실제로 업로드된 파일의 정보를 기록할 List

			List<BoardImage> uploadList = new ArrayList<BoardImage>();

			//images에 담겨 있는 파일 중 실제로 업로드된 파일만 분류

			for(int i=0; i<images.size(); i++) {
				//i 번째 요소에 업로드한 파일이 있다면
				if (images.get(i).getSize()>0) { 

					BoardImage img = new BoardImage();

					//img 에 파일 정보를 담아서 uploadList에 추가
					img.setImagePath(webPath);  //웹 접근 경로

					String fileName = images.get(i).getOriginalFilename();
					//					String renameFileName = Util.fileRename(fileName);

					//파일 변경명
					img.setImageReName(Util.fileRename(fileName));
					//파일 원본명
					img.setImageOriginal(fileName); 

					img.setImageOrder(i);// 이미지 순서
					img.setBoardNo(boardNo);// 게시글번호

					//uploadList에 추가
					uploadList.add(img);
				}

			}//분류 for문 종료

			//분류 작업 후 uploadList가 비어있지 않는 경우
			// == 업로드한 파일이 존재
			if(!uploadList.isEmpty()) {

				//BOARD_IMG 테이블에 insert 하기

				int result= dao.insertImageList(uploadList);
				//result == 삽입된 행의 개수

				//삽입된 행의 개수와 uploadList의 개수가 같다면
				// ==전체 insert 성공
				if(result==uploadList.size()) {
					//서버에 파일 저장(transforTo())

					//images : 실제 파일이 담긴 객체 리스트 (업로드 안된 인덱스 빈칸)

					//uploadList : 업로드된 파일의 정보 리스트(원본명,변경명,순서,경로,게시글번호)

					//순서 == images 업로드된 인덱스

					for(int i=0; i<uploadList.size(); i++) {
						int index = uploadList.get(i).getImageOrder();
						
						//변경명
						String rename= uploadList.get(i).getImageReName();


						images.get(index).transferTo( new File(filePath + rename) );
					}


				}else { //일부 또는 전체 insert 실패
					// * 웹 서비스 수행 중 1개라도 실패하면 전체 실패 *
					// -> rollback 필요
					
					// @Transactional(rollbackFor = Excepion.class)
					// -> 예외가 발생해야만 롤백함
					
					// 따라서 예외를 강제로 발생 시켜서 rollback 해야함!
					// -> 사용자 정의 예외 생성
					throw new FileUploadException(); //예외 강제 발생
					
				}


			}
		}

		return boardNo;
	}


	
	//게시글 수정
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int boardUpdate(Board board, List<MultipartFile> images, String webPath, String filePath,
			String deleteList) throws IllegalStateException, IOException {

		//XSS 방지 처리
		board.setBoardTitle(Util.XSSHandling(board.getBoardTitle()));
		board.setBoardContent(Util.XSSHandling(board.getBoardContent()));
		
		
		


		//1. 게시글 제목/내용만 수정
		int result = dao.boardUpdate(board);
		
		//2. 게시글 수정 성공 했을 때
		
		if(result>0) {
			//3. 삭제할 이미지가 존재한다면 
			//deleteList에 작성된 이미지 모두 삭제
			if(!deleteList.equals("")) {
				
				/* java에서 확인
				// 3-1)삭제할 이미지의 ORDER 확인을 위한 dao 호출
				//->현재 게시글의 IMG_ORDER 조회
				List<String> orderList = dao.checkImageOrder(board.getBoardNo());
				System.out.println(orderList);
				
				// 3-2)조회해온 IMG_ORDER가 deleteList에 존재한다면
				// orderList : 0,1
				// deleteList : "2,3,4"
				String[] deleteArr = deleteList.split(","); 
				
				boolean flag = false;
				for(int i=0; i<orderList.size(); i++) {
					for(int j=0; j<deleteArr.length; j++) {
						
						if(orderList.get(i).equals(deleteArr[j])) { //일치하는 게 있는 경우
							flag=true;
							break;
						}
					}
				}
				
				if(flag) {
					//3-3)deleteList에 작성된 이미지 모두 삭제
				}
				*/
				
				
				Map<String, Object> deleteMap= new HashMap<String, Object>();
				deleteMap.put("boardNo", board.getBoardNo());
				deleteMap.put("deleteList", deleteList);
				
				/* DB에서 조회*/
				// deleteList가 존재하는 게시글 이미지인 경우
				// 왜?->deleteList와 IMG_ORDER에 일치하는 값이 하나도 없으면 에러 발생
				int count = dao.checkImage(deleteMap);
				if(count >0) {
					
					//3-3)deleteList에 작성된 이미지 모두 삭제
					
					result = dao.imageDelete(deleteMap);
					if(result==0) {
						throw new ImageDeleteException(); //예외 강제 발생
					}
				}
				
			}
			
			//4. 새로 업로드된 이미지 분류 작업
			//이미지 수정 -> 실패 시 이미지 삽입
			
			List<BoardImage> uploadList = new ArrayList<BoardImage>();

			//images에 담겨 있는 파일 중 실제로 업로드된 파일만 분류

			for(int i=0; i<images.size(); i++) {
				//i 번째 요소에 업로드한 파일이 있다면
				if (images.get(i).getSize()>0) { 

					BoardImage img = new BoardImage();

					//img 에 파일 정보를 담아서 uploadList에 추가
					img.setImagePath(webPath);  //웹 접근 경로

					String fileName = images.get(i).getOriginalFilename();
					//					String renameFileName = Util.fileRename(fileName);

					//파일 변경명
					img.setImageReName(Util.fileRename(fileName));
					//파일 원본명
					img.setImageOriginal(fileName); 

					img.setImageOrder(i);// 이미지 순서
					img.setBoardNo(board.getBoardNo());// 게시글번호

					//uploadList에 추가
					uploadList.add(img);
					
					//오라클은 다중UPDATE를 지원하지 않기 때문에
					//하나씩 UPDATE 수행
					result=dao.imageUpdate(img);
					
					if(result==0) { //수정 실패 ==DB에 이미지 없음
									//-> 이미지 삽입 진행
						result=dao.imageInsert(img);
						
					}
					
				}
				

			}//분류 for문 종료
			if(!uploadList.isEmpty()) {
				
				for(int i=0; i<uploadList.size(); i++) {
					int index = uploadList.get(i).getImageOrder();
					
					//변경명
					String rename= uploadList.get(i).getImageReName();
					
					
					images.get(index).transferTo( new File(filePath + rename) );
				}
				
			}
			
			
			
		}
		
		
		
		return result;
	}


	//게시글 삭제
	@Override
	public int deleteBoard(int boardNo) {
		return dao.boardDelete(boardNo);
	}

}
