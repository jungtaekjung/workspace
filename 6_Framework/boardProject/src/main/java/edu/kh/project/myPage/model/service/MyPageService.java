package edu.kh.project.myPage.model.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import edu.kh.project.member.model.dto.Member;

public interface MyPageService {

	
	
	int updateInfo(Member updateMember);


	int changePw(String currentPw, String newPw, int memberNo);


	int secession(String memberPw, int memberNo);


	/** 프로필 이미지 수정
	 * @param profileImage
	 * @param loginMember
	 * @param webPath
	 * @param filePath
	 * @return
	 */
	int updateProfile(MultipartFile profileImage, Member loginMember, String webPath, String filePath) throws IllegalStateException, IOException;
	
	

}
