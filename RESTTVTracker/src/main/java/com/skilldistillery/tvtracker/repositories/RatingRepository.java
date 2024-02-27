package com.skilldistillery.tvtracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.tvtracker.entities.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
	Rating searchById(int ratingId);
	

}
