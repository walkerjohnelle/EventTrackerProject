package com.skilldistillery.jobtracker.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

	@OneToMany(mappedBy = "preferences")
	private List<Match> matches;

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

		if (match.getPreferences() != null) {
			match.getPreferences().removeMatch(match);

		}
		match.setPreferences(this);
	}

	public void removeMatch(Match match) {
		if (matches != null && matches.contains(match)) {
			matches.remove(match);
			match.setPreferences(null);
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
				+ remoteWorkImportance + ", matches=" + matches + "]";
	}

}
