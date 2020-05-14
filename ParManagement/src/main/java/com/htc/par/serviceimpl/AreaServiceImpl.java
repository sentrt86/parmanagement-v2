package com.htc.par.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.htc.par.constants.ParConstants;
import com.htc.par.data.daoimpl.AreaDAOImpl;
import com.htc.par.exceptions.ResourceAccessException;
import com.htc.par.exceptions.ResourceNotFoundException;
import com.htc.par.model.Area;
import com.htc.par.service.IAreaService;

@Service
public class AreaServiceImpl implements IAreaService {
	


	@Autowired
	AreaDAOImpl areaDaoImpl;

	/*
	 * Request handler to GET all Areas
	 * 
	 * @ResourseNotFoundException
	 */

	@Override
	public List<Area> getAllAreas() throws ResourceNotFoundException {	
		List<Area> area = new ArrayList<Area> ();
		
		try {
			area = areaDaoImpl.getAllAreas();			
			if(area.isEmpty())
			{
				throw new  ResourceNotFoundException(ParConstants.dataNotFound);
			}			

		}catch(DataAccessException ex) {
			throw new ResourceAccessException(ParConstants.databaseAccessIssue);			
		}
		
		return  area;
	}

}
