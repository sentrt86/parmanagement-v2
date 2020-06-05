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
import com.htc.par.model.ExternalStaff;
import com.htc.par.model.ResponseException;

@Service
public class ExternalStaffServiceImpl  implements IExternalStaffService{

	@Value("${ParServiceApiUrl}")
	private String parServiceApiUrl;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public List<ExternalStaff> getAllExtStaffs() throws Exception {
		ResponseException responseException = null;
		String url = parServiceApiUrl + "/extstaff/getExtStaffs";
		try {
			ResponseEntity<List<ExternalStaff>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<ExternalStaff>>() {});
			List<ExternalStaff> allExtStaffs = response.getBody();
			  for(ExternalStaff extStaff: allExtStaffs) {
				  extStaff.setExtStaffActive(extStaff.getExtStaffActive().equalsIgnoreCase("true") ? "Yes" : "No");
			  }
			  return allExtStaffs;
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);
			throw new Exception(responseException.getMessage());
		}
	
	}

		// Get the next extStaff id from extStaff sequence
		
		@Override
		public int getNextExtStaffId() throws Exception {
			ResponseException responseException = null;
			String url = parServiceApiUrl + "/extstaff/getNextExtStaffId";
			try {
				ResponseEntity<Integer> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<Integer>() {});
				return response.getBody();
			}catch(HttpStatusCodeException e) {
				ObjectMapper mapper = new ObjectMapper();		
				responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);
				throw new Exception(responseException.getMessage());
				
			}
			
		}

		
		// Delete the extStaff from the extStaff table
		
		@Override
		public String deleteExtStaff(int extStaffId) throws Exception {	
			ResponseException responseException = null;
			String url = parServiceApiUrl + "/extstaff/deleteExtStaff/"+ extStaffId;
			HttpEntity<Integer> request = new HttpEntity<>(extStaffId);
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


		// create the extStaff  in the extStaff table
		
		@Override
		public String createExtStaff(ExternalStaff extStaff) throws Exception {
			ResponseException responseException = null;
			String url = parServiceApiUrl + "/extstaff/createExtStaff";
			extStaff.setExtStaffActive(extStaff.getExtStaffActive().equalsIgnoreCase("Yes") ? "true" : "false");
			HttpEntity<ExternalStaff> request = new HttpEntity<>(extStaff);
			try { 
				ResponseEntity<String> 	response = restTemplate.exchange(url, HttpMethod.POST,request, new ParameterizedTypeReference<String>() {});							
				return response.getBody();
			}catch(HttpStatusCodeException e) {
				ObjectMapper mapper = new ObjectMapper();		
				responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);			
			} 
			return responseException.getMessage();
		}

		// update extStaff in the extStaff table
		
		@Override
		public String updateExtStaff(ExternalStaff extStaff) throws Exception {
			ResponseException responseException = null;
			String url = parServiceApiUrl + "/extstaff/updateExtStaff";
			extStaff.setExtStaffActive(extStaff.getExtStaffActive().equalsIgnoreCase("Yes") ? "true" : "false");
			HttpEntity<ExternalStaff> request = new HttpEntity<>(extStaff);
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
