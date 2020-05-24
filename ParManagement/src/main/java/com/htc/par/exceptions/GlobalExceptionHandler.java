package com.htc.par.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	
	public void handleAllExceptions(HttpServletRequest httpServletRequest,WebRequest webRequest,HttpStatus hpptStatus, Exception ex) {
		
		logger.error("PAR_Management:: Request url " + httpServletRequest.getRequestURI()
				+ " HttpStatus code: " + String.valueOf(HttpStatus.BAD_REQUEST));	
		logger.error("Error : " +  ex.toString());
		
	}
}
