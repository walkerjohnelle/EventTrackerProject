package com.skilldistillery.jobtracker.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.jobtracker.entities.Job;
import com.skilldistillery.jobtracker.entities.JobMatch;
import com.skilldistillery.jobtracker.entities.Skill;
import com.skilldistillery.jobtracker.entities.User;
import com.skilldistillery.jobtracker.repositories.JobMatchRepository;
import com.skilldistillery.jobtracker.repositories.UserRepository;

@Service
public class JobMatchServiceImpl implements JobMatchService {

	@Autowired
	private JobMatchRepository jmRepo;

	@Autowired
	private UserRepository userRepo;

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
		double matchScore = calculateMatchScore(jMatch.getUser(), jMatch.getJob());
		jMatch.setJobMatchScore(matchScore);
		return jmRepo.saveAndFlush(jMatch);
	}

	@Override
	public JobMatch updateJobMatch(int jobMId, JobMatch jMatch) {
		Optional<JobMatch> optJM = jmRepo.findById(jobMId);
		if (optJM.isPresent()) {
			double matchScore = calculateMatchScore(jMatch.getUser(), jMatch.getJob());
			jMatch.setJobMatchScore(matchScore);
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
	public List<JobMatch> getJobMatchesByUserId(int userId) {
		User user = userRepo.findById(userId).orElse(null);
		if (user == null) {
			return Collections.emptyList();
		}

		List<JobMatch> jobMatches = user.getJobMatches();

		// Calculate match scores for each job match
		for (JobMatch jobMatch : jobMatches) {
			double matchScore = calculateMatchScore(user, jobMatch.getJob());
			jobMatch.setJobMatchScore(matchScore);
		}

		// Sort job matches by match score (descending order)
		jobMatches.sort(Comparator.comparing(JobMatch::getJobMatchScore).reversed());

		return jobMatches;
	}

	// Calculate match score based on various criteria
	public double calculateMatchScore(User user, Job job) {
		double matchScore = 0;

		// Calculate percentage match for each criterion and aggregate the scores
		matchScore += calculateSkillsMatch(user.getSkills(), job.getSkills());
		matchScore += calculateClearanceMatch(user.getClearance(), job.getClearance()) ? 100 : 0;
		matchScore += calculateExperienceMatch(user.getExperience(), job.getMinExperience(), job.getMaxExperience());
		matchScore += calculateSalaryMatch(user.getMinSalary(), user.getMaxSalary(), job.getMinSalary(),
				job.getMaxSalary());
		matchScore += calculateRemoteWorkMatch(user.isRemoteWorkDesired(), job.isRemoteOrHybridOption());
		matchScore += calculateEducationMatch(user.getEducation(), job.getEducation());

		// Normalize the total match score to a percentage (if needed)
		// Adjust this based on how you want to interpret the match score
		matchScore /= 6; // Assuming there are 6 criteria

		return matchScore;
	}
	private double calculateSkillsMatch(List<Skill> userSkills, List<Skill> jobSkills) {
		int totalSkills = jobSkills.size();
		int matchingSkills = 0;

		for (Skill jobSkill : jobSkills) {
			for (Skill userSkill : userSkills) {
				if (jobSkill.getName().equalsIgnoreCase(userSkill.getName())) {
					matchingSkills++;
					break;
				}
			}
		}

		return (double) matchingSkills / totalSkills * 100;
	}

	private boolean calculateClearanceMatch(String userClearance, String jobClearance) {
		return userClearance.equalsIgnoreCase(jobClearance);
	}


	private double calculateExperienceMatch(int userExperience, int jobMinExperience, int jobMaxExperience) {
		if (userExperience >= jobMinExperience && userExperience <= jobMaxExperience) {
			return 100;
		} else {
			return 0;
		}
	}

	private double calculateSalaryMatch(double userMinSalary, double userMaxSalary, double jobMinSalary,
			double jobMaxSalary) {
		if (userMinSalary >= jobMinSalary && userMaxSalary <= jobMaxSalary) {
			return 100;
		} else {
			return 0;
		}
	}

	private double calculateRemoteWorkMatch(boolean desiresRemoteWork, boolean jobAllowsRemoteWork) {
		return desiresRemoteWork == jobAllowsRemoteWork ? 100 : 0;
	}

	private double calculateEducationMatch(String userEducationLevel, String jobEducationRequired) {
		return userEducationLevel.equalsIgnoreCase(jobEducationRequired) ? 100 : 0;
	}

}
