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
import com.htc.par.model.UserRole;
import com.htc.par.service.UserRoleServiceImpl;

@Controller
public class UserRoleController {
	
	
	@Autowired
	UserRoleServiceImpl userRoleServiceImpl;
	
	// Request Handler for UserRole Form
		
	@RequestMapping(value="/userrole", method=RequestMethod.GET) 
	public ModelAndView userRole(Locale locale,Model model)  throws Exception{ 		
		ModelAndView  modelView = new ModelAndView();
		modelView.addObject("allUserRolesList", userRoleServiceImpl.getAllUserRoles());
		modelView.addObject("username",HomeController.username);
		modelView.setViewName("userrole"); 
		return modelView;
	}
	
	
	// Request handler to get the next user role id
	
	@RequestMapping(value="/nextUserRoleId", method=RequestMethod.GET)
	@Produces(MediaType.TEXT_PLAIN)
	public @ResponseBody int getNextUserRoleId()  throws Exception{		
		return userRoleServiceImpl.getNextUserRoleId();		
	}
	
	// Request handler to create the user role
	
	@RequestMapping(value="/createUserRole", method=RequestMethod.POST) 
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody String createUserRole(@RequestBody String json,HttpServletRequest request) throws Exception { 				
		String data = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			UserRole userRole = mapper.readValue(json,UserRole.class);
			data = userRoleServiceImpl.createUserRole(userRole);
		} catch (JsonProcessingException e) {			
			e.printStackTrace();
		}		
		return data;
	}
	
	// Request handler to update the userRole

	@RequestMapping(value="/updateUserRole", method=RequestMethod.POST) 
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody String updateUserRole(@RequestBody String json,HttpServletRequest request) throws Exception { 		
		String data = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			UserRole userRole = mapper.readValue(json,UserRole.class);
			System.out.println("controller:"+ userRole.toString());
			data = userRoleServiceImpl.updateUserRole(userRole);
		} catch (JsonProcessingException e) {			
			e.printStackTrace();
		}		
		return data;
	}

	// Request handler to delete the userRole

	@RequestMapping(value="/deleteUserRole/{userRoleId}", method=RequestMethod.POST) 
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	public @ResponseBody String deleteUserRole(@PathVariable("userRoleId") String userRoleId,HttpServletRequest request) throws NumberFormatException, Exception { 
		System.out.println("controller userRole id:"+ userRoleId);
		String data = userRoleServiceImpl.deleteUserRole(Integer.parseInt(userRoleId));
		return data;
	}
	
	
	//Request handler to get all the user roles
		@RequestMapping(value="/getAllUserRoles", method=RequestMethod.GET) 
		@Produces(MediaType.TEXT_PLAIN)
		public @ResponseBody List<UserRole> getAllUserRoles() throws NumberFormatException, Exception { 
			return userRoleServiceImpl.getAllUserRoles();
		}

}
