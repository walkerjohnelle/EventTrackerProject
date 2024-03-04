package com.skilldistillery.tvtracker.entities;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int rating;
	private String review;

	@ManyToOne
	@JoinColumn(name = "tv_show_id")
	private TvShow tvShow;

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

	public TvShow getTvShow() {
		return tvShow;
	}

	public void setTvShow(TvShow tvShow) {
		this.tvShow = tvShow;
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
		return "Rating [id=" + id + ", rating=" + rating + ", review=" + review + ", tvShow=" + tvShow + "]";
	}

}
