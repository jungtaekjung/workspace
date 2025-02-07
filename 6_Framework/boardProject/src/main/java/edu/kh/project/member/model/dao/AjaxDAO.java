package edu.kh.project.member.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.project.member.model.dto.Member;

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

	/** 이메일 중복 검사
	 * @param email
	 * @return count
	 */
	public int checkEmail(String email) {
		
		return sqlSession.selectOne("ajaxMapper.checkEmail",email);
	}

	/** 닉네임 중복 검사
	 * @param nickname
	 * @return count
	 */
	public int checkNickname(String nickname) {
		return sqlSession.selectOne("ajaxMapper.checkNickname",nickname);
	}

	/** 이메일이 일치하는 회원 정보 조호;
	 * @param email
	 * @return member
	 */
	public Member selectMember(String email) {
		return sqlSession.selectOne("ajaxMapper.selectMember",email);
	}

	/** 일부 이메일 값을 입력받아 일치하는 회원 정보 조호;
	 * @param email
	 * @return
	 */
	public List<Member> selectAllMember(String email) {
		return sqlSession.selectList("ajaxMapper.selectAllMember",email);
	}
}
