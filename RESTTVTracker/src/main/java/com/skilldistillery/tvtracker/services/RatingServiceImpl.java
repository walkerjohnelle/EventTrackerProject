package com.skilldistillery.tvtracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.tvtracker.entities.Rating;
import com.skilldistillery.tvtracker.entities.TvShow;
import com.skilldistillery.tvtracker.repositories.RatingRepository;
import com.skilldistillery.tvtracker.repositories.TvShowRepository;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository ratingRepo;

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
	    Optional<Rating> ratingOpt = ratingRepo.findById(ratingId);
	    if (ratingOpt.isPresent()) {
	        ratingRepo.deleteById(ratingId);
	        return true;
	    }
	    return false;
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

	@Override
	public Rating createRating(Rating rating) {
		if (rating.getTvShow() == null || rating.getTvShow().getId() == 0) {
			throw new RuntimeException("Rating must be associated with a TvShow");
		}
		// Fetch the TvShow based on the id and set it to the rating
		TvShow tvShow = tvRepo.findById(rating.getTvShow().getId())
				.orElseThrow(() -> new RuntimeException("TvShow with id " + rating.getTvShow().getId() + " not found"));
		rating.setTvShow(tvShow);

		return ratingRepo.save(rating);
	}

}
