package edu.kh.jdbc.model.service;

import java.util.List;

import edu.kh.jdbc.model.dao.EmployeeDAO;
import edu.kh.jdbc.model.dto.Employee;

// Service : 요청에 맞는 기능을 수행하여 결과를 제공
// - 전달 받은 데이터 또는 DAO 수행 결과 데이터를 필요한 형태로 가공처리

public class EmployeeService {

	private EmployeeDAO dao = new EmployeeDAO();
	
	/** 전체 사원 정보 조회 서비스
	 * @return empList
	 */
	public List<Employee> selectAll(){
		// 별도로 가공할 내용이 없으면 바로 DAO 호출
		
		
	List<Employee> emplist = dao.selectAll();
		
		return emplist;
		
		
	
	
	
	}

	/** 사번으로 사원 정보 조회 서비스
	 * @param input
	 * @return empList
	 */
	public List<Employee> selectOne(int input) {
		
	List<Employee> emplist = dao.selectOne(input);	
		return emplist;
	}

	/** 입력 받은 급여 이상으로 받는 모든 직원 조회 서비스
	 * @param input
	 * @return empList
	 */
	public List<Employee> selectSalary(int input) {
	
	List<Employee> emplist = dao.selectSalary(input);
		return emplist;
	}

	/** 새로운 사원 정보 추가 서비스 
	 * @param emp
	 * @return result
	 */
	public int insertEmployee(Employee emp) {
		int result = dao.insertEmployee(emp);
		return result;
		
	}
		
		
		
		
		
		
		



	/** 사번으로 사원 정보 삭제
	 * @return
	 */
	public int deleteEmployee(int input) {
	return dao.deleteEmployee(input);
		
	}

	/** 사번으로 사원 정보 수정
	 * @param emp
	 * @return result
	 */
	public int updateEmployee(Employee emp) {
		int result = dao.updateEmployee(emp);
		return result;
	}


	public int updateBonus(Employee emp) {
		int result = dao.updateBonus(emp);
		return result;
	}

}



