package edu.kh.project.chatting.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	// 채팅 상대 검색
	
	@GetMapping(value="/chatting/selectTarget", produces="application/json; charset=UTF-8")
	@ResponseBody
	public List<Member> selectTarget(@RequestParam("query") String query, @SessionAttribute("loginMember") Member loginMember){
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("query", query);
		map.put("memberNo", loginMember.getMemberNo());
		
		return service.selectTarget(map);
		
	}
	
	@GetMapping("/chatting/enter")
	@ResponseBody
	public int enterChat(@RequestParam("targetNo") int targetNo, @SessionAttribute("loginMember") Member loginMember) {
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("targetNo", targetNo);
		map.put("memberNo", loginMember.getMemberNo());
		
		int chattingNo = service.checkChattingNo(map);
		
		if(chattingNo ==0) { // 기존에 채팅방이 없는 경우
			
			// 채팅방 생석
			chattingNo = service.createChattingRoom(map);
			
		}
		return chattingNo;
		
	}
	
	// 비동기로 채팅방 목록 조회
	@GetMapping(value="/chatting/roomList", produces="application/json; charset=UTF-8")
	@ResponseBody
	public List<ChattingRoom> selectRoomList(@SessionAttribute("loginMember") Member loginMember){
	
		return service.selectChattingRoomList(loginMember.getMemberNo());
		
	}
	@PutMapping("/chatting/updateReadFlag")
	@ResponseBody
	public int updateReadFlag(@RequestBody Map<String, Object> paramMap) {
		
		return service.updateReadFlag(paramMap);
		
	}
	
}