package com.htc.par.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.htc.par.model.Recruiter;



@Service
public interface IRecruiterService {
	
	
	public List<Recruiter>  getAllRecruiters();
	public int getNextRecruiterId();
	public String deleteRecruiter(int recruiterId);
	public String createRecruiter(Recruiter recruiter);
	public String updateRecruiter(Recruiter recruiter);
	
	

}
