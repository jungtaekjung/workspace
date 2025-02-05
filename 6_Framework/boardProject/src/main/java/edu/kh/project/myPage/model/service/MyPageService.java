package edu.kh.project.myPage.model.service;

import edu.kh.project.member.model.dto.Member;

public interface MyPageService {

	
	
	int updateInfo(Member updateMember);


	int changePw(String currentPw, String newPw, int memberNo);


	int secession(String memberPw, int memberNo);
	
	

}
