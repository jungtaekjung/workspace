package edu.kh.community.board.model.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class BoardDetail {
	
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String memberNickname;
	private String profileImage;
	private String createDate;
	private String updateDate;
	private int readCount;
	private int memberNo;
	private String boardName;
	
	private List<BoardImage> imageList;
}
