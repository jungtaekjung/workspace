package edu.kh.jdbc.member.model.dto;

import java.sql.Date;

public class Member {

	private int memNo;
	private String memId;
	private String memPw;
	private String memName;
	private char gender;
	private Date date;
	private char seFl;
	
	public Member() {}

	public Member(int memNo, String memId, String memPw, String memName, char gender, Date date, char seFl) {
		super();
		this.memNo = memNo;
		this.memId = memId;
		this.memPw = memPw;
		this.memName = memName;
		this.gender = gender;
		this.date = date;
		this.seFl = seFl;
	}

	
	
	
	
	public Member(String memId, String memPw, String memName, char gender) {
		super();
		this.memId = memId;
		this.memPw = memPw;
		this.memName = memName;
		this.gender = gender;
	}

	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemPw() {
		return memPw;
	}

	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public char getSeFl() {
		return seFl;
	}

	public void setSeFl(char seFl) {
		this.seFl = seFl;
	}

	@Override
	public String toString() {
		return "Member [memNo=" + memNo + ", memId=" + memId + ", memPw=" + memPw + ", memName=" + memName + ", gender="
				+ gender + ", date=" + date + ", seFl=" + seFl + "]";
	}
	
	
	
}
