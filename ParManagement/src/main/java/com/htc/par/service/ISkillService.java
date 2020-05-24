package com.htc.par.service;

import java.util.List;

import com.htc.par.model.Skill;

public interface ISkillService {
	
	public List<Skill>  getAllSkills();
	public int getNextSkillId();
	public String deleteSkill(int skillId);
	public String createSkill(Skill skill);
	public String updateSkill(Skill skill);

}
