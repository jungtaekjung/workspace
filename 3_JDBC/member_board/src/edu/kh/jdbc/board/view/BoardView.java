package edu.kh.jdbc.board.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import edu.kh.jdbc.board.model.service.BoardService;
import edu.kh.jdbc.member.model.dto.Board;
import edu.kh.jdbc.member.model.dto.Member;
import edu.kh.jdbc.member.model.dto.Reply;

public class BoardView {

	private Scanner sc = new Scanner(System.in);

	private BoardService service = new BoardService();


	/** 게시판 전용 메뉴 화면
	 * @param loginMember
	 */
	public void boardMenu(Member loginMember) {

		int menuNum = -1;

		do {

			try {

				System.out.println("\n****** 게시판 메뉴 ******\n");
				System.out.println("1. 게시글 목록 조회");
				System.out.println("2. 게시글 상세 조회");
				System.out.println("3. 게시글 작성");
				System.out.println("4. 게시글 검색(제목/내용/제목+내용/작성자");

				System.out.println("0. 회원 메뉴로 돌아가기");

				System.out.print("메뉴를 선택해주세요 >> ");
				menuNum = sc.nextInt();
				sc.nextLine();
				switch(menuNum) {
				case 1 : selectAll(); break;
				case 2 : selectOne(loginMember); break;
				// 상세 조회 시 게시글 수정/삭제
				// 댓글 수정/삭제 가 필요함
				// -> loginMember를 매개변수로 전달
				case 3 : insertBoard(loginMember.getMemNo());break;
				case 4 : searchBoard();break;
				case 0 : System.out.println("회원 메뉴로 돌아갑니다\n");break;
				default : System.out.println("메뉴에 작성된 번호를 입력해주세요.");

				}


			}catch(InputMismatchException e) {
				e.printStackTrace();
				sc.nextLine();
			}
		}while(menuNum !=0);
	}




	/**
	 * 게시글 목록 조회
	 */
	private void selectAll() {
		System.out.println("[게시글 목록 조회]");

		System.out.println("[ 게시글 목록 조회 ]");
		System.out.println("------------------------------------------------------------");
		System.out.println(" 글번호         제목              작성일        작성자    조회수     ");
		System.out.println("------------------------------------------------------------");





		// 게시글 목록 조회 Service 호출 후 결과 반환 받기
		try {
			List<Board> boardList = service.selectAll();

			if(boardList.isEmpty()) {
				System.out.println("조회된 게시글이 없습니다.");
			}else {
				for(Board board : boardList) {
					System.out.println(board);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 조회 결과가 없는 경우 : " 조회된 게시글이 없습니다. " 

		// 있는 경우 : 글번호, 제목, 작성자, 작성일, 조회수 향상된 for문 이용해서 출력
	}
	/** 게시글 상세조회
	 * @param loginMember
	 */
	private void selectOne(Member loginMember) {
		System.out.println("[게시글 상세 조회]");

		System.out.println("조회할 게시글 번호 입력 : ");

		int boardNo = sc.nextInt();

		try {
			// 게시글 상세조회 Service 호출 후 결과 반환( 게시글 1개의 정보 == Board)
			Board board = service.selectOne(boardNo);

			if(board != null) { // 조회된 게시글이 있을 경우
				// 상세 조회 출력
				System.out.println("\n------------------------------------------------------------");
				System.out.printf("번호 : %d     |  제목 : %s\n", board.getBoardNo(), board.getBoardTitle());
				System.out.println("------------------------------------------------------------");
				System.out.printf("작성자 : %s\n"
						+ "작성일 : %s\n"
						+ "조회수 : %d\n", 
						board.getMemberName(), board.getCreateDate(), board.getReadCount());
				System.out.println("------------------------------------------------------------");
				System.out.printf("\n%s\n\n", board.getBoardContent());
				System.out.println("------------------------------------------------------------");

				// 댓글 목록 조회
				System.out.println("\n[댓글]");
				for( Reply r : board.getReplyList() ) {
					System.out.printf("<%d> | %s | %s\n", 
							r.getReplyNo(), r.getMemberName(), r.getCreateDate());

					System.out.println(r.getReplyContent());
					System.out.println(".............................................................\n");
				}	
				//--------------------------------------------------------------------------------------
				// 상세 조회용 메뉴 출력

				System.out.println("====== 상세 조회 메뉴 ======");

				System.out.println("1. 댓글 삽입"); // 어떤 회원이든 가능

				// 댓글을 작성한 회원 번호 == 로그인한 회원 번호
				System.out.println("2. 댓글 수정");
				System.out.println("3. 댓글 삭제");

				// 상세 조회된 게시글의 회원 번호 == 로그인한 회원 번호

				if(board.getMemberNo() == loginMember.getMemNo()) {

					System.out.println("4. 게시글 수정");
					System.out.println("5. 게시글 삭제");
				}

				System.out.println("0. 게시판 메뉴로 돌아가기");

				System.out.println("메뉴 선택 >> ");
				int menuNum = sc.nextInt();
				sc.nextLine();
				
				
				switch(menuNum) {
				case 1 : insertReply(loginMember,boardNo); break;
				case 2 : case 3 : 
	                  // 수정 혹은 삭제 하고자 하는 댓글 번호 입력 받기
	                  // 입력 받은 댓글의 번호가 댓글 목록에 존재하는 경우 수정/삭제 진행
	                  // 만약 내가 작성한 댓글의 번호가 아닌 경우 "현재 로그인한 회원의 댓글이 아닙니다."
	                  
	                  // 존재하지 않는 경우 "해당 댓글이 존재하지 않습니다"
	                  // 댓글을 작성한 회원 번호 == 로그인한 회원 번호

	                  String temp = menuNum == 2 ? "\n[댓글 수정]\n" : "\n[댓글 삭제]\n";

	                  System.out.println(temp);

	                  System.out.print("수정하고 싶은 댓글 번호를 입력하세요   : ");
	                  int replyNo = sc.nextInt();
	                  sc.nextLine();

	                  // 입력 받은 댓글의 번호가 댓글 목록에 있는지 확인
	                  Reply reply = null; // 확인된 댓글을 참조할 변수

	                  for(Reply r : board.getReplyList()) {
	                     if(r.getReplyNo() == replyNo) { // 입력한 댓글 번호와 일치하는 댓글이 있다면
	                        reply = r;
	                        break;
	                     }
	                  }

	                  if(reply != null) { // 입력 받은 댓글의 번호가 댓글 목록에 존재하는 경우
	                     // 수정/삭제 진행
	                     // 만약 내가 작성한 댓글의 번호가 아닌 경우 "현재 로그인한 회원의 댓글이 아닙니다."

	                     // 해당 댓글의 회원 번호(작성자)와
	                     // 로그인한 회원의 번호와 같은지 확인
	                     // -> 같을 경우 로그인한 회원의 댓글임! 
	                     if(reply.getMemberNo() == loginMember.getMemNo()) {
	                        if(menuNum == 2) updateReply(replyNo); // 댓글 수정
	                        else          deleteReply(replyNo); // 댓글 삭제


	                     } else { // 내가 작성한 댓글이 아닌 경우
	                        System.out.println("\n현재 로그인한 회원의 댓글이 아닙니다.\n");
	                     }

	                  } else { // 존재하지 않는 경우
	                     System.out.println("\n해당 댓글이 존재하지 않습니다.\n");
	                  }

	                  break;
				// 수정 혹은 삭제 하고자 하는 댓글 번호 입력 받기
				
				// 입력 받은 댓글의 번호가 댓글 목록에 존재하는 경우 수정/삭제 진행
				// 만약 내가 작성한 댓글의 번호가 아닌 경우 "현재 로그인한 회원의 댓글이 아닙니다."
				
				// 존재하지 않는 경우 "해당 댓글이 존재하지 않습니다."
				
				case 4 : case 5 : 
					// 게시글 작성자 번호 == 로그인한 회원 번호
					if(board.getMemberNo() == loginMember.getMemNo()) {
						if(menuNum==4) {// 4번 게시글 수정
							updateBoard(boardNo);


						}else { //5번 게시글 삭제
							deleteBoard(boardNo);


						}
					}else {
						System.out.println("메뉴에 표시된 번호만 입력해주세요.\n");
					}
					break;
				case 0 : System.out.println("\n게시판 메뉴로 돌아갑니다.\n");break;
				default : System.out.println("메뉴가 표시된 번호만 입력해주세요.");
				}

			}else { // board == null
				System.out.println("존재하지 않는 게시글 번호입니다.");
			}

		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	


	/** 게시글 삭제
	 * @param boardNo
	 */
	private void deleteBoard(int boardNo) {
		System.out.println("[게시글 삭제]");

		// "정말 삭제하시겠습니가?(Y/N) : " -- 제대로 입력될 때 까지 무한 반복
		// -> "Y" 입력 시 보안문자 생성
		// -> 보안 문자가 일치하는 경우에 삭제 진행
		char ch = ' ';
		while(true) {
			System.out.println("정말 삭제하시겠습니까?(Y/N) : ");
			ch = sc.next().toUpperCase().charAt(0);

			if(ch=='Y'||ch=='N') {
				break;
			}else {
				System.out.println("Y 또는 N을 입력해주세요\n");
			}
		}
		if(ch=='Y') { //삭제하려는 경우
			//보안 문제 생성
			String cap = capcha();
			System.out.println("다음 보안문자를 입력해주세요 >> " + cap);

			System.out.print("보안 문자 입력 : ");
			String input = sc.next();

			if(input.equals(cap)) { // 입력받은 문자열과 보안 문자가 같을 경우
				int result = 0;
				// 삭제 Service 호출
				try {
					result = service.deleteBoard(boardNo);
				} catch (Exception e) {
					e.printStackTrace();
				}

				if(result>0) {
					System.out.println("삭제되었습니다.");
				}else {
					System.out.println("삭제가 되지 않았습니다.");
				}
			}
		}else { // 삭제 취소
			System.out.println("\n삭제를 취소했습니다.\n");
		}
	}
	/**
	 * 보안 문자 생성 메소드(랜덤 영어소문자 5개)
	 */
	private String capcha() {


		String cap= "";
		for(int i = 0; i<5;i++) {
			cap+=(char)(Math.random()*26+'a');
			//int 형 변환 : 97~122
			//char 형변환 : 'a' ~ 'z'
		}
		return cap;
	}

	/**	게시글 수정
	 * @param boardNo
	 */
	private void updateBoard(int boardNo) {
		System.out.println("\n[게시글 수정]\n");

		System.out.print("수정할 제목 : ");
		String boardTitle = sc.nextLine();
		
		System.out.println("\n수정할 내용 (종료 시 @exit 입력)\n");
		String boardContent = inputContent(); // 내용 입력 메소드 호출 후 결과 반환 받기
		
		
		// 게시글 번호, 수정한 제목 + 내용을 한번에 저장할 Board 객체 생성
		Board board = new Board();
		board.setBoardNo(boardNo);
		board.setBoardTitle(boardTitle);
		board.setBoardContent(boardContent);
		
		try {
			
		int	result = service.updateBoard(board);
		
		if(result !=0) {
			System.out.println(boardNo+"번 게시글이 수정되었습니다.\n");
		}else {
			System.out.println("게시글 수정 실패");
		}
	
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

	
	/* String(객체)
	 * - 불변성(immutable) <-> 가변성(mutable)
	 * 
	 * -> 한번 생성된 String 객체에 저장된 문자열은 변하지 않는다!
	 * 
	 * ex) String str = "abc"; // Heap 영역에 String 객체가 생성되고
	 * 						   // 생성된 객체에 "abc" 문자열 저장
	 * 
	 * 		str += "123"; // "123"이 저장된 String 객체 생성 후
	 * 					  // "abc"와 "123"이 합쳐진 String 객체가 추가로 별도 생성
	 * 					  // 그 후 "abc123" 객체의 주소를 str에 저장
	 * 
	 *  ** String의 문제점 **
	 *  
	 *  - String에 저장된 값을 바꾸거나 누적하려고 할 때 마다
	 *    String 객체가 무분별하게 생성됨 --> 메모리 낭비
	 *    
	 *  ** 해결 방법 **
	 *  - StringBuffer / StringBuilder (가변성)
	 *    클래스를 자바에서 제공함(사용 방법 동일)
	 *    -> 차이점은 동기/비동기  
	 * */
	
	/** 내용 입력 메소드
	 * @return content
	 */
	private String inputContent() {

		//String content = "";
		
		StringBuffer content = new StringBuffer();
		
		
		while(true) { // @exit이 입력될 때 까지 무한히 문자열을 입력 받아
			// 하나의 변수에 누적 == 게시글 내용


			String input = sc.nextLine();

			if(input.equals("@exit")) {
				break;
			}else {
				//content+=input+"\n"; // 줄 바꾸며 누적

				content.append(input);
				content.append("\n");
				// StringBuffer에 저장된 문자열의 제일 뒤에 input을 추가(누적)
				// append : (제일 뒤에)덧붙이다, 추가하다
				
				// -> 하나의 StringBuffer 객체에 문자열이 계속 누적됨 == 가변
				//   (추가적인 String 객체 생성 없음)
			}


		}
		return content.toString(); // StringBuffer에 오버라이딩된 toString()
		// -> 저장된 문자열을 String 형태로 반환
		
		
		
		
	}

	/** 댓글 삽입
	 * @param loginMember
	 * @param boardNo
	 */
	private void insertReply(Member loginMember, int boardNo) {
		System.out.println("[댓글 작성]");
		
		System.out.print("댓글 내용 입력(종료 시 @exit 입력) \n");
		
		String replyContent = inputContent();
		
		// 로그인한 회원 번호, 게시글 번호, 댓글 내용을 하나의 Reply 객체에 저장
		
		Reply reply = new Reply();
		reply.setBoardNo(boardNo);
		reply.setMemberNo(loginMember.getMemNo());
		reply.setReplyContent(replyContent);
		
		try {
			int result = service.insertReply(reply);
		
			if(result!=0) {
				System.out.println("\n댓글이 등록되었습니다\n");
			}else {
				System.out.println("\n댓글 등록 실패\n");
			}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	  /** 댓글 수정
	    * @param replyNo
	    */
	   private void updateReply(int replyNo) {
	      System.out.println("수정할 내용 입력 (종료 시 @exit 입력)");
	      String replyContent = inputContent();
	      
	      Reply reply = new Reply();
	      reply.setReplyNo(replyNo);
	      reply.setReplyContent(replyContent);
	      
	      try {
	         int result = service.updateReply(reply);
	         
	         if(result>0) {
	            System.out.println(replyNo + "번 댓글이 수정되었습니다.\n");
	         } else {
	            System.out.println("댓글 수정 실패...");
	         }
	         
	         
	      }catch(Exception e) {
	         e.printStackTrace();
	      }
	      
	   }
	   /** 댓글 삭제
	    * @param replyNo
	    */
	   private void deleteReply(int replyNo) {
	      char confirm = ' ';
	      while(true) {
	         System.out.print("정말 삭제하시겠습니까? (Y/N) : ");
	         confirm = sc.next().toUpperCase().charAt(0);

	         if(confirm == 'Y' || confirm == 'N') {
	            break;
	         } else {
	            System.out.println("Y 또는 N을 입력해주세요.\n");
	         }
	      }
	      if(confirm == 'Y') { // 삭제 하려는 경우
	         // 보안 문자 생성
	         String cap = capcha();
	         System.out.println("다음 보안문자를 입력해주세요 >> " + cap);

	         System.out.print("보안 문자 입력 : ");
	         String input = sc.next();

	         if(input.equals(cap)) { // 입력받은 문자열과 보안 문자가 같을 경우

	            try {
	               // 삭제 메소드 호출
	               int result = service.deleteReply(replyNo);
	               
	               if(result > 0) {
	                  System.out.println(replyNo + "번 댓글이 삭제되었습니다.");
	               }else {
	                  System.out.println("댓글 삭제 실패...");
	               }
	               
	            } catch(Exception e) {
	               e.printStackTrace();
	            }
	         } else { // 보안 문자 일치하지 않을 경우
	            System.out.println("\n보안 문자가 일치하지 않습니다.\n");
	            
	         }
	         
	      }else {
	         System.out.println("\n삭제를 취소했습니다.\n");
	      }
	   }

	
	
	
	
	
	
	
	
	
	
	   /** 게시글 삽입
	 * @param memberNo
	 */
	private void insertBoard(int memberNo) {
		      System.out.println("[게시글 삽입]");

		      System.out.print("\n게시글 제목 입력 : ");
		      String boardTitle = sc.nextLine();

		      System.out.print("\n게시글 내용 입력 (종료 시 @exit 입력) : ");
		      String boardContent = inputContent();

		      Board board = new Board();
		      board.setBoardTitle(boardTitle);
		      board.setBoardContent(boardContent);
		      board.setMemberNo(memberNo);

		      int result = 0;

		      try {
		         result = service.insertBoard(board);

		         if (result > 0) {
		            System.out.println("게시글 생성 성공!");
		         } else {
		            System.out.println("게시글 생성 실패...");
		         }

		      } catch (Exception e) {
		         e.printStackTrace();
		      }

		   }

	
	
	/** 게시글 검색
	 * 
	 */
	private void searchBoard() {
	      System.out.println("\n[게시글 검색]\n");
	      int menuNum = -1;
	      
	      do {
	         try {
	            System.out.println("==== 검색 조건을 선택해주세요 ====");
	            System.out.println("1. 제목");
	            System.out.println("2. 내용");
	            System.out.println("3. 제목 + 내용");
	            System.out.println("4. 작성자");
	            System.out.println("0. 돌아가기");
	            
	            System.out.print("조건 선택 >>> ");
	            menuNum = sc.nextInt();
	            sc.nextLine();
	            
	            switch(menuNum) {
	            case 0 : System.out.println("\n게시판 메뉴로 돌아갑니다\n");break;
	            case 1 : case 2 : case 3 : case 4 : 
	               // 검색어 입력 -> service 호출
	               System.out.println("검색어 : ");
	               String keyword = sc.nextLine();
	               
	               List<Board> boardList = service.searchBoard(menuNum, keyword);
	               
	               // 조회 결과가 없는 경우 : 검색 결과가 없습니다. 
	               if(boardList == null) {
	                  System.out.println("검색 결과가 없습니다.");
	               } else {
	                  for(Board b : boardList) {
	                     System.out.println(b);
	                  }
	                  
	               }
	               
	               
	               // 있는 경우 향상된 for문 이용해서 출력
	               
	               break;
	            default : System.out.println("\n메뉴에 작성된 번호만 입력해주세요\n");
	            }
	         }catch(Exception e) {
	            e.printStackTrace();
	            sc.nextLine();
	         }
	         
	         
	      }while(menuNum != 0);

	
	 }
	
	
}
