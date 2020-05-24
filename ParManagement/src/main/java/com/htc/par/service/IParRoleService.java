package com.htc.par.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.htc.par.model.ParRole;

@Service
public interface IParRoleService {
	

	public List<ParRole>  getAllParRoles();
	public int getNextParroleId();
	public String deleteParRole(int parRoleId);
	public String createParRole(ParRole parRole);
	public String updateParRole(ParRole parRole);

}
