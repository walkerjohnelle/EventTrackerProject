package com.skilldistillery.tvtracker.services;

import java.util.List;

import com.skilldistillery.tvtracker.entities.TvShow;

public interface TvShowService {
	List<TvShow> findAllShows();

	TvShow findById(int showId);

	TvShow createShow(TvShow show);

	TvShow updateShow(int showId, TvShow show);

	boolean deleteShow(int showId);
	
	List<TvShow> searchByKeyword(String keyword);

}
