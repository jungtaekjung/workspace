package edu.kh.project.board.model.exception;

public class ImageDeleteException extends RuntimeException{
	public ImageDeleteException() {
		super("이미지 삭제중 예외 발생");
	}

	public ImageDeleteException(String message) {
		super(message);
	}
	

}
