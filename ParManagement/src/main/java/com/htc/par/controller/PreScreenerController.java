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
import com.htc.par.model.Prescreener;
import com.htc.par.service.PreScreenerServiceImpl;


@Controller
public class PreScreenerController {
		@Autowired PreScreenerServiceImpl prescreenerServiceImpl;
	
		@RequestMapping(value="/prescreener", method=RequestMethod.GET) 
		public ModelAndView area(Locale locale,Model model)  throws Exception{ 	
		ModelAndView  modelView = new ModelAndView();
		List<Prescreener> allPrescreenersList = prescreenerServiceImpl.getAllPreScreener();
		modelView.addObject("allPrescreenersList", allPrescreenersList );
		modelView.setViewName("prescreener"); 
		return modelView;
	    }
	
	    @RequestMapping(value="/getNextPrescreenerId",method=RequestMethod.GET)
	    @Produces(MediaType.TEXT_PLAIN)
	    public @ResponseBody int getNextPrescreenerId()  throws Exception{	
	    	return prescreenerServiceImpl.getnextPrescreenerID();		
	    }
	
		
		//Request handler to create the prescreener
		
		@RequestMapping(value="/createPrescreener", method=RequestMethod.POST) 
		@Produces(MediaType.TEXT_PLAIN)
		@Consumes(MediaType.APPLICATION_JSON)
		public @ResponseBody String addPrescreener(@RequestBody String json,HttpServletRequest request) throws Exception { 				
			String data = null;
			ObjectMapper mapper = new ObjectMapper();
			try {
				Prescreener prescreener = mapper.readValue(json,Prescreener.class);
				data = prescreenerServiceImpl.addPrescreener(prescreener);
			} catch (JsonProcessingException e) {			
				e.printStackTrace();
			}		
			return data;
		}
		
		// Request handler to update the prescreener
		
		@RequestMapping(value="/updatePrescreener", method=RequestMethod.POST) 
		@Produces(MediaType.TEXT_PLAIN)
		@Consumes(MediaType.APPLICATION_JSON)
		public @ResponseBody String updatePrescreener(@RequestBody String json,HttpServletRequest request) throws Exception { 		
			
			String data = null;
			ObjectMapper mapper = new ObjectMapper();
			try {
				Prescreener prescreener = mapper.readValue(json,Prescreener.class);
				data = prescreenerServiceImpl.updatePrescreener(prescreener);
			} catch (JsonProcessingException e) {			
				e.printStackTrace();
			}		
			return data;
		}
		
		// Request handler to delete the area
		
		@RequestMapping(value="/deletePrescreener/{preScreenerId}", method=RequestMethod.POST) 
		@Produces(MediaType.TEXT_PLAIN)
		@Consumes(MediaType.TEXT_PLAIN)
		public @ResponseBody String deletePrescreener(@PathVariable("preScreenerId") String preScreenerId,HttpServletRequest request) throws NumberFormatException, Exception { 
			return prescreenerServiceImpl.deletePrescreener(Integer.parseInt(preScreenerId));
		}
		
		// Request handler to get all active recruiters

		@RequestMapping(value="/getActivePrescreeners", method=RequestMethod.GET) 
		@Produces(MediaType.TEXT_PLAIN)
		public @ResponseBody List<Prescreener> getActivePrescreener(HttpServletRequest request) throws NumberFormatException, Exception { 
			return prescreenerServiceImpl.getAllActivePreScreeners();
		}


}
