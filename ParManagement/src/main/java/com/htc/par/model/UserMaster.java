package com.htc.par.model;

import java.io.Serializable;

public class UserMaster  implements Serializable{
	

	private static final long serialVersionUID = 6291595140221519176L;
	
	
	private int userId;
	private String userFirstName;
	private String userLastName;
	private String userEmailTxt;
	private String userPhoneNo;
	private String userActive;
	private String  userName;
	private String  password;
	private UserRole userRole;
	
	public UserMaster() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserMaster(int userId, String userFirstName, String userLastName, String userEmailTxt, String userPhoneNo,
			String userActive, String userName, String password, UserRole userRole) {
		super();
		this.userId = userId;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userEmailTxt = userEmailTxt;
		this.userPhoneNo = userPhoneNo;
		this.userActive = userActive;
		this.userName = userName;
		this.password = password;
		this.userRole = userRole;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserFirstName() {
		return userFirstName;
	}
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	public String getUserLastName() {
		return userLastName;
	}
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	public String getUserEmailTxt() {
		return userEmailTxt;
	}
	public void setUserEmailTxt(String userEmailTxt) {
		this.userEmailTxt = userEmailTxt;
	}
	public String getUserPhoneNo() {
		return userPhoneNo;
	}
	public void setUserPhoneNo(String userPhoneNo) {
		this.userPhoneNo = userPhoneNo;
	}
	public String getUserActive() {
		return userActive;
	}
	public void setUserActive(String userActive) {
		this.userActive = userActive;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserRole getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	@Override
	public String toString() {
		return "UserMaster [userId=" + userId + ", userFirstName=" + userFirstName + ", userLastName=" + userLastName
				+ ", userEmailTxt=" + userEmailTxt + ", userPhoneNo=" + userPhoneNo + ", userActive=" + userActive
				+ ", userName=" + userName + ", password=" + password + ", userRole=" + userRole + "]";
	}
	
	
	

}
