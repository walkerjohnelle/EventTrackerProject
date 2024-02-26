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

import com.skilldistillery.tvtracker.entities.TvShow;
import com.skilldistillery.tvtracker.services.TvShowService;

import jakarta.servlet.http.HttpServletResponse;

@RequestMapping("api")
@RestController
public class TvShowController {

	@Autowired
	private TvShowService tvService;

	@GetMapping(path = { "shows", "shows/" })
	public List<TvShow> index() {
		return tvService.findAllShows();
	}

	@GetMapping("shows/{showId}")
	public TvShow show(@PathVariable("showId") int showId, HttpServletResponse rsp) {
		TvShow show = tvService.findById(showId);

		if (show == null) {
			rsp.setStatus(404);
		}
		return show;
	}

	@PostMapping("shows")
	public TvShow createJob(@RequestBody TvShow show, HttpServletResponse rsp) {
		TvShow createdShow = tvService.createShow(show);
		if (createdShow == null) {
			rsp.setStatus(404);
		} else {
			rsp.setStatus(201);
		}
		return createdShow;
	}

	@PutMapping("shows/{showId}")
	public TvShow updateShow(@PathVariable("showId") int showId, @RequestBody TvShow show, HttpServletResponse rsp) {
		try {
			show = tvService.updateShow(showId, show);
			if (show == null) {
				rsp.setStatus(404);
			}
		} catch (Exception e) {
			rsp.setStatus(400);
			show = null;
			e.printStackTrace();
		}
		return show;
	}

	@DeleteMapping("shows/{showId}")
	public void deleteShow(@PathVariable("showId") int showId, HttpServletResponse rsp) {
		TvShow show = tvService.findById(showId);

		try {
			if (show != null) {
				tvService.deleteShow(showId);
				rsp.setStatus(204);
			} else {
				rsp.setStatus(404);
			}
		} catch (Exception e) {
			rsp.setStatus(400);
			e.printStackTrace();
		}
	}

	@GetMapping("shows/search/{keyword}")
	public List<TvShow> searchByKeyword(@PathVariable("keyword") String keyword, HttpServletResponse rsp) {
		List<TvShow> films = tvService.searchByKeyword(keyword);
		if (films == null) {
			rsp.setStatus(404);
		}

		return films;
	}

}
