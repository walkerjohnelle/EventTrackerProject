package com.skilldistillery.jobtracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.jobtracker.entities.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {
	Location searchById(int locationId);
}
