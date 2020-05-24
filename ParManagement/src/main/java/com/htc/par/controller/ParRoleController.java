package com.htc.par.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.htc.par.service.ParRoleServiceImpl;



@Controller
public class ParRoleController {

	@Autowired
	ParRoleServiceImpl parRoleServiceImpl;
	
	@RequestMapping(value="/role", method=RequestMethod.GET)
	public ModelAndView parRole(Locale locale,Model model) {
		ModelAndView modelView  = new ModelAndView();
		modelView.addObject("allRolesList", parRoleServiceImpl.getAllParRoles());
		modelView.setViewName("parrole");
		return modelView;

	}
}
