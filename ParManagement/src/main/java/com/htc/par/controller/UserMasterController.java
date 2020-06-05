package com.htc.par.controller;

import java.util.ArrayList;
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
import com.htc.par.model.UserMaster;
import com.htc.par.model.UserRole;
import com.htc.par.service.UserMasterServiceImpl;

@Controller
public class UserMasterController {
	
	@Autowired
	UserMasterServiceImpl userMasterServiceImpl;
	
	//Request handler for UserMaster Form
	
	@RequestMapping(value="/user", method=RequestMethod.GET) 
	public ModelAndView user(Locale locale,Model model)  throws Exception{ 		
		ModelAndView  modelView = new ModelAndView();
		List<UserMaster> allUserMastersList   = new ArrayList<UserMaster>();
		allUserMastersList = userMasterServiceImpl.getAllUsers();
		modelView.addObject("allUserMastersList", allUserMastersList);
		modelView.addObject("username",HomeController.username);
		modelView.setViewName("user"); 
		return modelView;
	}
	
	// Request handler for getting the next user  id
	
		@RequestMapping(value="/nextUserId", method=RequestMethod.GET)
		@Produces(MediaType.TEXT_PLAIN)
		public @ResponseBody int getNextUserId()  throws Exception{		
			return userMasterServiceImpl.getNextUserId();		
		}
		
		//Request handler to create the userMaster
		
		@RequestMapping(value="/createUser", method=RequestMethod.POST) 
		@Produces(MediaType.TEXT_PLAIN)
		@Consumes(MediaType.APPLICATION_JSON)
		public @ResponseBody String createUser(@RequestBody String json,HttpServletRequest request) throws Exception { 			
			String data = null;
			UserMaster userMaster = new UserMaster();
			UserRole userRole = new UserRole();
			try {
				org.json.JSONObject jsonObj=new org.json.JSONObject(json);
				userMaster.setUserId(jsonObj.getInt("userId"));
				userMaster.setUserFirstName(jsonObj.getString("userFirstName"));
				userMaster.setUserLastName(jsonObj.getString("userLastName"));
				userMaster.setUserPhoneNo(jsonObj.getString("userPhoneNum"));
				userMaster.setUserEmailTxt(jsonObj.getString("userEmailTxt"));
				userMaster.setUserName(jsonObj.getString("username"));
				userMaster.setPassword(jsonObj.getString("password"));
				userMaster.setUserActive(jsonObj.getString("userActive"));
				userRole.setUserRoleId(jsonObj.getInt("userRoleId"));
				userRole.setUserRoleName("");
				userMaster.setUserRole(userRole);
			
				System.out.println("userMaster:"+userMaster);
				data = userMasterServiceImpl.createUser(userMaster);
			} catch (JsonProcessingException e) {			
				e.printStackTrace();
			}		
			return data;
		}
		
		// Request handler to update the userMaster
		
		@RequestMapping(value="/updateUser", method=RequestMethod.POST) 
		@Produces(MediaType.TEXT_PLAIN)
		@Consumes(MediaType.APPLICATION_JSON)
		public @ResponseBody String updateUserMaster(@RequestBody String json,HttpServletRequest request) throws Exception { 		
			
			String data = null;
			UserMaster userMaster = new UserMaster();
			UserRole userRole = new UserRole();
			try {
				org.json.JSONObject jsonObj=new org.json.JSONObject(json);
				userMaster.setUserId(jsonObj.getInt("userId"));
				userMaster.setUserFirstName(jsonObj.getString("userFirstName"));
				userMaster.setUserLastName(jsonObj.getString("userLastName"));
				userMaster.setUserPhoneNo(jsonObj.getString("userPhoneNum"));
				userMaster.setUserEmailTxt(jsonObj.getString("userEmailTxt"));
				userMaster.setUserName(jsonObj.getString("username"));
				userMaster.setPassword(jsonObj.getString("password"));
				userMaster.setUserActive(jsonObj.getString("userActive"));
				userRole.setUserRoleId(jsonObj.getInt("userRoleId"));
				userRole.setUserRoleName("");
				userMaster.setUserRole(userRole);

				data = userMasterServiceImpl.updateUser(userMaster);
			} catch (JsonProcessingException e) {			
				e.printStackTrace();
			}		
			return data;
		}
		
		// Request handler to delete the userMaster
		
		@RequestMapping(value="/deleteUser/{userId}", method=RequestMethod.POST) 
		@Produces(MediaType.TEXT_PLAIN)
		@Consumes(MediaType.TEXT_PLAIN)
		public @ResponseBody String deleteUserMaster(@PathVariable("userId") String userId,HttpServletRequest request) throws NumberFormatException, Exception { 
			String data = userMasterServiceImpl.deleteUser(Integer.parseInt(userId));
			return data;
		}
		

}
