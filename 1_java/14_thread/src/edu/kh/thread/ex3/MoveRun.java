package edu.kh.thread.ex3;

public class MoveRun {

	public static void main(String[] args) {
		
		Thread th1 =new Thread(new MoveHeart());
		Thread th2 =new Thread(new MoveStar());
		
		try {
			th2.start();
			Thread.sleep(500); // th1, th2의 실행 시간에 0.5초 차이를 줌
			th1.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}
}
