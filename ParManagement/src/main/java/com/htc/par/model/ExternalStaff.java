package com.htc.par.model;

public class ExternalStaff {
	
	private int extStaffId;
	private String extStaffName;
	private Area area;
	private boolean extStaffActive;
	
	
	public ExternalStaff() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ExternalStaff(int extStaffId, String extStaffName, Area area, boolean extStaffActive) {
		super();
		this.extStaffId = extStaffId;
		this.extStaffName = extStaffName;
		this.area = area;
		this.extStaffActive = extStaffActive;
	}


	public int getExtStaffId() {
		return extStaffId;
	}


	public void setExtStaffId(int extStaffId) {
		this.extStaffId = extStaffId;
	}


	public String getExtStaffName() {
		return extStaffName;
	}


	public void setExtStaffName(String extStaffName) {
		this.extStaffName = extStaffName;
	}


	public Area getArea() {
		return area;
	}


	public void setArea(Area area) {
		this.area = area;
	}


	public boolean isExtStaffActive() {
		return extStaffActive;
	}


	public void setExtStaffActive(boolean extStaffActive) {
		this.extStaffActive = extStaffActive;
	}


	@Override
	public String toString() {
		return "ExternalStaff [extStaffId=" + extStaffId + ", extStaffName=" + extStaffName + ", area=" + area
				+ ", extStaffActive=" + extStaffActive + "]";
	}
	
	
	

}
