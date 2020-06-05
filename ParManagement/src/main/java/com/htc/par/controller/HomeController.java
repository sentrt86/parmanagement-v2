package com.htc.par.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.htc.par.model.UserMaster;
import com.htc.par.service.UserMasterServiceImpl;


@Controller
public class HomeController {
	
	public static String username = "";

	@Autowired
	UserMasterServiceImpl userMasterServiceImpl;
	
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView main(Locale locale,Model model) {
		return  new ModelAndView("redirect:/home");

	}
	
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request,Locale locale,Model model) throws Exception {
		ModelAndView modelView  = new ModelAndView();
		List<UserMaster> userMaster   = new ArrayList<UserMaster>();
		HttpSession session     = request.getSession(true);		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		session.setAttribute("user",userDetail.getUsername());	
		userMaster = userMasterServiceImpl.getUserByUserName(userDetail.getUsername());
		username = userMaster.get(0).getUserFirstName() +' '+userMaster.get(0).getUserLastName();
		modelView.addObject("username",username);
		modelView.setViewName("home");
		return modelView;

	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView logn(@RequestParam(value = "error", required = false) String error,Locale locale, Model model) {
		ModelAndView modelView = new ModelAndView();
		if (error != null) {
			modelView.addObject("error", "Invalid username and password!");
		  }
		modelView.setViewName("login");
		return modelView;

	}
	
	
	@RequestMapping(value = "/accessdenied", method = RequestMethod.POST)
	public ModelAndView loginerror(Model model) {
		ModelAndView modelView = new ModelAndView();
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		  if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			modelView.addObject("username", userDetail.getUsername());
		  }
		modelView.setViewName("login");
		return modelView;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request,Model model) {
		ModelAndView modelView = new ModelAndView();
		HttpSession session = request.getSession();	
		if(session.getAttribute("user") != null){
            session.removeAttribute("user");
		}
		modelView.setViewName("login");
		return modelView;
	}
	 



}
