package com.htc.par.service;

import org.springframework.stereotype.Service;
import com.htc.par.exceptions.ResourceNotFoundException;
import com.htc.par.model.ParMaster;

@Service
public interface IParMasterService {
	
	public String createParMaster(ParMaster parmaster) throws Exception;
	public String updateParMaster(ParMaster parmaster) throws Exception;
	public String deleteParMasterByParNum(int parNum) throws Exception;
	public ParMaster getParMasterByParNum(String parNum) throws ResourceNotFoundException,Exception;
	public String updateIntentToFill(ParMaster parmaster) throws Exception;	
	public String updateEmailSent(ParMaster parmaster) throws Exception;	
	public Boolean sendEmailRecruiters(ParMaster parmaster) throws Exception;
	public int  getNextParSeqId() throws Exception;
	
	
	
	

}
