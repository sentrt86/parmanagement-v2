package com.htc.par.model;

public class ParRole {
	
	
	private int roleId;
	private String roleName;
	private boolean roleActive;
	
	public ParRole() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ParRole(int roleId, String roleName, boolean roleActive) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleActive = roleActive;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public boolean isRoleActive() {
		return roleActive;
	}

	public void setRoleActive(boolean roleActive) {
		this.roleActive = roleActive;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + ", roleActive=" + roleActive + "]";
	}
	
	

}
