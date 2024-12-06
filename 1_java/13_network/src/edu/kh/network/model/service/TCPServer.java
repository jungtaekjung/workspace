package edu.kh.network.model.service; 

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import java.text.SimpleDateFormat;
import java.util.Date;




public class TCPServer {

	/* server : 서비스를 제공하는 컴퓨터 또는 프로그램이다
	 * client : 서버에 서비스를 요청하여 사용하는 프로그램 또는 컴퓨터
	 * 
	 * TCP Socket 프로그래밍
	 * 
	 * TCP : 데이터 전달의 신회성을 최대한 보장하기 위한 방식(연결 지향형 통신)
	 *       순차적으로 데이터를 전달하고 확인 및 오류 시 재전송
	 * 
	 * Socket : 프로세스의 통신에 사용되는 양끝단
	 *          서버와 클라이언트가 통신하기 위한 매개체
	 * 
	 * ServerSocket : 서버용 소켓
	 * port와 연결되어 외부 요청을 기다리는 역할
	 * 클라이언트 요청이 올 경우 이를 수락accept 하고
	 * 클라이언트가 사용할 수 있는 소켓을 생성
	 * 서버 소켓과 클라이언트 소켓이 연결되어 데이터 통신이 가능해짐
	 * 
	 * 
	 */

	public void serverStart() {

		// 1.서버의 포트 번호 정함
		int port = 8500; //port 번호는 0 ~ 65535사이 지정가능
						 //단 1023번 이하는 사용중인경우가 많아서 제외

		//사용할 변수 미리 선언
		ServerSocket serverSocket = null; // 서버 소켓 저장변수
		Socket clientSocket = null;//클라이언트 소켓 저장변수


		InputStream is = null ; //클라이언트 > 서버 입력용 스트림 변수
		BufferedReader br = null; // 입력용 보조 스트림변수


		OutputStream os = null; // 서버> 클라이언트 출력용 스트림 변수
		PrintWriter pw = null; // 출력용 보조 스트림 변수

		try {

			// 서버 컴퓨터(내 컴퓨터)의 IP 관련 정보를 얻어옴
			InetAddress inet = InetAddress.getLocalHost();
			System.out.println("서버 IP 주소 : " +  inet.getHostAddress());


			// 2.서버용 소켓 객체 생성
			serverSocket = new ServerSocket(port);// 서버용 소켓을 생성하여 포트 결합


			// 3.클라이언트쪽에서 접속 요청이 오길 기다림
			// 서버용 소켓은 생성되면 클라이언트 요청이 오기 전까지
			// 다음 코드를 수행하지 않고 대기하고 있음

			System.out.println("[server]");
			System.out.println("클라이언트의 요청을 기다리고 있습니다...");


			// 4.접속 요청이 오면 요청 수락 후 해당 클라이언트에 대한 소켓 객체 생성
			// 요청을 수락하면 자동으로 socket객체가 얻어와짐
			clientSocket = serverSocket.accept();// accept 수락하다

			// 접속한 클라이언트의 IP를 얻어와 출력
			String clientIP = clientSocket.getInetAddress().getHostAddress();
			System.out.println(clientIP + "가 연결을 요청함");

			// 5.연결된 클라이언트와 입출력 스트림 생성
			is = clientSocket.getInputStream();//클라이언트 > 서버 입력

			os = clientSocket.getOutputStream();//서버 > 클라이언트 출력




			// 6.보조 스트림을 통해 성능 개선
			br = new BufferedReader(new InputStreamReader(is));
			// InputStreamReader : 문자기반 스트림과 바이트 기반 스트림에 연결에 사용되는 스트림

			pw = new PrintWriter(os);

			// 7.스트림을 통해 읽고 쓰기

			// 7-1. 서버 > 클라이언트에게 출력(메세지 전송)
			
			Date now = new Date(); // 생성된 시간을 기록하고 있는 시간 관련 객체
			
			SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			String time = sdf.format(now); // now에 저장된 시간 포멧을 변경
			
			
			pw.println(time+"[서버 접속 성공]");
			pw.flush();// 스트림 (버퍼)에 기록된내용을 밀어내는 코드
			// 미작성 시 클라이언트 쪽으로 출력x

			// 7-2. 클라이언트 > 서버에게 입력(메세지 전송 받기)
	
			
			String clientMessage = br.readLine(); // 클라이언트 메세지 한 줄을 읽어옴
			System.out.println(clientIP + " 로 부터 받은 메세지 : "+ clientMessage);
		

		}catch(IOException e) {
			e.printStackTrace(); // 예외 추적
		}finally { // try 구문에서 예외 발생 여부관계 없이 무조건 수행

			// 8.통신종료
			// 사용한 스트림 , 소켓 자원을 모두 반환(close)
			// 메모리 누수 관리

			// 보통 소켓, 스트림 생성 역순으로 close() 구문을 작성
			try {

				//보조스트림 close 시 연결된 기반 스트림 (is, os) 도 같이 close됨
				if(pw != null) pw.close(); // + os.close()
				if( br != null) br.close(); // + is.close()
				if(clientSocket != null) clientSocket.close();
				if(serverSocket != null) serverSocket.close();


			}catch(IOException e){
				e.printStackTrace();
			} 

		}

	}
}
