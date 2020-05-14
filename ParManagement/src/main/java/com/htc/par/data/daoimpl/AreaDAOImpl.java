package com.htc.par.data.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.htc.par.data.dao.IAreaDAO;
import com.htc.par.data.mapper.AreaRowMapper;
import com.htc.par.model.Area;

@Component
public class AreaDAOImpl implements IAreaDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	ParSqlQueries parSqlQueries;

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Area> getAllAreas() {
		List<Area> allAreaList = new ArrayList<Area> ();
		allAreaList = jdbcTemplate.query(ParSqlQueries.allAreaQuery,new Object[]{}, new AreaRowMapper());		
		return allAreaList;
	}

}
