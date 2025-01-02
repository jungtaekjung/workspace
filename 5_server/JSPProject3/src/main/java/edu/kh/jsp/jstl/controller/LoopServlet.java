package edu.kh.jsp.jstl.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.jsp.model.dto.Book;

@WebServlet("/jstl/loop")
public class LoopServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Book> bookList = new ArrayList<>();
		bookList.add(new Book("소년이 온다", "한강", 15000));
		bookList.add(new Book("어른의 행복은 조용하다", "태수", 18900));
		bookList.add(new Book("트렌드 코리아 2025", "김난도", 18000));
		bookList.add(new Book("행복하지 않으면 인생은 바뀌지 않는다", "브라이언 트레이시", 17550));
		bookList.add(new Book("세이노의 가르침", "세이노", 6200));
      
		req.setAttribute("bookList", bookList);
		
		req.getRequestDispatcher("/WEB-INF/views/jstl/loop.jsp").forward(req, resp);
	}
}
