package edu.kh.project.chatting.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.kh.project.chatting.model.dto.ChattingRoom;
import edu.kh.project.chatting.model.service.ChattingService;
import edu.kh.project.member.model.dto.Member;

@Controller

public class ChattingController {

	@Autowired
	private ChattingService service;

	// 채팅화면
	@GetMapping("/chatting")
	public String chatting(@SessionAttribute("loginMember") Member loginMember, Model model) {


		{
			int memberNo = loginMember.getMemberNo();

			
			List<ChattingRoom> chattingRoomList = service.selectChattingRoomList(memberNo);
			model.addAttribute("chattingRoomList", chattingRoomList);



			return "chatting/chatting";
		}
	}
}