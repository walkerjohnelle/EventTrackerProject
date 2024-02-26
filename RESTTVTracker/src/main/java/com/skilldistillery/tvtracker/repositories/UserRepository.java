package com.skilldistillery.tvtracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.tvtracker.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User searchById(int userId);
}
