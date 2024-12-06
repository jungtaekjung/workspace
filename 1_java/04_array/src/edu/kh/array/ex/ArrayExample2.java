package edu.kh.array.ex;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayExample2 {

	//얕은 복사(shallow : 얕은)
	// -> 주소를 복사하여 서로 다른 두 변수가
	// 	  하나의 배열(또는 객체)을 참조하는 상태를 만드는 복사 방법

	public void shallowCopy() {
		int[] arr = {1,2,3,4,5};


		//얕은 복사 진행
		int[] copyArr = arr;

		System.out.println("주소 확인");
		System.out.println("arr : " + arr);
		System.out.println("copyArr : " + copyArr);

		System.out.println("변경 전");
		System.out.println("arr : "+Arrays.toString(arr));
		System.out.println("copyArr : "+Arrays.toString(copyArr));

		//얕은 복사한 배열의 값 변경
		copyArr[2] = 555;


		System.out.println("변경 후");
		System.out.println("arr : "+Arrays.toString(arr));
		System.out.println("copyArr : "+Arrays.toString(copyArr));
	}

	//깊은 복사
	//-> 같은 자료형의 새로운 배열을 만들어서
	//   기존 배열의 데이터를 모두 복사하는 방법

	public void deepCopy() {

		int[] arr = {1,2,3,4,5}; //원본 배열

		// 1. for문을 이용한 깊은 복사
		int[] copyArr1 = new int[arr.length]; //5칸 짜리 배열 생성

		for(int i =0;i<copyArr1.length;i++) {
			copyArr1[i] = arr[i];

		}

		//2. System.arraycopy(원본배열,원본 복사 시작 인덱스,
		//						복사배열, 복사배열의 삽입 시작 인덱스, 복사길이);

		int [] copyArr2 = new int[arr.length];
		System.arraycopy(arr, 0, copyArr2, 0, arr.length);


		//3. 복사할 배열 참조 변수 = Arrays.copyOf(원본 배열 ,복사할 길이);
		int [] copyArr3 = Arrays.copyOf(arr,arr.length);



		//값 변경
		copyArr1[4] = 7;
		copyArr2[4] = 242;
		copyArr3[4] = 24242;

		//Arrays.toString(배열명) : 참조하는 배열에 있는 모든 값을 하나의 문자열로 얻어옴
		System.out.println("arr : " + Arrays.toString(arr));
		System.out.println("copyArr1 : " + Arrays.toString(copyArr1));
		System.out.println("copyArr2 : " + Arrays.toString(copyArr2));
		System.out.println("copyArr3 : " + Arrays.toString(copyArr3));

	}

	// 배열을 이용한 중복 데이터 제거 + 정렬

	public void createLotto() {
		//로또 번호 생성

		//1. 1~45 사이 중복되지 않는 난수 6개 생성
		//2. 생성된 난수를 오름차순 정렬

		//1) 정수 6개를 저장할 배열 선언 및 할당
		int[] lotto = new int[6];

		//2) 생성된 배열을 처음부터 끝까지 순차 접근하는 for문 작성
		for(int i =0;i<lotto.length;i++) {



			//3)1~45 사이의 난수 생성
			int random = (int)(Math.random()*45+1);


			//4)생성된 난수를 순서대로 배열 요소에 대입
			lotto[i]=random;

			//5)중복 검사를 위한 for문 작성
			for(int x=0;x<i;x++) {
				//6) 현재 생성된 난수와 같은 수가
				//	앞쪽 요소에 있는지 검사
				if(lotto[x]==random) {
					i--;
					//	i가 1씩 증가할 때 마다 난수가 하나 생성됨
					//	-> 중복 값이 있으므로 난수를 하나 더 생성해야된다.
					//	--> i는 기본적으로 0~5까지 6회 반복 되지만
					//		i값을 인위적으로 1씩 감소시켜 7회 반복하는 모양을 만듦
					break;
					//	앞쪽에서 중복 데이터를 발견한다면
					//	남은 값을 비교할 필요가 없음
					//	->효율 향상을 위해서 중복 검사하는 for문 종료
				}
			}
		}// for문 끝

		//7)	오름차순 정렬
		//->	선택,삽입,버블, 퀵 등등
		//--> 	자바가 정렬 방법을 미리 만들어서 제공하고 있음

		//Arrays.sort(배열명) : 배열 내 값들이 오름차순 정렬됨
		Arrays.sort(lotto);
		System.out.println(Arrays.toString(lotto));
	}
	public void practice10() {

		int[] arr =new int[10];
		System.out.print("발생한 난수 : ");

		for(int i =0;i<arr.length;i++ ) {
			int random = (int)(Math.random()*10+1);
			arr[i]=random;
			System.out.print(arr[i]+" ");
		}

		//최고 / 최저점 구하기
		int min = arr[0];
		int max = arr[0];

		// for문을 이용해서 arr 배열에 있는 모든 값과 
		// max,min값 비교
		// 이 때,
		// arr[i] 값이 max보다 크면 max에 대입
		// arr[i] 값이 min보다 작으면 min에 대입
		for(int i =0;i<arr.length;i++) {
			if(arr[i]>max) { //최고점 비교
				max = arr[i];
			}
			if(min>arr[i]){ //최저점 비교
				min = arr[i];
			}
		}
		System.out.println();
		System.out.println("최대값 :"+max);
		System.out.println("최소값 :"+min);


	}

	public void practice11() {
		int[] arr = new int[10];
		for(int i =0;i<arr.length;i++) {
			//난수 생성 -> 대입(단,중복X)
			arr[i]=(int)(Math.random()*10+1);

			//중복 확인 시 i값 감소 시켜서
			// 다음 반복에서 현재 인덱스에 난수 덮어쓰기
			for(int x=0;x<i;x++) {
				//x값의 최대값은 i보다 1작은 수

				//현재 생성된 난수가 앞서 대입된 난수와 같은 경우 == 중복
				if(arr[i]== arr[x]) {
					i--; //i를 1 감소
					// 바깥쪽 for문 반복 시 다시 i가 1 증가
					// -1 +1 == 0(제자리)

					break; //중복되는 값을 찾으면 더이상 검사할 필요X
				}
			}
		}
		//출력용 for문
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i]+" ");
		}
	}

	public void practice13() {
		Scanner sc =new Scanner(System.in);
		System.out.print("문자열 : ");
		String input =sc.nextLine();

		char[] arr = new char[input.length()];

		int count =0; //카운트용 변수

		System.out.println("문자열에 있는 문자 : ");
		for(int i = 0;i<arr.length;i++) {
			arr[i]=input.charAt(i); // 문자열 -> char배열에 옮겨 담기

			boolean flag =true; //신호용 변수
			// flag == true  : 중복 없음
			// flag == false : 중복 있음
			for(int x = 0;x<i;x++) { //중복 검사용 for문
				if(arr[i]==arr[x]) {
					//현재 대입된 문자가 앞서 대입된 문자와 같다면 중복
					flag = false;
					break;
				}
			}
			if(flag) { // 중복이 없을 경우
				count++; //카운트 1씩 증가
				if(i==0) { //첫바퀴인경우
					System.out.print(arr[i]);
				}else { //첫바퀴가 아닌경우
					System.out.print(", "+arr[i]);
				}
			}
		}//바깥쪽 for문 끝
		System.out.println("\n문자 개수 : "+count);
	}


	public void practice14() {
		Scanner sc = new Scanner(System.in);
		System.out.print("배열의 크기를 입력하세요 : ");
		int input = sc.nextInt();
		String[] arr = new String[input];
		String[] arr2= {"y","n"};
		while(true) {


			for(int i=0;i<arr.length;i++) {
				System.out.print(i+1 +"번째 문자열 : \n");
				arr[i]=sc.nextLine();
			}
			System.out.println();
			System.out.print("더 값을 입력하시겠습니까?(Y/N)");
			String str = sc.next();
			if(arr2[0].equals(str)) {
				System.out.print("더 입력하고 싶은 개수 : ");
				int input2= sc.nextInt();

				for(int j=input+1;j<=input2+input;j++) {
					System.out.print(input+1+"번째 문자열 : ");
					String[] arr3= arr;
					arr3[j] = sc.nextLine();
				}
			}if(arr2[1].equals(str)) {
				break;
			}
		}
		System.out.println(Arrays.toString(arr));



	}


	public void practice15() {
		Scanner sc =new Scanner(System.in);

		System.out.print("배열의 크기를 입력하세요 : ");
		int size =sc.nextInt();
		sc.nextLine(); //입력 버퍼에 남은 개행 문자 제거
		String[] arr= new String[size]; //배열 선언 및 할당

		int start = 0 ; // while문 내 for문의 초기식에 사용할 변수

		while(true) {
			for(int i=start;i<arr.length;i++) { 
				System.out.print((i+1)+"번째 문자열 : ");
				arr[i] =sc.nextLine();
			}
			System.out.print("더 값을 입력하시겠습니까?(Y/N) : ");
			char input = sc.nextLine().charAt(0);
			//입력 받은 문자열 중 제일 앞 문자 하나만 얻어옴

			if(input=='Y') {
				start=arr.length;
				//추가 입력 받기 위한 추가 배열 부분의 시작 위치 

				System.out.print("더 입력하고 싶은 개수 : ");
				int addSize = sc.nextInt();
				sc.nextLine();

				//증가된 크기의 배열을 생성하여 arr 배열 깊은 복사
				String[] copyArr = new String[arr.length+addSize];

				for(int i =0;i<arr.length;i++) { //기존 배열 크기 만큼만 반복
					copyArr[i]=arr[i]; //복사본 배열에 기존 배열 값을 같은 인덱스에 대입
				}

				//배열 얕은 복사
				arr = copyArr; 	//arr이 참조하는 주소 값을
								//copyArr의 주소 값으로 바꿔서
								//arr이 참조하는 배열의 크기가 증가한 것 처럼 보이게 함

			} else {
				System.out.println(Arrays.toString(arr));
				break; // while 반복 종료
			}
		}
	}
}





















