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


@Controller
public class ExternalStaffController {
	
	
	@Autowired
	ExternalStaffServiceImpl externalStaffServiceImpl;
	
	@Autowired
	AreaServiceImpl areaServiceImpl;
	
	// Request Handler for Area Form
		
	@RequestMapping(value="/extStaff", method=RequestMethod.GET) 
	public ModelAndView area(Locale locale,Model model)  throws Exception{ 		
		ModelAndView  modelView = new ModelAndView();
		modelView.addObject("allExtStaffsList", externalStaffServiceImpl.getAllExtStaffs());
		modelView.addObject("allAreasList", areaServiceImpl.getAllAreas());
		modelView.setViewName("externalstaff"); 
		return modelView;
	}
}
