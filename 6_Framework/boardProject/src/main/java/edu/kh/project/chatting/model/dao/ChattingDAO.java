package edu.kh.project.chatting.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.project.chatting.model.dto.ChattingRoom;
import edu.kh.project.member.model.dto.Member;

@Repository
public class ChattingDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public List<ChattingRoom> selectChattingRoomList(int memberNo) {
		return sqlSession.selectList("chattingMapper.selectChattingRoomList",memberNo);
	}

	
	/** 채팅 상대 검색
	 * @param map
	 * @return
	 */
	public List<Member> selectTarget(Map<String, Object> map) {
		return sqlSession.selectList("chattingMapper.selectTarget",map);
	}



	/** 채팅방 존재 여부 확인
	 * @param map
	 * @return chattingNo
	 */
	public int checkChattingNo(Map<String, Integer> map) {
		return sqlSession.selectOne("chattingMapper.checkChattingNo",map);
	}


	public int createChattingRoom(Map<String, Integer> map) {
			int result =	sqlSession.insert("chattingMapper.createChattingRoom",map);
			int chattingNo = 0;	
			if(result>0) chattingNo = map.get("chattingNo");
			return chattingNo;
	}


	

	public int updateReadFlag(Map<String, Object> paramMap) {
		return sqlSession.update("chattingMapper.updateReadFlag",paramMap);
	}
}
