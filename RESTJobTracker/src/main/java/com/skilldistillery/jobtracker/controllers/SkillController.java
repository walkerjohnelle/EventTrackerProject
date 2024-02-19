package com.skilldistillery.jobtracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.jobtracker.entities.Skill;
import com.skilldistillery.jobtracker.services.SkillService;

import jakarta.servlet.http.HttpServletResponse;

@RequestMapping("api")
@RestController
public class SkillController {

	@Autowired
	private SkillService sS;

	@GetMapping(path = { "skills", "skills/" })
	public List<Skill> index() {
		return sS.findAllSkills();
	}

	@GetMapping("skills/{skillId}")
	public Skill show(@PathVariable("skillId") int skillId, HttpServletResponse rsp) {
		Skill skill = sS.findById(skillId);

		if (skill == null) {
			rsp.setStatus(404);
		}
		return skill;
	}

	@PostMapping("skills")
	public Skill createSkill(@RequestBody Skill skill, HttpServletResponse rsp) {
		Skill createdSkill = sS.createSkill(skill);
		if (createdSkill == null) {
			rsp.setStatus(404);
		}
		return createdSkill;
	}

	@PutMapping("skills/{skillId}")
	public Skill updateSkill(@PathVariable("skillId") int skillId, @RequestBody Skill skill, HttpServletResponse rsp) {
		try {
			skill = sS.updateSkill(skillId, skill);
			if (skill == null) {
				rsp.setStatus(404);
			}
		} catch (Exception e) {
			rsp.setStatus(400);
			skill = null;
			e.printStackTrace();
		}
		return skill;
	}

	@DeleteMapping("skills/{skillId}")
	public void deleteSkill(@PathVariable("skillId") int skillId, HttpServletResponse rsp) {
		Skill skill = sS.findById(skillId);

		try {
			if (skill != null) {
				sS.deleteSkill(skillId);
				rsp.setStatus(204);
			} else {
				rsp.setStatus(404);
			}
		} catch (Exception e) {
			rsp.setStatus(400);
			e.printStackTrace();
		}
	}

}
