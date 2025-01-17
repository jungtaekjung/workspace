package edu.kh.community.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import edu.kh.community.board.model.dto.BoardDetail;
import edu.kh.community.board.model.dto.BoardImage;
import edu.kh.community.board.model.service.BoardService;
import edu.kh.community.common.MyRenamePolicy;
import edu.kh.community.member.model.dto.Member;

// 컨트롤러 : 요청에 따라 알맞은 Service를 호출하고 결과에 따라 응답을 제어
@WebServlet("/board/write")
public class BoardWriteController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		try {
			String mode = req.getParameter("mode"); // insert/update 구분

			// insert는 별도 처리 없이 jsp로 위임

			// update는 기존 게시글 내용을 조회하는 처리 필요
			if(mode.equals("update")) {


			}

			String path = "/WEB-INF/views/board/boardWriteForm.jsp";
			req.getRequestDispatcher(path).forward(req, resp);

		}catch(Exception e) {
			e.printStackTrace();
		}



	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			// *** enctype="multipart/form-data" 인코딩 미지정 형식의 요청 ***
			// -> HttpServletRequest로 파라미터 얻어오기 불가능!
			// --> MultipartRequest를 이용(cos.jar 라이브러리 제공)
			// ---> 업로드 최대 용량, 저장 실제 경로, 파일명 변경 정책, 문자 파라미터 인코딩 설정 필요


			// 필요한 파라미터 정리
			int maxSize = 1024 * 1024 * 100; // 업로드 최대 용량 (100MB)

			HttpSession session = req.getSession();

			String root = session.getServletContext().getRealPath("/");

			String folderPath ="/resources/images/board/";

			String filePath = root + folderPath;

			String encoding = "UTF-8";

			// ** MultipartRequest 객체 생성과 동시에 파라미터로 전달된 파일이
			// 지정된 경로에 저장(업로드) 된다
			MultipartRequest mpReq = new MultipartRequest(req, filePath, maxSize, encoding, new MyRenamePolicy());

			// MultipartRequest.getFileNames()
			// - 요청 파라미터 중 모든 file 타입 태그의 name 속성 값을 얻어와
			//	 Enumeration 형태로 반환 (Iterator의 과거 버전)

			// --> 해당 객체에 여러 값이 담겨 있고 이를 순서대로 얻어오는 방법을 제공
			// (보통 순서가 없는 모음(Set과 같은 경우)에서 하나씩 꺼낼 때 사용)

			Enumeration<String> files =mpReq.getFileNames();
			// file 타입 태그의 name 속성 0,1,2,3,4가 순서가 섞인 상태로 얻어와짐

			
			// 업로드된 이미지의 정보를 모아둘 List 생성
			List<BoardImage> imageList = new ArrayList<>();
			
			while(files.hasMoreElements()) { // 다음 요소가 있으면 true 
				String name= files.nextElement(); // 다음 요소(name 속성 값)을 얻어옴

				// System.out.println("name" + name);
				// file 타입 태그의 name 속성 값 얻어옴
				// + 업로드가 안된 file 타입 태그의 name도 얻어옴

				// 변경된 파일명
				String rename = mpReq.getFilesystemName(name);
				
				// 원본 파일명
				String original = mpReq.getOriginalFileName(name);
				
				//System.out.println("rename :" + rename);
				//System.out.println("original:" + original);
			
			
				if(rename != null) { // 업로드된 파일이 있을 경우
									 // 현재 files에서 얻어온 name 속성을 이용해
									 // 원본 또는 변경명을 얻어왔을 때 그 값이 null이 아닌 경우
					
				// 이미지 정보를 담은 객체(BoardImage)를 생성
				BoardImage image =new BoardImage();
				image.setImageLevel(Integer.parseInt(name)); // 이미지위치
				image.setImageOriginal(original); // 원본명(다운로드 시 사용)
				image.setImageRename(folderPath + rename); // 폴더 경로 + 변경명 
				
				imageList.add(image);
				} // if문 끝
			} // while문 끝

			// 이미지를 제외한 게시글 관련 정보 얻어오기
			String boardTitle = mpReq.getParameter("boardTitle");
			String boardContent = mpReq.getParameter("boardContent");
			System.out.println(boardContent);
			
			int boardCode = Integer.parseInt(mpReq.getParameter("type"));
			String mode = mpReq.getParameter("mode");
			
			Member loginMember =(Member)session.getAttribute("loginMember");
			int memberNo = loginMember.getMemberNo();
			
			// 게시글 관련 정보를 하나의 객체(BoardDetail)에 담기
			BoardDetail detail = new BoardDetail();
			
			detail.setBoardTitle(boardTitle);
			detail.setBoardContent(boardContent);
			detail.setMemberNo(memberNo);
			// boardCode는 별도 매개변수로 전달 예정
			
			// ----------- 게시글 작성에 필요한 기본 파라미터 얻어오기 끝 --------------------
			
			BoardService service = new BoardService();
			
			// 모드(insert/update)에 따라서 서비스 호출
			if(mode.equals("insert")) { // 게시글 삽입
				
				// 게시글 삽입 서비스 호출 후 결과 반환 받기
				// -> 반환된 게시글 번호를 이용해서 상세조회로 리다이렉트 예정
				int boardNo = service.insertBoard(detail,imageList,boardCode);
				
				String path = null;
				
				if(boardNo != 0) { // 성공
					session.setAttribute("message", "게시글이 등록되었습니다.");
					path = "detail?no=" + boardNo +"&type="+boardCode;
				}else { // 실패
					session.setAttribute("message", "게시글 등록 실패");
					path = "write?mode=insert";
				}
				resp.sendRedirect(path);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
