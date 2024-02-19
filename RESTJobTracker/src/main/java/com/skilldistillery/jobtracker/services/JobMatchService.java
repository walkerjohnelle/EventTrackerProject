package com.skilldistillery.jobtracker.services;

import java.util.List;

import com.skilldistillery.jobtracker.entities.Job;
import com.skilldistillery.jobtracker.entities.JobMatch;
import com.skilldistillery.jobtracker.entities.User;

public interface JobMatchService {
	List<JobMatch> findAllJobMatches();

	JobMatch findById(int jobMId);

	JobMatch createJobMatch(JobMatch jMatch);

	JobMatch updateJobMatch(int jobMId, JobMatch jMatch);

	boolean deleteJobMatch(int jobMId);
	
	double calculateMatchScore(User user, Job job);

}
