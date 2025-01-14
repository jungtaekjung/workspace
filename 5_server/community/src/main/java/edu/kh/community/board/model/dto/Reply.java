package edu.kh.community.board.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Reply {
	private int replyNo;
	private String replyContent;
	private String createDate;
	private int memberNo;
	private String memberNickname;
	private String profileImage;
	private int boardNo;
	
}
