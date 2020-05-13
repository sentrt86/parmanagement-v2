package com.htc.par.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView home(Locale locale,Model model) {
		ModelAndView modelView  = new ModelAndView();
		modelView.setViewName("home");
		return modelView;
		
	}
	
	@RequestMapping(value="/parentry", method=RequestMethod.GET)
	public ModelAndView parEntry(Locale locale,Model model) {
		ModelAndView modelView  = new ModelAndView();
		modelView.setViewName("parentry");
		return modelView;
		
	}
	



}