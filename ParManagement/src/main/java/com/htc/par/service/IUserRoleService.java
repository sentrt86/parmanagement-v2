package com.htc.par.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.htc.par.model.UserRole;

@Service
public interface IUserRoleService {
	
	public List<UserRole>  getAllUserRoles() throws Exception ;
	public int getNextUserRoleId() throws Exception;
	public String deleteUserRole(int userRoleId) throws Exception;
	public String createUserRole(UserRole userRole)  throws Exception ;
	public String updateUserRole(UserRole userRole)  throws Exception;

}
