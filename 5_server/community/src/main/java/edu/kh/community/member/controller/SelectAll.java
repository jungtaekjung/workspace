package edu.kh.community.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.kh.community.member.model.dto.Member;
import edu.kh.community.member.model.service.MemberService;

@WebServlet("/member/selectAll")
public class SelectAll extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
			List<Member> memberList =new MemberService().selectAll();
			System.out.println(memberList);
		
			new Gson().toJson(memberList,resp.getWriter());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

}
