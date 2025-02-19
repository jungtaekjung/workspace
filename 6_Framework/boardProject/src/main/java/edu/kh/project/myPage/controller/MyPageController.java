package edu.kh.project.myPage.controller;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.myPage.model.service.MyPageService;
@SessionAttributes({"loginMember"})
// 1. Model에 세팅된 값의 key와 {} 작성된 값이 일치하면 session scope로 이동
// 2. SessionStatus를 이용하여 {} 작성된 값을 제거 시 필요
@Controller // 요청,응답 제어 클래스 + Bean 등록
@RequestMapping("/myPage")
public class MyPageController {

	@Autowired // MyPageService의 자식 MyPageServiceImpl 의존성 주입(DI)
	private MyPageService service;

	//내 정보 페이지로 이동
	@GetMapping("/info")
	public String info(){
		// ViewResolver 설정 -> servlet-context.xml
		return "myPage/myPage-info";
	}

	// 회원 정보 수정
	@PostMapping("/info")
	public String info(Member updateMember, String[] memberAddress
			, @SessionAttribute("loginMember") Member loginMember
			, RedirectAttributes ra) {
		// Member updateMember : 수정할 닉네임, 전화번호가 담긴 커맨드 객체
		// String[] memberAddress : name="memberAddress"인 input 3개의 값(주소)
		// @SessionAttribute("loginMember") Member loginMember
		// :Session에서 얻어온 "loginMember" 객체를 매개변수 Member loginMember에 저장

		//RedirectAttributes ra : 리다이렉트 시 request scope로 값 전달

		//----------------------------------------------

		// 주소 하나로 합치기
		if(updateMember.getMemberAddress().equals(",,")) {
			updateMember.setMemberAddress(null);
		}else { //주소를 입력한 경우

			String addr = String.join("^^^",memberAddress );
			updateMember.setMemberAddress(addr);
		}

		//로그인한 회원의 번호를 updateMember에 추가
		updateMember.setMemberNo(loginMember.getMemberNo());

		// DB에 회원 정보 수정 서비스 호출
		int result=service.updateInfo(updateMember);

		String message = null;
		if(result>0) {
			message="회원 정보가 수정되었습니다.";

			//Session에 있는 loginMember도 수정(동기화)
			loginMember.setMemberNickname(updateMember.getMemberNickname());
			loginMember.setMemberTel(updateMember.getMemberTel());
			loginMember.setMemberAddress(updateMember.getMemberAddress());
		}else {
			message = "회원 정보 수정 실패.";
		}
		// 성공 : 회원 정보가 수정되었습니다.
		// 실패 : 회원 정보 수정 실패
		ra.addFlashAttribute("message",message);
		// -> 성공/실패 시 내 정보 페이지로 재요청
		return "redirect:info"; // 상대경로

	}


	// 비밀번호 변경 페이지 이동
	@GetMapping("/changePw")
	public String changePw(){
		return "myPage/myPage-changePw";
	}

	// 비밀번호 변경
	@PostMapping("/changePw")
	public String changePw(String currentPw, String newPw
			,@SessionAttribute("loginMember") Member loginMember
			, RedirectAttributes ra){

		//로그인한 회원 번호
		int memberNo=loginMember.getMemberNo();
		//비밀번호 변경 서비스 호출

		int result = service.changePw(currentPw,newPw,memberNo);

		String message=null;
		String path = "redirect:";
		if(result>0) {
			message="비밀번호가 변경되었습니다.";
			path +="/myPage/info";
		}else {
			message="비밀번호가 현재 비밀번호가 일치하지 않습니다.";
			path +="changePw";
		}
		// 성공 : 비밀번호가 변경되었습니다. -> 내정보페이지
		// 실패 : 현재 비밀번호가 일치하지 않습니다. -> 비밀번호 변경 페이지
		ra.addFlashAttribute("message",message);
		return path;
	}



	@GetMapping("/profile")
	public String profile(){
		return "myPage/myPage-profile";
	}

	@GetMapping("/secession")
	public String secession(){
		return "myPage/myPage-secession";
	}

	// 회원 탈퇴

	
	@PostMapping("/secession")
	public String secession(@SessionAttribute("loginMember") Member loginMember,RedirectAttributes ra
			,String memberPw,SessionStatus status, HttpServletResponse resp) {

		//---------------------------------
		// Member loginMember : 로그인한 회원의 정보
		// String memberPw : 입력한 비밀번호
		// SessionStatus status : 세션 관리 객체
		// RedirectAttributes ra : 리다이렉트 시 request로 값 전달하는 객체
		// HttpServletResponse resp : 서버 -> 클라이언트로 응답하는 방법을 제공하는 객체
		//---------------------------------
		// 1. 로그인한 회원의 회원 번호 얻어오기
		int memberNo=loginMember.getMemberNo();
		// 2. 회원 탈퇴 서비스 호출
		// - 비밀번호가 일치하면 MEMBER_DEL_FL -> 'Y'로 바꾸고 1 반환
		// - 비밀번호가 일치하지 않으면 -> 0 반환
		int result=service.secession(memberNo,memberPw);

		// 3. 탈퇴 성공 시
		// - message : 탈퇴 되었습니다.
		// - 로그아웃
		// - 메인페이지로 리다이렉트
	

		String message=null;
		String path = "redirect:";
		if(result>0) {
			message="탈퇴 되었습니다";
			path +="/";
			status.setComplete();
			
			
			
			// + 쿠키 삭제
			Cookie cookie = new Cookie("saveId","");
			// 만약 같은 이름의 쿠키가 존재한다면 덮어쓰기됨
			

			cookie.setMaxAge(0); 			//기존 쿠키 삭제
			
			
			cookie.setPath("/"); // 클라이언트가 어떤 요청을 할 때 쿠키가 첨부될지 경로(주소)를 지정
			
			resp.addCookie(cookie); //응답 객체 이용해서 만들어진 쿠키를 클라이언트에게 전달
									// -> 클라이언트 컴퓨터에 파일로 생성

	 
		// - message : 현재 비밀번호가 일치하지 않습니다.
	


		}else {
			// 4. 탈퇴 실패 시
			message="현재 비밀번호가 일치하지 않습니다";
			// - 회원 탈퇴 페이지로 리다이렉트
			path +="secession";
		}

		ra.addFlashAttribute("message",message);
		return path;
	}
	
	
	/*MultipartFile : input type="file"로 제출된 파일을 저장한 객체
	 * 
	 *[제공하는 메소드]
	 *- transferTo() : 파일을 지정된 경로에 저장(메모리 -> HDD/SSD)
	 *- getOriginalFilename() : 파일 원본명
	 *- getSize() : 파일 크기
	 *
	 * */
	
	
	//프로필 이미지 수정
	
	@PostMapping("/profile")
	public String updateProfile(
			@RequestParam("profileImage") MultipartFile profileImage //업로드 파일
			,@SessionAttribute("loginMember")Member loginMember //로그인한 회원
			,RedirectAttributes ra // 리다이렉트 시 메세지 전달
			,HttpSession session // 세션 객체
			) throws IllegalStateException, IOException {
		
		//웹 접근 경로
		String webPath = "/resources/images/member/";
		
		//실제로 이미지 파일이 저장 되어야 하는 서버 컴퓨터 경로
		String filePath= session.getServletContext().getRealPath(webPath);
		
		//프로필 이미지 수정 서비스 호출
		int result = service.updateProfile(profileImage,loginMember,webPath,filePath);
		
		String message = null;
		if(result > 0)  message="프로필 이미지가 변경되었습니다.";
		else			message="프로필 이미지 변경 실패";
		
		ra.addFlashAttribute("message",message);
		
				
		return "redirect:profile";
	}






}
