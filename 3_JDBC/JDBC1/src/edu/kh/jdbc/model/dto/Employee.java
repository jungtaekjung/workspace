package edu.kh.jdbc.model.dto;

import java.sql.Date;


// DTO( Data Transfer Object) : 데이터 전달용 객체
public class Employee {

	// DTO의 필드는 DB 컬럼과 비슷하게 작성되는 경우가 많음
	// -> 상황에 따라서 필드가 추가 또는 삭제될 수 있음(개발자 재량)
	private int empId;
	private String empName;
	private String empNo; 
	private String email; 
	private String phone; 
	private String deptCode; 
	private String jobCode; 
	private String salLevel; 
	private int salary; 
	private double bonus; 
	private int managerId; 
	private Date hireDate; // java.sql.Date 
	private Date entDate; // java.sql.Date 
	private char entYn; 

public Employee() {}

public Employee(int empId, String empName, String empNo, String email, String phone, String deptCode, String jobCode,
		String salLevel, int salary, double bonus, int managerId, Date hireDate, Date entDate, char entYn) {
	super();
	this.empId = empId;
	this.empName = empName;
	this.empNo = empNo;
	this.email = email;
	this.phone = phone;
	this.deptCode = deptCode;
	this.jobCode = jobCode;
	this.salLevel = salLevel;
	this.salary = salary;
	this.bonus = bonus;
	this.managerId = managerId;
	this.hireDate = hireDate;
	this.entDate = entDate;
	this.entYn = entYn;
}






public Employee(int empId, String empName, String empNo, String email, String phone, String deptCode, String jobCode,
		int salary, double bonus) {
	super();
	this.empId = empId;
	this.empName = empName;
	this.empNo = empNo;
	this.email = email;
	this.phone = phone;
	this.deptCode = deptCode;
	this.jobCode = jobCode;
	this.salary = salary;
	this.bonus = bonus;
}






public int getEmpId() {
	return empId;
}

public void setEmpId(int empId) {
	this.empId = empId;
}

public String getEmpName() {
	return empName;
}

public void setEmpName(String empName) {
	this.empName = empName;
}

public String getEmpNo() {
	return empNo;
}

public void setEmpNo(String empNo) {
	this.empNo = empNo;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPhone() {
	return phone;
}

public void setPhone(String phone) {
	this.phone = phone;
}

public String getDeptCode() {
	return deptCode;
}

public void setDeptCode(String deptCode) {
	this.deptCode = deptCode;
}

public String getJobCode() {
	return jobCode;
}

public void setJobCode(String jobCode) {
	this.jobCode = jobCode;
}

public String getSalLevel() {
	return salLevel;
}

public void setSalLevel(String salLevel) {
	this.salLevel = salLevel;
}

public int getSalary() {
	return salary;
}

public void setSalary(int salary) {
	this.salary = salary;
}

public double getBonus() {
	return bonus;
}

public void setBonus(double bonus) {
	this.bonus = bonus;
}

public int getManagerId() {
	return managerId;
}

public void setManagerId(int managerId) {
	this.managerId = managerId;
}

public Date getHireDate() {
	return hireDate;
}

public void setHireDate(Date hireDate) {
	this.hireDate = hireDate;
}

public Date getEntDate() {
	return entDate;
}

public void setEntDate(Date entDate) {
	this.entDate = entDate;
}

public char getEntYn() {
	return entYn;
}

public void setEntYn(char entYn) {
	this.entYn = entYn;
}

@Override
public String toString() {
	return "Employee [empId=" + empId + ", empName=" + empName + ", empNo=" + empNo + ", email=" + email + ", phone="
			+ phone + ", deptCode=" + deptCode + ", jobCode=" + jobCode + ", salLevel=" + salLevel + ", salary="
			+ salary + ", bonus=" + bonus + ", managerId=" + managerId + ", hireDate=" + hireDate + ", entDate="
			+ entDate + ", entYn=" + entYn + "]";
}



}
