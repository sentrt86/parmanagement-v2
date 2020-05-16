package com.htc.par.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.htc.par.model.Area;

@Service
public class AreaServiceImpl implements IAreaService{

	@Autowired
	RestTemplate restTemplate;
	

	@Override
	public List<Area> getAllAreas() {
		
		String url = "http://localhost:8082/par/area/getAreaById/9000";
		ResponseEntity<List<Area>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Area>>() {});
		if (response.getStatusCode() == HttpStatus.OK)
		{
			List<Area> allAreas = response.getBody();
			
			  for(Area area: allAreas) {
				  String areaActive = area.getAreaActive();
				  String value = (areaActive = true ? "Yes" : "No");
				  area.setAreaActive(value);		  
			  }
			  
			  return allAreas;
		}
		
		 
		return null;
	}

}
