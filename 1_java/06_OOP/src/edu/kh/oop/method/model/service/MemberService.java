package edu.kh.oop.method.model.service;

import java.util.Scanner;

import edu.kh.oop.method.model.vo.Member;

public class MemberService {

	//속성(필드)
	private Scanner sc = new Scanner(System.in);
	// System.in : 자바에서 기본적으로 지정해둔 입력 장치(키보드)

	private Member memberInfo =null; // 가입한 회원의 정보를 저장할 변수
	private Member loginMember = null; // 로그인한 회원의 정보를 저장할 변수

	//기능(생성자, 메소드)

	public MemberService() {} // 기본생성자

	// ** 메소드 작성법 **

	// [접근제한자] [예약어]		반환형				메소드명([매개변수]){}
	// public	  static		기본자료형			
	// protected  final			참조형(배열, 클래스)
	// (default)  abstract		void
	// private    static final


	public void displayMenu() { //메뉴 화면 출력 기능

		int menuNum =0; //메뉴 선택용 변수

		do {	// 한번은 무조건 반복함

			System.out.println("===== 회원 정보 관리 프로그램 =====");
			System.out.println("1. 회원가입");
			System.out.println("2. 로그인");
			System.out.println("3. 회원 정보 조회");
			System.out.println("4. 회원 정보 수정");
			System.out.println("0. 프로그램 종료");


			System.out.println("메뉴 입력 >> ");
			menuNum= sc.nextInt(); // 필드에 작성된 Scanner sc 사용

			switch(menuNum) {
			case 1 : System.out.println(signUp());;break;
			// 같은 클래스 내부에 있는 필드, 메소드는 이름만 불러도 호출 가능 !	
			case 2 : System.out.println(logIn()); break;
			case 3 : System.out.println(selectMember());break;
			case 4 : int result = updateMember();// 회원 정보 수정 메소드 수행 후
												 // 반환되는 결과를 result 변수에 저장
			if(result== -1) {
				System.out.println("로그인 후 이용해주세요.");
				
			}else if(result ==1) {
				System.out.println("회원 정보가 수정되었습니다.");
			}else { //result == 0
				System.out.println("회원 정보 수정 실패(비밀번호 불일치)");
			}
			break; 										
			case 0 : System.out.println("프로그램을 종료합니다.");break;
			default : System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");break;
			}
		}while(menuNum!=0);
	}

	// 회원 가입 기능
	public String signUp() {
		// (반환형)
		// 해당 메소드가 실행이 끝나고 호출한 곳으로 돌아갈 때
		// void : 반환할 값이 없다
		// String : String 자료형 값을 가지고 돌아간다.
		System.out.println("\n***** 회원 가입 *****");

		System.out.print("아이디 : ");
		String memberId = sc.next();

		System.out.print("비밀번호 : ");
		String memberPw = sc.next();

		System.out.print("비밀번호 확인 : ");
		String memberPw2 = sc.next();

		System.out.print("이름 : ");
		String memberName = sc.next();

		System.out.print("나이 : ");
		int memberAge = sc.nextInt();


		// 비밀번호, 비밀번호 확인이 일치하면 회원가입
		// 일치하지 않으면 회원가입 실패
		if(memberPw.equals(memberPw2)) {

			// 입력 받은 정보를 이용해서 Member 객체 생성한 후
			// 생성된 객체의 주소를 필드에 있는 memberInfo에 대입
			memberInfo = new Member(memberId, memberPw2, memberName, memberAge);

			System.out.println(memberInfo.toString());

			return ("회원 가입 성공");
			// 메소드를 호출한 곳으로 "회원 가입 성공" 반환함
		}else {
			return ("회원 가입 실패");
		}
	}
	public String logIn() {


		//로그인 기능(메소드)
		System.out.println("\n***** 로그인 ****");

		//회원 가입을 했는지 부터 확인
		// == memberInfo가 객체를 참조하고 있는지 확인

		if(memberInfo==null) { // 회원 가입을 먼저 안한 경우
			// null : 아무것도 참조하고 있지 않음

			return "회원 가입부터 진행해주세요.";
		}
		//회원 가입 정보(memberInfo가 참조하는 Member 객체)에서
		// 저장된 아이디, 비밀번호가 
		// 입력된 아이디, 비밀번호와 같으면 "로그인 성공"
		// 아니면 "아이디 또는 비밀번호가 일치하지 않습니다"


		System.out.print("아이디 : ");
		String memberId = sc.next();

		System.out.print("비밀번호 : ");
		String memberPw = sc.next();

		if(memberInfo.getMemberId().equals(memberId)&& 
				memberInfo.getMemberPw().equals(memberPw)) {
			// 입력 받은 memberId와
			// 필드에서 memberInfo가 참조 중인 Member 객체의 memberId가 같은지 확인 

			// 회원 가입한 회원의 정보를 loginMember에 저장
			// -> 어떤 회원이 로그인 했는지 구분 가능함
			loginMember = memberInfo;
			// 참조형 = Member객체 주소 (얕은 복사)


			return loginMember.getMemberName()+ "님 환영합니다~";

		}else {
			return "아이디 또는 비밀번호가 일치하지 않습니다.";
		}


	}

	//회원 정보 조회 기능
	public String selectMember() {
		System.out.println("\n**** 회원 정보 조회 ****");

		// 1) 로그인 여부 확인
		// -> 안된 경우 : 로그인 후 이용해주세요

		if(loginMember == null) {
			//loginMember가 참조하는 객체가 없을 경우

			return"로그인 후 이용해주세요.";
			// return 구문이 수행되면 해당 메소드는 즉시 종료되고
			// 호출한 곳으로 돌아감
		}

		// 2) 로그인이 되어있는 경우
		//    회원 정보를 출력할 문자열을 만들어서 반환
		//  (단, 비밀번호는 제외)

		// 이름 : 유저일
		// 아이디 : user01
		// 나이 : 34세
		return" 이름 : " + loginMember.getMemberName()
		+"\n 아이디 : "+ loginMember.getMemberId()
		+"\n 나이 : "	 + loginMember.getMemberAge() + "세";
		// int edu.kh.oop.method.model.vo.Member.getMemberAge()
		// 반환형		메소드 코드 위치 

		//회원 정보 수정
		//이름,나이 입력받아서
		// 로그인이 되어있으면 수정
		// 로그인한 회원의 비밀번호와 입력한 비밀번호가 일치하는 경우 수정




	}
	public int updateMember() {
		System.out.println("\n **** 회원 정보 수정****");


		// 1) 로그인 여부 판별
		if(loginMember == null ) {
			return-1;

		}
		// 2) 수정할 회우너 정보 입력 받기(이름, 나이)
		System.out.print("수정할 이름 입력 :");
		String inputName = sc.next();

		System.out.print("수정할 나이 입력 : ");
		int inputAge = sc.nextInt();


		//3 ) 비밀번호를 입력 받아서 
		// 	  로그인한 회원의 비밀번호와 일치하는지 확인
		System.out.print("비밀번호 입력 : ");
		String inputPw= sc.next();

		if(loginMember.getMemberPw().equals(inputPw)) {
			// 4) 비밀번호가 같을 경우
			//		로그인한 회원의 이름, 나이를 입력 받은 값으로 변경

			loginMember.setMemberName(inputName);
			//입력 받은 inputName을
			//loginMember가 참조하는 Member 객체의 필드 memberName에 대입

			loginMember.setMemberAge(inputAge);

			return 1;
		}else {
			//5) 비밀번호가 다를경우
			return 0;
		}



	}





















	/*public String fixMember() {
		System.out.println("회원 정보 수정");
		System.out.print("이름을 입력해주세요 : ");
		String memberName = sc.next();
		System.out.print("나이를 입력해주세요: ");
		int memberAge = sc.nextInt();
		System.out.print("비밀번호를 입력해주세요");
		String memberPw = sc.next();
		if(memberInfo==null) {
			return"로그인 후 이용해주세요";
		}if(memberInfo.getMemberPw().equals(memberPw)) {

			return"수정";
		}
			return"다시";










	}*/



}

