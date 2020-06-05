package com.htc.par.controller;

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
import com.htc.par.model.ParRole;
import com.htc.par.service.ParRoleServiceImpl;



@Controller
public class ParRoleController {

	@Autowired
	ParRoleServiceImpl parRoleServiceImpl;

	// Request handler for getting the all the par roles

	@RequestMapping(value="/role", method=RequestMethod.GET)
	public ModelAndView parRole(Locale locale,Model model) throws Exception {
		ModelAndView modelView  = new ModelAndView();
		modelView.addObject("allRolesList", parRoleServiceImpl.getAllParRoles());
		modelView.addObject("username",HomeController.username);
		modelView.setViewName("parrole");
		return modelView;

	}


	// Request handler for getting the next par role id

	@RequestMapping(value="/nextParRoleId", method=RequestMethod.GET)
	@Produces(MediaType.TEXT_PLAIN)
	public @ResponseBody int getNextParRoleId()  throws Exception{		
		return parRoleServiceImpl.getNextParRoleId();		
	}

	//Request handler to create the parRole

	@RequestMapping(value="/createParRole", method=RequestMethod.POST) 
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody String createParRole(@RequestBody String json,HttpServletRequest request) throws Exception { 			
		String data = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			ParRole parRole = mapper.readValue(json,ParRole.class);
			System.out.println("create:"+ parRole.toString());
			data = parRoleServiceImpl.createParRole(parRole);
		} catch (JsonProcessingException e) {			
			e.printStackTrace();
		}		
		return data;
	}

	// Request handler to update the par role

	@RequestMapping(value="/updateParRole", method=RequestMethod.POST) 
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody String updateParRole(@RequestBody String json,HttpServletRequest request) throws Exception { 		

		String data = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			ParRole parRole = mapper.readValue(json,ParRole.class);
	
			data = parRoleServiceImpl.updateParRole(parRole);
		} catch (JsonProcessingException e) {			
			e.printStackTrace();
		}		
		return data;
	}

	// Request handler to delete the par role

	@RequestMapping(value="/deleteParRole/{parRoleId}", method=RequestMethod.POST) 
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	public @ResponseBody String deleteParRole(@PathVariable("parRoleId") String parRoleId,HttpServletRequest request) throws NumberFormatException, Exception { 
		String data = parRoleServiceImpl.deleteParRole(Integer.parseInt(parRoleId));
		return data;
	}

}
