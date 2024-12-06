package edu.kh.oop.practice.model.service;

import edu.kh.oop.practice.model.vo.Book;

public class BookService {
	
	public void practice() {
		
		
		
		
		Book bk = new Book(); // 기본 생성자를 이용하여 첫 번째 Book 객체 생성
		System.out.print(bk.getTitle());
		System.out.print(bk.getPrice());		
		System.out.print(bk.getDiscountRate());
		System.out.print(bk.getAuthor());
		
		Book bk2 = new Book("자바의 정석",30000,0.2,"남궁성");
		
		bk.setTitle("C언어");
		bk.setPrice(30000);
		bk.setDiscountRate(0.1);
		bk.setAuthor("홍길동");
	
	
		System.out.println("수정된 결과 확인");
		System.out.println(bk.toString());
		System.out.println("===================");
		
		
		
		System.out.println("도서명 = "+ bk.getTitle());
		System.out.printf("할인된 가격 : %0.f원\n", bk.getPrice()-bk.getPrice()*bk.getDiscountRate());
	}
	
	
}
