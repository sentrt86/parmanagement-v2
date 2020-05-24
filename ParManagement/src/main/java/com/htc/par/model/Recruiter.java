package com.htc.par.model;

public class Recruiter {
	
	private int recruiterId;
	private String recruiterName;
	private String recruiterPhoneNo;
	private String recruiterEmail;
	private String recruiterEmailFlag;
	private String recruiterActive;
	public Recruiter() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Recruiter(int recruiterId, String recruiterName, String recruiterPhoneNo, String recruiterEmail,
			String recruiterEmailFlag, String recruiterActive) {
		super();
		this.recruiterId = recruiterId;
		this.recruiterName = recruiterName;
		this.recruiterPhoneNo = recruiterPhoneNo;
		this.recruiterEmail = recruiterEmail;
		this.recruiterEmailFlag = recruiterEmailFlag;
		this.recruiterActive = recruiterActive;
	}
	public int getRecruiterId() {
		return recruiterId;
	}
	public void setRecruiterId(int recruiterId) {
		this.recruiterId = recruiterId;
	}
	public String getRecruiterName() {
		return recruiterName;
	}
	public void setRecruiterName(String recruiterName) {
		this.recruiterName = recruiterName;
	}
	public String getRecruiterPhoneNo() {
		return recruiterPhoneNo;
	}
	public void setRecruiterPhoneNo(String recruiterPhoneNo) {
		this.recruiterPhoneNo = recruiterPhoneNo;
	}
	public String getRecruiterEmail() {
		return recruiterEmail;
	}
	public void setRecruiterEmail(String recruiterEmail) {
		this.recruiterEmail = recruiterEmail;
	}
	public String getRecruiterEmailFlag() {
		return recruiterEmailFlag;
	}
	public void setRecruiterEmailFlag(String recruiterEmailFlag) {
		this.recruiterEmailFlag = recruiterEmailFlag;
	}
	public String getRecruiterActive() {
		return recruiterActive;
	}
	public void setRecruiterActive(String recruiterActive) {
		this.recruiterActive = recruiterActive;
	}
	@Override
	public String toString() {
		return "Recruiter [recruiterId=" + recruiterId + ", recruiterName=" + recruiterName + ", recruiterPhoneNo="
				+ recruiterPhoneNo + ", recruiterEmail=" + recruiterEmail + ", recruiterEmailFlag=" + recruiterEmailFlag
				+ ", recruiterActive=" + recruiterActive + "]";
	}
	
	
	

}
