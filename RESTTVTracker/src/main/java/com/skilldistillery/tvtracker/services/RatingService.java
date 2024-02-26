package com.skilldistillery.tvtracker.services;

import java.util.List;

import com.skilldistillery.tvtracker.entities.Rating;

public interface RatingService {
	List<Rating> findAllRatings();

	Rating findById(int ratingId);

	Rating createRating(int showId, Rating rating);

	Rating updateRating(int ratingId, Rating rating);
	
	List<Rating> getUserRatings(int userId);

	boolean deleteRating(int ratingId);
}
