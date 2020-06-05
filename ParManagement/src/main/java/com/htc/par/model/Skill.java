package com.htc.par.model;

import java.io.Serializable;

public class Skill implements Serializable {
	

	private static final long serialVersionUID = -976001832559615487L;
	
	
	private int skillId;
	private String SkillName;
	private String skillActive;
	
	
	public Skill() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Skill(int skillId, String skillName, String skillActive) {
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
	public String getSkillActive() {
		return skillActive;
	}
	public void setSkillActive(String skillActive) {
		this.skillActive = skillActive;
	}
	@Override
	public String toString() {
		return "Skill [skillId=" + skillId + ", SkillName=" + SkillName + ", skillActive=" + skillActive + "]";
	}
	
	

}
