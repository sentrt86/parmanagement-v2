package com.htc.par.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.htc.par.model.ParRole;
import com.htc.par.model.ResponseException;

@Service
public class ParRoleServiceImpl implements IParRoleService {

	@Value("${ParServiceApiUrl}")
	private String parServiceApiUrl;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public List<ParRole> getAllParRoles() throws Exception{

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

	// Get the next par role id from par role sequence
	
		@Override
		public int getNextParRoleId() throws Exception {
			ResponseException responseException = null;
			String url = parServiceApiUrl + "/role/getNextParRoleId";
			try {
				ResponseEntity<Integer> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Integer>() {});
				return response.getBody();
			}catch(HttpStatusCodeException e) {
				ObjectMapper mapper = new ObjectMapper();		
				responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);
				throw new Exception(responseException.getMessage());
				
			}
			
		}

		
		// Delete the par role from the par role table
		
		@Override
		public String deleteParRole(int parRoleId) throws Exception {	
			ResponseException responseException = null;
			String url = parServiceApiUrl + "/role/deleteParRole/"+ parRoleId;
			HttpEntity<Integer> request = new HttpEntity<>(parRoleId);
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


		// create the par role  in the par role table
		
		@Override
		public String createParRole(ParRole parRole) throws Exception {
			ResponseException responseException = null;
			String url = parServiceApiUrl + "/role/createParRole";
			parRole.setRoleActive(parRole.getRoleActive().equalsIgnoreCase("Yes") ? "true" : "false");
			HttpEntity<ParRole> request = new HttpEntity<>(parRole);
			try { 
				ResponseEntity<String> 	response = restTemplate.exchange(url, HttpMethod.POST,request, new ParameterizedTypeReference<String>() {});							
				return response.getBody();
			}catch(HttpStatusCodeException e) {
				ObjectMapper mapper = new ObjectMapper();		
				responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);			
			} 
			return responseException.getMessage();
		}

		// update par role in the par role table
		
		@Override
		public String updateParRole(ParRole parRole) throws Exception {
			ResponseException responseException = null;
			String url = parServiceApiUrl + "/role/updateParRole";
			parRole.setRoleActive(parRole.getRoleActive().equalsIgnoreCase("Yes") ? "true" : "false");
			HttpEntity<ParRole> request = new HttpEntity<>(parRole);
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
