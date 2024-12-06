package edu.kh.collection.list.model.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import edu.kh.collection.list.model.vo.Student;

public class StudentService {

	// 필드
	private List<Student> studentList = 
										// new ArrayList<>(); // 검색 효율이 좋은 List
										   new LinkedList<>(); // 추가, 제거에 효율이 좋은 List

	// 기본 생성자
	public StudentService() {
		studentList.add(new Student(2,4,2,"이사이",'M',42));
		studentList.add(new Student(4,2,4,"이사이",'F',24));
		studentList.add(new Student(4,4,2,"이사일",'M',42));
		studentList.add(new Student(4,4,4,"이사삼",'F',43));
		studentList.add(new Student(2,2,2,"이사사",'M',44));

	}
	boolean result = true;
	// param : parameter ( 전달 인자 == 전달 받은 값)
	/** 학생 추가 service 메소드
	 * @param grade
	 * @param ban
	 * @param number
	 * @param name
	 * @param gender
	 * @param score
	 * @return boolean
	 */
	public boolean addStudent(int grade, int ban, int number, String name, char gender, int score) {
		
		
		// 전달 받은 값을 이용해서 학생 객체를 만들고
		// studentList에 추가 후 boolean 반환
		//	return	studentList.add(new Student(grade, ban, number, name, gender, score));

		// 중복 검사 후 추가하기(업그레이드)
		
		// 1) 중복 검사 메소드 호출(중복이면 true, 아니면 false)
		boolean result = duplicateValidation(grade,ban,number,name,gender,score);
		
		// 2) 중복이면 false 반환 
		//	  아니면 List.add() 호출 후 결과 반환
		if(result) { // true인 경우 == 중복 O
			return false;
		}
		return studentList.add(new Student(grade,ban,number,name,gender,score));
	}
	public List<Student> selectAllStudent() {
		return studentList;
	}
	public Student selectOne(int index) {

		// studentList의 index 범위를 넘어선 경우
		if(index <0|| index>=studentList.size()) {
			return null;
		}


		// index 범위 정상
		return studentList.get(index);
	}
	/**
	 * @param index
	 * @return Student 객체 주소 또는 null
	 */
	public Student deleteOne(int index) {

		if(index<0||index>=studentList.size()) {

			return null;
		}
		
		// List에서 제거된 학생 객체의 주소가 반환
		return studentList.remove(index);
	}
	/** 학생 성별 조회 service 메소드
	 * @param gender
	 * @return searchList
	 */
	public List<Student> selectGender(char gender) {
		//	1. 검색 결과를 저장할 List 부터 생성
		List<Student> searchList = new ArrayList<>();
		
		//	2. studentList에서 학생을 한명씩 꺼내서
		//		성별이 gender와 일치하는 학생을
		//		searchList에 추가
		
		// 향상된 for문 : for(요소 1개 참조 변수 : 배열 또는 컬렉션)
		// -> 매 반복 마다 배열 또는 컬렉션의 요소를
		//		순서대로 하나씩 꺼내어 참조 변수에 대입하는 반복문
		
		for(Student std: studentList) {
			if(gender==std.getGender()) {
				searchList.add(std);
			}
		}
		return searchList; // 검색 결과가 저장된 List 반환
	
	
	
	}
	/**
	 * @param grade
	 * @return searchList
	 */
	public List<Student> selectGrade(int grade) {
		
		List<Student> searchGrade = new ArrayList<>();
		
		for(Student std : studentList) {
			if(grade == std.getGrade()) {
				searchGrade.add(std);
			}
		}
		return searchGrade;
		
	}
	/**
	 * @param name
	 * @return searchList
	 */
	public List<Student> selectName(String name) {
		// 검색 결과 저장하는 List
		List<Student> searchName = new ArrayList<>();
		
		for(Student s : studentList) {
			/* String(객체) 값 비교시 A.equals(B) 사용 
			 *  
			 *  s.getName() == name (주소 비교)
			 *  s.getName().equals(name) (객체의 값(필드) 비교)
			 *  
			 * */
			
			
			
			if(name.equals(s.getName())){ // 이름이 같다면
				searchName.add(s);
				
			}
		}
		return searchName; 	// 검색 결과 반환
	}
	/** 학생 중복 확인 service 메소드
	 * @param grade
	 * @param ban
	 * @param number
	 * @param name
	 * @param gender
	 * @param score
	 * @return 중복 0 : true / 중복 X : false
	 */
	public boolean duplicateValidation(int grade, int ban, int number, String name, char gender, int score) {
		
		//전달받은 값으로 학생 객체 생성
		Student s = new Student(grade, ban, number, name, gender, score);
		
		// boolean List.contains(요소)
		// - 요소가 List에 있으면 true, 없으면 false
		
		// * contains 사용 전제조건 *
		// 비교하려는 객체(클래스)에 Object의 equals()가
		// 오버라이딩 되어있어야 한다!
		
		
		return studentList.contains(s);
	}
	/** 성적 순서로 정렬 service 메소드
	 * @return 정렬된 학생 목록
	 */
	public List<Student> sortScore() {
		// Objects / Arrays / Collections : 유용한 기능 모음
		
		// Collections.sort(List) : 원본 List가 정렬
		// -> 이 기능을 사용하려면
		//	  List의 요소(Student)에 정렬 기준을 정하는 코드가 있어야 함
		
		Collections.sort(studentList);
		// -> 점수 오름차순 (점점 커지는 순서) 정렬
		
		Collections.reverse(studentList);
		// -> List 순서를 뒤집음 (오름 -> 내림)
		return studentList;
	}

}
