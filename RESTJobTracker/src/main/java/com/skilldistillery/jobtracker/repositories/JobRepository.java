package com.skilldistillery.jobtracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.jobtracker.entities.Job;

public interface JobRepository extends JpaRepository<Job, Integer> {
	Job searchById(int jobId);
}
