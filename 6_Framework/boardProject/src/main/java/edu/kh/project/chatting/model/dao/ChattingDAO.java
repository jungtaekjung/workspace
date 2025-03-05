package edu.kh.project.chatting.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.project.chatting.model.dto.ChattingRoom;
import edu.kh.project.chatting.model.dto.Message;
import edu.kh.project.member.model.dto.Member;

@Repository
public class ChattingDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;






	/** 채팅 목록 조회
	 * @param memberNo
	 * @return roomList
	 */
	public List<ChattingRoom> selectRoomList(int memberNo) {
		return sqlSession.selectList("chattingMapper.selectRoomList",memberNo);
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




	/** 채팅방 생성
	 * @param map
	 * @return chattingNo
	 */
	public int createChattingRoom(Map<String, Integer> map) {
		int result = sqlSession.insert("chattingMapper.createChattingRoom",map);
		int chattingNo=0;
		if(result >0) chattingNo = map.get("chattingNo");
		return chattingNo;
	}



	/** 채팅방 읽음 표시
	 * @param paramMap
	 * @return
	 */
	public int updateReadFlag(Map<String, Object> paramMap) {
		return sqlSession.update("chattingMapper.updateReadFlag",paramMap);
	}



	/** 채팅방 메세지 목록 조회
	 * @param chattingNo
	 * @return
	 */
	public List<Message> selectMessageList(int chattingNo) {
		return sqlSession.selectList("chattingMapper.selectMessageList",chattingNo);
	}












	/** 메세지 삽입
	 * @param msg
	 * @return result
	 */
	public int insertMessage(Message msg) {
		return sqlSession.insert("chattingMapper.insertMessage",msg);
	}
	
	

}
