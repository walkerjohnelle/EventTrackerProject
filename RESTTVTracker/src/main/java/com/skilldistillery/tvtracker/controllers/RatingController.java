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

import jakarta.servlet.http.HttpServletResponse;

@RequestMapping("api")
@RestController
public class RatingController {

	@Autowired
	private RatingService rS;

	@GetMapping(path = { "ratings", "ratings/" })
	public List<Rating> index() {
		return rS.findAllRatings();
	}

	@GetMapping("ratings/{ratingId}")
	public Rating show(@PathVariable("ratingId") int ratingId, HttpServletResponse rsp) {
		Rating rating = rS.findById(ratingId);

		if (rating == null) {
			rsp.setStatus(404);
		}
		return rating;
	}

	@GetMapping("users/{userId}/ratings")
	public List<Rating> getUserRatings(@PathVariable("userId") int userId, HttpServletResponse rsp) {
		List<Rating> userRatings = rS.getUserRatings(userId);
		if (userRatings == null || userRatings.isEmpty()) {
			rsp.setStatus(404);
		}
		return userRatings;
	}

	@PostMapping("ratings/shows/{showId}")
	public Rating createRating(@PathVariable("showId") int showId, @RequestBody Rating rating,
			HttpServletResponse rsp) {

		Rating createdRating = rS.createRating(showId, rating); // Corrected method call
		if (createdRating == null) {
			rsp.setStatus(404);
		}
		return createdRating;
	}

	@PutMapping("ratings/{ratingId}")
	public Rating updateRating(@PathVariable("ratingId") int ratingId, @RequestBody Rating rating,
			HttpServletResponse rsp) {
		try {
			rating = rS.updateRating(ratingId, rating);
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

	@DeleteMapping("ratings/{ratingId}")
	public void deleteRating(@PathVariable("ratingId") int ratingId, HttpServletResponse rsp) {
		Rating rating = rS.findById(ratingId);

		try {
			if (rating != null) {
				rS.deleteRating(ratingId);
				rsp.setStatus(204);
			} else {
				rsp.setStatus(404);
			}
		} catch (Exception e) {
			rsp.setStatus(400);
			e.printStackTrace();
		}
	}

}
