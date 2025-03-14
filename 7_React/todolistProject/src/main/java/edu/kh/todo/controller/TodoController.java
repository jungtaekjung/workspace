package edu.kh.todo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.kh.todo.model.dto.Todo;
import edu.kh.todo.model.dto.TodoMember;
import edu.kh.todo.model.service.TodoService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TodoController {
	
	
	@Autowired
	private TodoService service;	
	
	@GetMapping("/test")
	public int test() {
		return 100;
	}

	
	/** 아이디 중복 검사*/
	@GetMapping("idCheck")
	public int idCheck(String id) {
		log.info(id);
		return service.idCheck(id);
	}
	
	/** 회원 가입 */
	@PostMapping(value="/signup", produces="application/json; charset=UTF-8" )
	public int signup(@RequestBody TodoMember member) {
		
		log.info(member.toString());
		return service.signup(member);
	}
	
	
	/** 로그인 */
	@PostMapping("/login")
	public Map<String, Object> login(@RequestBody TodoMember member){ 
		return service.login(member);
	}
	
	/** todoList 추가 */
	@PostMapping("/todo")
	public int insert(@RequestBody Todo todo) {
		return service.insert(todo);
	}
	
	/** todoList 수정 */
	@PutMapping("/todo")
	public int update(@RequestBody Todo todo) {
		return service.update(todo);
	}
	
	/** todoList 삭제 */
	@DeleteMapping("/todo")
	public int delete(@RequestBody int todoNo) {
		return service.delete(todoNo);
	}
	
	
	
}
