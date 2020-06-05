package com.htc.par.model;

import java.io.Serializable;

public class UserRole implements Serializable{
	
	

	private static final long serialVersionUID = 5829674111426717000L;
	
	
	private int userRoleId;
	private String userRoleName;
	
	public UserRole() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserRole(int userRoleId, String userRoleName) {
		super();
		this.userRoleId = userRoleId;
		this.userRoleName = userRoleName;
	}

	public int getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getUserRoleName() {
		return userRoleName;
	}

	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}

	@Override
	public String toString() {
		return "UserRole [userRoleId=" + userRoleId + ", userRoleName=" + userRoleName + "]";
	}
	
	
	

}
