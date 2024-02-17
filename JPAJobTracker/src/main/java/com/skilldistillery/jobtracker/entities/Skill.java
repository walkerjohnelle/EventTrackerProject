package com.skilldistillery.jobtracker.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Skill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;

	@ManyToMany(mappedBy = "skills")
	private List<User> users;

	@ManyToMany(mappedBy = "skills")
	private List<Job> jobs;

	public Skill() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
			user.addSkill(this);
		}
	}

	public void removeUser(User user) {
		if (users != null && users.contains(user)) {
			users.remove(user);
			user.removeSkill(this);
		}
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
			job.addSkill(this);
		}
	}

	public void removeJob(Job job) {
		if (jobs != null && jobs.contains(job)) {
			jobs.remove(job);
			job.removeSkill(this);
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
		Skill other = (Skill) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Skill [id=" + id + ", name=" + name + ", users=" + users + ", jobs=" + jobs.size() + "]";
	}

}
