package com.htc.par.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RecruiterController {
	
	@RequestMapping(value="/recruiter", method=RequestMethod.GET)
	public ModelAndView skill(Locale locale,Model model) {
		ModelAndView modelView  = new ModelAndView();
		modelView.setViewName("recruiter");
		return modelView;

	}

}
