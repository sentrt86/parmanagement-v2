package com.htc.par.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.htc.par.model.Skill;
import com.htc.par.model.ResponseException;

@Service
public class SkillServiceImpl implements ISkillService {
	

	@Value("${ParServiceApiUrl}")
	private String parServiceApiUrl;
	
	@Autowired
	RestTemplate restTemplate;
	
	// Get all the skills from the skill table

	@Override
	public List<Skill> getAllSkills() {
		String url = parServiceApiUrl + "/skill/getSkills";
		
		ResponseEntity<List<Skill>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Skill>>() {});
		if (response.getStatusCode() == HttpStatus.OK)
		{
			List<Skill> allSkills = response.getBody();
			  for(Skill skill: allSkills) {
				  skill.setSkillActive(skill.getSkillActive().equalsIgnoreCase("true") ? "Yes" : "No");
			  }
			  return allSkills;
		}
		return null;
	}

	// Get the next skill id from skill sequence
	
	@Override
	public int getNextSkillId() throws Exception{
		
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/skill/getNextSkillId";
		try {
			ResponseEntity<Integer> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Integer>() {});
			return response.getBody();
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);
			throw new Exception(responseException.getMessage());
			
		}
	}

	// Delete the skill from the skill table
	
	@Override
	public String deleteSkill(int skillId) throws Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/skill/deleteSkill/"+ skillId;
		HttpEntity<Integer> request = new HttpEntity<>(skillId);
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

	// create the skill  in the skill table
	
	@Override
	public String createSkill(Skill skill) throws Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/skill/createSkill";
		skill.setSkillActive(skill.getSkillActive().equalsIgnoreCase("Yes") ? "true" : "false");
		HttpEntity<Skill> request = new HttpEntity<>(skill);
		try { 
			ResponseEntity<String> 	response = restTemplate.exchange(url, HttpMethod.POST,request, new ParameterizedTypeReference<String>() {});							
			return response.getBody();
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);			
		} 
		return responseException.getMessage();
	}

	// update skill in the skill table
	
	@Override
	public String updateSkill(Skill skill) throws Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/skill/updateSkill";
		skill.setSkillActive(skill.getSkillActive().equalsIgnoreCase("Yes") ? "true" : "false");
		HttpEntity<Skill> request = new HttpEntity<>(skill);
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
