package com.htc.par.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.htc.par.model.UserMaster;
import com.htc.par.model.ResponseException;


@Service
public class UserMasterServiceImpl implements IUserMasterService{
	
	
	@Value("${ParServiceApiUrl}")
	private String parServiceApiUrl;
	
	@Autowired
	RestTemplate restTemplate;

	// Get all the user info from the user master table for given user name
	
	@Override
	public List<UserMaster> getUserByUserName(String username) throws Exception {
		List<UserMaster> userMaster = new ArrayList<UserMaster>();
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/usermaster/getUserMasterByUserName/"+ username;
		HttpEntity<String> request = new HttpEntity<>(username);
		try {
		ResponseEntity<List<UserMaster>> response = restTemplate.exchange(url, HttpMethod.GET,request, new ParameterizedTypeReference<List<UserMaster>>() {});		
		userMaster = response.getBody();
		return userMaster;
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);	
			throw new Exception(responseException.getMessage());

		}
		

	}
	
	// Get all the user info from the user master table
	@Override
	public List<UserMaster> getAllUsers() throws Exception {
		ResponseException responseException = null;
		List<UserMaster> allUserMaster = new ArrayList<UserMaster>();
		String url = parServiceApiUrl + "/usermaster/getAllUserMaster";
		try {
			ResponseEntity<List<UserMaster>> response = restTemplate.exchange(url, HttpMethod.GET,null, new ParameterizedTypeReference<List<UserMaster>>() {});		
			allUserMaster = response.getBody();
			for(UserMaster userMaster : allUserMaster) {
				userMaster.setUserActive(userMaster.getUserActive().equalsIgnoreCase("true") ? "Yes" : "No");
			}
			return allUserMaster;
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);	
			throw new Exception(responseException.getMessage());

		}
		

	}
	
	// Get the next user  id from user sequence
	
		@Override
		public int getNextUserId() throws Exception {
			ResponseException responseException = null;
			String url = parServiceApiUrl + "/usermaster/getNextUserMasterId";
			try {
				ResponseEntity<Integer> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Integer>() {});
				return response.getBody();
			}catch(HttpStatusCodeException e) {
				ObjectMapper mapper = new ObjectMapper();		
				responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);
				throw new Exception(responseException.getMessage());
				
			}
			
		}

		
		// Delete the user info from the user master table
		
		@Override
		public String deleteUser(int userMasterId) throws Exception {	
			ResponseException responseException = null;
			String url = parServiceApiUrl + "/usermaster/deleteUserMaster/"+ userMasterId;
			HttpEntity<Integer> request = new HttpEntity<>(userMasterId);
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


		// create the user info in the user master table
		
		@Override
		public String createUser(UserMaster userMaster) throws Exception {
			ResponseException responseException = null;
			BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
			String url = parServiceApiUrl + "/usermaster/createUserMaster";
			userMaster.setUserActive(userMaster.getUserActive().equalsIgnoreCase("Yes") ? "true" : "false");
			userMaster.setPassword(bCrypt.encode(userMaster.getPassword()));
			HttpEntity<UserMaster> request = new HttpEntity<>(userMaster);
			try { 
				ResponseEntity<String> 	response = restTemplate.exchange(url, HttpMethod.POST,request, new ParameterizedTypeReference<String>() {});							
				return response.getBody();
			}catch(HttpStatusCodeException e) {
				ObjectMapper mapper = new ObjectMapper();		
				responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);			
			} 
			return responseException.getMessage();
		}

		// update user master in the user master table
		
		@Override
		public String updateUser(UserMaster userMaster) throws Exception {
			ResponseException responseException = null;
			BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
			String url = parServiceApiUrl + "/usermaster/updateUserMaster";
			userMaster.setUserActive(userMaster.getUserActive().equalsIgnoreCase("Yes") ? "true" : "false");
			userMaster.setPassword(bCrypt.encode(userMaster.getPassword()));
			HttpEntity<UserMaster> request = new HttpEntity<>(userMaster);
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
