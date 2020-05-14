package com.htc.par.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.htc.par.model.Area;

public class AreaRowMapper  implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

		final Area area = new Area();
		
		area.setAreaId(rs.getInt("AREA_ID"));
		area.setAreaName(rs.getString("AREA_NM"));
		area.setAreaActive(rs.getBoolean("AREA_ACTIVE"));
		
		return area;
	}

}
