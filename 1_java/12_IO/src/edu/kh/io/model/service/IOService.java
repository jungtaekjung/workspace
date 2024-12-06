package edu.kh.io.model.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.kh.io.model.dto.Member;

public class IOService {

	/*	IO
	 * 
	 * 	Input (입력) : 외부 -> 내부로 데이터를 들여오는 것
	 *	Output(출력) : 내부 -> 외부로 데이터를 내보내는 것 
	 *
	 * Stream(스트림) : 데이터가 이동하는 통로( 기본적으로 단(한 쪽)방향)
	 * 
	 * 바이트 기반 스트림
	 * - InputStream, OutputStream 최상위 인터페이스
	 * 
	 * - 1byte 단위로 데이터를 입/출력 하는 스트림
	 * 
	 * - 1byte 범위 문자열(아스키코드 (영어, 숫자, 특수문자))
	 *	 또는 이미지, 영상, 오디오 등 문자가 아닌 파일/데이터 
	 *
	 * 
	 * 문자 기반 스트림 
	 * - Reader, Writer 최상위 인터페이스
	 * 
	 * - 2byte 단위로 데이터(문자)를 입/출력 하는 스트림
	 * 
	 * - 2byte 범위 문자열, 문자만 저장된 파일
	 *   채팅, 인터넷 요청 시 데이터 전달 등에 이용
	 * 
	 * */

	// 절대 경로 : 절대적인 기준으로 부터 목표까지의 경로
	// 서울 기준 -> 서울 강남구 테헤란로 14길 6 남도빌딩 3층 KH 정보교육원
	// C드라이브 기준 -> C:/workspace/1_Java/12_IO/JRE

	// 상대 경로 : 임의의(현재위치) 기준으로부터 목표까지의 경로
	// KH정보교육원 기준 스타벅스까지의 경로 : 옆옆건물

	// 현재 프로젝트(12_IO) 폴더가 현재 위치(기준)
	// - 현재 위치에서 JRE 까지의 경로 : JRE



	/**
	 * 바이트 기반 파일 출력
	 */
	public void byteOutput() {

		FileOutputStream fos = null;
		// Unhandled exception type FileNotFoundException
		// FileOutputStream 객체 생성 시
		// FileNotFoundException 예외가 발생할 가능성이 있음 -> 예외 처리 필요


		try {
			// byte 기반 파일 출력 스트림 생성
			fos =new FileOutputStream("byte/byteTest.txt");
			// byte폴더 내부에 byteTest.txt 파일이 있으면 출력을 위해 연결하고
			// 없으면 파일을 만들어서 연결해라

			String content = "Hello World\n" 
					+ "IO 재밌다~\n"
					+ "242424242\n"
					+ "!@#$%^&\n";

			for(int i=0; i<content.length(); i++) { // 글자 수 만큼 반복
				// System.out.println(content.charAt(i));

				// 한 문자씩 끊어서 파일로 출력하기
				fos.write(content.charAt(i));
				// i번째 문자 하나
			}
			System.out.println("출력 완료");

		}catch(IOException e) {
			// IO 관련 코드는 IOException을 발생 시킬 가능성이 높음
			System.out.println("예외 발생");
			e.printStackTrace(); // 예외가 발생한 메소드까지의 모든 내용 출력
		}finally {
			// 예외가 발생하든 말든 무조건 실행

			// 사용한 스트림 자원 반환(통로 없앰) --> 필수 작성!
			// 프로그램 메모리 관리 차원에서 항상 다쓰면 끊어줌
			try {
				if(fos !=null) fos.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 문자 기반 파일 출력
	 */
	public void charOutput() {
		// 프로그램 -> 파일에 씀
		FileWriter fw = null;

		try {

			// fw = new FileWriter("경로", 이어쓰기 옵션) -> byte 기반 스트림도 사용 가능한 옵션
			fw = new FileWriter("char/charTest.txt",true);
			// char 폴더에 charTest.txt가 있으면 문자 출력 스트림 연결
			// 없으면 만들어서 연결

			String content = "오늘은 회식날 입니다.\n"
					+242424242
					+"\n!@#$%^&\n qweasdzxc";

			fw.write(content); // 문자열을 통째로 내보냄
			// -> 한 줄을 통째로 내보내기 위해서 "버퍼"를 이용하는데
			//	  아직 버퍼에 담겨 있음
			// --> 이걸 강제로 밀어넣어서 버퍼를 비워야 함

			// close() 구문을 수행하면 통로에 남아있는 내용을 모두 내보내고
			// 통로를 없앤다

			System.out.println("char 기반 출력 완료");
		}catch(IOException e ) {
			e.printStackTrace();

		}finally {
			try{
				// fw 스트림 객체가 있을 때에만 닫기(메모리 반환)
				if(fw!=null) fw.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 바이트 기반 파일 입력
	 */
	public void byteInput() {

		FileInputStream fis = null;

		try {
			fis =new FileInputStream("lyrics/APT.txt");

			// * 읽어올 땐 한 글자씩 *

			// 읽어온 한 글자를 저장할 변수 선언
			int value = 0;
			while(true) {
				value = fis.read(); // 다음 1byte를 읽어와 int로 저장
				// 다음 내용이 없으면 -1 반환
				if(value ==-1) { // 다 읽었으면 
					break;
				}
				System.out.print((char)value);
			}

		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(fis != null) fis.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void CharInput() {

		FileReader fr = null;

		try {
			fr =new FileReader("lyrics/APT.txt");

			// * 읽어올 땐 한 글자씩 *

			// 읽어온 한 글자를 저장할 변수 선언
			int value = 0;
			while(true) {
				value = fr.read(); // 다음 1byte를 읽어와 int로 저장
				// 다음 내용이 없으면 -1 반환
				if(value ==-1) { // 다 읽었으면 
					break;
				}
				System.out.print((char)value);
			}

		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(fr != null) fr.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 파일 복사하기
	 */
	public void fileCopy() {

		Scanner sc = new Scanner(System.in);

		// byte 기반 스트림 이용
		// + 성능 향상을 위한 보조스트림
		// -> BufferedInput / OutputStream
		// -> 모아서 한번에 입/출력

		BufferedInputStream bis =null;
		BufferedOutputStream bos = null;

		try {
			System.out.print("복사할 파일 경로 : ");
			String target = sc.nextLine(); // 한 줄 입력

			System.out.print("복사본이 저장될 경로 + 파일명 :");
			String copy = sc.nextLine();

			// 복사 대상을 읽어올 InputStream 생성 + 보조 스트림으로 성능 향상

			bis= new BufferedInputStream(new FileInputStream(target));
			// 보조스트림 (기반 스트림)

			// 복사한 파일을 출력할 OutputStream 생성 + 보조 스트림으로 성능 향상
			bos = new BufferedOutputStream(new FileOutputStream(copy));

			int value = 0; // 1byte씩 읽어서 저장할 임시 변수
			while(true) {
				value = bis.read(); // 1btye 읽어오기, 없으면 -1 반환

				if(value == -1) break;
				// if, for, while 등 {}안에 코드가 1줄이면 생략 가능

				bos.write(value); // 1byte씩 출력하기

			}
			System.out.println("복사 완료");

		}catch(FileNotFoundException e) {
			System.out.println("파일 존재X");
		}catch(IOException e ){
			e.printStackTrace();
		}finally {
			try {
				if(bis!=null)bis.close();
				if(bos!=null)bos.close();

				// 보조 스트림을 close() 하면
				// 연결된 기반 스트림도 같이 close() 된다.
			}catch(IOException e) {
				e.printStackTrace();
			}
		}

	}
	/**
	 * 객체 출력 보조 스트림
	 */
	public void objectOutput() {

		// ObjectOutputStream : 객체를 바이트 기반으로 출력하는 보조 스트림
		ObjectOutputStream oos = null;

		try {

			// java.io.File : 파일/폴더를 참조하는 객체
			File folder = new File("object"); // object라는 이름의 파일/폴더 참조

			if(!folder.exists()){ // object 폴더가 존재하지 않는다면
				folder.mkdir(); // make directory(== folder) : 폴더 만들기
			}

			oos = new ObjectOutputStream(new FileOutputStream("object/Member.txt"));

			// 기반 스트림 : 생성된 스트림 객체를 이용해 직접 입출력을 수행 할 수 있는 스트림
			// 보조 스트림 : 스트림 객체로 생성되어도 직접 입출력 할 수 없고
			//			  기반 스트림의 성능을 향상 시키거나 새로운 기능을 추가하기 위해 사용
			
			// 내보낼 회원 객체 생성
			Member mem = new Member("member42", "pass42", "사십이번멤버", 42);

			// 회원 객체를 파일로 출력
			oos.writeObject(mem);

			System.out.println("회원 출력 완료");
			
		}catch(IOException e ){
			e.printStackTrace();
		}finally {
			try {
				if(oos!=null) oos.close();
			}catch(IOException e ){
				e.printStackTrace();
			}
		}
	}

	/**
	 * 객체 입력 보조 스트림
	 */
	public void objectInput() {
		
		ObjectInputStream ois = null;
		
		try {
			ois = new ObjectInputStream(new FileInputStream("object/Member.txt"));
			
			Member mem =(Member)ois.readObject();
			// readObject() : 직렬화된 객체 데이터를 읽어와
			//				  역직렬화 시켜 정상적인 객체 형태로 반환

			// ClassNotFoundException, IOException
		System.out.println(mem);
		
		}catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ois!=null)ois.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * List에 객체를 담아서 파일로 출력
	 */
	public void listOutput() {
		
		ObjectOutputStream oos = null;
		
		try {
			
			oos = new ObjectOutputStream(new FileOutputStream("object/MemberList.ini"));
					// 보조스트림 ( 기반 스트림) 
			
			List<Member> list = new ArrayList<>();
			
			list.add(new Member("member242", "pass242", "242멤버", 242));
			list.add(new Member("member42", "pass42", "42멤버", 42));
			list.add(new Member("member24", "pass24", "24멤버", 24));
			list.add(new Member("member24242", "pass24242", "24242멤버", 24242));
			
			oos.writeObject(list);
			// writeObject(객체) : 출력하려는 객체는 직렬화가 가능해야만 한다.
			//					 ->Serializable 인터페이스 구현 필수
			
			
			
			// 컬렉션은 모두 직렬화가 가능하도록 Serializable 인터페이스 구현 O
			// -> 단, 컬렉션에 저장하는 객체가 직렬화가 가능하지 않다면 출력 X
			// --> NotSerializableException 발생
			System.out.println("멤버 리스트 출력 완료");
			
			
		}catch(Exception e ) {
			// Exception : 최상위 부모
			// -> 모든 예외 처리 가능
			e.printStackTrace();
		}finally {
			try {
				if(oos!=null) oos.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}


	/**
	 * 객체 입력 보조 스트림
	 */
	public void listInput() {
		
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream("object/MemberList.ini"));
			
			List<Member> list =(List<Member>)ois.readObject();
			for(Member m : list) {
				
				System.out.println(m);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if(ois!=null) ois.close();
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}








