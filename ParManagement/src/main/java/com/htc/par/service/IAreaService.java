package com.htc.par.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.htc.par.exceptions.ResourceNotFoundException;

import com.htc.par.model.Area;

@Service
public interface IAreaService {
	
	
	public List<Area> getAllAreas() throws ResourceNotFoundException;
	


}
