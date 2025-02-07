package edu.kh.project.member.model.service;

import java.util.List;

import edu.kh.project.member.model.dto.Member;

public interface AjaxService {

	String selectNickname(String email);

	String selectTel(String nickname);

	int checkEmail(String email);

	int checkNickname(String nickname);

	Member selectMember(String email);

	List<Member> selectAllMember(String email);

}
