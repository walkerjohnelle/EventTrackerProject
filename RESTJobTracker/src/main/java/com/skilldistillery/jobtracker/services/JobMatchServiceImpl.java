package com.skilldistillery.jobtracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.jobtracker.entities.JobMatch;
import com.skilldistillery.jobtracker.repositories.JobMatchRepository;

@Service
public class JobMatchServiceImpl implements JobMatchService {

	@Autowired
	private JobMatchRepository jmRepo;

	@Override
	public List<JobMatch> findAllJobMatches() {
		return jmRepo.findAll();
	}

	@Override
	public JobMatch findById(int jobMId) {
		return jmRepo.searchById(jobMId);
	}

	@Override
	public JobMatch createJobMatch(JobMatch jMatch) {
		return jmRepo.saveAndFlush(jMatch);
	}

	@Override
	public JobMatch updateJobMatch(int jobMId, JobMatch jMatch) {
		Optional<JobMatch> optJM = jmRepo.findById(jobMId);
		if (optJM.isPresent()) {
			jMatch.setId(jobMId);
			return jmRepo.saveAndFlush(jMatch);
		}
		return null;
	}

	@Override
	public boolean deleteJobMatch(int jobMId) {
		Optional<JobMatch> optJM = jmRepo.findById(jobMId);
		if (optJM.isPresent()) {
			jmRepo.delete(optJM.get());
			return true;
		}
		return false;
	}
}
