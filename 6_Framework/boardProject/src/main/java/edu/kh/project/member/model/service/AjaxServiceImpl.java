package edu.kh.project.member.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.project.member.model.dao.AjaxDAO;
import edu.kh.project.member.model.dto.Member;

@Service // 서비스 + bean등록(IOC)
public class AjaxServiceImpl implements AjaxService{
	
	
	@Autowired // DI 
	private AjaxDAO dao;

	
	//이메일로 닉네임 조회
	@Override
	public String selectNickname(String email) {
		return dao.selectNickname(email);
	}


	@Override
	public String selectTel(String nickname) {
		return dao.selectTel(nickname);
	}


	@Override
	public String dupCheck(String email) {
		return dao.dupCheck(email);
	}

	//이메일 중복 검사
	@Override
	public String checkEmail(String email) {
		return dao.checkEmail(email);
	}


	@Override
	public int checkNickname(String nickname) {
		return dao.checkNickname(nickname);
	}

	//이메일로 회원 정보 조회
	@Override
	public Member selectMember(String email) {
		return dao.selectMember(email);
	}


	//이메일이 일부라도 일치하는 모든 회원 조회
	@Override
	public List<Object> selectMemberList(String input) {
		return dao.selectMemberList(input);
	}




}
