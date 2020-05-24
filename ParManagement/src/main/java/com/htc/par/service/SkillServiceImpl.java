package com.htc.par.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.htc.par.model.Area;
import com.htc.par.model.Skill;

@Service
public class SkillServiceImpl implements ISkillService {
	

	@Value("${ParServiceApiUrl}")
	private String parServiceApiUrl;
	
	@Autowired
	RestTemplate restTemplate;

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

	@Override
	public int getNextSkillId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String deleteSkill(int skillId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createSkill(Skill skill) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateSkill(Skill skill) {
		// TODO Auto-generated method stub
		return null;
	}

}
