package com.skilldistillery.jobtracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.jobtracker.entities.JobMatch;

public interface JobMatchRepository extends JpaRepository<JobMatch, Integer> {
	JobMatch searchById(int jobMId);
}
