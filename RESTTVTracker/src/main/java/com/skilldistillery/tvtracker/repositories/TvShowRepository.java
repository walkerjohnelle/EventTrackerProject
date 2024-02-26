package com.skilldistillery.jobtracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.jobtracker.entities.Skill;

public interface SkillRepository extends JpaRepository<Skill, Integer> {
	Skill searchById(int skillId);
}
