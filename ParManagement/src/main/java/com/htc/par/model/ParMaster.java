package com.htc.par.model;

public class ParMaster {
	
	private int parId;
	private String parNumber;
	private String parDescriptionText;
	private String parReceivedDate;
	private String parStatus;
	private String intentToFill;
	private String  intentSentDate;
	private String emailSent;
	private String  parComment;
	private Area area;
	private Skill skill;
	private ParRole parRole;
	private ExternalStaff externalStaff;
	private Location location;
	
	public ParMaster() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ParMaster(int parId, String parNumber, String parDescriptionText, String parReceivedDate, String parStatus,
			String intentToFill, String intentSentDate, String emailSent, String parComment, Area area, Skill skill,
			ParRole parRole, ExternalStaff externalStaff, Location location) {
		super();
		this.parId = parId;
		this.parNumber = parNumber;
		this.parDescriptionText = parDescriptionText;
		this.parReceivedDate = parReceivedDate;
		this.parStatus = parStatus;
		this.intentToFill = intentToFill;
		this.intentSentDate = intentSentDate;
		this.emailSent = emailSent;
		this.parComment = parComment;
		this.area = area;
		this.skill = skill;
		this.parRole = parRole;
		this.externalStaff = externalStaff;
		this.location = location;
	}
	public int getParId() {
		return parId;
	}
	public void setParId(int parId) {
		this.parId = parId;
	}
	public String getParNumber() {
		return parNumber;
	}
	public void setParNumber(String parNumber) {
		this.parNumber = parNumber;
	}
	public String getParDescriptionText() {
		return parDescriptionText;
	}
	public void setParDescriptionText(String parDescriptionText) {
		this.parDescriptionText = parDescriptionText;
	}
	public String getParReceivedDate() {
		return parReceivedDate;
	}
	public void setParReceivedDate(String parReceivedDate) {
		this.parReceivedDate = parReceivedDate;
	}
	public String getParStatus() {
		return parStatus;
	}
	public void setParStatus(String parStatus) {
		this.parStatus = parStatus;
	}
	public String getIntentToFill() {
		return intentToFill;
	}
	public void setIntentToFill(String intentToFill) {
		this.intentToFill = intentToFill;
	}
	public String getIntentSentDate() {
		return intentSentDate;
	}
	public void setIntentSentDate(String intentSentDate) {
		this.intentSentDate = intentSentDate;
	}
	public String getEmailSent() {
		return emailSent;
	}
	public void setEmailSent(String emailSent) {
		this.emailSent = emailSent;
	}
	public String getParComment() {
		return parComment;
	}
	public void setParComment(String parComment) {
		this.parComment = parComment;
	}
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public Skill getSkill() {
		return skill;
	}
	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	public ParRole getParRole() {
		return parRole;
	}
	public void setParRole(ParRole parRole) {
		this.parRole = parRole;
	}
	public ExternalStaff getExternalStaff() {
		return externalStaff;
	}
	public void setExternalStaff(ExternalStaff externalStaff) {
		this.externalStaff = externalStaff;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "ParMaster [parId=" + parId + ", parNumber=" + parNumber + ", parDescriptionText=" + parDescriptionText
				+ ", parReceivedDate=" + parReceivedDate + ", parStatus=" + parStatus + ", intentToFill=" + intentToFill
				+ ", intentSentDate=" + intentSentDate + ", emailSent=" + emailSent + ", parComment=" + parComment
				+ ", area=" + area + ", skill=" + skill + ", parRole=" + parRole + ", externalStaff=" + externalStaff
				+ ", location=" + location + "]";
	}

	
}
