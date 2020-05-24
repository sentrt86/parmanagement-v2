package com.htc.par.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
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
			  for(Candidate extStaff: allCandidates) {
				  extStaff.setCandidateActive(extStaff.getCandidateActive().equalsIgnoreCase("true") ? "Yes" : "No");
				  
				  System.out.println("ext:"+ extStaff.toString());
			  }
			  return allCandidates;
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);
			throw new Exception(responseException.getMessage());
		}
	}

	@Override
	public int getNextCandidateId() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String deleteCandidate(int candidateId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createCandidate(Candidate candidate) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateCandidate(Candidate candidate) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	

}
