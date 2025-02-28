package edu.kh.project.chatting.model.service;

import java.util.List;
import java.util.Map;

import edu.kh.project.chatting.model.dto.ChattingRoom;
import edu.kh.project.chatting.model.dto.Message;
import edu.kh.project.member.model.dto.Member;

public interface ChattingService {

	List<ChattingRoom> selectChattingRoomList(int memberNo);

	List<Member> selectTarget(Map<String, Object> map);



	/** 채팅방 입장(없으면 생성)
	 * @param map
	 * @return chattingNo
	 */
	int checkChattingNo(Map<String, Integer> map);

	int createChattingRoom(Map<String, Integer> map);


	/** 채팅 읽음 표시
	 * @param paramMap
	 * @return result
	 */
	int updateReadFlag(Map<String, Object> paramMap);

	/** 채팅방 메세지 목록 조회
	 * @param paramMap
	 * @return messageList
	 */
	List<Message> selectMessageList(Map<String, Object> paramMap);

	int insertMessage(Message msg);
	

}
