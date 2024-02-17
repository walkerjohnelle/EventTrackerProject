package com.skilldistillery.jobtracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.jobtracker.entities.Preferences;
import com.skilldistillery.jobtracker.repositories.PreferencesRepository;

@Service
public class PreferencesServiceImpl implements PreferencesService {

	@Autowired
	private PreferencesRepository prefRepo;

	@Override
	public List<Preferences> findAllPreferences() {
		return prefRepo.findAll();
	}

	@Override
	public Preferences findById(int prefId) {
		return prefRepo.searchById(prefId);
	}

	@Override
	public Preferences createPreferences(Preferences pref) {
		return prefRepo.saveAndFlush(pref);
	}

	@Override
	public Preferences updatePreferences(int prefId, Preferences pref) {
		Optional<Preferences> optPref = prefRepo.findById(prefId);
		if (optPref.isPresent()) {
			pref.setId(prefId);
			return prefRepo.saveAndFlush(pref);
		}
		return null;
	}

	@Override
	public boolean deletePreferences(int prefId) {
		Optional<Preferences> optPref = prefRepo.findById(prefId);
		if (optPref.isPresent()) {
			prefRepo.delete(optPref.get());
			return true;
		}
		return false;
	}
}
