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
import com.htc.par.model.Skill;
import com.htc.par.service.SkillServiceImpl;

@Controller
public class SkillController {
	
	@Autowired
	SkillServiceImpl skillServiceImpl;
	
	
	
	// Request handler for Skill Form
		
	@RequestMapping(value="/skill", method=RequestMethod.GET) 
	public ModelAndView skill(Locale locale,Model model)  throws Exception{ 		
		ModelAndView  modelView = new ModelAndView();
		modelView.addObject("allSkillsList", skillServiceImpl.getAllSkills());
		modelView.addObject("username",HomeController.username);
		modelView.setViewName("skill"); 
		return modelView;
	}
	
	// Request handler for getting the next skill id
	
	@RequestMapping(value="/nextSkillId", method=RequestMethod.GET)
	@Produces(MediaType.TEXT_PLAIN)
	public @ResponseBody int getNextSkillId()  throws Exception{		
		return skillServiceImpl.getNextSkillId();		
	}
	
	//Request handler to create the skill
	
	@RequestMapping(value="/createSkill", method=RequestMethod.POST) 
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody String createSkill(@RequestBody String json,HttpServletRequest request) throws Exception { 			
		String data = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			Skill skill = mapper.readValue(json,Skill.class);
			data = skillServiceImpl.createSkill(skill);
		} catch (JsonProcessingException e) {			
			e.printStackTrace();
		}		
		return data;
	}
	
	// Request handler to update the skill
	
	@RequestMapping(value="/updateSkill", method=RequestMethod.POST) 
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody String updateSkill(@RequestBody String json,HttpServletRequest request) throws Exception { 		
		
		String data = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			Skill skill = mapper.readValue(json,Skill.class);
			data = skillServiceImpl.updateSkill(skill);
		} catch (JsonProcessingException e) {			
			e.printStackTrace();
		}		
		return data;
	}
	
	// Request handler to delete the skill
	
	@RequestMapping(value="/deleteSkill/{skillId}", method=RequestMethod.POST) 
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	public @ResponseBody String deleteSkill(@PathVariable("skillId") String skillId,HttpServletRequest request) throws NumberFormatException, Exception { 
		String data = skillServiceImpl.deleteSkill(Integer.parseInt(skillId));
		return data;
	}
	
	//Request handler to get all the areas
		@RequestMapping(value="/getAllSkills", method=RequestMethod.GET) 
		@Produces(MediaType.TEXT_PLAIN)
		public @ResponseBody List<Skill> getAllSkills() throws NumberFormatException, Exception { 
			return skillServiceImpl.getAllSkills();
		}

}
