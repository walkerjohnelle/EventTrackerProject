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

import com.skilldistillery.jobtracker.entities.JobMatch;
import com.skilldistillery.jobtracker.services.JobMatchService;

import jakarta.servlet.http.HttpServletResponse;

@RequestMapping("api")
@RestController
public class JobMatchController {

	@Autowired
	private JobMatchService jmS;

	@GetMapping(path = { "jobmatches", "jobmatches/" })
	public List<JobMatch> index() {
		return jmS.findAllJobMatches();
	}

	@GetMapping("jobmatches/{jobMId}")
	public JobMatch show(@PathVariable("jobMId") int jobMId, HttpServletResponse rsp) {
		JobMatch jMatch = jmS.findById(jobMId);

		if (jMatch == null) {
			rsp.setStatus(404);
		}
		return jMatch;
	}

	@PostMapping("jobmatches")
	public JobMatch createJobMatch(@RequestBody JobMatch jMatch, HttpServletResponse rsp) {
		JobMatch createdJM = jmS.createJobMatch(jMatch);
		if (createdJM == null) {
			rsp.setStatus(404);
		}
		return createdJM;
	}

	@PutMapping("jobmatches/{jobMId}")
	public JobMatch updateJobMatch(@PathVariable("jobMId") int jobMId, @RequestBody JobMatch jMatch,
			HttpServletResponse rsp) {
		try {
			jMatch = jmS.updateJobMatch(jobMId, jMatch);
			if (jMatch == null) {
				rsp.setStatus(404);
			}
		} catch (Exception e) {
			rsp.setStatus(400);
			jMatch = null;
			e.printStackTrace();
		}
		return jMatch;
	}

	@DeleteMapping("jobmatches/{jobMId}")
	public void deleteJobMatch(@PathVariable("jobMId") int jobMId, HttpServletResponse rsp) {
		JobMatch jMatch = jmS.findById(jobMId);

		try {
			if (jMatch != null) {
				jmS.deleteJobMatch(jobMId);
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
