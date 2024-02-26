package com.skilldistillery.tvtracker.services;

import java.util.List;

import com.skilldistillery.tvtracker.entities.User;

public interface UserService {
	List<User> findAllUsers();

	User findById(int userId);

	User createUser(User user);

	User updateUser(int userId, User user);

	boolean deleteUser(int userId);

}
