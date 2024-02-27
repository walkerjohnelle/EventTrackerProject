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
		return ratingRepo.findById(ratingId).orElse(null);
	}

	@Override
	public Rating createRating(Rating rating) {

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
	public boolean deleteRating(int ratingId, int showId) {
		TvShow show = tvRepo.findById(showId).orElse(null);
		if (show == null) {
			return false;
		}

		Rating rating = ratingRepo.findById(ratingId).orElse(null);
		if (rating == null || rating.getTvShow() == null || rating.getTvShow().getId() != showId) {
			return false;
		}

		ratingRepo.deleteById(ratingId);
		return true;
	}

	@Override
	public List<Rating> getUserRatings(int userId) {
		return userRepo.findById(userId).map(user -> user.getRatings()).orElse(null);
	}

	@Override
	public List<Rating> getRatingsByShow(int showId) {
		Optional<TvShow> showOptional = tvRepo.findById(showId);
		if (showOptional.isPresent()) {
			TvShow show = showOptional.get();
			return show.getRatings();
		} else {
			return null;
		}
	}

}
