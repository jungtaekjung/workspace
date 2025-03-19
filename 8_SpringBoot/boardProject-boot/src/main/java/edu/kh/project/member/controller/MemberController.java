package edu.kh.project.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.member.model.dto.Member;

@Controller
@RequestMapping("/member")
@SessionAttributes("loginMember")
public class MemberController {
	
	@PostMapping("/login")
	public String login(Member inputMember, Model model, RedirectAttributes ra) {
		// 로그인 성공 시 (임시)
		if(inputMember.getMemberEmail().equals("user01") && 
		   inputMember.getMemberPw().equals("pass01!")) {
			
			Member loginMember = new Member();
			loginMember.setMemberEmail("user01");
			loginMember.setMemberNickname("유저일");
			loginMember.setMemberNo(1);
			
			System.out.println("로그인 성공");
			model.addAttribute("loginMember",loginMember);
			
			
		}else {
			// 로그인 실패 시
			ra.addFlashAttribute("message","아이디 또는 비밀번호가 일치하지 않습니다.");
			
		}
		
		return "redirect:/";
	}
	
	// 로그아웃
	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:/";
		
	}
}
