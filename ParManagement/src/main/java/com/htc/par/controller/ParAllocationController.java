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
import com.htc.par.model.ParAllocation;
import com.htc.par.model.Prescreener;
import com.htc.par.model.Recruiter;
import com.htc.par.service.ParAllocationServiceImpl;


@Controller
public class ParAllocationController {

	@Autowired
	ParAllocationServiceImpl parAllocationServiceImpl;



	// Request handler for candidate received form

	@RequestMapping(value="/candidatereceived", method=RequestMethod.GET)
	public ModelAndView candidateReceived(Locale locale,Model model) throws Exception {
		ModelAndView modelView  = new ModelAndView();
		modelView.addObject("username",HomeController.username);
		modelView.setViewName("candidatereceived");
		return modelView;

	}



	// Request handler for prescreening form

	@RequestMapping(value="/prescreening", method=RequestMethod.GET) 
	public ModelAndView prescreening(Locale locale,Model model)  throws Exception{ 		
		ModelAndView  modelView = new ModelAndView();
		modelView.addObject("username",HomeController.username);
		modelView.setViewName("prescreening"); 
		return modelView;
	}


	// Request handler for submit candidate Form

	@RequestMapping(value="/submitcandidate", method=RequestMethod.GET) 
	public ModelAndView submitcandidate(Locale locale,Model model)  throws Exception{ 		
		ModelAndView  modelView = new ModelAndView();
		modelView.addObject("username",HomeController.username);
		modelView.setViewName("submitcandidate"); 
		return modelView;
	}


	// Request handler to get the par allocation (Candidate Received) by par num

	@RequestMapping(value = "/getCandidateReceivedByParNum/{parNum}", method=RequestMethod.GET)
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public @ResponseBody List<ParAllocation> getCandidateReceivedByParNum(@PathVariable("parNum") String parNum) throws Exception {
		return parAllocationServiceImpl.getCandidateReceivedByParNum(parNum);


	}

	// Request handler to delete the par allocation for particular par allocation id

	@RequestMapping(value = "/deleteParAllocationByParAllocId/{parAllocId}", method=RequestMethod.POST)
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public @ResponseBody String deleteParAllocationByParAllocId(@PathVariable("parAllocId") int parAllocId) throws Exception {
		return parAllocationServiceImpl.deleteParAllocationByParAllocId(parAllocId);


	}

	// Request handler to create the par allocation (Candidate Received)

	@RequestMapping(value = "/createParAllocation", method=RequestMethod.POST)
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody String createParAllocation(@RequestBody String json, HttpServletRequest request) throws Exception {
		String data = null;
		ParAllocation parAllocation = new ParAllocation();
		Candidate candidate = new Candidate();
		Recruiter recruiter = new Recruiter();


		try { 

			org.json.JSONObject jsonObj = new org.json.JSONObject(json);
			candidate.setCandidateId(jsonObj.getInt("candidateId"));
			candidate.setCandidateName(jsonObj.getString("candidateName"));
			recruiter.setRecruiterId(jsonObj.getInt("recruiterId"));
			recruiter.setRecruiterName(jsonObj.getString("recruiterName"));

			parAllocation.setParCode(jsonObj.getInt("parCode"));
			parAllocation.setCandidate(candidate);
			parAllocation.setRecruiter(recruiter);

			data = parAllocationServiceImpl.createParAllocation(parAllocation);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return data;


	}

	// Request handler to update the par allocation (Candidate Received)

	@RequestMapping(value = "/updateSubmitCandidate", method=RequestMethod.POST)
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody String updateSubmitCandidate(@RequestBody String json, HttpServletRequest request) throws Exception {
		String data = null;
		ParAllocation parAllocation = new ParAllocation();
		Candidate candidate = new Candidate();


		try { 

			org.json.JSONObject jsonObj = new org.json.JSONObject(json);
			candidate.setCandidateName(jsonObj.getString("candidateName"));

			parAllocation.setParCode(jsonObj.getInt("parCode"));
			parAllocation.setParAllocationId(jsonObj.getInt("parAllocId"));
			parAllocation.setSubmitIndicator(jsonObj.getString("submitIndicator"));
			parAllocation.setSubmitDate(jsonObj.getString("submitDate"));
			parAllocation.setCandidate(candidate);

			data = parAllocationServiceImpl.updateSubmitCandidate(parAllocation);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return data;


	}

	// Request handler to get the par allocation by par num

	@RequestMapping(value = "/getParAllocationByParNum/{parNum}", method=RequestMethod.GET)
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public @ResponseBody List<ParAllocation> getParAllocationByParNum(@PathVariable("parNum") String parNum) throws Exception {
		return parAllocationServiceImpl.getParAllocationByParNum(parNum);


	}
	// Request handler to update the par allocation (Pre screening)

	@RequestMapping(value = "/updatePrescreening", method=RequestMethod.POST)
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody String updatePrescreening(@RequestBody String json, HttpServletRequest request) throws Exception {
		String data = null;
		ParAllocation parAllocation = new ParAllocation();
		Prescreener prescreener = new Prescreener();
		Candidate candidate = new Candidate();

		try { 

			org.json.JSONObject jsonObj = new org.json.JSONObject(json);
			prescreener.setPreScreenerId(jsonObj.getInt("prescreenerId"));
			candidate.setCandidateName(jsonObj.getString("candidateName"));

			parAllocation.setParCode(jsonObj.getInt("parCode"));
			parAllocation.setParAllocationId(jsonObj.getInt("parAllocId"));
			parAllocation.setPrescreenerDate(jsonObj.getString("prescreeningDate"));
			parAllocation.setPrescreenerCommentText(jsonObj.getString("prescreeningCommentText"));
			parAllocation.setCandidate(candidate);

			parAllocation.setPrescreener(prescreener);

			data = parAllocationServiceImpl.updatePrescreening(parAllocation);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return data;


	}

}
