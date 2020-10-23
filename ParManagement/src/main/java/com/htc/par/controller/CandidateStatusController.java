
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
import com.htc.par.model.ParMaster;
import com.htc.par.model.Prescreener;
import com.htc.par.service.CandidateServiceImpl;
import com.htc.par.service.CandidateStatusServiceImpl;
import com.htc.par.service.ParAllocationServiceImpl;
import com.htc.par.service.ParMasterServiceImpl;
import com.htc.par.service.PreScreenerServiceImpl;

@Controller
public class CandidateStatusController {
	
	@Autowired
	CandidateServiceImpl candidateServiceImpl;
	
	
	@Autowired
	PreScreenerServiceImpl preScreenerServiceImpl;
	
	
	@Autowired
	ParMasterServiceImpl parMasterServiceImpl;
	
	@Autowired
	CandidateStatusServiceImpl candidateStatusServiceImpl;
	
	@Autowired
	ParAllocationServiceImpl parAllocationServiceImpl;
	
	
	
	
	
	@RequestMapping(value="/candidateStatus",method=RequestMethod.GET)
	public ModelAndView candidate(Locale locale,Model model)  throws Exception{
		System.out.println("Main controller invoked");
		ModelAndView  modelView = new ModelAndView();
		modelView.addObject("allCandidateList", candidateServiceImpl.getAllCandidates());
		
		modelView.addObject("allprescreenerList",preScreenerServiceImpl.getAllPreScreener());
		
		
		modelView.setViewName("candidateStatus"); 
		return modelView;
	}
	
	@RequestMapping(value="/getParMasterBycandidateId/{candidateId}",method=RequestMethod.GET)
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public @ResponseBody List<ParMaster> getParMasterbyCandidateId(@PathVariable("candidateId") int candidateId ) throws Exception{
		System.out.println("Senthil Skips starte -controller");
		return candidateStatusServiceImpl.getParMasterbyCandidateId(candidateId);
	}
	
	
	
	

}
