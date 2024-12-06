package edu.kh.inheritance.practice.run;

import edu.kh.inheritance.practice.model.service.PracticeService;
import edu.kh.inheritance.practice.model.vo.Person;

public class PracticeRun {
public static void main(String[] args) {
	PracticeService service = new PracticeService();
	service.homework();
}
}
