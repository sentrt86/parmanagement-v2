package com.htc.par.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.htc.par.model.Recruiter;



@Service
public interface IRecruiterService {
	
	
	public List<Recruiter>  getAllRecruiters() throws Exception;
	public List<Recruiter>  getAllActiveRecruiters() throws Exception;
	public int getNextRecruiterId() throws Exception;
	public String deleteRecruiter(int recruiterId) throws Exception;
	public String createRecruiter(Recruiter recruiter) throws Exception;
	public String updateRecruiter(Recruiter recruiter) throws Exception;
	
	

}
