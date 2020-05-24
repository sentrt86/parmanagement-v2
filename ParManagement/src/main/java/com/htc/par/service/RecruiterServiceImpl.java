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
import com.htc.par.model.Recruiter;

@Service
public class RecruiterServiceImpl implements IRecruiterService{

	@Value("${ParServiceApiUrl}")
	private String parServiceApiUrl;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public List<Recruiter> getAllRecruiters() {
		String url = parServiceApiUrl + "/recruiter/getRecruiters";
		
		ResponseEntity<List<Recruiter>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Recruiter>>() {});
		if (response.getStatusCode() == HttpStatus.OK)
		{
			System.out.println("response:"+response.getBody().toString());
			List<Recruiter> allRecruiters = response.getBody();
			  for(Recruiter recruiter: allRecruiters) {
				  recruiter.setRecruiterActive(recruiter.getRecruiterActive().equalsIgnoreCase("true") ? "Yes" : "No");
				  recruiter.setRecruiterEmailFlag(recruiter.getRecruiterEmailFlag().equalsIgnoreCase("true") ? "Yes" : "No");
				  
				  System.out.println(recruiter.toString());
			  }
			  return allRecruiters;
		}
		
		
		return null;
	}

	@Override
	public int getNextRecruiterId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String deleteRecruiter(int recruiterId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createRecruiter(Recruiter recruiter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateRecruiter(Recruiter recruiter) {
		// TODO Auto-generated method stub
		return null;
	}

}
