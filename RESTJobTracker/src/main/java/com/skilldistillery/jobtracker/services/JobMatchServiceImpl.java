package com.skilldistillery.jobtracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.jobtracker.entities.Job;
import com.skilldistillery.jobtracker.entities.JobMatch;
import com.skilldistillery.jobtracker.entities.Skill;
import com.skilldistillery.jobtracker.entities.User;
import com.skilldistillery.jobtracker.repositories.JobMatchRepository;

@Service
public class JobMatchServiceImpl implements JobMatchService {

	@Autowired
	private JobMatchRepository jmRepo;

	@Override
	public List<JobMatch> findAllJobMatches() {
		return jmRepo.findAll();
	}

	@Override
	public JobMatch findById(int jobMId) {
		return jmRepo.searchById(jobMId);
	}

	@Override
	public JobMatch createJobMatch(JobMatch jMatch) {
		return jmRepo.saveAndFlush(jMatch);
	}

	@Override
	public JobMatch updateJobMatch(int jobMId, JobMatch jMatch) {
		Optional<JobMatch> optJM = jmRepo.findById(jobMId);
		if (optJM.isPresent()) {
			jMatch.setId(jobMId);
			return jmRepo.saveAndFlush(jMatch);
		}
		return null;
	}

	@Override
	public boolean deleteJobMatch(int jobMId) {
		Optional<JobMatch> optJM = jmRepo.findById(jobMId);
		if (optJM.isPresent()) {
			jmRepo.delete(optJM.get());
			return true;
		}
		return false;
	}

	 @Override
	    public double calculateMatchScore(User user, Job job) {
	        // Retrieve user preferences
	        int locationImportance = user.getLocationImportance();
	        List<Skill> userSkills = user.getSkills();
	        // Retrieve job details
	        String jobLocation = job.getLocation();
	        List<Skill> requiredSkills = job.getSkills();
	        // Calculate match score based on preferences and job details
	        double locationScore = calculateLocationScore(user.getLocation(), job.getLocation(), locationImportance);
	        double skillsScore = calculateSkillsScore(userSkills, requiredSkills);
	        // Add more criteria and calculate overall score
	        double overallScore = (locationScore + skillsScore) / 2; // Simple average for demonstration
	        return overallScore;
	    }

	 private double calculateLocationScore(String userLocation, String jobLocation, int locationImportance) {
		    if (userLocation.equalsIgnoreCase(jobLocation)) {
		        // If user location matches job location, give full importance score
		        return locationImportance;
		    } else {
		        // If user location does not match job location, give partial importance score
		        // You can define your own logic here, such as using distance calculation or geographical coordinates
		        return locationImportance / 2; // For demonstration, giving half importance score if locations don't match
		    }
		}

	 private double calculateSkillsScore(List<Skill> userSkills, List<Skill> requiredSkills) {
		    // Count the number of matching skills between user and job
		    int matchingSkills = 0;
		    for (Skill requiredSkill : requiredSkills) {
		        for (Skill userSkill : userSkills) {
		            if (requiredSkill.getName().equalsIgnoreCase(userSkill.getName())) {
		                matchingSkills++;
		                break; // Exit inner loop once a match is found
		            }
		        }
		    }
		    // Calculate the score based on the ratio of matching skills to total required skills
		    double score = (double) matchingSkills / requiredSkills.size();
		    return score * 100; // Convert to percentage for better interpretation
		}

}
