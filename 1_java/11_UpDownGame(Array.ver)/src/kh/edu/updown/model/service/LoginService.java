package kh.edu.updown.model.service;

import java.util.Scanner;

import kh.edu.updown.model.vo.Member;

public class LoginService {

	private Scanner sc = new Scanner(System.in);

	// 업다운 게임 시작
	// 1 ~ 100 사이 숫자 중 랜덤하게 한 숫자를 지정하고 업/다운 게임을 진행
	// 맞춘 횟수가 현재 로그인한 회원의 최초 또는 최고 기록인 경우 회원의 highScore 필드 값을 변경
	public void startGame(Member loginMember) {

		System.out.println("[Game Start...]");

		// 1~100 사이 난수 발생
		int random = (int)(Math.random()*100+1);

		int count = 0; // 입력 횟수 카운트

		while(true) {

			count++; // while문이 반복 될 때마다 count

			System.out.print(count + "번째 입력 : ");
			int input = sc.nextInt();
			sc.nextLine();

			if(random==input) {
				System.out.println("정답");
				System.out.println("입력 시도 횟수 : " + count);

				if(loginMember.getHighScore() ==0 || loginMember.getHighScore()>count) {
					System.out.println("최고 기록 달성");

					loginMember.setHighScore(count);
				}
				break; // while문 종료
			}else {
				if(random<input) {
					System.out.println("DOWN");
				}else {
					System.out.println("UP");
				}
			}
			if(input<1||input>99) {
				System.out.println("1~100사이 정수만 입력해주세요");
				sc.nextLine();
			}
		}
	}


	// 내 정보 조회
	// 로그인한 멤버의 정보 중 비밀번호를 제외한 나머지 정보만 화면에 출력
	public void selectMyInfo(Member loginMember) {

		System.out.println("[내 정보 조회]");
	
		System.out.println("[내 정보 조회");
		System.out.println("아이디 : " + loginMember.getMemberId());
		System.out.println("이름 : " + loginMember.getMemberName());
		System.out.println("최고점수 : " + loginMember.getHighScore()+"회");
	
	}

	// 전체 회원 조회
	// 전체 회원의 아이디, 이름, 최고점수를 출럭
	public void selectAllMember(Member[] members) {

		System.out.println("[전체 회원 조회]");
		System.out.printf("%6s %6s %7s\n","[아이디]","[이름]","[최고점수]");

		for(int i=0; i<members.length; i++) {
			
			if(members[i]!=null) {
				System.out.printf("%7s $6s %6d\n", members[i].getMemberId(),members[i].getMemberName()
								, members[i].getHighScore());
			}
		}
	}

	// 비밀번호 변경
	// 현재 비밀번호를 입력 받아 
	// 같은 경우에만 새 비밀번호를 입력 받아 비밀번호 변경
	public void updatePassword(Member loginMember) {

		System.out.println("[비밀번호 변경]");
		
		System.out.print("현재 비밀번호 입력: ");
		String currentPw = sc.next();
		
		if(currentPw.equals(loginMember.getMemberPw())) {
			System.out.print("새 비밀번호");
			String newPw = sc.next();
			
			loginMember.setMemberPw(newPw);
			System.out.println("비밀번호가 변경되었습니다.");
		}else {
			System.out.println("현재 비밀번호가 일치하지 않습니다.");
		}


	}



}
