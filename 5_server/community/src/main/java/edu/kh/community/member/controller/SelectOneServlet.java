package edu.kh.community.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.google.gson.Gson;

import edu.kh.community.member.model.dto.Member;
import edu.kh.community.member.model.service.MemberService;

@WebServlet("/member/selectOne")
public class SelectOneServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String inputEmail = req.getParameter("inputEmail");
		

		try {
			// 회원 정보 조회 서비스 호출 후 결과 반환 받기
			
			MemberService service = new MemberService();
			
			Member member = service.selectOne(inputEmail);
			
			
			// print 매개변수로 참조형 변수가 작성되면
			// 해당 참조형 변수의 toString() 메소드를 자동으로 호출해서 출력
			
			// java 객체 -> JSON(javascript 객체 형태의 문자열) -> javascript 객체 
			//				"{ K : V } "
			
			// 1) JSON 직접 작성 -> 오타 많이 남, 작성하기 번거로움
			//String str = "{\"memberEmail\":\""+ member.getMemberEmail()+"\"}";
			//System.out.println(str);
			
			// 2) JSON-Simple 라이브러리에서 제공하는 JSONObject 사용
			/*if(member !=null) {
				JSONObject obj = new JSONObject(); // Map 형식 객체
				
				obj.put("memberEmail", member.getMemberEmail());
				obj.put("memberNickname", member.getMemberNickname());
				obj.put("memberTel", member.getMemberTel());
				obj.put("memberAddress", member.getMemberAddress());
				obj.put("enrollDate", member.getEnrollDate());
				
				// JSONObject의 toString() 메소드는
				// JSON 형태로 출력될 수 있도록 오버라이딩 되어있다
				resp.getWriter().println(obj.toString());
			}*/
			
			// 3)GSON 라이브러리를 이용한 Java 객체 -> JSON 변환
			// new Gson().toJson(객체,응답스트림)
			// -> 매개변수에 작성된 객체를 JSON 형태로 변환 후 스트림을 통해서 출력

			new Gson().toJson(member,resp.getWriter());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
