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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tv_show")
public class TvShow {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String genre;
	private String description;
	@Column(name = "release_year")
	private int releaseYear;
	private int seasons;
	@Column(name = "total_episodes")
	private int totalEpisodes;
	private boolean active;
	@Column(name = "streaming_platform")
	private String streamingPlatform;
	@Column(name = "image_url")
	private String imageUrl;

	@JsonIgnore
	@OneToMany(mappedBy = "tvShow")
	private List<Rating> ratings;

	public TvShow() {

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public int getSeasons() {
		return seasons;
	}

	public void setSeasons(int seasons) {
		this.seasons = seasons;
	}

	public int getTotalEpisodes() {
		return totalEpisodes;
	}

	public void setTotalEpisodes(int totalEpisodes) {
		this.totalEpisodes = totalEpisodes;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getStreamingPlatform() {
		return streamingPlatform;
	}

	public void setStreamingPlatform(String streamingPlatform) {
		this.streamingPlatform = streamingPlatform;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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
			if (rating.getTvShow() != null) {
				rating.getTvShow().removeRating(rating);
			}
			rating.setTvShow(this);
		}
	}

	public void removeRating(Rating rating) {
		if (ratings != null && ratings.contains(rating)) {
			ratings.remove(rating);
			rating.setTvShow(null);
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
		TvShow other = (TvShow) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "TvShow [id=" + id + ", title=" + title + ", genre=" + genre + ", description=" + description
				+ ", releaseYear=" + releaseYear + ", seasons=" + seasons + ", totalEpisodes=" + totalEpisodes
				+ ", active=" + active + ", streamingPlatform=" + streamingPlatform + ", imageUrl=" + imageUrl
				+ ", ratings=" + ratings + "]";
	}

}
