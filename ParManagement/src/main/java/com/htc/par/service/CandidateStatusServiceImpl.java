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
import com.htc.par.model.ParMaster;
import com.htc.par.model.ResponseException;

@Service
public class CandidateStatusServiceImpl {
	
	@Value("${ParServiceApiUrl}")
	private String parServiceApiUrl;
	
	@Autowired
	RestTemplate restTemplate;
	
	
	public List<ParMaster> getParMasterbyCandidateId(int candidateId) throws Exception{
		System.out.println("Senthil Skips starte -service -1: "+candidateId);
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/getParMasterBycandidateId/"+candidateId;
		HttpEntity<Integer> request = new HttpEntity<>(candidateId);
		try {
			ResponseEntity<List<ParMaster>> response = restTemplate.exchange(url, HttpMethod.GET,request, new ParameterizedTypeReference<List<ParMaster>>() {});
			System.out.println("service method resonse body");
			System.out.println("response get body"+response.getBody());
			return response.getBody();
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();	
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);
			throw new Exception(responseException.getMessage());

		}
		
	}

}
