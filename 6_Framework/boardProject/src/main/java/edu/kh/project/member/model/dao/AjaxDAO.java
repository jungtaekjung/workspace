package edu.kh.project.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository // DB 연결 + bean 등록 (IOC)
public class AjaxDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	/** 이메일로 닉네임 조회
	 * @param email
	 * @return nickname
	 */
	public String selectNickname(String email) {
		
		
		return sqlSession.selectOne("ajaxMapper.selectNickname",email);
	}

	/** 닉네임으로 전화번호 조회
	 * @param nickname
	 * @return memberTel
	 */
	public String selectTel(String nickname) {
		return sqlSession.selectOne("ajaxMapper.selectTel",nickname);
	}
}
