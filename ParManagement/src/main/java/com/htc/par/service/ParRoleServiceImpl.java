package com.htc.par.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.htc.par.model.ParRole;

@Service
public class ParRoleServiceImpl implements IParRoleService {

	@Value("${ParServiceApiUrl}")
	private String parServiceApiUrl;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public List<ParRole> getAllParRoles() {

		String url = parServiceApiUrl + "/role/getParRoles";
		
		ResponseEntity<List<ParRole>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<ParRole>>() {});
		if (response.getStatusCode() == HttpStatus.OK)
		{
			List<ParRole> allParRoles = response.getBody();
			  for(ParRole parRole: allParRoles) {
				  parRole.setRoleActive(parRole.getRoleActive().equalsIgnoreCase("true") ? "Yes" : "No");
			  }
			  return allParRoles;
		}
		return null;
	}

	@Override
	public int getNextParroleId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String deleteParRole(int parRoleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createParRole(ParRole parRole) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateParRole(ParRole parRole) {
		// TODO Auto-generated method stub
		return null;
	}

}
