package com.htc.par.controller;


import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	
	// Request handler for Area Form
		
	@RequestMapping(value="/area", method=RequestMethod.GET) 
	public ModelAndView area(Locale locale,Model model)  throws Exception{ 		
		ModelAndView  modelView = new ModelAndView();
		modelView.addObject("allAreasList", areaServiceImpl.getAllAreas());
		modelView.addObject("username",HomeController.username);
		modelView.setViewName("area"); 
		return modelView;
	}
	
	// Request handler for getting the next area id
	
	@RequestMapping(value="/nextAreaId", method=RequestMethod.GET)
	@Produces(MediaType.TEXT_PLAIN)
	public @ResponseBody int getNextAreaId()  throws Exception{		
		return areaServiceImpl.getNextAreaId();		
	}
	
	//Request handler to create the area
	
	@RequestMapping(value="/createArea", method=RequestMethod.POST) 
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody String createArea(@RequestBody String json,HttpServletRequest request) throws Exception { 			
		String data = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			Area area = mapper.readValue(json,Area.class);
			data = areaServiceImpl.createArea(area);
		} catch (JsonProcessingException e) {			
			e.printStackTrace();
		}		
		return data;
	}
	
	// Request handler to update the area
	
	@RequestMapping(value="/updateArea", method=RequestMethod.POST) 
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody String updateArea(@RequestBody String json,HttpServletRequest request) throws Exception { 		
		
		String data = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			Area area = mapper.readValue(json,Area.class);
			data = areaServiceImpl.updateArea(area);
		} catch (JsonProcessingException e) {			
			e.printStackTrace();
		}		
		return data;
	}
	
	// Request handler to delete the area
	
	@RequestMapping(value="/deleteArea/{areaId}", method=RequestMethod.POST) 
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	public @ResponseBody String deleteArea(@PathVariable("areaId") String areaId,HttpServletRequest request) throws NumberFormatException, Exception { 
		String data = areaServiceImpl.deleteArea(Integer.parseInt(areaId));
		return data;
	}
	
	
	//Request handler to get all the areas
	@RequestMapping(value="/getAllAreas", method=RequestMethod.GET) 
	@Produces(MediaType.TEXT_PLAIN)
	public @ResponseBody List<Area> getAllAreas() throws NumberFormatException, Exception { 
		return areaServiceImpl.getAllAreas();
	}

}
