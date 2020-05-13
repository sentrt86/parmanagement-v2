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
	public ModelAndView parentry(Locale locale,Model model) {
		ModelAndView modelView  = new ModelAndView();
		modelView.setViewName("parentry");
		return modelView;

	}

	@RequestMapping(value="/area", method=RequestMethod.GET) 
	public ModelAndView area(Locale locale,Model model) { 
		System.out.println("pradeep"); ModelAndView
		modelView = new ModelAndView(); 
		modelView.setViewName("area"); 
		return modelView;

	}

	@RequestMapping(value="/skill", method=RequestMethod.GET)
	public ModelAndView skill(Locale locale,Model model) {
		ModelAndView modelView  = new ModelAndView();
		modelView.setViewName("skill");
		return modelView;

	}







}
