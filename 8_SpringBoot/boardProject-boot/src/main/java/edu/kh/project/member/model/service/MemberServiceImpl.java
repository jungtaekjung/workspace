package edu.kh.project.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.kh.project.member.model.dao.MemberDAO;
import edu.kh.project.member.model.dto.Member;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired 
	private MemberDAO dao;

	@Autowired 
	private BCryptPasswordEncoder bcrypt;

	
	@Override
	   public Member login(Member inputMember) {
	      
	      
	      
	      // -> 별도로 제공해주는 matches(평문, 암호문)을 이용해서 비교
	      
	      Member loginMember = dao.login(inputMember);
	      
	      if(loginMember != null) { // 아이디가 일치하는 회원이 조회된 경우
	         
	         
	         if(bcrypt.matches(inputMember.getMemberPw(), loginMember.getMemberPw())) {
	            
	            loginMember.setMemberPw(null);
	            
	         } else { // 다를 경우
	            loginMember = null; // 로그인 실패처럼 만들기
	         }
	         
	      }
	      
	      return loginMember;
	   }
}
