package edu.kh.oop.cls.run;

import edu.kh.oop.cls.model.service.ClsService;
import edu.kh.oop.cls.model.vo.Student;

public class ClsRun {
	public static void main(String[] args) {
	
	//	Student std = new Student();
		//std.ex();
		
		ClsService cls = new ClsService();
//		cls.ex3(); // 기본생성자 예제
		//System.out.println(Student.schoolName);
		cls.ex4();
	
	}
}
