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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.htc.par.exceptions.ResourceNotFoundException;
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
		String url = parServiceApiUrl + "/parmaster/createParMaster";
		HttpEntity<ParMaster> request = new HttpEntity<>(parmaster);
		try {
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST,request, new ParameterizedTypeReference<String>() {});
			return response.getBody();
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);
			throw new Exception(responseException.getMessage());

		}
	}

	@Override
	public String updateIntentToFill(ParMaster parmaster) throws Exception {
		ResponseException responseException = null;	
		parmaster.setIntentToFill(parmaster.getIntentToFill().equalsIgnoreCase("Yes") ? "true" : "false");
		String url = parServiceApiUrl + "/parmaster/updateIntentToFill";
		HttpEntity<ParMaster> request = new HttpEntity<>(parmaster);
		try {
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, new ParameterizedTypeReference<String>() {});
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
		String url = parServiceApiUrl + "/parmaster/getParMasterByParNum/"+parNum;
		HttpEntity<String> request = new HttpEntity<>(parNum);
		try {
			ResponseEntity<List<ParMaster>> response = restTemplate.exchange(url, HttpMethod.GET,request, new ParameterizedTypeReference<List<ParMaster>>() {});
			System.out.println(response.getBody());
			return response.getBody();
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();	
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);
			throw new Exception(responseException.getMessage());

		}
	}

}
