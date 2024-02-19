package com.skilldistillery.jobtracker.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String company;
	@Column(name = "min_salary")
	private double minSalary;
	@Column(name = "max_salary")
	private double maxSalary;
	private String education;
	@Column(name = "min_experience")
	private int minExperience;
	@Column(name = "max_experience")
	private int maxExperience;
	private String applicationStatus;
	@Column(name = "contact_info")
	private String contactInfo;
	@Column(name = "job_listing")
	private String jobListing;
	private String clearance;
	@Column(name = "remote_hybrid")
	private boolean remoteOrHybridOption;
	private String location;

	@ManyToMany(mappedBy = "jobs")
	private List<User> users;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "job_has_skills", joinColumns = @JoinColumn(name = "job_id"), inverseJoinColumns = @JoinColumn(name = "skill_id"))
	private List<Skill> skills;

	@JsonIgnore
	@OneToMany(mappedBy = "job")
	private List<JobMatch> jobMatches;

	public Job() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public double getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(double minSalary) {
		this.minSalary = minSalary;
	}

	public double getMaxSalary() {
		return maxSalary;
	}

	public void setMaxSalary(double maxSalary) {
		this.maxSalary = maxSalary;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public int getMinExperience() {
		return minExperience;
	}

	public void setMinExperience(int minExperience) {
		this.minExperience = minExperience;
	}

	public int getMaxExperience() {
		return maxExperience;
	}

	public void setMaxExperience(int maxExperience) {
		this.maxExperience = maxExperience;
	}

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	public String getJobListing() {
		return jobListing;
	}

	public void setJobListing(String jobListing) {
		this.jobListing = jobListing;
	}

	public String getClearance() {
		return clearance;
	}

	public void setClearance(String clearance) {
		this.clearance = clearance;
	}

	public boolean isRemoteOrHybridOption() {
		return remoteOrHybridOption;
	}

	public void setRemoteOrHybridOption(boolean remoteOrHybridOption) {
		this.remoteOrHybridOption = remoteOrHybridOption;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public void addUser(User user) {
		if (users == null) {
			users = new ArrayList<>();
		}
		if (!users.contains(user)) {
			users.add(user);
			user.addJob(this);
		}
	}

	public void removeUser(User user) {
		if (users != null && users.contains(user)) {
			users.remove(user);
			user.removeJob(this);
		}
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public void addSkill(Skill skill) {
		if (skills == null) {
			skills = new ArrayList<>();
		}

		if (!skills.contains(skill)) {
			skills.add(skill);
			skill.addJob(this);
		}
	}

	public void removeSkill(Skill skill) {
		if (skills != null && skills.contains(skill)) {
			skills.remove(skill);
			skill.removeJob(this);
		}
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<JobMatch> getJobMatches() {
		return jobMatches;
	}

	public void setJobMatches(List<JobMatch> jobMatches) {
		this.jobMatches = jobMatches;
	}

	public void addJobMatch(JobMatch jobMatch) {
		if (jobMatches == null) {
			jobMatches = new ArrayList<>();
		}

		if (!jobMatches.contains(jobMatch)) {
			jobMatches.add(jobMatch);
		}

		if (jobMatch.getUser() != null) {
			jobMatch.getUser().removeJobMatch(jobMatch);

		}
		jobMatch.setJob(this);
	}

	public void removeJobMatch(JobMatch jobMatch) {
		if (jobMatches != null && jobMatches.contains(jobMatch)) {
			jobMatches.remove(jobMatch);
			jobMatch.setJob(null);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Job other = (Job) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Job [id=" + id + ", title=" + title + ", company=" + company + ", minSalary=" + minSalary
				+ ", maxSalary=" + maxSalary + ", education=" + education + ", minExperience=" + minExperience
				+ ", maxExperience=" + maxExperience + ", applicationStatus=" + applicationStatus + ", contactInfo="
				+ contactInfo + ", jobListing=" + jobListing + ", clearance=" + clearance + ", remoteOrHybridOption="
				+ remoteOrHybridOption + ", location=" + location + ", users=" + users.size() + ", skills="
				+ skills.size() + ", jobMatches=" + jobMatches.size() + "]";
	}

}
