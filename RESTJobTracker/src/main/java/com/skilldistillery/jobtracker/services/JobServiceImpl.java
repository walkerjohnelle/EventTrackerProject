package com.skilldistillery.jobtracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skilldistillery.jobtracker.entities.Job;
import com.skilldistillery.jobtracker.entities.User;
import com.skilldistillery.jobtracker.repositories.JobRepository;

@Service
@Transactional
public class JobServiceImpl implements JobService {

	@Autowired
	private JobRepository jobRepo;

	@Override
	public List<Job> findAllJobs() {
		return jobRepo.findAll();
	}

	@Override
	public Job findById(int jobId) {
		Optional<Job> optJob = jobRepo.findById(jobId);
		return optJob.orElse(null);
	}

	@Override
	public Job createJob(Job job) {
		return jobRepo.saveAndFlush(job);
	}

	@Override
	public Job updateJob(int jobId, Job job) {
		Optional<Job> optJob = jobRepo.findById(jobId);
		if (optJob.isPresent()) {
			job.setId(jobId);
			return jobRepo.saveAndFlush(job);
		}
		return null;
	}

	@Override
	public boolean deleteJob(int jobId) {
		Optional<Job> optJob = jobRepo.findById(jobId);
		if (optJob.isPresent()) {
			Job job = optJob.get();

			List<User> users = job.getUsers();
			if (users != null) {
				for (User user : users) {
					user.removeJob(job);
				}
			}

			job.setSkills(null);

			jobRepo.delete(job);

			return true;
		}
		return false;
	}
}
