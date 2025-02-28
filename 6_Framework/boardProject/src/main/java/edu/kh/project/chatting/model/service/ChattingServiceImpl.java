package edu.kh.project.chatting.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.project.chatting.model.dao.ChattingDAO;
import edu.kh.project.chatting.model.dto.ChattingRoom;
import edu.kh.project.chatting.model.dto.Message;
import edu.kh.project.common.utility.Util;
import edu.kh.project.member.model.dto.Member;

@Service
public class ChattingServiceImpl implements ChattingService{
	
	@Autowired
	private ChattingDAO dao;

	@Override
	public List<ChattingRoom> selectChattingRoomList(int memberNo) {
		return dao.selectChattingRoomList(memberNo);
	}

	// 채팅 상대 검색
	@Override
	public List<Member> selectTarget(Map<String, Object> map) {
		return dao.selectTarget(map);
	}

	

	// 채팅방 입장(없으면 생성)
	@Override
	public int checkChattingNo(Map<String, Integer> map) {
		return dao.checkChattingNo(map);
	}
	// 채팅방 생성
	@Override
	public int createChattingRoom(Map<String, Integer> map) {
		return dao.createChattingRoom(map);
	}

	// 채팅 읽음 표시
	@Override
	public int updateReadFlag(Map<String, Object> paramMap) {
		return dao.updateReadFlag(paramMap);
	}

	@Override
	public List<Message> selectMessageList(Map<String, Object> paramMap) {
		
		// 1) 메세지 목록 조회
		List<Message> messageList = 
				dao.selectMessageList(Integer.parseInt(String.valueOf(paramMap.get("chattingNo"))));
		
		// String.valueOf : Object의 값을 String으로 변환
		//					값이 null인 경우 "null"이라는 문자열로 처리
		
		// 2) 메세지목록이 존재하는 경우 알림 읽음 처리
		 if(!messageList.isEmpty()) {
			 dao.updateReadFlag(paramMap);
		 }
		return messageList;
	}

	@Override
	public int insertMessage(Message msg) {
		
		// XSS 방지 처리
		msg.setMessageContent(Util.XSSHandling(msg.getMessageContent()));
		
		
		return dao.insertMessage(msg);
	}

	
	
	

}
