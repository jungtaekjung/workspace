package edu.kh.collection.map.ex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.kh.collection.list.model.vo.Student;

public class MapEx {

	/*
	 * Map : 특정 키워드(key)를 입력하면
	 * 			해당 되는 상세한 값(value)을 얻을 수 있는 컬렉션 객체
	 * 
	 * - K:V 한 쌍으로 데이터를 저장 (K:V 한쌍을 묶어서 Entry라고 부름)
	 * 
	 * 
	 * - Key : 키워드 역할
	 * 1) 중복 X (Key 중복 시 Value를 새로운 내용으로 덮어씌움)
	 * 	* Key역할을 하는 객체에는 equals(), hashCode()가 오버라이딩 되어있어야 함
	 * 	
	 * 2) 순서 유지 X
	 * 
	 * --> Map에서 Key만 묶어놓으면 Set과 같은 특징을 지님
	 * 
	 * - Value : 키워드에 대응되는 값의 역할
	 * 
	 * 1) Key로 인해서 구분됨 -> 중복 허용
	 * --> Value만 묶어놓으면 List와 같은 특징을 지님
	 */

	public void ex1() {
		// key제한    value제한
		Map<Integer, String> map = new HashMap<>();
									// Map의 자식 클래스 중 가장 대표되는 Map
	
		// 1. V map.put(K k, V v) : Map에 추가
		// Map에 추가 시 Key가 중복되면 새로운 Value로 덮어 씌우고
		// 이전 Value가 반환
		// 중복이 아니면 null 반환
		
		System.out.println(map.put(1, "아이스 바닐라 라떼"));
		System.out.println(map.put(2, "아이스 녹차 라떼"));
		System.out.println(map.put(3, "아이스 흑임자 라떼"));
		System.out.println(map.put(3, "아이스 흑당 밀크티"));
	
		System.out.println(map);
	
	
		// 2. V map.get(K k ) : 
		// map에서 Key에 해당하는 Value를 반환
		// 일치하는 Key가 없으면 null 반환
		
		System.out.println(map.get(1));
		System.out.println(map.get(2));
		System.out.println(map.get(3));
		System.out.println(map.get(4)); // null
	
		// Value가 String이기 때문에 String 변수에 저장 가능
		String s = map.get(1);
		
		// 3. int map.size() : 저장된 K:V쌍의 수를 반환
		System.out.println("K:V 몇쌍? : "+ map.size());
		
		// 4. V map.remove(K k) : 
		// map에서 Key가 일치하는 요소(K:V)를 제거
		// 제거되는 V 반환, 없으면 null 반환
		System.out.println(map.remove(1) + "제거");
		System.out.println(map.remove(10) + "제거");
	
		// 5. void map.clear() : map 다 지움
		map.clear();
	
		// 6. boolean map.isEmpty() : 비었으면 true, 아니면 false
		System.out.println("map은 비었는가? : " + map.isEmpty());
	}
	
	public void ex2() {
		// Map에 저장된 값 순차 접근(반복해서 순서대로 하나씩 꺼내기)
		
		// 1. Set map.keySet() :
		// key만을 모아놓은 집합(Set)을 반환
		
		Map<String, String> map = new HashMap<>();
	
		map.put("학원", "서울시 강남구");
		map.put("롯데월드", "서울시 송파구");
		map.put("스타필드 하남", "경기도 하남시");
	
		Set<String> set = map.keySet();
		
		System.out.println(set);
	
		System.out.println("----------------------------------");
		
		// 2. keySet을 이용해서 Map에 있는 모든 요소 순차 접근
		for(String key :set) {
			System.out.printf("[%s] %s \n", key,map.get(key));
		}
	
	}
	
	public void ex3() {
		// Map은 언제 사용하면 좋을까?
		
		// 1) 한 번에 많은 양의 데이터를 
		//	  명확하게 구분할 수 있는 형태로 전달해야되는 경우
		
		// 2) VO(Value Object) : 값 전달용 객체
		//	  VO가 없거나, 있어도 사용 빈도가 낮은 경우
		//	  Map으로 대체해서 사용
		
		Map<String, Object> s1 = new HashMap<>();
		// Value의 타입이 Object로 제한되어있다.
		// == 어떤 객체든 Value로 들어올 수 있다.
		
		s1.put("grade", 2); // 3(int) -> Integer로 Auto Boxing
		s1.put("ban", 4);  
		s1.put("number", 2); 
		s1.put("name", "카리나"); 
		s1.put("gender", "F"); 
		s1.put("score", "42"); 
		
		Set<String> set = s1.keySet(); // key들의 집합
		
		for(String s : set) {
			System.out.printf("%s %s\n",s,s1.get(s));
			
		}
		System.out.println("------------------------------");
		
		// Map과 VO 비교
		Student s2 = new Student();
		s2.setGrade(2);
		s2.setBan(4);
		s2.setNumber(24);
		s2.setName("이사이");
		s2.setGender('F');
		s2.setScore(42);
		System.out.printf("s1 : %d학년 %d반 %d번 %s \n",
				s1.get("grade"),s1.get("ban"),s1.get("number"),s1.get("name"));
		
		System.out.printf("s2 : %d학년 %d반 %d번 %s \n",
				s2.getGrade(),s2.getBan(),s2.getNumber(),s2.getName());
	
	}

	public void ex4() {
		
		List<Map<String, Object>> list = new ArrayList<>();
		
		for(int i=0;i<10;i++) {
			
			// Map 생성
			Map<String, Object> map = new HashMap<>();
		
			// map에 데이터 추가
			map.put("id", "user242"+ i);
			map.put("pw", "pass242"+ i);
		
			// map을 List에 추가
			list.add(map);
			
		}
		for(Map<String, Object> temp :list) {
			System.out.println(temp.get("id"));
		}
		// for문 종료 시 List에는 10개의 Map 객체가 추가 되어있음
		
		// * List에 저장된 Map에서 key가 "id"인 경우 value 모두 출력
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	}




}
