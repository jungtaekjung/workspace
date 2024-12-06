package edu.kh.thread.ex4;

import java.util.Random;

public class RaceStar implements Runnable{

	@Override
	public void run() {

		Random ran = new Random();
		try {
			for(int i=0;i<=10;i++) {
				RaceRun.clear();
				
				if(i==0) {
					System.out.println("시작");
					System.out.println("♡");
					System.out.println("★");
				Thread.sleep(1000);
				continue;
				}
				
				System.out.println(i+"회차");
				int temp = ran.nextInt(5)+1;
				RaceRun.starCount +=temp;
				
				System.out.println("★"+temp+"칸 이동");
				
				for(int h =0;h<RaceRun.heartCount; h++) {
					System.out.print("-");
				}
				System.out.println("♡");
				for(int s = 0;s<RaceRun.starCount;s++) {
					System.out.print("-");
				}
				System.out.print("★");
				
				Thread.sleep(1000);
			}
			
		}catch(InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}
