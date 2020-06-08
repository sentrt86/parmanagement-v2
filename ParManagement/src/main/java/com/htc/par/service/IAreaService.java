package com.htc.par.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.htc.par.model.Area;

@Service
public interface IAreaService {
	
	public List<Area>  getAllAreas() throws Exception ;
	public List<Area>  getActiveAreas() throws Exception ;
	public int getNextAreaId() throws Exception;
	public String deleteArea(int areaId) throws Exception;
	public String createArea(Area area)  throws Exception ;
	public String updateArea(Area area)  throws Exception;
	

}