package edu.kh.community.member.model.service;

import java.sql.Connection;

import static edu.kh.community.common.JDBCTemplate.*;
import edu.kh.community.member.model.dao.MemberDAO;
import edu.kh.community.member.model.dto.Member;

public class MemberService {

	private MemberDAO dao = new MemberDAO(); 
	/** 로그인 서비스
	 * @param mem
	 * @return loginMember
	 */
	public Member login(Member mem) throws Exception{
		// Connection 얻어오기
		Connection conn = getConnection();
		// DAO 수행
		Member loginMember = dao.login(mem,conn);
		
		// Connection 반환
		close(conn);
		// 결과 반환
		
		return loginMember;
	}

	public int signUp(Member mem) throws Exception{
		
		Connection conn = getConnection(); // DBCP에서 얻어옴
		
		int result = dao.signUp(mem,conn);
		if(result !=0) commit(conn);
		else			rollback(conn);
		
		close(conn); //DBCP로 돌려줌
		
		return result;
	}

	/** 내 정보 수정
	 * @param mem
	 * @return result
	 * @throws Exception
	 */
	public int myPageInfo(Member mem) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.myPageInfo(mem,conn);
		if(result !=0) commit(conn);
		else 			rollback(conn);
		
		close(conn);
		
		return result;
		
		
	}
}
