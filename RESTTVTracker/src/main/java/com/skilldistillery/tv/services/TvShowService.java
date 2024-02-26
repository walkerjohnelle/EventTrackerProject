package com.skilldistillery.jobtracker.services;

import java.util.List;

import com.skilldistillery.jobtracker.entities.Job;

public interface JobService {
	List<Job> findAllJobs();

	Job findById(int jobId);

	Job createJob(Job job);

	Job updateJob(int jobId, Job job);

	boolean deleteJob(int jobId);
}
