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
import jakarta.persistence.OneToOne;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String email;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	private String password;
	private boolean employed;
	private String role;
	private String clearance;
	private String education;

	@OneToOne
	@JoinColumn(name = "preferences_id")
	private Preferences preferences;
	@OneToMany(mappedBy = "user")
	private List<Match> matches;
	@OneToOne
	@JoinColumn(name = "location_id")
	private Location location;
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "user_has_job", 
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "job_id"))
	private List<Job> jobs;
	
	@ManyToMany
	@JoinTable(name = "user_has_skills", 
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "skill_id"))
	private List<Skill> skills;

	public User() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEmployed() {
		return employed;
	}

	public void setEmployed(boolean employed) {
		this.employed = employed;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getClearance() {
		return clearance;
	}

	public void setClearance(String clearance) {
		this.clearance = clearance;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public Preferences getPreferences() {
		return preferences;
	}

	public void setPreferences(Preferences preferences) {
		this.preferences = preferences;
	}

	public List<Match> getMatches() {
		return matches;
	}

	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}

	public void addMatch(Match match) {
		if (matches == null) {
			matches = new ArrayList<>();
		}

		if (!matches.contains(match)) {
			matches.add(match);
		}

		if (match.getUser() != null) {
			match.getUser().removeMatch(match);

		}
		match.setUser(this);
	}

	public void removeMatch(Match match) {
		if (matches != null && matches.contains(match)) {
			matches.remove(match);
			match.setUser(null);
		}
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public void addJob(Job job) {
		if (jobs == null) {
			jobs = new ArrayList<>();
		}

		if (!jobs.contains(job)) {
			jobs.add(job);
			job.addUser(this);
		}
	}

	public void removeJob(Job job) {
		if (jobs != null && jobs.contains(job)) {
			jobs.remove(job);
			job.removeUser(this);
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
			skill.addUser(this);
		}
	}

	public void removeSkill(Skill skill) {
		if (skills != null && skills.contains(skill)) {
			skills.remove(skill);
			skill.removeUser(this);
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
		User other = (User) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", password=" + password + ", employed=" + employed + ", role=" + role + ", clearance=" + clearance
				+ ", education=" + education + ", preferences=" + preferences + ", matches=" + matches.size()
				+ ", location=" + location + "]";
	}

}
