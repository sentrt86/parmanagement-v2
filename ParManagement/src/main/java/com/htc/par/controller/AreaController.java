package com.htc.par.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.htc.par.exceptions.ResourceNotFoundException;
import com.htc.par.model.Area;
import com.htc.par.serviceimpl.AreaServiceImpl;


/*
 * Controller to handle Capability Area information 
 * 
 */

@Controller
public class AreaController {
	
	private static final Logger logger = LoggerFactory.getLogger(AreaController.class);
	
	
	@Autowired
	AreaServiceImpl areaServiceImpl;
	
	// Request Handler for Area Form
		
	@RequestMapping(value="/area", method=RequestMethod.GET) 
	public ModelAndView area(Locale locale,Model model) { 		
		ModelAndView  modelView = new ModelAndView();
		modelView.addObject("allAreasList",this.getAllAreas());
		modelView.setViewName("areaForm"); 
		return modelView;
	}
	
	// Request handler to get all Areas

	@RequestMapping(value="getAllAreas",method=RequestMethod.GET)
	public List<Area> getAllAreas(){
		List <Area> allAreasList = new ArrayList<Area>();
		try
		{
			allAreasList = areaServiceImpl.getAllAreas();
			
		}catch(ResourceNotFoundException ex) {			
			logger.info(ex.getMessage());
		}catch(Exception ex) {
			logger.info(ex.getMessage());
		}	
		
		return allAreasList;
		
	}
	
	

}
