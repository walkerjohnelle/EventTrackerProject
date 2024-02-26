package com.skilldistillery.tvtracker.entities;

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
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String email;
	private String username;
	private String password;
	@Column(name = "profile_picture_url")
	private String profilePicture;
	private boolean active;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "user_has_tv_show", 
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "tv_show_id"))
	private List<TvShow> shows;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Rating> ratings;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<TvShow> getShows() {
		return shows;
	}

	public void setShows(List<TvShow> shows) {
		this.shows = shows;
	}

	public void addShow(TvShow show) {
		if (shows == null) {
			shows = new ArrayList<>();
		}

		if (!shows.contains(show)) {
			shows.add(show);
			show.addUser(this);
		}
	}

	public void removeShow(TvShow show) {
		if (shows != null && shows.contains(show)) {
			shows.remove(show);
			show.removeUser(this);
		}
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public void addRating(Rating rating) {
		if (ratings == null) {
			ratings = new ArrayList<>();
		}

		if (!ratings.contains(rating)) {
			ratings.add(rating);
		}

		if (rating.getUser() != null) {
			rating.getUser().removeRating(rating);

		}
		rating.setUser(this);
	}

	public void removeRating(Rating rating) {
		if (ratings != null && ratings.contains(rating)) {
			ratings.remove(rating);
			rating.setUser(null);
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
		return "User [id=" + id + ", email=" + email + ", username=" + username + ", password=" + password
				+ ", profilePicture=" + profilePicture + ", active=" + active + ", shows=" + shows.size() + "]";
	}

}
