package edu.kh.community.member.model.dao;

import static edu.kh.community.common.JDBCTemplate.*;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import edu.kh.community.member.model.dto.Member;

public class MemberDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private Properties prop;

	// 기본 생성자
	public MemberDAO() {
		try {
			prop = new Properties();

			String filePath 
			= MemberDAO.class.getResource("/edu/kh/community/sql/member-sql.xml").getPath();

			prop.loadFromXML(new FileInputStream(filePath));

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/** 로그인 DAO
	 * @param mem
	 * @param conn
	 * @return loginMember
	 * @throws Exception
	 */
	public Member login(Member mem, Connection conn) throws Exception{

		Member loginMember =null; // 결과 저장용 변수

		try {
			String sql = prop.getProperty("login");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, mem.getMemberEmail());
			pstmt.setString(2, mem.getMemberPw());

			rs = pstmt.executeQuery();

			if(rs.next()) {
				loginMember = new Member();
				loginMember.setMemberNo(rs.getInt("MEMBER_NO"));
				loginMember.setMemberEmail(    rs.getString("MEMBER_EMAIL") );
				loginMember.setMemberNickname(    rs.getString("MEMBER_NICK")    );
				loginMember.setMemberTel(       rs.getString("MEMBER_TEL")     );
				loginMember.setMemberAddress(    rs.getString("MEMBER_ADDR")  );
				loginMember.setProfileImage(    rs.getString("PROFILE_IMG")  );
				loginMember.setEnrollDate(       rs.getString("ENROLL_DT")     );

			}

		}finally {
			close(rs);
			close(pstmt);
		}

		return loginMember;
	}

	/**	회원가입 DAO
	 * @param mem
	 * @param conn
	 * @return result
	 * @ throws Exception
	 */
	public int signUp(Member mem, Connection conn) throws Exception{
		int result = 0;

		try {
			String sql = prop.getProperty("signUp");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, mem.getMemberEmail());
			pstmt.setString(2, mem.getMemberPw());
			pstmt.setString(3, mem.getMemberNickname());
			pstmt.setString(4, mem.getMemberTel());
			pstmt.setString(5, mem.getMemberAddress());

			result=pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}

	/** 내 정보 수정 DAO
	 * @param mem
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int myPageInfo(Member mem, Connection conn) throws Exception{
		int result = 0;

		try {
			String sql = prop.getProperty("myPageInfo");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, mem.getMemberNickname());
			pstmt.setString(2, mem.getMemberTel());
			pstmt.setString(3, mem.getMemberAddress());
			pstmt.setInt(4, mem.getMemberNo());

			result=pstmt.executeUpdate();

		}finally {
			close(pstmt);
		}
		return result;
	}



	/** 비밀번호 변경 
	 * @param currentPw
	 * @param newPw
	 * @param memberNo
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int changePw(String currentPw, String newPw, int memberNo, Connection conn) throws Exception{
		int result=0;
		try {
			String sql = prop.getProperty("changePw");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, newPw);
			pstmt.setInt(2,memberNo);
			pstmt.setString(3, currentPw);

			result=pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}

	/** 회원 탈퇴
	 * @param memberPw
	 * @param memberNo
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int secession(String memberPw, int memberNo, Connection conn) throws Exception{
		int result=0;
		try {
			String sql = prop.getProperty("secession");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, memberPw);
			pstmt.setInt(2,memberNo);

			result=pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}

	/** 이메
	 * @param memberEmail
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int emailDupCheck(String memberEmail, Connection conn) throws Exception{
		int result = 0;

		try {
			String sql = prop.getProperty("emailDupCheck");

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberEmail);

			rs= pstmt.executeQuery();

			if(rs.next()) {
				result=rs.getInt(1); // 1번 컬럼의 결과를 result에 대입
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	public int nickDupCheck(String memberNickname, Connection conn) throws Exception{
		int result = 0;

		try {
			String sql = prop.getProperty("nickDupCheck");

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberNickname);

			rs= pstmt.executeQuery();

			if(rs.next()) {
				result=rs.getInt(1); 
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}


}
