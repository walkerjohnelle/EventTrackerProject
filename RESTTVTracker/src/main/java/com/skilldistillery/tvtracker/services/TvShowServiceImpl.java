package com.skilldistillery.tvtracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skilldistillery.tvtracker.entities.TvShow;
import com.skilldistillery.tvtracker.repositories.TvShowRepository;

@Service
@Transactional
public class TvShowServiceImpl implements TvShowService {

	@Autowired
	private TvShowRepository tvRepo;

	@Override
	public TvShow findById(int showId) {
		Optional<TvShow> optShow = tvRepo.findById(showId);
		return optShow.orElse(null);
	}

	@Override
	public List<TvShow> findAllShows() {
		return tvRepo.findAll();
	}

	@Override
	public TvShow createShow(TvShow tvShow) {
		return tvRepo.saveAndFlush(tvShow);
	}

	@Override
	public TvShow updateShow(int showId, TvShow show) {
		Optional<TvShow> optShow = tvRepo.findById(showId);
		if (optShow.isPresent()) {
			show.setId(showId);
			return tvRepo.saveAndFlush(show);
		}
		return null;

	}

	@Override
	public boolean deleteShow(int showId) {
		Optional<TvShow> optShow = tvRepo.findById(showId);
		if (optShow.isPresent()) {
			TvShow show = optShow.get();

			show.setRatings(null);

			tvRepo.delete(show);

			return true;
		}
		return false;
	}

	@Override
	public List<TvShow> searchByKeyword(String keyword) {
		List<TvShow> shows = tvRepo.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
		return shows;
	}

	@Override
	public boolean existsById(int showId) {
		return tvRepo.existsById(showId);
	}
}
