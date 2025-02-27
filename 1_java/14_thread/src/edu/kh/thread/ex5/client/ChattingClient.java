package edu.kh.thread.ex5.client;

import java.net.Socket;
import java.util.Scanner;

import edu.kh.thread.ex5.thread.Receiver;
import edu.kh.thread.ex5.thread.Sender;

// 클라이언트
public class ChattingClient {

	
	public void start() {
		
		Scanner sc = new Scanner(System.in);
	
		// ip, port 입력 받기
		System.out.print("접속할 IP : ");
		String serverIP = sc.next();
		
		System.out.print("포트 번호 : ");
		int port = sc.nextInt();
		sc.nextLine(); // 입력 버퍼에 남아있는 개행문자 제거
		
		Socket socket = null;
		
		try {
			
			// 2. 서버 접속
			socket = new Socket(serverIP,port);
			
			if(socket !=null) { // 서버 접속 성공 시 
				
				System.out.println("<<서버 접속 성공!>>");
			
				System.out.print("클라이언트 이름 입력 : ");
				String clientName = sc.nextLine();
			
				// 스레드를 이용해서 코드 작성 예정
				// ------------------------------------------------------
				// ------------------------------------------------------
			
				// 클라이언트 -> 서버 출력 역할 객체 생성
				Sender sender = new Sender(socket, clientName);
				
				// 클라이언트 -< 서버 입력 역할 객체 생성
				Receiver receiver = new Receiver(socket);
				
				// 스레드로 생성
				Thread th1 = new Thread(sender);
				Thread th2 = new Thread(receiver);
				
				th1.start();
				th2.start();
				// 스레드가 시작되면서 입력 ,출력이 동시에
				// 무한히 수행되는 서버 완성
				
				// join() : 해당 스레드 종료 시 까지 현재 스레드를 대기
				th1.join();
				th2.join();
			}
			
		}catch(Exception e ) {
			e.printStackTrace();
		}finally {
			try {
				if( socket!=null) socket.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	
	}
}
