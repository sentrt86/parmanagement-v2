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
import com.htc.par.model.ResponseException;

	

@Service
public class AreaServiceImpl implements IAreaService{

	@Value("${ParServiceApiUrl}")
	private String parServiceApiUrl;
	
	@Autowired
	RestTemplate restTemplate;
	
	
	// Get all the areas from the area table

	@Override
	public List<Area> getAllAreas() throws Exception  {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/area/getAreas";
		try {
			ResponseEntity<List<Area>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Area>>() {});
			List<Area> allAreas = response.getBody();
			  for(Area area: allAreas) {
				  area.setAreaActive(area.getAreaActive().equalsIgnoreCase("true") ? "Yes" : "No");
			  }
			  return allAreas;
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);
			throw new Exception(responseException.getMessage());
		}
	}

    // Get the next area id from area sequence
	
	@Override
	public int getNextAreaId() throws Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/area/getNextAreaId";
		try {
			ResponseEntity<Integer> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Integer>() {});
			return response.getBody();
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);
			throw new Exception(responseException.getMessage());
			
		}
		
	}

	
	// Delete the area from the area table
	
	@Override
	public String deleteArea(int areaId) throws Exception {	
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/area/deleteArea/"+ areaId;
		HttpEntity<Integer> request = new HttpEntity<>(areaId);
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


	// create the area  in the area table
	
	@Override
	public String createArea(Area area) throws Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/area/createArea";
		area.setAreaActive(area.getAreaActive().equalsIgnoreCase("Yes") ? "true" : "false");
		HttpEntity<Area> request = new HttpEntity<>(area);
		try { 
			ResponseEntity<String> 	response = restTemplate.exchange(url, HttpMethod.POST,request, new ParameterizedTypeReference<String>() {});							
			return response.getBody();
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);			
		} 
		return responseException.getMessage();
	}

	// update area in the area table
	
	@Override
	public String updateArea(Area area) throws Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/area/updateArea";
		area.setAreaActive(area.getAreaActive().equalsIgnoreCase("Yes") ? "true" : "false");
		HttpEntity<Area> request = new HttpEntity<>(area);
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
