package com.skilldistillery.jobtracker.services;

import java.util.List;

import com.skilldistillery.jobtracker.entities.Preferences;

public interface PreferencesService {
	List<Preferences> findAllPreferences();

	Preferences findById(int prefId);

	Preferences createPreferences(Preferences pref);

	Preferences updatePreferences(int prefId, Preferences pref);

	boolean deletePreferences(int prefId);
}
