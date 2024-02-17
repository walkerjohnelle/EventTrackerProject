package com.skilldistillery.jobtracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.jobtracker.entities.Job;
import com.skilldistillery.jobtracker.repositories.JobRepository;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	private JobRepository jobRepo;

	@Override
	public List<Job> findAllJobs() {
		return jobRepo.findAll();
	}

	@Override
	public Job findById(int jobId) {
		return jobRepo.searchById(jobId);
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
			jobRepo.delete(optJob.get());
			return true;
		}
		return false;
	}
}
