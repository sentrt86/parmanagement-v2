package com.htc.par.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.htc.par.service.AreaServiceImpl;
import com.htc.par.service.ExternalStaffServiceImpl;
import com.htc.par.service.ParRoleServiceImpl;
import com.htc.par.service.SkillServiceImpl;

@Controller
public class ParMasterController {

	@Autowired
	AreaServiceImpl areaServiceImpl;

	@Autowired
	SkillServiceImpl skillServiceImpl;

	@Autowired
	ParRoleServiceImpl parRoleServiceImpl;

	@Autowired
	ExternalStaffServiceImpl extStaffServiceImpl;


	@RequestMapping(value="/parentry", method=RequestMethod.GET)
	public ModelAndView parentry(Locale locale,Model model) throws Exception {
		ModelAndView modelView  = new ModelAndView();
		modelView.addObject("allAreasList", areaServiceImpl.getAllAreas());
		modelView.addObject("allSkillsList", skillServiceImpl.getAllSkills());
		modelView.addObject("allParRolesList", parRoleServiceImpl.getAllParRoles());
		modelView.addObject("allExtStaffsList", extStaffServiceImpl.getAllExtStaffs());
		modelView.addObject("username",HomeController.username);
		modelView.setViewName("parentry");
		return modelView;

	}

	@RequestMapping(value="/emailrecruiters", method=RequestMethod.GET)
	public ModelAndView emailRecruiters(Locale locale,Model model) throws Exception {
		ModelAndView modelView  = new ModelAndView();
		modelView.addObject("username",HomeController.username);
		modelView.setViewName("emailrecruiters");
		return modelView;

	}
	@RequestMapping(value="/intenttofill", method=RequestMethod.GET)
	public ModelAndView intentToFill(Locale locale,Model model) throws Exception {
		ModelAndView modelView  = new ModelAndView();
		modelView.addObject("username",HomeController.username);
		modelView.setViewName("intenttofill");
		return modelView;

	}

	@RequestMapping(value="/candidatereceived", method=RequestMethod.GET)
	public ModelAndView candidateReceived(Locale locale,Model model) throws Exception {
		ModelAndView modelView  = new ModelAndView();
		modelView.addObject("username",HomeController.username);
		modelView.setViewName("candidatereceived");
		return modelView;

	}



	// Request handler for Prescreener Form

	@RequestMapping(value="/prescreening", method=RequestMethod.GET) 
	public ModelAndView prescreening(Locale locale,Model model)  throws Exception{ 		
		ModelAndView  modelView = new ModelAndView();
		modelView.addObject("username",HomeController.username);
		modelView.setViewName("prescreening"); 
		return modelView;
	}


	// Request handler for Prescreener Result Form

	@RequestMapping(value="/prescreeningresults", method=RequestMethod.GET) 
	public ModelAndView prescreeningResult(Locale locale,Model model)  throws Exception{ 		
		ModelAndView  modelView = new ModelAndView();
		modelView.addObject("username",HomeController.username);
		modelView.setViewName("prescreeningresults"); 
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

	// Request handler for offer received Form

	@RequestMapping(value="/offerreceived", method=RequestMethod.GET) 
	public ModelAndView offerReceived(Locale locale,Model model)  throws Exception{ 		
		ModelAndView  modelView = new ModelAndView();
		modelView.addObject("username",HomeController.username);
		modelView.setViewName("offerreceived"); 
		return modelView;
	}

	// Request handler for setup submitted Form

	@RequestMapping(value="/setupsubmitted", method=RequestMethod.GET) 
	public ModelAndView setupSubmitted(Locale locale,Model model)  throws Exception{ 		
		ModelAndView  modelView = new ModelAndView();
		modelView.addObject("username",HomeController.username);
		modelView.setViewName("setupsubmitted"); 
		return modelView;
	}
	
	
	// Request handler for candidate onboard Form

		@RequestMapping(value="/candidateonboard", method=RequestMethod.GET) 
		public ModelAndView candidateOnboard(Locale locale,Model model)  throws Exception{ 		
			ModelAndView  modelView = new ModelAndView();
			modelView.addObject("username",HomeController.username);
			modelView.setViewName("candidateonboard"); 
			return modelView;
		}


}
