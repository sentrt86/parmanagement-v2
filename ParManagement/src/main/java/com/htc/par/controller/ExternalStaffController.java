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
import com.htc.par.model.Area;
import com.htc.par.model.ExternalStaff;
import com.htc.par.service.AreaServiceImpl;
import com.htc.par.service.ExternalStaffServiceImpl;


@Controller
public class ExternalStaffController {
	
	
	@Autowired
	ExternalStaffServiceImpl externalStaffServiceImpl;
	
	@Autowired
	AreaServiceImpl areaServiceImpl;
	
	
	// Request Handler for ExtStaff Form
		
	@RequestMapping(value="/extStaff", method=RequestMethod.GET) 
	public ModelAndView extStaff(Locale locale,Model model)  throws Exception{ 		
		ModelAndView  modelView = new ModelAndView();
		modelView.addObject("allExtStaffsList", externalStaffServiceImpl.getAllExtStaffs());
		modelView.addObject("allAreasList", areaServiceImpl.getAllAreas());
		modelView.addObject("username",HomeController.username);
		modelView.setViewName("externalstaff"); 
		return modelView;
	}
	
	// Request handler for getting the next extStaff id
	
		@RequestMapping(value="/nextExtStaffId", method=RequestMethod.GET)
		@Produces(MediaType.TEXT_PLAIN)
		public @ResponseBody int getNextExtStaffId()  throws Exception{		
			return externalStaffServiceImpl.getNextExtStaffId();		
		}
		
		//Request handler to create the extStaff
		
		@RequestMapping(value="/createExtStaff", method=RequestMethod.POST) 
		@Produces(MediaType.TEXT_PLAIN)
		@Consumes(MediaType.APPLICATION_JSON)
		public @ResponseBody String createExtStaff(@RequestBody String json,HttpServletRequest request) throws Exception { 			
			String data = null;
			ExternalStaff extStaff = new ExternalStaff();
			Area area = new Area();
			try {
				org.json.JSONObject jsonObj=new org.json.JSONObject(json);
				
				area.setAreaId(jsonObj.getInt("areaId"));
				extStaff.setExtStaffId(jsonObj.getInt("externalStaffId"));
				extStaff.setExtStaffName(jsonObj.getString("externalStaffName"));
				extStaff.setExtStaffActive(jsonObj.getString("externalStaffActive"));
				extStaff.setArea(area);
				data = externalStaffServiceImpl.createExtStaff(extStaff);
			} catch (JsonProcessingException e) {			
				e.printStackTrace();
			}		
			return data;
		}
		
		// Request handler to update the extStaff
		
		@RequestMapping(value="/updateExtStaff", method=RequestMethod.POST) 
		@Produces(MediaType.TEXT_PLAIN)
		@Consumes(MediaType.APPLICATION_JSON)
		public @ResponseBody String updateExtStaff(@RequestBody String json,HttpServletRequest request) throws Exception { 		
			
			String data = null;
			ExternalStaff extStaff = new ExternalStaff();
			Area area = new Area();
			try {
				org.json.JSONObject jsonObj=new org.json.JSONObject(json);				
				area.setAreaId(jsonObj.getInt("areaId"));
				extStaff.setExtStaffId(jsonObj.getInt("externalStaffId"));
				extStaff.setExtStaffName(jsonObj.getString("externalStaffName"));
				extStaff.setExtStaffActive(jsonObj.getString("externalStaffActive"));
				extStaff.setArea(area);
				data = externalStaffServiceImpl.updateExtStaff(extStaff);
			} catch (JsonProcessingException e) {			
				e.printStackTrace();
			}		
			return data;
		}
		
		// Request handler to delete the extStaff
		
		@RequestMapping(value="/deleteExtStaff/{extStaffId}", method=RequestMethod.POST) 
		@Produces(MediaType.TEXT_PLAIN)
		@Consumes(MediaType.TEXT_PLAIN)
		public @ResponseBody String deleteExtStaff(@PathVariable("extStaffId") String extStaffId,HttpServletRequest request) throws NumberFormatException, Exception { 
			String data = externalStaffServiceImpl.deleteExtStaff(Integer.parseInt(extStaffId));
			return data;
		}

}
