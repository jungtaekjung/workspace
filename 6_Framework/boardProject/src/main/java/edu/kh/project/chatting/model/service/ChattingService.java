package edu.kh.project.chatting.model.service;

import java.util.List;

import edu.kh.project.chatting.model.dto.ChattingRoom;

public interface ChattingService {

	List<ChattingRoom> selectChattingRoomList(int memberNo);
	

}
