package com.htc.par.service;

import java.util.List;

import com.htc.par.model.Candidate;

public interface ICandidateService {
	
	public List<Candidate>  getAllCandidates() throws Exception;
	public List<Candidate>  getAllActiveCandidates() throws Exception;
	public int getNextCandidateId() throws Exception;
	public String deleteCandidate(int candidateId) throws Exception;
	public String createCandidate(Candidate candidate) throws Exception;
	public String updateCandidate(Candidate candidate) throws Exception;

}
