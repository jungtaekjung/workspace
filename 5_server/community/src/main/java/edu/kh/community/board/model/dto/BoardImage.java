package edu.kh.community.board.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardImage {

	private int imageNo;
	private String imageRename;
	private String imageOriginal;
	private int imageLevel;
	private int boardNo;
}
