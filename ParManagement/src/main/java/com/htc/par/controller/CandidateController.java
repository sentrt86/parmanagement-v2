package com.htc.par.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.htc.par.model.Candidate;
import com.htc.par.model.Skill;
import com.htc.par.service.CandidateServiceImpl;
import com.htc.par.service.SkillServiceImpl;

@Controller
public class CandidateController {

	@Autowired
	CandidateServiceImpl candidateServiceImpl;

	@Autowired
	SkillServiceImpl skillServiceImpl;

	// Request Handler for Candidate Form

	@RequestMapping(value="/candidate", method=RequestMethod.GET) 
	public ModelAndView candidate(Locale locale,Model model)  throws Exception{ 		
		ModelAndView  modelView = new ModelAndView();
		modelView.addObject("allCandidatesList", candidateServiceImpl.getAllCandidates());
		modelView.addObject("allSkillsList", skillServiceImpl.getAllSkills());
		modelView.addObject("username",HomeController.username);
		modelView.setViewName("candidate"); 
		return modelView;
	}

	// Request handler for getting the next candidate id

	@RequestMapping(value="/nextCandidateId", method=RequestMethod.GET)
	@Produces(MediaType.TEXT_PLAIN)
	public @ResponseBody int getNextCandidateId()  throws Exception{		
		return candidateServiceImpl.getNextCandidateId();		
	}

	//Request handler to create the candidate

	@RequestMapping(value="/createCandidate", method=RequestMethod.POST) 
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody String createCandidate(@RequestBody String json,HttpServletRequest request) throws Exception { 			
		String data = null;
		Candidate candidate = new Candidate(); 
	    Skill skill = new Skill();
		try {
			org.json.JSONObject jsonObj=new org.json.JSONObject(json);			 
			candidate.setCandidateId(jsonObj.getInt("candidateId"));
		    candidate.setCandidateName(jsonObj.getString("candidateName"));
	    	candidate.setCandidatePhoneNum(jsonObj.getString("candidatePhoneNum"));
			candidate.setCandidateEmailTxt(jsonObj.getString("candidateEmailTxt"));
			candidate.setCandidateReceivedDate(jsonObj.getString("candidateReceivedDate"));
			candidate.setCandidateActive(jsonObj.getString("candidateActive"));
			skill.setSkillId(jsonObj.getInt("skillId"));
			candidate.setSkill(skill);
			
			System.out.println("Main:"+candidate.toString());
			data = candidateServiceImpl.createCandidate(candidate);
		} catch (JsonProcessingException e) {			
			e.printStackTrace();
		}		
		return data;
	}

	// Request handler to update the candidate

	@RequestMapping(value="/updateCandidate", method=RequestMethod.POST) 
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody String updateCandidate(@RequestBody String json,HttpServletRequest request) throws Exception { 		

		String data = null;
		Candidate candidate = new Candidate(); 
	    Skill skill = new Skill();

		try {
			
			
			  org.json.JSONObject jsonObj=new org.json.JSONObject(json);			 
			  candidate.setCandidateId(jsonObj.getInt("candidateId"));
			  candidate.setCandidateName(jsonObj.getString("candidateName"));
			  candidate.setCandidatePhoneNum(jsonObj.getString("candidatePhoneNum"));
			  candidate.setCandidateEmailTxt(jsonObj.getString("candidateEmailTxt"));
			  candidate.setCandidateReceivedDate(jsonObj.getString("candidateReceivedDate"));
			  candidate.setCandidateActive(jsonObj.getString("candidateActive"));
			  skill.setSkillId(jsonObj.getInt("skillId"));
			  candidate.setSkill(skill);
			  data = candidateServiceImpl.updateCandidate(candidate);
			 
		} catch (JsonProcessingException e) {			
			e.printStackTrace();
		}		
		return data;
	}

	// Request handler to delete the candidate

	@RequestMapping(value="/deleteCandidate/{candidateId}", method=RequestMethod.POST) 
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	public @ResponseBody String deleteCandidate(@PathVariable("candidateId") String candidateId,HttpServletRequest request) throws NumberFormatException, Exception { 
		String data = candidateServiceImpl.deleteCandidate(Integer.parseInt(candidateId));
		return data;
	}
	
	// Request handler to get all active candidates
	@RequestMapping(value="/getActiveCandidates", method=RequestMethod.GET) 
	@Produces(MediaType.TEXT_PLAIN)
	public @ResponseBody List<Candidate> getActiveCandidate(HttpServletRequest request) throws NumberFormatException, Exception { 
		return candidateServiceImpl.getAllActiveCandidates();
	}

}
