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
import com.htc.par.model.Candidate;
import com.htc.par.model.ResponseException;

@Service
public class CandidateServiceImpl  implements ICandidateService{

	@Value("${ParServiceApiUrl}")
	private String parServiceApiUrl;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public List<Candidate> getAllCandidates() throws Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/candidate/getCandidates";
		try {
			ResponseEntity<List<Candidate>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Candidate>>() {});
			List<Candidate> allCandidates = response.getBody();
			  for(Candidate candidate: allCandidates) {
				  candidate.setCandidateActive(candidate.getCandidateActive().equalsIgnoreCase("true") ? "Yes" : "No");
			  }
			  return allCandidates;
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);
			throw new Exception(responseException.getMessage());
		}
	}
	
	@Override
	public List<Candidate> getAllActiveCandidates() throws Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/candidate/getActiveCandidates";
		try {
			ResponseEntity<List<Candidate>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Candidate>>() {});
			List<Candidate> allCandidates = response.getBody();
			  for(Candidate candidate: allCandidates) {
				  candidate.setCandidateActive(candidate.getCandidateActive().equalsIgnoreCase("true") ? "Yes" : "No");
			  }
			  return allCandidates;
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);
			throw new Exception(responseException.getMessage());
		}
	}

	// Get the next candidate id from candidate sequence
	
		@Override
		public int getNextCandidateId() throws Exception {
			ResponseException responseException = null;
			String url = parServiceApiUrl + "/candidate/getNextCandidateId";
			try {
				ResponseEntity<Integer> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Integer>() {});
				return response.getBody();
			}catch(HttpStatusCodeException e) {
				ObjectMapper mapper = new ObjectMapper();		
				responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);
				throw new Exception(responseException.getMessage());
				
			}
			
		}

		
		// Delete the candidate from the candidate table
		
		@Override
		public String deleteCandidate(int candidateId) throws Exception {	
			ResponseException responseException = null;
			String url = parServiceApiUrl + "/candidate/deleteCandidate/"+ candidateId;
			HttpEntity<Integer> request = new HttpEntity<>(candidateId);
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


		// create the candidate  in the candidate table
		
		@Override
		public String createCandidate(Candidate candidate) throws Exception {
			ResponseException responseException = null;
			String url = parServiceApiUrl + "/candidate/createCandidate";
			candidate.setCandidateActive(candidate.getCandidateActive().equalsIgnoreCase("Yes") ? "true" : "false");
			HttpEntity<Candidate> request = new HttpEntity<>(candidate);
			try { 
				ResponseEntity<String> 	response = restTemplate.exchange(url, HttpMethod.POST,request, new ParameterizedTypeReference<String>() {});							
				return response.getBody();
			}catch(HttpStatusCodeException e) {
				ObjectMapper mapper = new ObjectMapper();		
				responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);			
			} 
			return responseException.getMessage();
		}

		// update candidate in the candidate table
		
		@Override
		public String updateCandidate(Candidate candidate) throws Exception {
			ResponseException responseException = null;
			String url = parServiceApiUrl + "/candidate/updateCandidate";
			candidate.setCandidateActive(candidate.getCandidateActive().equalsIgnoreCase("Yes") ? "true" : "false");
			HttpEntity<Candidate> request = new HttpEntity<>(candidate);
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
