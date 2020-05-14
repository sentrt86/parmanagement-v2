package com.htc.par.model;

public class Skill {
	
	
	private int skillId;
	private String SkillName;
	private boolean skillActive;
	
	
	public Skill() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Skill(int skillId, String skillName, boolean skillActive) {
		super();
		this.skillId = skillId;
		SkillName = skillName;
		this.skillActive = skillActive;
	}
	public int getSkillId() {
		return skillId;
	}
	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}
	public String getSkillName() {
		return SkillName;
	}
	public void setSkillName(String skillName) {
		SkillName = skillName;
	}
	public boolean isSkillActive() {
		return skillActive;
	}
	public void setSkillActive(boolean skillActive) {
		this.skillActive = skillActive;
	}
	@Override
	public String toString() {
		return "Skill [skillId=" + skillId + ", SkillName=" + SkillName + ", skillActive=" + skillActive + "]";
	}
	
	

}
