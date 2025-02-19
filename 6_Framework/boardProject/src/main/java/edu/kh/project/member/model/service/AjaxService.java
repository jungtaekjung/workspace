package edu.kh.project.member.model.service;

import java.util.List;
import java.util.Map;

import edu.kh.project.member.model.dto.Member;

public interface AjaxService {

	
	
	/** 이메일로 닉네임 조회
	 * @param email
	 * @return nickname
	 */
	String selectNickname(String email);

	
	/** 닉네임으로 전화번호 조회
	 * @param nickname
	 * @return
	 */
	String selectTel(String nickname);


	String dupCheck(String email);


	/** 이메일 중복검사
	 * @param email
	 * @return count
	 */
	String checkEmail(String email);


	int checkNickname(String nickname);


	/** 이메일로 회원 정보 조회
	 * @param email
	 * @return
	 */
	Member selectMember(String email);


	List<Object> selectMemberList(String input);

}
