package com.htc.par.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
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
				  
				  System.out.println("ext:"+ extStaff.toString());
			  }
			  return allExtStaffs;
		}catch(HttpStatusCodeException e) {
			ObjectMapper mapper = new ObjectMapper();		
			responseException = mapper.readValue(e.getResponseBodyAsString(),ResponseException.class);
			throw new Exception(responseException.getMessage());
		}
	}

	@Override
	public int getNextExtStaffId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String deleteExtStaff(int extStaffId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createExtStaff(ExternalStaff externalStaff) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateExtStaff(ExternalStaff externalStaff) {
		// TODO Auto-generated method stub
		return null;
	}

}
