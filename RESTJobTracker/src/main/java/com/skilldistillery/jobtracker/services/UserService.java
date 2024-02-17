package com.skilldistillery.jobtracker.services;

import java.util.List;

import com.skilldistillery.jobtracker.entities.User;

public interface UserService {
	List<User> findAllUsers();

	User findUserById(int userId);

	User createUser(User user);

	User updateUser(int userId, User user);

	boolean deleteUser(int userId);
}
