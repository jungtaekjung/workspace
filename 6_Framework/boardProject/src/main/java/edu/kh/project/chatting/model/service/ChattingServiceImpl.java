package edu.kh.project.chatting.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.project.chatting.model.dao.ChattingDAO;
import edu.kh.project.chatting.model.dto.ChattingRoom;

@Service
public class ChattingServiceImpl implements ChattingService{
	
	@Autowired
	private ChattingDAO dao;

	@Override
	public List<ChattingRoom> selectChattingRoomList(int memberNo) {
		return dao.selectChattingRoomList(memberNo);
	}

}
