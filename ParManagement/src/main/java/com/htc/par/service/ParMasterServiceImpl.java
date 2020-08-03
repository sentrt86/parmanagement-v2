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
import com.htc.par.model.Recruiter;
import com.htc.par.model.ResponseException;
import com.htc.par.utility.EmailUtility;


@Service
public class ParMasterServiceImpl  implements IParMasterService{
	
	@Value("${ParServiceApiUrl}")
	private String parServiceApiUrl;

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	RecruiterServiceImpl recruiterServiceImpl;
	
	@Autowired
	EmailUtility emailUtility;
	

	@Override
	public String createParMaster(ParMaster parmaster) throws Exception {
		
		System.out.println("par:"+parmaster.toString());
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
	public String updateParMaster(ParMaster parmaster) throws Exception {
		ResponseException responseException = null;
		parmaster.setIntentToFill(parmaster.getIntentToFill().equalsIgnoreCase("Yes") ? "true" : "false");
		parmaster.setEmailSent(parmaster.getEmailSent().equalsIgnoreCase("Yes") ? "true" : "false");
		String url = parServiceApiUrl + "/parmaster/updateParMaster";
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
	public ParMaster getParMasterByParNum(String parNum) throws Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/parmaster/getParMasterByParNum/"+parNum;
		HttpEntity<String> request = new HttpEntity<>(parNum);
		try {
			ResponseEntity<ParMaster> response = restTemplate.exchange(url, HttpMethod.GET,request, new ParameterizedTypeReference<ParMaster>() {});
			return response.getBody();
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();	
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);
			throw new Exception(responseException.getMessage());

		}
	}

	@Override
	public String deleteParMasterByParNum(int parNum) throws Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/parmaster/deleteParMaster/"+parNum;
		HttpEntity<Integer> request = new HttpEntity<>(parNum);
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
	public Boolean sendEmailRecruiters(ParMaster parmaster) throws Exception {
		// TODO Auto-generated method stub
		
		 int maillistSize=0;
		 int maillistCount=0;
		 
		java.util.Map<String, Object> model = new java.util.HashMap<String, Object>();
        model.put("areaName", parmaster.getArea().getAreaName());
        model.put("roleName", parmaster.getParRole().getRoleName());
        model.put("locationName", parmaster.getLocation().getLocationName());
        model.put("skillName", parmaster.getSkill().getSkillName());
        model.put("parNum", parmaster.getParNumber());
        model.put("extstaffName", parmaster.getExternalStaff().getExtStaffName());
        model.put("parRecievedDate",parmaster.getParReceivedDate());
        model.put("parStatus", parmaster.getParStatus());
        model.put("intenttoFilldate", parmaster.getIntentSentDate());
        model.put("parComment", parmaster.getParComment());
        
        List<Recruiter> recruiterList = recruiterServiceImpl.getAllActiveRecruiters();
       
        for (Recruiter recruiter : recruiterList) {
        	if(recruiter.getRecruiterEmailFlag().equalsIgnoreCase("Yes")) {
            	maillistSize++;
        	}
        	
		}
        
        String mailList[] = new String[maillistSize++];
        
        for (Recruiter recruiter : recruiterList) {
        	if(recruiter.getRecruiterEmailFlag().equalsIgnoreCase("Yes")) {
        		mailList[maillistCount]=recruiter.getRecruiterEmail();
            	maillistCount++;
        	}
        	
		}
        
		return emailUtility.sendEmail(model,mailList);
	}

	@Override
	public String updateEmailSent(ParMaster parmaster) throws Exception {
		ResponseException responseException = null;	
		parmaster.setIntentToFill(parmaster.getIntentToFill().equalsIgnoreCase("Yes") ? "true" : "false");
		parmaster.setEmailSent(parmaster.getEmailSent().equalsIgnoreCase("Yes") ? "true" : "false");
		String url = parServiceApiUrl + "/parmaster/updateEmailSent";
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

	

}
