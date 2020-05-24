package com.htc.par.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.htc.par.service.AreaServiceImpl;

@Controller
public class ParMasterController {
	
	@Autowired
	AreaServiceImpl areaServiceImpl;
	
	
	@RequestMapping(value="/parentry", method=RequestMethod.GET)
	public ModelAndView parentry(Locale locale,Model model) throws Exception {
		ModelAndView modelView  = new ModelAndView();
		modelView.addObject("allAreasList", areaServiceImpl.getAllAreas());		
		modelView.setViewName("parentry");
		return modelView;

	}

}
