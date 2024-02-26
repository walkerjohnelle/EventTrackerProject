package com.skilldistillery.tvtracker.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int rating;
	private String review;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "tv_show_has_rating", 
	joinColumns = @JoinColumn(name = "rating_id"), 
	inverseJoinColumns = @JoinColumn(name = "tv_show_id"))
	private List<TvShow> shows;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public List<TvShow> getShows() {
		return shows;
	}

	public void setShows(List<TvShow> shows) {
		this.shows = shows;
	}

	public void addShow(TvShow show) {
		if(shows == null) {
			shows = new ArrayList<>();
		}
		
		if(!shows.contains(show)) {
			shows.add(show);
			show.addRating(this);
		}
	}
	
	public void removeShow(TvShow show) {
		if(shows != null && shows.contains(show)) {
			shows.remove(show);
			show.removeRating(this);
		}
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
		Rating other = (Rating) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Rating [id=" + id + ", rating=" + rating + ", review=" + review + ", shows=" + shows.size() + "]";
	}

}
