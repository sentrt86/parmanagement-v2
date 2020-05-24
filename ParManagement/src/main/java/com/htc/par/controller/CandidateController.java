package com.htc.par.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.htc.par.service.AreaServiceImpl;
import com.htc.par.service.CandidateServiceImpl;
import com.htc.par.service.SkillServiceImpl;

@Controller
public class CandidateController {
	
	@Autowired
	CandidateServiceImpl candidateServiceImpl;
	
	@Autowired
	SkillServiceImpl skillServiceImpl;
	
	// Request Handler for Area Form
		
	@RequestMapping(value="/candidate", method=RequestMethod.GET) 
	public ModelAndView area(Locale locale,Model model)  throws Exception{ 		
		ModelAndView  modelView = new ModelAndView();
		modelView.addObject("allCandidatesList", candidateServiceImpl.getAllCandidates());
		modelView.addObject("allSkillsList", skillServiceImpl.getAllSkills());
		modelView.setViewName("candidate"); 
		return modelView;
	}

}
