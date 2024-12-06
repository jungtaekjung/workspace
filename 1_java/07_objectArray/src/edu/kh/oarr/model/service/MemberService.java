package edu.kh.oarr.model.service;

import java.util.Scanner;

import edu.kh.oarr.model.vo.Member;

public class MemberService {

	private Scanner sc = new Scanner(System.in);

	//Member 5칸짜리 객체 배열 선언 및 할당
	private Member[] memberArr = new Member[5];

	//현재 로그인한 회원의 정보를 저장할 변수 선언 
	private Member loginMember=null;

	public MemberService() { // 기본 생성자

		// memberArr 배열 0,1,2, 인덱스 초기화
		memberArr[0] = new Member("user42", "pass42", "사이", 42, "송파");
		memberArr[1] = new Member("user242", "pass242", "이사이", 242, "송파");
		memberArr[2] = new Member("user24", "pass24", "이사", 24, "석촌");
	}

	public void displayMenu() { //메뉴 화면 출력 기능

		int menuNum =0; //메뉴 선택용 변수

		do {	// 한번은 무조건 반복함
			System.out.println("===== 회원 정보 관리 프로그램 v2=====");
			System.out.println("1. 회원가입");
			System.out.println("2. 로그인");
			System.out.println("3. 회원 정보 조회");
			System.out.println("4. 회원 정보 수정");
			System.out.println("5. 회원 검색(지역)");
			System.out.println("0. 프로그램 종료");


			System.out.println("메뉴 입력 >> ");
			menuNum= sc.nextInt(); // 필드에 작성된 Scanner sc 사용

			switch(menuNum) {
			case 1 :System.out.println(SignUp()); break;
			// 같은 클래스 내부에 있는 필드, 메소드는 이름만 불러도 호출 가능 !	
			case 2 :System.out.println(login()); break;
			case 3 :System.out.println(selectMember()); break;
			case 4 :System.out.println(updateMember()); break;// 회원 정보 수정 메소드 수행 후
			case 5 : searchRegion();break;									 // 반환되는 결과를 result 변수에 저장


			case 0 : System.out.println("프로그램을 종료합니다.");break;
			default : System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");break;
			}

		}while(menuNum!=0);

	}
	public String SignUp() {
		System.out.println("\n**** 회원 가입 ****");
		// 객체 배열(memberArr)에 가입한 회원 정보를 저장할 예정
		// -> 새로운 회원 정보를 저장할 공간이 있는지 확인하고
		// 	  빈 공간의 인덱스 번호 얻어오기	--> 새로운 메소드 작성

		int index = emptyIndex(); // memberArr 배열에서 비어있는 인덱스를 반환 받음


		if(index == -1) { // 비어있는 인덱스가 없을 경우 -> 회원 가입 불가
			return "회원 가입이 불가능 합니다.(인원 수 초과)";
		}
		System.out.println("현재 회원 수 : " + index);
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

		System.out.print("지역 : ");
		String region = sc.next();

		//비밀번호, 비밀번호 확인 일치 시 회원가입 
		if(memberPw.equals(memberPw2)) {

			// Member 객체를 생성해서 할당된 주소를 memberArr의 비어있는 인덱스에 대입
			memberArr[index]= new Member(memberId,memberPw2,memberName,memberAge,region);
			return("회원 가입 성공");
		}else {
			return("회원 가입 실패(비밀번호 불일치)");
		}
	}
	// memberArr의 비어있는 인덱스 번호를 반환하는 메소드
	// 단, 비어있는 인덱스가 없으면 -1 반환
	public int emptyIndex() {   ///설명 듣고 싶da.

		//memberArr 배열을 0번 인덱스부터 끝까지 접근해서
		//참조하는 값이 null인 경우의 인덱스 반환

		//
		for(int i = 0; i<memberArr.length;i++) {
			if(memberArr[i]==null) {
				return i; // 현재 메소드를 종료하고 호출한 곳으로 i 값을 가지고 돌아감
			}
		}		
		// for문을 수행했지만 return이 되지 않았다
		// == 배열에 빈칸이 없었다 == -1 반환
		return -1;
	}

	//로그인 메소드
	public String login() {
		System.out.println("\n**** 로그인 ****");

		System.out.print("아이디 입력 : ");
		String memberId = sc.next();

		System.out.print("비밀번호 입력 : ");
		String memberPw = sc.next();

		//1) memberArr 배열 내 요소를 순서대로 접근
		for(int i =0;i<memberArr.length;i++) {
			if(memberArr[i]!= null) {	//회원의 정보가 있을 경우

				// 2) 회원 정보의 아이디, 비밀번호와
				//	  입력 받은 아이디(memberId), 비밀번호(memberPw)가 같은지 확인	
				if(memberArr[i].getMemberId().equals(memberId) &&
						memberArr[i].getMemberPw().equals(memberPw)) {

					//3) 로그인 회원 정보를 참조하는 변수 loginMember에 
					// 	현재 접근 중인 memberArr[i] 요소에 저장된 주소를 얕은 복사
					loginMember = memberArr[i];
					return loginMember.getMemberName()+"님 환영합니다";
					//	break; // 더 이상 같은 아이디, 비밀번호가 없기 때문에 for문 종료
				}


			}


		}
		// 4) 로그인 성공/실패 여부에 따라서 결과 값 반환
		return"아이디 또는 비밀번호가 일치하지 않습니다.";
	}
	//회원 정보 조회 메소드
	public String selectMember() {

		//1) 로그인 여부 확인
		// 로그인이 안되어 있는 경우 "로그인 후 이용해주세요." 반환

		// 2) 로그인이 되어있는 경우
		// 회원 정보를 출력할 문자열을 만들어서 반환해라
		//(단, 비밀번호는 제외)

		if(loginMember == null) {
			return "로그인 후 이용해주세요.";
		}
		return loginMember.toString();
	}
	public String updateMember() {
		System.out.println("***** 회원 정보 수정 *****");

		if(loginMember==null) {
			return "회원 가입후 이용해주세요";
		}
		System.out.print("변경할 이름 : ");
		String inputName = sc.next();

		System.out.print("변경할 나이 : ");
		int inputAge = sc.nextInt();

		System.out.print("변경할 지역 :");
		String inputRegion = sc.next();

		System.out.print("비밀번호 입력 : ");
		String inputPw = sc.next();

		if(inputPw.equals(loginMember.getMemberPw())) {

			loginMember.setMemberName(inputName);

			loginMember.setMemberAge(inputAge);

			loginMember.SetRegion(inputRegion);

			return"정보 수정완료";
		}
		return"비밀번호가 틀렸습니다";
	}

	//회원 검색(지역) 메소드
	public void searchRegion() {
		System.out.println("\n***** 회원 검색(지역) *****");

		System.out.print("검색할 지역을 입력하세요 :");
		String inputRegion = sc.next();
		boolean flag = true; // 검색 결과 신호용 변수

		for(int i =0; i<memberArr.length;i++) {


			// 있을 경우
			// 1) memberArr 배열의 모든 요소 순차 접근

			// 2) memberArr[i] 요소가 null인 경우	
			if(memberArr[i]==null) {
				break;
			}

			// 3) memberArr[i] 요소에 저장된 지역이
			//	입력 받은 지역과 같을 경우 회원의 아이디, 이름 출력
			if(memberArr[i].getRegion().equals(inputRegion)) {
				System.out.printf("아이디 : %s, 이름 : %s\n",memberArr[i].getMemberId(),memberArr[i].getMemberName());
				flag = false;
			}
		}
		if(flag) {
			System.out.println("일치하는 검색 결과가 없습니다.");
		}
	}














}

