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
import com.htc.par.model.Area;
import com.htc.par.model.ParMaster;
import com.htc.par.model.ResponseException;

@Service
public class ParMasterServiceImpl  implements IParMasterService{
	
	@Value("${ParServiceApiUrl}")
	private String parServiceApiUrl;

	@Autowired
	RestTemplate restTemplate;
	

	@Override
	public String createParMaster(ParMaster parmaster) throws Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/parmaster/createParMaster/";
		HttpEntity<ParMaster> request = new HttpEntity<>(parmaster);
		try {
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,request, new ParameterizedTypeReference<String>() {});
			return response.getBody();
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);
			throw new Exception(responseException.getMessage());

		}
	}

	@Override
	public String updateIntentToFill(String parNum, Boolean intentToFill, String intentSentDate) throws Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/parmaster/updateIntentToFill/"+parNum+"/"+intentToFill+"/"+intentSentDate;
		try {
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<String>() {});
			return response.getBody();
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);
			throw new Exception(responseException.getMessage());

		}
	}

	// Get next par sequence number
	
	@Override
	public int getNextParSeqId() throws Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/parmaster/getNextParSeqId";
		try {
			ResponseEntity<Integer> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Integer>() {});
			return response.getBody();
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);
			throw new Exception(responseException.getMessage());

		}
	}

	@Override
	public List<ParMaster> getParMasterByParNum(String parNum) throws Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/parmaster/getParMasterByParNum";
		HttpEntity<String> request = new HttpEntity<>(parNum);
		try {
			ResponseEntity<List<ParMaster>> response = restTemplate.exchange(url, HttpMethod.GET,request, new ParameterizedTypeReference<List<ParMaster>>() {});
			return response.getBody();
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);
			throw new Exception(responseException.getMessage());

		}
	}

}
