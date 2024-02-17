package com.skilldistillery.jobtracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.jobtracker.entities.User;
import com.skilldistillery.jobtracker.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<User> findAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public User findUserById(int userId) {
		Optional<User> optUser = userRepo.findById(userId);
		return optUser.orElse(null);
	}

	@Override
	public User createUser(User user) {
		return userRepo.saveAndFlush(user);
	}

	@Override
	public User updateUser(int userId, User user) {
		Optional<User> optUser = userRepo.findById(userId);
		if (optUser.isPresent()) {
			user.setId(userId); 
			return userRepo.saveAndFlush(user);
		}
		return null;
	}

	@Override
	public boolean deleteUser(int userId) {
		Optional<User> optUser = userRepo.findById(userId);
		if (optUser.isPresent()) {
			userRepo.delete(optUser.get());
			return true;
		}
		return false;
	}
}
