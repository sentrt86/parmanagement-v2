package com.htc.par.model;

import java.io.Serializable;

public class ParRole  implements Serializable{
	

	private static final long serialVersionUID = 1882681046914897919L;
	
	private int roleId;
	private String roleName;
	private String roleActive;
	
	public ParRole() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ParRole(int roleId, String roleName, String roleActive) {
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

	public String getRoleActive() {
		return roleActive;
	}

	public void setRoleActive(String roleActive) {
		this.roleActive = roleActive;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + ", roleActive=" + roleActive + "]";
	}
	
	

}
