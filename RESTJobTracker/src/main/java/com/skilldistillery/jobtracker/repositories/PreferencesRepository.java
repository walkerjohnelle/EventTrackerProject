package com.skilldistillery.jobtracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.jobtracker.entities.Preferences;

public interface PreferencesRepository extends JpaRepository<Preferences, Integer> {
	Preferences searchById(int prefId);
}
