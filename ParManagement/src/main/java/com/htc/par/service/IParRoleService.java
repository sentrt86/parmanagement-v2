package com.htc.par.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.htc.par.model.ParRole;

@Service
public interface IParRoleService {
	

	public List<ParRole>  getAllParRoles() throws Exception;
	public List<ParRole>  getActiveParRoles() throws Exception;
	public int getNextParRoleId() throws Exception;
	public String deleteParRole(int parRoleId) throws Exception;
	public String createParRole(ParRole parRole) throws Exception;
	public String updateParRole(ParRole parRole) throws Exception;

}
