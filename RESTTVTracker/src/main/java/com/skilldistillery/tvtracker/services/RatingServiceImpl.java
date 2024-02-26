package com.skilldistillery.tvtracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.tvtracker.entities.Rating;
import com.skilldistillery.tvtracker.entities.TvShow;
import com.skilldistillery.tvtracker.repositories.RatingRepository;
import com.skilldistillery.tvtracker.repositories.TvShowRepository;
import com.skilldistillery.tvtracker.repositories.UserRepository;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository ratingRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private TvShowRepository tvRepo;

	@Override
	public List<Rating> findAllRatings() {
		return ratingRepo.findAll();
	}

	@Override
	public Rating findById(int ratingId) {
		return ratingRepo.searchById(ratingId);
	}

	@Override
	public Rating createRating(int showId, Rating rating) {
		TvShow show = tvRepo.searchById(showId);

		rating.addShow(show);

		return ratingRepo.saveAndFlush(rating);
	}

	@Override
	public Rating updateRating(int ratingId, Rating rating) {
		Optional<Rating> optRating = ratingRepo.findById(ratingId);
		if (optRating.isPresent()) {
			rating.setId(ratingId);
			return ratingRepo.saveAndFlush(rating);
		}
		return null;
	}

	@Override
	public boolean deleteRating(int ratingId) {
		Optional<Rating> optRating = ratingRepo.findById(ratingId);
		if (optRating.isPresent()) {
			ratingRepo.delete(optRating.get());
			return true;
		}
		return false;
	}

	@Override
	public List<Rating> getUserRatings(int userId) {
		List<Rating> userRatings = userRepo.searchById(userId).getRatings();
		return userRatings;
	}

}
