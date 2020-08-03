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
import com.htc.par.model.Recruiter;
import com.htc.par.model.ResponseException;

@Service
public class RecruiterServiceImpl implements IRecruiterService{

	@Value("${ParServiceApiUrl}")
	private String parServiceApiUrl;

	@Autowired
	RestTemplate restTemplate;
	
	
	// Get all the recruiters from the recruiter table

	@Override
	public List<Recruiter> getAllRecruiters() throws Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/recruiter/getRecruiters";
		try {
			ResponseEntity<List<Recruiter>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Recruiter>>() {});
			List<Recruiter> allRecruiters = response.getBody();
			for(Recruiter recruiter: allRecruiters) {
				recruiter.setRecruiterActive(recruiter.getRecruiterActive().equalsIgnoreCase("true") ? "Yes" : "No");
				recruiter.setRecruiterEmailFlag(recruiter.getRecruiterEmailFlag().equalsIgnoreCase("true") ? "Yes" : "No");
			}
			return allRecruiters;
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);
			throw new Exception(responseException.getMessage());
		}
	}
	
	// Get all the active recruiters from the recruiter table

		@Override
		public List<Recruiter> getAllActiveRecruiters() throws Exception {
			ResponseException responseException = null;
			String url = parServiceApiUrl + "/recruiter/getActiveRecruiters";
			try {
				ResponseEntity<List<Recruiter>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Recruiter>>() {});
				List<Recruiter> allRecruiters = response.getBody();
				for(Recruiter recruiter: allRecruiters) {
					recruiter.setRecruiterActive(recruiter.getRecruiterActive().equalsIgnoreCase("true") ? "Yes" : "No");
					recruiter.setRecruiterEmailFlag(recruiter.getRecruiterEmailFlag().equalsIgnoreCase("true") ? "Yes" : "No");
				}
				return allRecruiters;
			}catch(HttpStatusCodeException e) {
				ObjectMapper mapper = new ObjectMapper();		
				responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);
				throw new Exception(responseException.getMessage());
			}
		}

	// Get the next recruiter id from recruiter sequence

	@Override
	public int getNextRecruiterId() throws Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/recruiter/getNextRecruiterId";
		try {
			ResponseEntity<Integer> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Integer>() {});
			return response.getBody();
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);
			throw new Exception(responseException.getMessage());

		}

	}


	// Delete the recruiter from the recruiter table

	@Override
	public String deleteRecruiter(int recruiterId) throws Exception {	
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/recruiter/deleteRecruiter/"+ recruiterId;
		HttpEntity<Integer> request = new HttpEntity<>(recruiterId);
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


	// create the recruiter  in the recruiter table

	@Override
	public String createRecruiter(Recruiter recruiter) throws Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/recruiter/createRecruiter";
		recruiter.setRecruiterActive(recruiter.getRecruiterActive().equalsIgnoreCase("Yes") ? "true" : "false");
		recruiter.setRecruiterEmailFlag(recruiter.getRecruiterEmailFlag().equalsIgnoreCase("Yes") ? "true" : "false");
		HttpEntity<Recruiter> request = new HttpEntity<>(recruiter);
		try { 
			ResponseEntity<String> 	response = restTemplate.exchange(url, HttpMethod.POST,request, new ParameterizedTypeReference<String>() {});							
			return response.getBody();
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);			
		} 
		return responseException.getMessage();
	}

	// Update recruiter in the recruiter table

	@Override
	public String updateRecruiter(Recruiter recruiter) throws Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/recruiter/updateRecruiter";
		recruiter.setRecruiterActive(recruiter.getRecruiterActive().equalsIgnoreCase("Yes") ? "true" : "false");
		recruiter.setRecruiterEmailFlag(recruiter.getRecruiterEmailFlag().equalsIgnoreCase("Yes") ? "true" : "false");
		HttpEntity<Recruiter> request = new HttpEntity<>(recruiter);
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
