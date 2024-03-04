package com.skilldistillery.tvtracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.tvtracker.entities.TvShow;

public interface TvShowRepository extends JpaRepository<TvShow, Integer> {
	TvShow searchById(int showId);

	List<TvShow> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String keyword1, String keyword2);

}