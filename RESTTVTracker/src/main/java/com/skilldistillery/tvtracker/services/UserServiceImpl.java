package com.skilldistillery.tvtracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skilldistillery.tvtracker.entities.User;
import com.skilldistillery.tvtracker.repositories.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<User> findAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public User findById(int userId) {
		Optional<User> userOpt = userRepo.findById(userId);
		return userOpt.orElse(null);
	}

	@Override
	public User createUser(User user) {
		if (user != null) {
			return userRepo.save(user);
		}
		return null;
	}

	@Override
	public User updateUser(int userId, User user) {
		Optional<User> existingUserOpt = userRepo.findById(userId);
		if (existingUserOpt.isPresent()) {
			User existingUser = existingUserOpt.get();
			// Update user properties
			existingUser.setEmail(user.getEmail());
			existingUser.setUsername(user.getUsername());
			existingUser.setPassword(user.getPassword());
			existingUser.setProfilePicture(user.getProfilePicture());
			existingUser.setActive(user.isActive());

			return userRepo.save(existingUser);
		}
		return null;
	}

	@Override
	public boolean deleteUser(int userId) {
		Optional<User> existingUserOpt = userRepo.findById(userId);
		if (existingUserOpt.isPresent()) {
			User existingUser = existingUserOpt.get();
			userRepo.delete(existingUser);
			return true;
		}
		return false;
	}

}
