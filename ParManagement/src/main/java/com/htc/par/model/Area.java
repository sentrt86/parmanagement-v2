package com.htc.par.model;

public class Area {
	
	private int areaId;
	private String areaName;
	private boolean areaActive;
	
	
	
	public Area() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Area(int areaId, String areaName, boolean areaActive) {
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


	public boolean isAreaActive() {
		return areaActive;
	}


	public void setAreaActive(boolean areaActive) {
		this.areaActive = areaActive;
	}


	@Override
	public String toString() {
		return "Skill [areaId=" + areaId + ", areaName=" + areaName + ", areaActive=" + areaActive + "]";
	}
	
	

}
