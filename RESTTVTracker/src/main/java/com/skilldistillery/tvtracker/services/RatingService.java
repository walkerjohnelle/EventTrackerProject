package com.skilldistillery.tvtracker.services;

import java.util.List;

import com.skilldistillery.tvtracker.entities.Rating;

public interface RatingService {
	List<Rating> findAllRatings();

	Rating findById(int ratingId);

	Rating createRating(Rating rating);

	Rating updateRating(int ratingId, Rating rating);

	boolean deleteRating(int ratingId, int showId);

	List<Rating> getRatingsByShow(int showId);
}
