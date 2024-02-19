package com.skilldistillery.jobtracker.services;

import java.util.List;

import com.skilldistillery.jobtracker.entities.Skill;

public interface SkillService {
	List<Skill> findAllSkills();

	Skill findById(int skillId);

	Skill createSkill(Skill skill);

	Skill updateSkill(int skillId, Skill skill);

	boolean deleteSkill(int skillId);
}
