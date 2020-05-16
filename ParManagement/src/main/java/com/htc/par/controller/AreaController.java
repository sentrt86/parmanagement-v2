package com.htc.par.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.htc.par.model.Area;
import com.htc.par.service.AreaServiceImpl;


/*
 * Controller to handle Capability Area information 
 * 
 */

@Controller
public class AreaController {
	
	@Autowired
	AreaServiceImpl areaServiceImpl;
	
	// Request Handler for Area Form
		
	@RequestMapping(value="/area", method=RequestMethod.GET) 
	public ModelAndView area(Locale locale,Model model) { 		
		ModelAndView  modelView = new ModelAndView();
		modelView.addObject("allAreasList", areaServiceImpl.getAllAreas());
		modelView.setViewName("area"); 
		return modelView;
	}
	


}
