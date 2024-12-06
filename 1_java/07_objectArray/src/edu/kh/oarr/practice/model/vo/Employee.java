package edu.kh.oarr.practice.model.vo;

public class Employee {

	private int employeeNum;
	private String employeeName;
	private String employeeTeam;
	private String employeeLevel;
	private int employeePay;
	
	public Employee() {}
	
	public Employee(int employeeNum, String employeeName, 
					String employeeTeam, String employeeLevel, int employeePay) {
		this.employeeNum = employeeNum;
		this.employeeName = employeeName;
		this.employeeTeam = employeeTeam;
		this.employeeLevel = employeeLevel;
		this.employeePay = employeePay;
	}

	public int getEmployeeNum() {
		return employeeNum;
	}

	public void setEmployeeNum(int employeeNum) {
		this.employeeNum = employeeNum;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeTeam() {
		return employeeTeam;
	}

	public void setEmployeeTeam(String employeeTeam) {
		this.employeeTeam = employeeTeam;
	}

	public String getEmployeeLevel() {
		return employeeLevel;
	}

	public void setEmployeeLevel(String employeeLevel) {
		this.employeeLevel = employeeLevel;
	}

	public int getEmployeePay() {
		return employeePay;
	}

	public void setEmployeePay(int employeePay) {
		this.employeePay = employeePay;
	}

	@Override
	public String toString() {
		return "사번 : " + employeeNum + 
				"\n 이름 :" + employeeName+
				"\n 부서 : "+employeeTeam+
				"\n 직급 : "+employeeLevel+
				"\n 급여 : "+employeePay;
	
	

	}
	
	
}