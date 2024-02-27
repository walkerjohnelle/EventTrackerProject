package com.skilldistillery.tvtracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.tvtracker.entities.Rating;
import com.skilldistillery.tvtracker.services.RatingService;
import com.skilldistillery.tvtracker.services.TvShowService;

import jakarta.servlet.http.HttpServletResponse;

@RequestMapping("api")
@RestController
public class RatingController {

	@Autowired
	private RatingService ratingService;

	@Autowired
	private TvShowService tvS;

	@GetMapping(path = { "ratings", "ratings/" })
	public List<Rating> index() {
		return ratingService.findAllRatings();
	}

	@GetMapping("ratings/{ratingId}")
	public Rating show(@PathVariable("ratingId") int ratingId, HttpServletResponse rsp) {
		Rating rating = ratingService.findById(ratingId);

		if (rating == null) {
			rsp.setStatus(404);
		}
		return rating;
	}

	@GetMapping("users/{userId}/ratings")
	public List<Rating> getUserRatings(@PathVariable("userId") int userId, HttpServletResponse rsp) {
		List<Rating> userRatings = ratingService.getUserRatings(userId);
		if (userRatings == null || userRatings.isEmpty()) {
			rsp.setStatus(404);
		}
		return userRatings;
	}

	@GetMapping("shows/{showId}/ratings")
	public List<Rating> getRatingsByShow(@PathVariable("showId") int showId, HttpServletResponse rsp) {
		if (!tvS.existsById(showId)) {
			return null;
		}

		return ratingService.getRatingsByShow(showId);
	}

	@PostMapping("ratings")
	public Rating createRating(@RequestBody Rating rating, HttpServletResponse rsp) {
		Rating createdRating = ratingService.createRating(rating);

		if (createdRating != null) {
			rsp.setStatus(201);
		} else {
			rsp.setStatus(404);
		}
		return createdRating;
	}

	@PutMapping("ratings/{ratingId}")
	public Rating updateRating(@PathVariable("ratingId") int ratingId, @RequestBody Rating rating,
			HttpServletResponse rsp) {
		try {
			rating = ratingService.updateRating(ratingId, rating);
			if (rating == null) {
				rsp.setStatus(404);
			}
		} catch (Exception e) {
			rsp.setStatus(400);
			rating = null;
			e.printStackTrace();
		}
		return rating;
	}

	@DeleteMapping("{showId}/ratings/{ratingId}")
	public void deleteRating(@PathVariable("showId") int showId, @PathVariable("ratingId") int ratingId,
			HttpServletResponse rsp) {
		if (ratingService.deleteRating(ratingId, showId)) {
			rsp.setStatus(204);
		} else {
			rsp.setStatus(404);
		}

	}
}
