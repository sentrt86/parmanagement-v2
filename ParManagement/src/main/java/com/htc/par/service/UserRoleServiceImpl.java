package com.htc.par.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.htc.par.model.UserRole;
import com.htc.par.model.ResponseException;

@Service
public class UserRoleServiceImpl implements IUserRoleService {

	@Value("${ParServiceApiUrl}")
	private String parServiceApiUrl;
	
	@Autowired
	RestTemplate restTemplate;
	
	// Get all the user roles from the user role table
	@Override
	public List<UserRole> getAllUserRoles() throws Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/userrole/getUserRoles";
		try {
			ResponseEntity<List<UserRole>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<UserRole>>() {});
			return response.getBody();
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);
			throw new Exception(responseException.getMessage());
		}
	}

	// Get the next user role id  from the use role sequence
	
	@Override
	public int getNextUserRoleId() throws Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/userrole/getNextUserRoleId";
		try {			
			ResponseEntity<Integer> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Integer>() {});
			return response.getBody();
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);
			throw new Exception(responseException.getMessage());
		}	
	}

	// Delete the use role from the user role table using the user role id
	
	@Override
	public String deleteUserRole(int userRoleId) throws Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/userrole/deleteUserRole/"+userRoleId ;
		HttpEntity<Integer> request = new HttpEntity<>(userRoleId);
		try {
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST,request, new ParameterizedTypeReference<String>() {});		
			return response.getBody();
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);	
			System.out.println("error:"+ responseException.getMessage());
		}
		
		return responseException.getMessage();
	}

	// Create the user role in the user role table
	@Override
	public String createUserRole(UserRole userRole) throws Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/userrole/createUserRole";
		HttpEntity<UserRole> request = new HttpEntity<>(userRole);
		try { 
			ResponseEntity<String> 	response = restTemplate.exchange(url, HttpMethod.POST,request, new ParameterizedTypeReference<String>() {});							
			return response.getBody();
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);			
		} 
		return responseException.getMessage();
	}

	// update the user role in the user role table
	
	@Override
	public String updateUserRole(UserRole userRole) throws Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/userrole/updateUserRole";
		System.out.println(userRole.toString());
		HttpEntity<UserRole> request = new HttpEntity<>(userRole);
		try {
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST,request, new ParameterizedTypeReference<String>() {});		
			return response.getBody();
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);	
		}		
		return responseException.getMessage();
	}

}
