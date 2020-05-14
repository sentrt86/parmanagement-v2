package com.htc.par.data.daoimpl;

import org.springframework.stereotype.Component;

@Component
public class ParSqlQueries {
	
	public static String allAreaQuery = "SELECT AREA_ID,AREA_NM,AREA_ACTIVE FROM AREA_LKUP";

}
