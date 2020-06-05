package com.htc.par.model;

import java.io.Serializable;

public class Area  implements Serializable {
	

	private static final long serialVersionUID = 5827424856206703745L;
	
	private int areaId;
	private String areaName;
	private String areaActive;
	
	public Area() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Area(int areaId, String areaName, String areaActive) {
		super();
		this.areaId = areaId;
		this.areaName = areaName;
		this.areaActive = areaActive;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaActive() {
		return areaActive;
	}

	public void setAreaActive(String areaActive) {
		this.areaActive = areaActive;
	}

	@Override
	public String toString() {
		return "Area [areaId=" + areaId + ", areaName=" + areaName + ", areaActive=" + areaActive + "]";
	}
	
	
	

}
