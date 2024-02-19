package com.skilldistillery.jobtracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.jobtracker.entities.Skill;
import com.skilldistillery.jobtracker.repositories.JobRepository;
import com.skilldistillery.jobtracker.repositories.SkillRepository;
import com.skilldistillery.jobtracker.repositories.UserRepository;

@Service
public class SkillServiceImpl implements SkillService {

	@Autowired
	private SkillRepository skillRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private JobRepository jobRepo;

	@Override
	public List<Skill> findAllSkills() {
		return skillRepo.findAll();
	}

	@Override
	public Skill findById(int skillId) {
		return skillRepo.searchById(skillId);
	}

	@Override
	public List<Skill> getUserSkills(int userId) {
		List<Skill> userSkills = userRepo.searchById(userId).getSkills();
		return userSkills;
	}

	@Override
	public List<Skill> getJobRequiredSkills(int jobId) {
		List<Skill> jobSkills = jobRepo.searchById(jobId).getSkills();
		return jobSkills;
	}

	@Override
	public Skill createSkill(Skill skill) {
		return skillRepo.saveAndFlush(skill);
	}

	@Override
	public Skill updateSkill(int skillId, Skill skill) {
		Optional<Skill> optSkill = skillRepo.findById(skillId);
		if (optSkill.isPresent()) {
			skill.setId(skillId);
			return skillRepo.saveAndFlush(skill);
		}
		return null;
	}

	@Override
	public boolean deleteSkill(int skillId) {
		Optional<Skill> optSkill = skillRepo.findById(skillId);
		if (optSkill.isPresent()) {
			skillRepo.delete(optSkill.get());
			return true;
		}
		return false;
	}

}
