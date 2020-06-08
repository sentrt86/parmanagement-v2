package com.htc.par.service;

import java.util.List;

import com.htc.par.model.Skill;

public interface ISkillService {
	
	public List<Skill>  getAllSkills() throws Exception;
	public List<Skill>  getActiveSkills() throws Exception;
	public int getNextSkillId() throws Exception;
	public String deleteSkill(int skillId) throws Exception;
	public String createSkill(Skill skill) throws Exception;
	public String updateSkill(Skill skill) throws Exception;

}
