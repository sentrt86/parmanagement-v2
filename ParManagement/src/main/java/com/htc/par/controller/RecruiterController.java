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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.htc.par.model.Recruiter;
import com.htc.par.service.RecruiterServiceImpl;

@Controller
public class RecruiterController {

	@Autowired
	RecruiterServiceImpl recruiterServiceImpl;
	
	
	// Request handler for getting all the recruiters

	@RequestMapping(value="/recruiter", method=RequestMethod.GET)
	public ModelAndView skill(Locale locale,Model model) throws Exception {
		ModelAndView modelView  = new ModelAndView();
		modelView.addObject("allRecruitersList",recruiterServiceImpl.getAllRecruiters());
		modelView.addObject("username",HomeController.username);
		modelView.setViewName("recruiter");
		return modelView;


	}

	// Request handler for getting the next recruiter id

	@RequestMapping(value="/nextRecruiterId", method=RequestMethod.GET)
	@Produces(MediaType.TEXT_PLAIN)
	public @ResponseBody int getNextRecruiterId()  throws Exception{		
		return recruiterServiceImpl.getNextRecruiterId();		
	}

	//Request handler to create the recruiter

	@RequestMapping(value="/createRecruiter", method=RequestMethod.POST) 
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody String createRecruiter(@RequestBody String json,HttpServletRequest request) throws Exception { 			
		String data = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			Recruiter recruiter = mapper.readValue(json,Recruiter.class);
			data = recruiterServiceImpl.createRecruiter(recruiter);
		} catch (JsonProcessingException e) {			
			e.printStackTrace();
		}		
		return data;
	}

	// Request handler to update the recruiter

	@RequestMapping(value="/updateRecruiter", method=RequestMethod.POST) 
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody String updateRecruiter(@RequestBody String json,HttpServletRequest request) throws Exception { 		

		String data = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			Recruiter recruiter = mapper.readValue(json,Recruiter.class);
			System.out.println("rec:"+ recruiter.toString());
			data = recruiterServiceImpl.updateRecruiter(recruiter);
		} catch (JsonProcessingException e) {			
			e.printStackTrace();
		}		
		return data;
	}

	// Request handler to delete the recruiter

	@RequestMapping(value="/deleteRecruiter/{recruiterId}", method=RequestMethod.POST) 
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	public @ResponseBody String deleteRecruiter(@PathVariable("recruiterId") String recruiterId,HttpServletRequest request) throws NumberFormatException, Exception { 
		String data = recruiterServiceImpl.deleteRecruiter(Integer.parseInt(recruiterId));
		return data;
	}
	
	// Request handler to get all active recruiters

		@RequestMapping(value="/getActiveRecruiters", method=RequestMethod.GET) 
		@Produces(MediaType.TEXT_PLAIN)
		public @ResponseBody List<Recruiter> getActiveRecruiter(HttpServletRequest request) throws NumberFormatException, Exception { 
			return recruiterServiceImpl.getAllActiveRecruiters();
		}

}
