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

import com.skilldistillery.jobtracker.entities.Job;
import com.skilldistillery.jobtracker.services.JobService;

import jakarta.servlet.http.HttpServletResponse;

@RequestMapping("api")
@RestController
public class JobController {

	@Autowired
	private JobService jS;

	@GetMapping(path = { "jobs", "jobs/" })
	public List<Job> index() {
		return jS.findAllJobs();
	}

	@GetMapping("jobs/{jobId}")
	public Job show(@PathVariable("jobId") int jobId, HttpServletResponse rsp) {
		Job job = jS.findById(jobId);

		if (job == null) {
			rsp.setStatus(404);
		}
		return job;
	}

	@PostMapping("jobs")
	public Job createJob(@RequestBody Job job, HttpServletResponse rsp) {
		Job createdJob = jS.createJob(job);
		if (createdJob == null) {
			rsp.setStatus(404);
		}
		return createdJob;
	}

	@PutMapping("jobs/{jobId}")
	public Job updateJob(@PathVariable("jobId") int jobId, @RequestBody Job job, HttpServletResponse rsp) {
		try {
			job = jS.updateJob(jobId, job);
			if (job == null) {
				rsp.setStatus(404);
			}
		} catch (Exception e) {
			rsp.setStatus(400);
			job = null;
			e.printStackTrace();
		}
		return job;
	}

	@DeleteMapping("jobs/{jobId}")
	public void deleteJob(@PathVariable("jobId") int jobId, HttpServletResponse rsp) {
		Job job = jS.findById(jobId);

		try {
			if (job != null) {
				jS.deleteJob(jobId);
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
