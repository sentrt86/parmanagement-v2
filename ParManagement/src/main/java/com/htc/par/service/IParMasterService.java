package com.htc.par.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.htc.par.model.ParMaster;

@Service
public interface IParMasterService {
	
	public String createParMaster(ParMaster parmaster) throws Exception;
	public String updateIntentToFill(String parNum,Boolean intentToFill, String intentSentDate) throws Exception;
	public int  getNextParSeqId() throws Exception;
	public List<ParMaster> getParMasterByParNum(String parNum) throws Exception;

}
