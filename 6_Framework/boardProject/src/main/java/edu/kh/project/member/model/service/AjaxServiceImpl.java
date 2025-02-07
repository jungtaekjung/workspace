package edu.kh.project.member.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.project.member.model.dao.AjaxDAO;
import edu.kh.project.member.model.dto.Member;

@Service // 서비스 + bean등록(IOC)
public class AjaxServiceImpl implements AjaxService{

	@Autowired // DI(의존성)
	private AjaxDAO dao;

	// 이메일로 닉네임 조회
	@Override
	public String selectNickname(String email) {
		return dao.selectNickname(email);
	}
	
	// 닉네임으로 전화번호 조회
	@Override
	public String selectTel(String nickname) {
		return dao.selectTel(nickname);
	}

	// 이메일 중복 검사
	@Override
	public int checkEmail(String email) {
		return dao.checkEmail(email);
	}

	// 닉네임 중복 검사
	@Override
	public int checkNickname(String nickname) {
		return  dao.checkNickname(nickname);
	}

	@Override
	public Member selectMember(String email) {
		return dao.selectMember(email);
	}

	@Override
	public List<Member> selectAllMember(String email) {
		return dao.selectAllMember(email);
	}
}
