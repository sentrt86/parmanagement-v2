package com.htc.par.service;

import java.util.List;

import com.htc.par.exceptions.ResourceNotFoundException;
import com.htc.par.model.ParAllocation;

public interface IParAllocationService {
	
	public String deleteParAllocationByParAllocId(int parAllocId) throws Exception;
	public String createParAllocation(ParAllocation parAllocation) throws Exception;
	public List<ParAllocation> getParAllocationByParNum(String parNum) throws ResourceNotFoundException,Exception;
	
	public List<ParAllocation> getCandidateReceivedByParNum(String parNum) throws ResourceNotFoundException,Exception;
	
	public String updateSubmitCandidate(ParAllocation parAllocation) throws Exception;
	
	public String updatePrescreening(ParAllocation parAllocation) throws Exception;

}
