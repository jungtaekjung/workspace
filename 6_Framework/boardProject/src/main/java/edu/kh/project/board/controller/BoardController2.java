package edu.kh.project.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.board.model.dto.Board;
import edu.kh.project.board.model.service.BoardService2;
import edu.kh.project.member.model.dto.Member;

@Controller
@RequestMapping("/board2")
@SessionAttributes("loginMember")
public class BoardController2 {
	
	@Autowired
	private BoardService2 service;

	// *** @PathVariable 사용 시 문제점과 해결 방법 ***
	// 문제점 : 요청 주소와 @PathVariable로 가져다 쓸 주소의 레벨이 같다면
	//		  구분하지 않고 모두 매핑되는 문제가 발생
	
	// 해결 방법 : @PathVariable 지정 시 정규 표현식 사용
	//			{키 : 정규표현식}
	
	// 게시글 작성 화면 전환
	@GetMapping("/{boardCode:[0-9]+}/insert")
	public String boardInsert(@PathVariable("boardCode") int boardCode) {
		// @PathVariable : 주소 값 가져오기 + request scope에 세팅
		System.out.println("boardCode : " + boardCode);
		
		return "board/boardWrite";
	}
	
	// 게시글 작성
	@PostMapping("/{boardCode:[0-9]+}/insert")
	public String boardInsert(@PathVariable("boardCode") int boardCode
							  ,Board board // 커맨드 객제(필드에 전달받은 파라미터 값 담겨있음)
							  , @RequestParam(value="images", required=false) List<MultipartFile> images
							  , @SessionAttribute("loginMember") Member loginMember
							  , RedirectAttributes ra	
							  , HttpSession session
			) throws IllegalStateException, IOException {
		// 파라미터 : 제목, 내용, 파일(0~5)
		// 세션 : 로그인한 회원의 번호
		// 리다이렉트 시 데이터 전달 : RedirectAttributes
		// 파일 저장 경로 : HttpSession
		// 게시판 코드 : @PathVariable("boardCode")
		
		/* List<MultipartFile>
		 * - 업로드된 이미지가 없어도 List에 MultipartFile 객체가 추가됨
		 * - 단, 업로드된 이미지가 없는 MultipartFile 객체는
		 * 	 파일크기(size)가 0 또는 파일명(getOriginalFileName)이 ""
		 * 
		 * */
		
		// 1. 로그인한 회원번호, boardCode를 board에 세팅
		board.setMemberNo(loginMember.getMemberNo());
		board.setBoardCode(boardCode);
		
		// 2. 업로드된 이미지 서버에 실제로 저장되는 경로와
		//	  웹에서 요청 시 이미지를 볼 수 있는 경로(웹 접근 경로)
		String webPath ="/resources/images/board/";
		String filePath = session.getServletContext().getRealPath(webPath);
		
		// 3. 게시글 삽입 서비스 호출 후 게시글 번호 반환 받기
		int boardNo = service.boardInsert(board, images, webPath, filePath);
		
		System.out.println(board);
		String message = null;
		String path = "redirect:";
		
		if(boardNo>0) { // 게시글 삽입 성공 시
			// -> 방금 삽입한 게시글의 상세 조회 페이지로 리다이렉트
			// -> /board/{boardCode}/{boardNo}
			path += "/board/" + boardCode + "/" + boardNo;
			message = "게시글이 성공적으로 등록되었습니다.";
			
		}else {// 게시글 삽입 실패 시
			// -> 게시글 작성 페이지로 리다이렉트
			path +="insert";
			message="게시글 등록에 실패했습니다";
		}
		ra.addFlashAttribute("message",message);
		
		return path;
	}
	
}
