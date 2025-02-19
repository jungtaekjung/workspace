package edu.kh.project.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.board.model.dto.Board;
import edu.kh.project.board.model.service.BoardService;
import edu.kh.project.board.model.service.BoardService2;
import edu.kh.project.member.model.dto.Member;

@Controller
@RequestMapping("/board2")
public class BoardController2 {
	
	@Autowired
	private BoardService2 service;
	
	@Autowired //게시글 수정 시 상세조회 서비스 호출용
	private BoardService boardService;
	
	// *** @PathVariable 사용 시 문제점과 해결 방법 ***
	// 문제점 : 요청 주소와 @PathVariable로 가져다 쓸 주소의 레벨이 같다면
	//			구분하지 않고 모두 매핑되는 문제가 발생
	
	// 해결 방법 : @PathVariable 지정 시 정규 표현식 사용
	//			{키:정규표현식}
	
	// 게시글 작성 화면 전환
	
	@GetMapping("/{boardCode:[0-9]+}/insert")
	public String boardInsert(@PathVariable("boardCode") int boardCode) {
		// @PathVariable : 주소 값 가져오기 + request scope에 세팅
		return "board/boardWrite";
	}
	
	//게시글 작성
	@PostMapping("/{boardCode:[0-9]+}/insert")
	public String boardInsert(@PathVariable("boardCode") int boardCode
			,Board board // 커맨드 객체(필드에 전달받은 파라미터 값 담겨있음)
			,@RequestParam(value="images", required=false) List<MultipartFile> images
			,@SessionAttribute("loginMember")Member loginMember
			,RedirectAttributes ra
			,HttpSession session) throws IllegalStateException, IOException {
		
		//파라미터 : 제목,내용,파일(0~5)
		//세션 : 로그인한 회원의 번호
		//리다이렉트 시 데이터 전달 : RedirectAttributes
		//파일 저장 경로 : HttpSession
		//게시판 코드 : @PathVariable("boardCode")
		
		
		/* List<MultipartFile>
		 * -업로드된 이미지가 없어도 List에 MultipartFile 객체가 추가됨
		 * 
		 * -단, 업로드된 이미지가 없는 MultipartFile 객체는 
		 * 	파일크기(size)가 0 또는 파일명(getOriginalFileName())이 ""(빈칸)
		 * */
		
		//1.로그인한 회원 번호와 boardCode를 board에 세팅
		board.setBoardCode(boardCode);
		board.setMemberNo(loginMember.getMemberNo());
		
		//2.업로드된 이미지 서버에 실제로 저장되는 경로와
		//	웹에서 요청 시 이미지를 볼 수 있는 경로(웹 접근 경로)
		String webPath="/resources/images/board/";
		String filePath=session.getServletContext().getRealPath(webPath);
		
		//3. 게시글 삽입 서비스 호출 후 게시글 번호 반환 받기
		int boardNo = service.boardInsert(board, images,webPath,filePath);
		String message=null;
		String path="redirect:";
		
		if(boardNo>0) {			//게시글 삽입 성공 시
			// -> 방금 삽입한 게시글의 상세 조회 페이지로 리다이렉트
			// -> /board/{boardCode}/{boardNo}
			message="게시글이 등록되었습니다.";
			path+="/board/"+ boardCode + "/" + boardNo;
			
			
		}else {//게시글 삽입 실패 시
			// -> 게시글 작성 페이지로 리다이렉트
			message="게시글 등록 실패.";
			path+="insert";
			
		}
		
		ra.addFlashAttribute("message",message);
		return path;
		
	}
	
	//게시글 수정 화면 전환
	@GetMapping("/{boardCode:[0-9]+}/{boardNo}/update")
	public String boardUpdate(@PathVariable("boardCode") int boardCode,
								@PathVariable("boardNo") int boardNo,
								Model model //데이터 전달용 객체(기본 request scope)
								) {
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("boardCode", boardCode);
		map.put("boardNo", boardNo);
		
		//게시글 상세 조회 서비스 호출
		Board board = boardService.selectBoard(map);
		
		model.addAttribute("board",board);
				
		
		return "board/boardUpdate";
	}
	
	
	//게시글 수정
	@PostMapping("/{boardCode}/{boardNo}/update")
	public String boardUpdate(
			@PathVariable("boardCode") int boardCode,
			@PathVariable("boardNo") int boardNo,
			@RequestParam(value="cp" ,required=false, defaultValue="1") String cp ,// 쿼리스트링
			@RequestParam(value="deleteList" ,required=false) String deleteList ,// 삭제할 이미지 순서
			@RequestParam(value="images", required=false) List<MultipartFile> images, // 업로드된 파일 리스트
			Board board, //커맨드 객체
			RedirectAttributes ra, //리다이렉트 시 값 전달용
			HttpSession session // 서버 파일 저장 경로를 얻어올 용도
			) throws IllegalStateException, IOException {
		String message=null;
		String path="redirect:";
		
		//1. boardCode, boardNo를 커맨드 객체에 세팅
		board.setBoardCode(boardCode);
		board.setBoardNo(boardNo);
		
		//2. 이미지 서버 저장 경로, 웹 접근 경로
		String webPath="/resources/images/board/";
		String filePath=session.getServletContext().getRealPath(webPath);
		
		//3. 게시글 수정 서비스 호출
		int result = service.boardUpdate(board, images,webPath,filePath,deleteList);
		
		//4. 결과에 따라 message,path 설정
		//- 수정 성공 시 : 상세조회 페이지 + "게시글이 수정되었습니다."
		//- 수정 실패 시 : 수정 페이지 + "게시글 수정 실패."
		
		if(result>0) {	 //수정 성공시
			
			// -> /board/{boardCode}/{boardNo}
			message="게시글이 수정되었습니다.";
			path+="/board/"+ boardCode + "/" + boardNo + "?cp="+cp;
			
		}else { //게시글 수정 실패시
			message="게시글 수정 실패.";
			path+="update";
			
		}
		
		ra.addFlashAttribute("message",message);
		return path;
		
	}
	
	
	
	//게시글 삭제
		@GetMapping("/{boardCode:[0-9]+}/{boardNo}/delete")
		public String boardDelete(	@PathVariable("boardNo") int boardNo,
				@PathVariable("boardCode") int boardCode
				,@RequestParam(value="cp",required=false,defaultValue="1") String cp
				,RedirectAttributes ra
				,@SessionAttribute("loginMember")Member loginMember
				,Board board
				,@RequestHeader("referer") String referer //이전 요청 주소 
				) {
			
			int result=0;
			String message=null;
			String path="redirect:";
			//게시글 상세 조회 서비스 호출
			//board.setMemberNo(loginMember.getMemberNo());
			//board.setBoardNo(boardNo);
			//result = boardService.deleteBoard(board);
			result = service.deleteBoard(boardNo);
			
			if(result>0) {	 
				
				// -> /board/{boardCode}/{boardNo}
				message="게시글이 삭제되었습니다.";
				path+="/board/"+ boardCode  ;
				
			}else { //게시글 수정 실패시
				message="게시글 수정 실패.";
				path+="/board/"+ boardCode + "/" + boardNo+ "cp="+cp;
				path += referer;
				
			}
			
			
			ra.addFlashAttribute("message",message);
			return path;
			
					
			
		
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
