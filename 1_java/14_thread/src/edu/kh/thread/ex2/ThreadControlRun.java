package edu.kh.thread.ex2;

public class ThreadControlRun {
public static void main(String[] args) {
	
	Thread sleepTest = new Thread(new SleepThread1());
//	sleepTest.start();


	Thread clock = new Thread(new MyClock());
//	clock.start();

	
	// 인터럽트 확인하기
	InterruptTest it = new InterruptTest();
//	it.test();

	StopWatchController st = new StopWatchController();
	st.watchStart();
}


}
