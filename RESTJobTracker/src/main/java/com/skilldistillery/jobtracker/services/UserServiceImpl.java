package com.skilldistillery.jobtracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skilldistillery.jobtracker.entities.JobMatch;
import com.skilldistillery.jobtracker.entities.User;
import com.skilldistillery.jobtracker.repositories.JobMatchRepository;
import com.skilldistillery.jobtracker.repositories.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private JobMatchRepository jobMatchRepo;

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
			User savedUser = userRepo.save(user);
			if (user.getJobMatches() != null) {
				for (JobMatch jobMatch : user.getJobMatches()) {
					jobMatch.setUser(savedUser);
					jobMatchRepo.save(jobMatch);
				}
			}
			return savedUser;
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
			existingUser.setFirstName(user.getFirstName());
			existingUser.setLastName(user.getLastName());
			existingUser.setPassword(user.getPassword());
			existingUser.setEmployed(user.isEmployed());
			existingUser.setRole(user.getRole());
			existingUser.setClearance(user.getClearance());
			existingUser.setEducation(user.getEducation());
			existingUser.setLocation(user.getLocation());
			existingUser.setSalaryImportance(user.getSalaryImportance());
			existingUser.setLocationImportance(user.getLocationImportance());
			existingUser.setBenefitsImportance(user.getBenefitsImportance());
			existingUser.setRemoteWorkImportance(user.getRemoteWorkImportance());
			existingUser.setSkills(user.getSkills());
			existingUser.setJobMatches(user.getJobMatches());
			return userRepo.save(existingUser);
		}
		return null;
	}

	@Override
	public boolean deleteUser(int userId) {
		Optional<User> existingUserOpt = userRepo.findById(userId);
		if (existingUserOpt.isPresent()) {
			User existingUser = existingUserOpt.get();
			if (existingUser.getJobMatches() != null) {
				for (JobMatch jobMatch : existingUser.getJobMatches()) {
					jobMatch.setUser(null);
				}
				jobMatchRepo.deleteAll(existingUser.getJobMatches());
			}
			userRepo.delete(existingUser);
			return true;
		}
		return false;
	}

}
