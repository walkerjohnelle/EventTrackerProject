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
import jakarta.persistence.OneToMany;

@Entity
public class Preferences {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "salary_importance")
	private int salaryImportance;
	@Column(name = "location_importance")
	private int locationImportance;
	@Column(name = "benefits_importance")
	private int benefitsImportance;
	@Column(name = "remote_work_importance")
	private int remoteWorkImportance;

	@JsonIgnore
	@OneToMany(mappedBy = "preferences")
	private List<JobMatch> jobMatches;

	public Preferences() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSalaryImportance() {
		return salaryImportance;
	}

	public void setSalaryImportance(int salaryImportance) {
		this.salaryImportance = salaryImportance;
	}

	public int getLocationImportance() {
		return locationImportance;
	}

	public void setLocationImportance(int locationImportance) {
		this.locationImportance = locationImportance;
	}

	public int getBenefitsImportance() {
		return benefitsImportance;
	}

	public void setBenefitsImportance(int benefitsImportance) {
		this.benefitsImportance = benefitsImportance;
	}

	public int getRemoteWorkImportance() {
		return remoteWorkImportance;
	}

	public void setRemoteWorkImportance(int remoteWorkImportance) {
		this.remoteWorkImportance = remoteWorkImportance;
	}

	public List<JobMatch> getJobMatches() {
		return jobMatches;
	}

	public void setJobMatches(List<JobMatch> jobMatches) {
		this.jobMatches = jobMatches;
	}

	public void addMatch(JobMatch jobMatch) {
		if (jobMatches == null) {
			jobMatches = new ArrayList<>();
		}

		if (!jobMatches.contains(jobMatch)) {
			jobMatches.add(jobMatch);
		}

		if (jobMatch.getPreferences() != null) {
			jobMatch.getPreferences().removeMatch(jobMatch);

		}
		jobMatch.setPreferences(this);
	}

	public void removeMatch(JobMatch jobMatch) {
		if (jobMatches != null && jobMatches.contains(jobMatch)) {
			jobMatches.remove(jobMatch);
			jobMatch.setPreferences(null);
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
		Preferences other = (Preferences) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Preferences [id=" + id + ", salaryImportance=" + salaryImportance + ", locationImportance="
				+ locationImportance + ", benefitsImportance=" + benefitsImportance + ", remoteWorkImportance="
				+ remoteWorkImportance + ", jobMatches=" + jobMatches.size() + "]";
	}


}
