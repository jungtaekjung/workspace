package edu.kh.project.board.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Session;
import javax.management.Query;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.board.model.dto.Board;
import edu.kh.project.board.model.service.BoardService;
import edu.kh.project.member.model.dto.Member;

@Controller
@RequestMapping("/board")
@SessionAttributes("loginMember")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	/* 목록 조회 : /board/1?cp=1 (cp : current page, 현재페이지)
	 * 상세 조회 : /board/1/1500?cp=1
	 * 
	 * ** 컨트롤러 별도 생성 예정 **
	 * 삽입 : /board2/1/insert (code == BOARD_CODE, 게시판 종류)
	 * 수정 : /board2/1/1500/update (no == BOARD_NO, 게시글 번호)
	 * 삭제 : /board2/1/1500/delete
	 * */
	
	
	
	// @PathVariable : URL 경로에 있는 값을 매개변수로 이용할 수 있게 하는 어노테이션
	//					+ request scope에 세팅
	
	// @PathVariable을 사용하는 경우
	// -자원(resource) 구분 /식별
	// ex) github.com/userId
	// ex) /board/1 -> 1번(공지사항) 게시판
	// ex) /board/2 -> 2번(자유 게시판) 게시판
	
	
	// Query String을 사용하는 경우
	// -정렬, 필터링
	// ex) search.naver.com?query=날씨
	// ex) search.naver.com?query=점심
	// ex) /board2/insert?code=1
	// ex) /board2/insert?code=2
	// -> 삽입이라는 공통된 동작 수행
	//	  단, code에 따라 어디에 삽입할지 지정(필터링)
	
	// ex) /board/list?order=recent(최신순)
	// ex) /board/list?order=most(인기순)
	
	//게시글 목록 조회
	@GetMapping("/{boardCode}")
	public String selectBoardList(@PathVariable("boardCode") int boardCode
			,@RequestParam(value="cp",required=false,defaultValue="1") int cp
			,Model model
			,@RequestParam Map<String, Object> paramMap // 전달받은 파라미터들
			) {
		
		//boardCode 확인
		//System.out.println("boardCode:"+boardCode);
		
		

		if(paramMap.get("key")==null) { 	//검색어가 없을 경우
			//게시글 목록 조회 서비스 호출
			Map<String, Object> map = service.selectBoardList(boardCode,cp);
			
			// 조회 결과를 request scope에 세팅 후 forward
			model.addAttribute("map",map);
			
		}else { //검색어가 있을 경우
			
			paramMap.put("boardCode",boardCode);
			
			Map<String, Object> map = service.selectBoardList(paramMap,cp);
			
			model.addAttribute("map",map);
			
		}
		
		
		

		return "board/boardList";
	}
	
	//게시글 상세조회
	@GetMapping("/{boardCode}/{boardNo}")
	public String boardDetail(@PathVariable("boardCode") int boardCode ,
							@PathVariable("boardNo") int boardNo,
							Model model /*데이터 전달용 객체*/
							,RedirectAttributes ra // 리다이렉트 시 데이터 전달용 객체
							, @SessionAttribute(value="loginMember", required=false) Member loginMember
								//세션에서 loginMember 얻어와서 회원 정보 저장, 없는 경우 null
							
							//쿠키를 이용한 조회 수 증가 시 사용
							, HttpServletRequest req
							, HttpServletResponse resp
							) throws ParseException {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("boardCode", boardCode);
		map.put("boardNo", boardNo);
		
		//게시글 상세 조회 서비스 호출
		Board board = service.selectBoard(map);
		String path;
		if(board!=null) { //조회 결과가 있을 경우
			//-----------------------------------------------
			// 현재 로그인한 상태인 경우
			// 로그인한 회원이 해당 게시글에 좋아요를 눌렀는지 확인
			
			if(loginMember!=null) {
				// 좋아요 여부 확인 서비스 호출
			//	int result = service.boardLikeCheck(boardNo,loginMember);
			//	model.addAttribute("result",result);
				
				map.put("memberNo", loginMember.getMemberNo());
				
				int result=service.boardLikeCheck(map);
				
				// 좋아요를 누른 적이 있을 경우
				if(result>0) {
					model.addAttribute("likeCheck","yes");
				}
			}
				
				//----------------------------------
				//쿠키를 이용한 조회수 증가 처리
				
				// 1) 비회원 또는 로그인한 회원의 글이 아닌 경우
				if(loginMember ==null || loginMember.getMemberNo() != board.getMemberNo()) {
					
					// 2) 쿠키 얻어오기
					Cookie c = null;
					
					//요청에 담겨있는 모든 쿠키 얻어오기
					Cookie[] cookies = req.getCookies();
					System.out.println("cookies =" + cookies);
					
					if(cookies !=null) { //쿠키가 존재할 경우
						
						// 쿠키 중 "readBoardNo" 라는 쿠키를 찾아서 c에 대입
						for(Cookie cookie : cookies) {
							if(cookie.getName().equals("readBoardNo")) {
								c=cookie;
								break;
							}
						}
					}
					
					
					// 3) 기존 쿠키가 없거나(c ==null)
					//    존재는 하나 현재 게시글 번호가
					//	  쿠키에 저장되지 않은 경우(오늘 해당 게시글을 본적이 없는 경우)
					
					int result=0;
					
					if(c ==null) {
						// 쿠키 존재 X -> 하나 새로 생성
						c= new Cookie("readBoardNo", "|"+boardNo+"|");
						
						// 조회수 증가 서비스 호출
						result=service.updateReadCount(boardNo);
					}else {
						// 현재 게시글 번호가 쿠키에 있는 지 확인
						
						//Cookie.getValue() : 쿠키에 저장된 모든 값을 읽어와 String으로 반환
						
						// String.indexOf("문자열") : 찾는 문자열이 몇번 인덱스에 존재하는지 반환
						//							단, 없는 경우 -1 반환
						if(c.getValue().indexOf("|"+boardNo+"|")==-1) {
							//쿠키에 현재 게시글 번호가 없다면
							
							//기존 값에 게시글 번호 추가해서 다시 세팅
							c.setValue(c.getValue() + "|"+boardNo+"|");
							//조회수 증가 서비스 호출
							result=service.updateReadCount(boardNo);
						}
					}//4) 종료
					
					// 5) 조회수 증가 성공 시
					//	  쿠키가 적용되는 경로, 수명(당일 23시 59분 59초) 지정
					
					if(result>0) {
						
						//조회된 board의 조회수와 DB의 조회수 동기화
						board.setReadCount(board.getReadCount()+1);
						
						// 쿠키 적용 경로 설정
						c.setPath("/"); // "/" 이하 경로 요청 시 쿠키 서버로 전달
						
						//수명 지정
											//싱글톤 패턴
						Calendar cal = Calendar.getInstance();
						cal.add(Calendar.DATE, 1);
						
						//날짜 표기법 변경 객체
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						
						// java.util.Date
						Date current = new Date(); //현재 시간
						
						Date temp = new Date(cal.getTimeInMillis()); // 내일 (24시간 후)
						// 2025-02-14 11:41:12
						
						Date tmr = sdf.parse(sdf.format(temp)); // 내일 0시 0분 0초
						
						//내일 0시 0분 0초 - 현재시간
						
						long diff = (tmr.getTime()-current.getTime()) / 1000;
						// -> 내일 0시 0분 0초까지 남은 시간을 초단위로 반환
						
						c.setMaxAge((int)diff); // 수명 설정
						
						resp.addCookie(c); //응답 객체를 이용해서 클라이언트에게 전달

					}
					
				} 
				
				
				
				
				
				
			
			//-----------------------------------------------
			 path = "board/boardDetail"; 			//boardDetail 페이지
			 model.addAttribute("board", board);
			
		}else {//조회 결과가 없을 경우
			path = "redirect:/board/"+ boardCode; // 해당 게시판 목록 첫 페이지
			ra.addFlashAttribute("message","해당 게시글이 존재하지 않습니다.");
			
		}
		
		return path;
	}
	
	
	//좋아요 처리
	@PostMapping("/like")
	@ResponseBody //비동기 요청한 곳으로 반환값 돌려 보냄.
	public int like(@RequestBody Map<String, Integer> paramMap){
		//System.out.println("paramMap : "+ paramMap );
		return service.like(paramMap);
		
	}
	
	
	//게시글 통합검색
	@GetMapping("/search")
	public String searchList(@RequestParam(value="cp",required=false,defaultValue="1") int cp
			,Model model
			,@RequestParam Map<String, Object> paramMap // 전달받은 파라미터들
			) {
		
		
			Map<String, Object> map = service.selectBoardList(paramMap,cp);
			
			// 조회 결과를 request scope에 세팅 후 forward
			
			
			model.addAttribute("map",map);
			
		
		
		return "board/boardSearchList";
	}
	
	//게시글 자동완성
	@GetMapping("/autocomplete")
	@ResponseBody
	public Map<String, Object> autocomplete(Model model
			,@RequestParam Map<String, Object> paramMap) {
		
		System.out.println(paramMap);
		Map<String, Object> map = service.autocomplete(paramMap);
		
		
		
		return map;
	}
	
	   // 헤더 검색
    @GetMapping(value="/headerSearch", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<Map<String, Object>> headerSearch(String query){
       return service.headerSearch(query);
    }
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
