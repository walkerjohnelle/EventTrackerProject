package com.skilldistillery.jobtracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.jobtracker.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
