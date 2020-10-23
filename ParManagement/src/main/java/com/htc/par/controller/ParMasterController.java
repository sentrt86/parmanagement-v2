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
import com.htc.par.constants.ParConstants;
import com.htc.par.model.Area;
import com.htc.par.model.ExternalStaff;
import com.htc.par.model.Location;
import com.htc.par.model.ParMaster;
import com.htc.par.model.ParRole;
import com.htc.par.model.Skill;
import com.htc.par.service.AreaServiceImpl;
import com.htc.par.service.ExternalStaffServiceImpl;
import com.htc.par.service.LocationServiceImpl;
import com.htc.par.service.ParMasterServiceImpl;
import com.htc.par.service.ParRoleServiceImpl;
import com.htc.par.service.SkillServiceImpl;
import org.springframework.mail.MailException;

@Controller
public class ParMasterController {

	@Autowired
	AreaServiceImpl areaServiceImpl;

	@Autowired
	SkillServiceImpl skillServiceImpl;

	@Autowired
	ParRoleServiceImpl parRoleServiceImpl;

	@Autowired
	ExternalStaffServiceImpl extStaffServiceImpl;

	@Autowired
	ParMasterServiceImpl parmasterServiceImpl;

	@Autowired
	LocationServiceImpl  locationServiceImpl;


	// Request handler for par form

	@RequestMapping(value="/parentry", method=RequestMethod.GET)
	public ModelAndView parentry(Locale locale,Model model) throws Exception {
		ModelAndView modelView  = new ModelAndView();
		modelView.addObject("allAreasList", areaServiceImpl.getActiveAreas());
		modelView.addObject("allSkillsList", skillServiceImpl.getActiveSkills());
		modelView.addObject("allParRolesList", parRoleServiceImpl.getActiveParRoles());
		modelView.addObject("allExtStaffsList", extStaffServiceImpl.getActiveExtStaffs());
		modelView.addObject("allLocationsList", locationServiceImpl.getActiveLocation());
		modelView.addObject("username",HomeController.username);
		modelView.setViewName("parentry");
		return modelView;

	}

	// Request handler for par form

	@RequestMapping(value="/parentryedit", method=RequestMethod.GET)
	public ModelAndView parentryEdit(Locale locale,Model model) throws Exception {
		ModelAndView modelView  = new ModelAndView();
		modelView.addObject("allAreasList", areaServiceImpl.getActiveAreas());
		modelView.addObject("allSkillsList", skillServiceImpl.getActiveSkills());
		modelView.addObject("allParRolesList", parRoleServiceImpl.getActiveParRoles());
		modelView.addObject("allExtStaffsList", extStaffServiceImpl.getActiveExtStaffs());
		modelView.addObject("allLocationsList", locationServiceImpl.getActiveLocation());
		modelView.addObject("username",HomeController.username);
		modelView.setViewName("parentryedit");
		return modelView;

	}

	// Request handler for email recruiters form

	@RequestMapping(value="/emailrecruiters", method=RequestMethod.GET)
	public ModelAndView emailRecruiters(Locale locale,Model model) throws Exception {
		ModelAndView modelView  = new ModelAndView();
		modelView.addObject("username",HomeController.username);
		modelView.setViewName("emailrecruiters");
		return modelView;

	}

	// Request handler for intent to fill form

	@RequestMapping(value="/intenttofill", method=RequestMethod.GET)
	public ModelAndView intentToFill(Locale locale,Model model) throws Exception {
		ModelAndView modelView  = new ModelAndView();
		modelView.addObject("username",HomeController.username);
		modelView.setViewName("intenttofill");
		return modelView;

	}


	// Request handler for offer received Form

	@RequestMapping(value="/offerreceived", method=RequestMethod.GET) 
	public ModelAndView offerReceived(Locale locale,Model model)  throws Exception{ 		
		ModelAndView  modelView = new ModelAndView();
		modelView.addObject("username",HomeController.username);
		modelView.setViewName("offerreceived"); 
		return modelView;
	}

	// Request handler for setup submitted form

	@RequestMapping(value="/setupsubmitted", method=RequestMethod.GET) 
	public ModelAndView setupSubmitted(Locale locale,Model model)  throws Exception{ 		
		ModelAndView  modelView = new ModelAndView();
		modelView.addObject("username",HomeController.username);
		modelView.setViewName("setupsubmitted"); 
		return modelView;
	}


	// Request handler for candidate on board form

	@RequestMapping(value="/candidateonboard", method=RequestMethod.GET) 
	public ModelAndView candidateOnboard(Locale locale,Model model)  throws Exception{ 		
		ModelAndView  modelView = new ModelAndView();
		modelView.addObject("username",HomeController.username);
		modelView.setViewName("candidateonboard"); 
		return modelView;
	}

	//Request handler to create the par


	@RequestMapping(value = "/createParMaster", method=RequestMethod.POST)
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody String createParMaster(@RequestBody String json, HttpServletRequest request) throws Exception {
		String data = null;
		Area area = new Area();
		Skill skill = new Skill();
		ParRole parRole = new ParRole();
		Location location = new Location();
		ExternalStaff extStaff = new ExternalStaff();
		ParMaster parmaster = new ParMaster();

		try { 

			org.json.JSONObject jsonObj = new org.json.JSONObject(json);
			area.setAreaId(jsonObj.getInt("areaId"));
			skill.setSkillId(jsonObj.getInt("skillId"));
			location.setLocationId(jsonObj.getInt("locationId"));
			extStaff.setExtStaffId(jsonObj.getInt("extStaffId"));
			parRole.setRoleId(jsonObj.getInt("roleId"));

			parmaster.setParId(jsonObj.getInt("parId"));
			parmaster.setParNumber(jsonObj.getString("parNumber"));
			parmaster.setParDescriptionText(jsonObj.getString("parDescriptionText"));
			parmaster.setParStatus(jsonObj.getString("parStatus"));
			parmaster.setParReceivedDate(jsonObj.getString("parReceivedDate"));
			parmaster.setEmailSent(jsonObj.getString("emailSent"));
			parmaster.setIntentSentDate(jsonObj.getString("intentSentDate"));
			parmaster.setIntentToFill(jsonObj.getString("intentToFill"));
			parmaster.setParComment(jsonObj.getString("parComment"));
			parmaster.setArea(area);
			parmaster.setSkill(skill);
			parmaster.setLocation(location);
			parmaster.setExternalStaff(extStaff);
			parmaster.setParRole(parRole);
			data = parmasterServiceImpl.createParMaster(parmaster);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return data;


	}

	@RequestMapping(value = "/updateParMaster", method=RequestMethod.POST)
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody String updateParMaster(@RequestBody String json, HttpServletRequest request) throws Exception {
		String data = null;
		Area area = new Area();
		Skill skill = new Skill();
		ParRole parRole = new ParRole();
		Location location = new Location();
		ExternalStaff extStaff = new ExternalStaff();
		ParMaster parmaster = new ParMaster();

		try { 

			org.json.JSONObject jsonObj = new org.json.JSONObject(json);
			area.setAreaId(jsonObj.getInt("areaId"));
			skill.setSkillId(jsonObj.getInt("skillId"));
			location.setLocationId(jsonObj.getInt("locationId"));
			extStaff.setExtStaffId(jsonObj.getInt("extStaffId"));
			parRole.setRoleId(jsonObj.getInt("roleId"));

			parmaster.setParId(jsonObj.getInt("parId"));
			parmaster.setParNumber(jsonObj.getString("parNumber"));
			parmaster.setParDescriptionText(jsonObj.getString("parDescriptionText"));
			parmaster.setParStatus(jsonObj.getString("parStatus"));
			parmaster.setParReceivedDate(jsonObj.getString("parReceivedDate"));
			parmaster.setEmailSent(jsonObj.getString("emailSent"));
			parmaster.setIntentSentDate(jsonObj.getString("intentSentDate"));
			parmaster.setIntentToFill(jsonObj.getString("intentToFill"));
			parmaster.setParComment(jsonObj.getString("parComment"));
			parmaster.setArea(area);
			parmaster.setSkill(skill);
			parmaster.setLocation(location);
			parmaster.setExternalStaff(extStaff);
			parmaster.setParRole(parRole);

			data = parmasterServiceImpl.updateParMaster(parmaster);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return data;


	}

	// Request handler to update the intent to fill

	@RequestMapping(value = "/updateIntentToFill", method=RequestMethod.POST)
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody String updateIntentToFill(@RequestBody String json, HttpServletRequest request) throws Exception {
		String data = null;

		ParMaster parmaster = new ParMaster();

		try { 

			org.json.JSONObject jsonObj = new org.json.JSONObject(json);
			parmaster.setParId(jsonObj.getInt("parId"));
			parmaster.setParNumber(jsonObj.getString("parNumber"));
			parmaster.setIntentSentDate(jsonObj.getString("intentSentDate"));
			parmaster.setIntentToFill(jsonObj.getString("intentToFill"));
			data = parmasterServiceImpl.updateIntentToFill(parmaster);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return data;


	}

	// Request handler to get the par master by par num 

	@RequestMapping(value = "/getParMasterByParNum/{parNum}", method=RequestMethod.GET)
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody ParMaster getParMasterByParNum(@PathVariable("parNum") String parNum) throws Exception {
		System.out.println("Senthil Skips for get par master by parnum"+parmasterServiceImpl.getParMasterByParNum(parNum));
		return parmasterServiceImpl.getParMasterByParNum(parNum);

	}


	// Request handler to delete the par master by par num 

	@RequestMapping(value = "/deleteParMaster/{parNum}", method=RequestMethod.GET)
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public @ResponseBody String deleteParMasterByParNum(@PathVariable("parNum") int parNum) throws Exception {
		return parmasterServiceImpl.deleteParMasterByParNum(parNum);

	}


	//Request handler to get the next par sequence 

	@RequestMapping(value="/getNextParSeqId", method=RequestMethod.GET) 
	@Produces(MediaType.TEXT_PLAIN)
	public @ResponseBody int getNextParSeqId() throws Exception { 			
		return parmasterServiceImpl.getNextParSeqId();	
	}
	
	//Request handler to send email to recruiters
	
	@RequestMapping(value="/sendEmailRecruiters",method=RequestMethod.POST)
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody String sendEmailRecruiters(@RequestBody String json,HttpServletRequest request) throws Exception { 		
		 	
		Area area = new Area();
		Skill skill = new Skill();
		ParRole parRole = new ParRole();
		Location location = new Location();
		ExternalStaff extStaff = new ExternalStaff();
		ParMaster parmaster = new ParMaster();

		try { 
			

			org.json.JSONObject jsonObj = new org.json.JSONObject(json);
			area.setAreaName(jsonObj.getString("areaName"));
			skill.setSkillName(jsonObj.getString("skillName"));
			location.setLocationName(jsonObj.getString("locationName"));
			extStaff.setExtStaffName(jsonObj.getString("extStaffName"));
			parRole.setRoleName(jsonObj.getString("roleName"));

			parmaster.setParId(jsonObj.getInt("parId"));
			parmaster.setParNumber(jsonObj.getString("parNumber"));
			parmaster.setParDescriptionText(jsonObj.getString("parDescriptionText"));
			parmaster.setParStatus(jsonObj.getString("parStatus"));
			parmaster.setParReceivedDate(jsonObj.getString("parReceivedDate"));
			parmaster.setEmailSent(jsonObj.getString("emailSent"));
			parmaster.setIntentSentDate(jsonObj.getString("intentSentDate"));
			parmaster.setIntentToFill(jsonObj.getString("intentToFill"));
			parmaster.setParComment(jsonObj.getString("parComment"));
			parmaster.setArea(area);
			parmaster.setSkill(skill);
			parmaster.setLocation(location);
			parmaster.setExternalStaff(extStaff);
			parmaster.setParRole(parRole);
			if(parmasterServiceImpl.sendEmailRecruiters(parmaster)) {
				return parmasterServiceImpl.updateEmailSent(parmaster);
			};
			

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		catch (MailException mailException) {
			mailException.printStackTrace();
		}
		return ParConstants.emailUnSuccessfull;

		

	}



}
