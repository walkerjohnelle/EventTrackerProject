package com.skilldistillery.jobtracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skilldistillery.jobtracker.entities.JobMatch;
import com.skilldistillery.jobtracker.entities.Skill;
import com.skilldistillery.jobtracker.entities.User;
import com.skilldistillery.jobtracker.repositories.UserRepository;

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
	        existingUser.setFirstName(user.getFirstName());
	        existingUser.setLastName(user.getLastName());
	        existingUser.setPassword(user.getPassword());
	        existingUser.setEmployed(user.isEmployed());
	        existingUser.setRole(user.getRole());
	        existingUser.setClearance(user.getClearance());
	        existingUser.setEducation(user.getEducation());
	        existingUser.setLocation(user.getLocation());
	        existingUser.setExperience(user.getExperience());
	        existingUser.setMinSalary(user.getMinSalary());
	        existingUser.setMaxSalary(user.getMaxSalary());
	        existingUser.setRemoteWorkDesired(user.isRemoteWorkDesired());

	        // Update skills
	        List<Skill> newSkills = user.getSkills();
	        if (newSkills != null) {
	            existingUser.getSkills().clear(); // Clear existing skills
	            for (Skill skill : newSkills) {
	                existingUser.addSkill(skill); // Add new skills
	            }
	        }

	        // Update job matches
	        List<JobMatch> newJobMatches = user.getJobMatches();
	        if (newJobMatches != null) {
	            existingUser.getJobMatches().clear(); // Clear existing job matches
	            for (JobMatch jobMatch : newJobMatches) {
	                existingUser.addJobMatch(jobMatch); // Add new job matches
	            }
	        }

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
