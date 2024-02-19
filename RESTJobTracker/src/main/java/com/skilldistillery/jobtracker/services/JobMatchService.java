package com.skilldistillery.jobtracker.services;

import java.util.List;

import com.skilldistillery.jobtracker.entities.JobMatch;

public interface JobMatchService {
	List<JobMatch> findAllJobMatches();

	JobMatch findById(int jobMId);

	JobMatch createJobMatch(JobMatch jMatch);

	JobMatch updateJobMatch(int jobMId, JobMatch jMatch);

	boolean deleteJobMatch(int jobMId);
}
