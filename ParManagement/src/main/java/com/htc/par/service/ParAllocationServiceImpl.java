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
import com.htc.par.exceptions.ResourceNotFoundException;
import com.htc.par.model.ParAllocation;
import com.htc.par.model.ResponseException;

@Service
public class ParAllocationServiceImpl  implements IParAllocationService{
	
	
	@Value("${ParServiceApiUrl}")
	private String parServiceApiUrl;

	@Autowired
	RestTemplate restTemplate;
	
	
	@Override
	public List<ParAllocation> getCandidateReceivedByParNum(String parNum) throws ResourceNotFoundException, Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/parallocation/getCandidateReceivedByParNum/"+parNum;
		HttpEntity<String> request = new HttpEntity<>(parNum);
		try {
			ResponseEntity<List<ParAllocation>> response = restTemplate.exchange(url, HttpMethod.GET,request, new ParameterizedTypeReference<List<ParAllocation>>() {});
			return response.getBody();
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();	
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);
			throw new Exception(responseException.getMessage());

		}
	}

	@Override
	public String deleteParAllocationByParAllocId(int parAllocId) throws Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/parallocation/deleteParAllocationParAllocId/"+parAllocId;
		HttpEntity<Integer> request = new HttpEntity<>(parAllocId);
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
	public String createParAllocation(ParAllocation parAllocation) throws Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/parallocation/createParAllocation";
		HttpEntity<ParAllocation> request = new HttpEntity<>(parAllocation);
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
	public String updateSubmitCandidate(ParAllocation parAllocation) throws Exception {
		ResponseException responseException = null;	
		parAllocation.setSubmitIndicator(parAllocation.getSubmitIndicator().equalsIgnoreCase("Yes") ? "true" : "false");
		String url = parServiceApiUrl + "/parallocation/updateSubmitCandidate";
		System.out.println("submit:"+ parAllocation.toString());
		HttpEntity<ParAllocation> request = new HttpEntity<>(parAllocation);
		try {
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, new ParameterizedTypeReference<String>() {});
			return response.getBody();
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);
			throw new Exception(responseException.getMessage());

		}
	}


	@Override
	public String updatePrescreening(ParAllocation parAllocation) throws Exception {
		ResponseException responseException = null;	
		String url = parServiceApiUrl + "/parallocation/updatePrescreening";
		System.out.println("submit:"+ parAllocation.toString());
		HttpEntity<ParAllocation> request = new HttpEntity<>(parAllocation);
		try {
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, new ParameterizedTypeReference<String>() {});
			return response.getBody();
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);
			throw new Exception(responseException.getMessage());

		}
	}

	@Override
	public List<ParAllocation> getParAllocationByParNum(String parNum) throws ResourceNotFoundException, Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/parallocation/getParAllocationByParNum/"+parNum;
		HttpEntity<String> request = new HttpEntity<>(parNum);
		try {
			ResponseEntity<List<ParAllocation>> response = restTemplate.exchange(url, HttpMethod.GET,request, new ParameterizedTypeReference<List<ParAllocation>>() {});
			return response.getBody();
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();	
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);
			throw new Exception(responseException.getMessage());

		}
	}

}
